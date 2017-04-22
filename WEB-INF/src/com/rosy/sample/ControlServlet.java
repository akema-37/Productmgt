package com.rosy.sample;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *  認証サーブレット
 *  @version 1.1
 *  @author akema
 */
public class ControlServlet extends HttpServlet {

	/** doGet()メソッド
	 * doGetメソッドはログイン画面へ、リダイレクトします。
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		rd = getServletConfig().getServletContext().getRequestDispatcher("/login.jsp");
		rd.forward(request, response);
	}

	/** doPost()メソッド
	 * ログイン画面からPostされたユーザー名とパスワードを用いて認証をします。
	 *  認証ができた場合：セッションにユーザー情報を格納したUserBeanを保存し、list.jspにリダイレクト
	 *  認証に失敗した場合：autherror.jspにリダイレクトします。
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UserBean ub =  new UserBean();

		String formun = request.getParameter("j_username");
		String formps = request.getParameter("j_password");
		ub.setaName(formun);
		ub.setPassword(formps);

		UserAuthBean uab =  new UserAuthBean();

		HttpSession session = null;
		RequestDispatcher rd = null;
		// 認証に成功
		if (uab.execute(ub) == true) {
			// セッションオブジェクトを作成
			if (checkSession(request) == true) {
				// セッションオブジェクトがある場合は獲得
				session = request.getSession(false);
			} else {
				// セッションオブジェクトがない場合は新規作成
				session = request.getSession(true);
			}
			// Beansをセッションオブジェクトに保存
			session.setAttribute("userbeans", ub);
			rd = getServletConfig().getServletContext().getRequestDispatcher(
					"/search.jsp");
		} else {
			// 認証に失敗
			rd = getServletConfig().getServletContext().getRequestDispatcher(
					"/autherror.jsp");
		}
		rd.forward(request, response);
	}

	// セッションのオブジェクトのチェックメソッド
	public boolean checkSession(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if (session != null) {
			return true;
		} else {
			return false;
		}
	}

}
