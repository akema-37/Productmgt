package com.rosy.sample;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  登録サーブレット
 *  @version 1.1
 *  @author akema
 */
public class InsertServlet extends HttpServlet {

	/**
	 *  クライアントからデータの要求がある場合に呼び出されるdoGet()メソッド
	 *  商品情報の登録処理を行います。
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		InsertLogic insertLogic = new InsertLogic();

		// エンコード方式の指定
		response.setContentType("text/html; charset=Windows-31J");
		request.setCharacterEncoding("Windows-31J");

			// 詳細画面から送信されたデータを受け取る
			String productid = request.getParameter("productid");
			String productname = request.getParameter("productname");
			String productnamekana = request.getParameter("productnamekana");
			String productkbna = request.getParameter("productkbnA");
			String productkbnb = request.getParameter("productkbnB");
			String productkbnc = request.getParameter("productkbnC");
			String costprice = request.getParameter("costprice");
			String sellingprice = request.getParameter("sellingprice");
			String supplier = request.getParameter("supplier");
			String maker = request.getParameter("maker");

			insertLogic.setProductId(productid);
			insertLogic.setProductName(productname);
			insertLogic.setProductnameKana(productnamekana);
			insertLogic.setProductKbnA(productkbna);
			insertLogic.setProductKbnB(productkbnb);
			insertLogic.setProductKbnC(productkbnc);
			insertLogic.setCostPrice(costprice);
			insertLogic.setSellingPrice(sellingprice);
			insertLogic.setSupplier(supplier);
			insertLogic.setMaker(maker);

		try {
			insertLogic.insert();
			String resultMessage = insertLogic.getResultMessage();

			// エラーがない場合は、メッセージを表示し自画面に遷移する
			request.setAttribute("resultMessage",resultMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		getServletConfig().getServletContext().getRequestDispatcher("/insert.jsp").forward(request, response);
		}

	/**
	 * doGet()呼び出しメソッド
	 * PostでもGetと同じ処理をさせるため、doGet()を呼び出して実行する。
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
