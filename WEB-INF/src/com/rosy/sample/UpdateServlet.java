package com.rosy.sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  更新サーブレット
 *  @version 1.1
 *  @author akema
 */

public class UpdateServlet extends HttpServlet {

	/**
	 *  クライアントからデータの要求がある場合に呼び出されるdoGet()メソッド
	 *  詳細画面から受け取った情報を更新します。
	 *  更新後は、また自画面に遷移します。
	 */

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UpdateLogic updateLogic = new UpdateLogic();

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

			updateLogic.setProductId(productid);
			updateLogic.setProductName(productname);
			updateLogic.setProductnameKana(productnamekana);
			updateLogic.setProductKbnA(productkbna);
			updateLogic.setProductKbnB(productkbnb);
			updateLogic.setProductKbnC(productkbnc);
			updateLogic.setCostPrice(costprice);
			updateLogic.setSellingPrice(sellingprice);
			updateLogic.setSupplier(supplier);
			updateLogic.setMaker(maker);

		try {
			updateLogic.update();
			String resultMessage = updateLogic.getResultMessage();

			// ③更新したデータを再表示
			DetailLogic detailLogic = new DetailLogic();

			List<Map<String, String>> results = new ArrayList<>();

			detailLogic.setProductId(productid);
			results = detailLogic.detailSearch();

			request.setAttribute("results", results);

			// エラーがない場合は、メッセージを表示し
			// 自画面に遷移する
			request.setAttribute("resultMessage",resultMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		getServletConfig().getServletContext().getRequestDispatcher("/update.jsp").forward(request, response);
		}

	/**
	 * doGet()呼び出しメソッド
	 * PostでもGetと同じ処理をさせるため、doGet()を呼び出して実行する。
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
