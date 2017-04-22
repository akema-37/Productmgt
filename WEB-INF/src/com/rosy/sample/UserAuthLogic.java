package com.rosy.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserAuthLogic {
	public UserAuthLogic() {

	}


	/**
	 * ユーザー名(漢字)
	 */
	private String nameKanji;

	/**
	 * ユーザー名(漢字)を取得します。
	 *
	 * @return ユーザー名(漢字)
	 */
	public String getNameKanji() {
		return nameKanji;
	}

	/**
	 * ユーザー名(漢字)を設定します。
	 *
	 * @param nameKanji
	 *            ユーザー名(漢字)
	 */
	public void setNameKanji(String nameKanji) {
		this.nameKanji = nameKanji;
	}

	/**
	 *  ログイン情報認証処理
	 *  @return 認証に成功：true
	 *  @return 認証に失敗：false
	 *  @throws Exception
	 */
	public boolean execute(UserBean ub) throws Exception {
		String id = ub.getId();
		String pass = ub.getPassword();

		// ユーザIDとパスワードを引数にし、ログイン情報取得処理を呼び出す
		nameKanji = listSearch(id,pass);

		// ユーザ名が取得できたらtrueそうでない場合はfalse
		if (nameKanji != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 *  ログイン情報取得処理
	 *  SQL文を作成し、ログイン情報を取得します。
	 *  @return ユーザ名
	 *  @throws Exception
	 */
	public String listSearch(String id, String pass) throws Exception {

		String result = null;


		// DB接続用コネクション
		Connection db = DriverManager.getConnection(
                "jdbc:mysql://localhost/productmgt","root", "root");

		// パラメータ付き SQL 文をデータベースに送るためのプリペアーステートメント
		PreparedStatement ps = null;

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM userinfo WHERE ");
		sb.append("id = '").append(id).append("' ");
		sb.append("AND password = '").append(pass).append("'");

		// 作成したSQL文を変数に入れる
		String query = sb.toString();

//		// データソースの取得
//		Context context = new InitialContext();
//
//		// データベース名を設定
//		DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/Productmgt");
//
//		// DBに接続
//		db = ds.getConnection();

		// 作成した、SQL文をプリペアーステートメントにセット
		ps = db.prepareStatement(query);

		// 問い合わせ実行
		ResultSet rs = ps.executeQuery();

		// 検索結果の取得(取得するデータがない場合はfalseが返り、繰り返しを抜ける)
		while(rs.next()){
			result = rs.getString("namekanji");
		}

		// Close処理
		rs.close();
		ps.close();
		db.close();

		return result;

	}





}
