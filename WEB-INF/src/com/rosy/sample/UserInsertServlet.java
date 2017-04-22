package com.rosy.sample;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  ユーザ登録サーブレット
 *  @version 1.1
 *  @author akema
 */
public class UserInsertServlet extends HttpServlet {

	/**
	 *  クライアントからデータの要求がある場合に呼び出されるdoGet()メソッド
	 *  ユーザ情報の登録処理を行います。
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserInsertLogic userInsertLogic = new UserInsertLogic();

		// エンコード方式の指定
		response.setContentType("text/html; charset=Windows-31J");
		request.setCharacterEncoding("Windows-31J");

			// 詳細画面から送信されたデータを受け取る
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String emailaddress = request.getParameter("emailaddress");
			String namekanji = request.getParameter("namekanji");
			String namekana = request.getParameter("namekana");
			String hiredyyyymm = request.getParameter("hiredyyyymm");
			String position = request.getParameter("position");

			userInsertLogic.setId(id);
			userInsertLogic.setPassWord(password);
			userInsertLogic.setEmailAddress(emailaddress);
			userInsertLogic.setNameKanji(namekanji);
			userInsertLogic.setNameKana(namekana);
			userInsertLogic.setHiredYyyyMm(hiredyyyymm);
			userInsertLogic.setPosition(position);

		try {
			// ユーザ登録処理の呼び出し
			userInsertLogic.userInsert();
			String resultMessage = userInsertLogic.getResultMessage();

			// エラーがない場合は、メッセージを表示し自画面に遷移する
			request.setAttribute("resultMessage",resultMessage);

		} catch (Exception e) {
			// 不正アクセスなんちゃらとコンソールに出力されるのでコメントアウト：原因調査中
//			e.printStackTrace();
		}

		getServletConfig().getServletContext().getRequestDispatcher("/user_registration.jsp").forward(request, response);
		}

	/**
	 * doGet()呼び出しメソッド
	 * PostでもGetと同じ処理をさせるため、doGet()を呼び出して実行する。
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
