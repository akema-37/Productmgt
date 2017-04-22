package com.rosy.sample;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  検索サーブレット
 *  @version 1.1
 *  @author akema
 */
public class SearchServlet extends HttpServlet {

	/** クライアントからデータの要求がある場合に呼び出されるdoGet()メソッド
	 *  検索画面から受け取った情報を元に検索処理を行い、
	 *  一覧画面に遷移します。
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 検索ロジッククラスのインスタンスを生成
		SearchLogic searchLogic = new SearchLogic();

		// エンコード方式の指定
		response.setContentType("text/html; charset=Windows-31J");
		request.setCharacterEncoding("Windows-31J");

		// 一覧情報取得結果格納用リスト作成
		List<Map<String, String>> listResult = null;
		try {

			// 検索画面で入力されたデータを受け取る
			String productKbnA = request.getParameter("productKbnA");
			String productKbnB = request.getParameter("productKbnB");
			String productKbnC = request.getParameter("productKbnC");
			String productName = request.getParameter("productName");

			// 画面から取得した値を、検索ロジックにセットする。
			searchLogic.setProductKbnA(productKbnA);
			searchLogic.setProductKbnB(productKbnB);
			searchLogic.setProductKbnC(productKbnC);
			searchLogic.setProductName(productName);

			// 検索ロジッククラスの一覧検索メソッドを呼び出す。
			listResult = searchLogic.listSearch();

			// 取得したデータをlist.jspで表示するためリクエストオブジェクトに設定する。
			request.setAttribute("results", listResult);

		} catch (Exception e) {
			// エラーの場合は以下のメッセージを表示する
			request.setAttribute("errMsg", "想定外のエラーが発生しました。");
		}

		// エラーがない場合は、
		//一覧画面に遷移する
		getServletConfig().getServletContext().getRequestDispatcher("/list.jsp").forward(request, response);
	}

	/** クライアントからデータが送られてくる場合に呼び出されるdoPost()メソッド
	 *  postでもGetと同じ処理をさせるため、doGet()を呼び出して実行する。
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
