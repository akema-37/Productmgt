package com.rosy.sample;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  詳細サーブレット
 *  @version 1.1
 *  @author akema
 */
public class DetailServlet extends HttpServlet {

	/**
	 *   一覧画面からラジオボタンで選択された商品IDに紐づく
	 *   詳細情報を取得し更新画面に遷移します。
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 詳細ロジッククラスのインスタンスを生成します。
		DetailLogic detailLogic = new DetailLogic();

		// 詳細情報取得結果格納用リスト作成
		List<Map<String, String>> listResult = null;

		try {

			// 一覧画面からラジオボタンで選択されたデータを受け取る
			String productId = request.getParameter("select");

			// 取得した文字がなぜか化けるので文字コード変更
			productId = new String(productId.getBytes("8859_1"), "Windows-31J");

			// ラジオボタンから取得した商品IDを、詳細ロジックにセットする。
			detailLogic.setProductId(productId);

			// 詳細ロジッククラスの詳細情報検索メソッドを呼び出す。
			listResult = detailLogic.detailSearch();
			String resultMessage = detailLogic.getResultMessage();

			// 取得したデータをupdate.jspで表示するためリクエストオブジェクトに設定する。
			request.setAttribute("results", listResult);
			request.setAttribute("resultMessage", resultMessage);

		} catch (Exception e) {
			// エラーの場合は以下のメッセージを表示する
			request.setAttribute("errMsg", "想定外のエラーが発生しました。");
		}

		// エラーがない場合は、
		// 更新画面に遷移する
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
