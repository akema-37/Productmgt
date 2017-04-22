package com.rosy.sample;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  ユーザ情報登録処理クラス
 *  DBに接続し、ユーザ情報の登録処理を行います。
 *  @version 1.1
 *  @author akema
 */
public class UserInsertLogic implements Serializable {

	// コンストラクタ
	public UserInsertLogic() {
	}

	/**
	 * ユーザID
	 */
	private String id;

	/**
	 * パスワード
	 */
	private String passWord;

	/**
	 * メールアドレス
	 */
	private String emailAddress;

	/**
	 * 顧客名(漢字)
	 */
	private String nameKanji;

	/**
	 * 顧客名(カナ)
	 */
	private String  nameKana;

	/**
	 * 入社年月
	 */
	private String  hiredYyyyMm;
	/**
	 * 役職
	 */
	private String position;

	/**
	 * 結果メッセージ
	 */
	private String resultMessage;

	/**
	 * ユーザIDを取得します。
	 * @return ユーザID
	 */
	public String getId() {
		return id;
	}

	/**
	 *ユーザIDを設定します。
	 * @param id ユーザID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * パスワードを取得します。
	 * @return パスワード
	 */
	public String getPassWord() {
		return passWord;
	}

	/**
	 * パスワードを設定します。
	 * @param passWord パスワード
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}


	/**
	 * メールアドレスを取得します。
	 * @return メールアドレス
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * メールアドレスを設定します。
	 * @param emailAddress メールアドレス
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * 顧客名(漢字)を取得します。
	 * @return 顧客名(漢字)
	 */
	public String getNameKanji() {
		return nameKanji;
	}

	/**
	 * 顧客名(漢字)を設定します。
	 * @param nameKanji 顧客名(漢字)
	 */
	public void setNameKanji(String nameKanji) {
		this.nameKanji = nameKanji;
	}

	/**
	 * 顧客名(カナ)を取得します。
	 * @return 顧客名(カナ)
	 */
	public String getNameKana() {
		return nameKana;
	}

	/**
	 * 顧客名(カナ)を設定します。
	 * @param nameKana 顧客名(カナ)
	 */
	public void setNameKana(String nameKana) {
		this.nameKana = nameKana;
	}

	/**
	 * 入社年月を取得します。
	 * @return 入社年月
	 */
	public String getHiredYyyyMm() {
		return hiredYyyyMm;
	}

	/**
	 * 入社年月を設定します。
	 * @param hiredYyyyMm 入社年月
	 */
	public void setHiredYyyyMm(String hiredYyyyMm) {
		this.hiredYyyyMm = hiredYyyyMm;
	}

	/**
	 * 役職を取得します。
	 * @return 役職
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * 役職を設定します。
	 * @param position 役職
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * 結果メッセージを取得します。
	 * @return 結果メッセージ
	 */
	public String getResultMessage() {
		return resultMessage;
	}

	/**
	 * 結果メッセージを設定します。
	 * @param resultMessage 結果メッセージ
	 */
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	/**
	 * ユーザ情報登録処理
	 * SQL文を作成し、ユーザ情報をします。
	 * @throws Exception
	 */
	public void userInsert() throws Exception {
		// DB接続用コネクション
		Connection db =DriverManager.getConnection(
                "jdbc:mysql://localhost/productmgt","root", "root");

		// 文字チェックフラグ
		boolean charCheckFlag = charCheck();

		// ユーザIDの存在チェック
		boolean idCheckFlag = idCheck();

		try {
			if (charCheckFlag) {
				// charCheckFlagがtrueだった場合はuser_registration.jspに遷移する。
				// メッセージはcharCheck()メソッドで設定されているので何もしない
			} else if (idCheckFlag) {
				// idCheckFlagがtrueだった場合は、メッセージを設定しuser_registration.jspに遷移する。
				resultMessage = "ID：" + id + "は、既に登録済みです。";
			} else {
				// charCheckFlagとidCheckFlagがfalseだった場合は、登録処理を行う

				// createQuery()メソッドの呼び出して登録用のSQL文の作成する
				StringBuilder sb = createQuery();

//				// データソースの取得
//				Context context = new InitialContext();
//				DataSource ds = (DataSource) context
//						.lookup("java:comp/env/jdbc/Productmgt");
//
//				// DBに接続します。
//				db = ds.getConnection();

				// パラメータ付き SQL 文をデータベースに送るための PreparedStatement オブジェクトを作成します。
				String query = sb.toString();
				PreparedStatement ps = db.prepareStatement(query);

				// 登録実行
				ps.executeUpdate();

				// メッセージを設定
				resultMessage = "ユーザID：" + id + "の登録が完了しました。";

				ps.close();
				db.close();
			}
		} catch (SQLException ie) {
			// エラーメッセージ設定
			resultMessage = "登録に失敗しました。ID重複または、必須項目欠落、文字数不正があります。";

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (db != null) {
					db.close();
				}
			} catch (SQLException e) {
				System.out.println("SQLException:" + e.getMessage());
			}
		}
	}

	/**
	 * 文字チェック処理
	 * @return 文字が未入力や不正だった場合：true
	 * @return 文字が正常だった場合：false
	 * @throws Exception
	 */
	private boolean charCheck() throws Exception{

		// 文字数チェックフラグの定義
		boolean charCheckFlag = false;

		// 数字の入力チェックの正規表現
		final String MATCH_NUMBER = "^[0-9]+$";

		// 半角英数字の入力チェックの正規表現
		final String MATCH_CHAR ="^[0-9a-zA-Z]+$";

		// メールアドレスの入力チェックの正規表現
		final String MATCH_MAIL = "^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]"
				+ "+(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*"
				+ "+(.*)@[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)+$";

		// IDのチェック処理
		if(id == null || "".equals(id)){
			resultMessage = "IDは、必須項目です。";
			charCheckFlag = true;
		} else if(!id.matches(MATCH_NUMBER)){
			resultMessage = "IDは、数字のみで入力して下さい。";
			charCheckFlag = true;
		} else if (id.length() != 8){
			resultMessage = "IDは、8桁で入力して下さい。";
			charCheckFlag = true;
		} else

		// パスワードのチェック処理
		if(passWord == null || "".equals(passWord)){
			resultMessage = "パスワードは、必須項目です。";
			charCheckFlag = true;
		} else if (passWord.length() > 8){
			resultMessage = "パスワードは、8桁以内で入力して下さい。";
			charCheckFlag = true;
		} else if (!passWord.matches(MATCH_CHAR)){
			resultMessage = "パスワードは、半角英数字で入力して下さい。";
			charCheckFlag = true;
		} else

		// アドレスのチェック処理
		if(emailAddress == null || "".equals(emailAddress)){
			resultMessage = "メールアドレスは、必須項目です。";
			charCheckFlag = true;
		} else if (!emailAddress.matches(MATCH_MAIL)){
			resultMessage = "メールアドレスの値が不正です。";
			charCheckFlag = true;
		} else

		// 顧客名(漢字)のチェック処理
		if(nameKanji == null || "".equals(nameKanji)){
			resultMessage = "顧客名(漢字)は、必須項目です。";
			charCheckFlag = true;
		} else

		// 顧客名(カナ)のチェック処理
		if(nameKana == null || "".equals(nameKana)){
			resultMessage = "顧客名(カナ)は、必須項目です。";
			charCheckFlag = true;
		} else

		// 入社年月のチェック処理
		if(hiredYyyyMm == null || "".equals(hiredYyyyMm)){
			resultMessage = "入社年月は、必須項目です。";
			charCheckFlag = true;
		} else if (!hiredYyyyMm.matches(MATCH_NUMBER)){
			resultMessage = "入社年月は、半角数字で入力して下さい。";
			charCheckFlag = true;
		} else if (hiredYyyyMm.length() != 6){
			resultMessage = "入社年月は、6桁で入力して下さい。";
			charCheckFlag = true;
		}

		return charCheckFlag;
	}

	/**
	 * ユーザーID存在チェック処理
	 * @return ユーザーIDが存在した場合：true
	 * @return ユーザーIDが存在ない場合：false
	 * @throws Exception
	 */
	private boolean idCheck() throws Exception{
		boolean idCheckFlag = false;

		Connection db = DriverManager.getConnection(
                "jdbc:mysql://localhost/productmgt","root", "root");
		PreparedStatement ps = null;

		try {
			// ユーザIDが入力されていない場合は処理を行わない
			if (id == null || "".equals(id)) {
				return false;
			} else {

				// ユーザIDが入力されていた場合は、SQL文を組み立てる
				StringBuilder sb = new StringBuilder();
				sb.append("SELECT * FROM userinfo WHERE id =");
				sb.append("'").append(id).append("'");

				String query = sb.toString();

//				Context context = new InitialContext();

//				DataSource ds = (DataSource) context
//						.lookup("java:comp/env/jdbc/Productmgt");
				// DBに接続します。
//				db = ds.getConnection();

				ps = db.prepareStatement(query);

				// パラメータ付き SQL 文をデータベースに送るための PreparedStatement オブジェクトを作成します。
				ResultSet rs = ps.executeQuery();

				// 検索結果の行数分繰り返す
				int i = 0;
				while(rs.next()) {
				    i++;
				}
				// 検索結果が存在した場合はフラグをtrueにする
				if (i > 0) {
					idCheckFlag = true;
				}

				ps.close();
				db.close();
			}
		} catch (SQLException ie) {
			resultMessage = "チェック中にエラーが発生しました。";
			throw ie;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (db != null) {
					db.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}
		return idCheckFlag;
	}

	/**
	 * SQL文作成処理
	 * @return 更新結果
	 */
	private StringBuilder createQuery(){
		StringBuilder sb = new StringBuilder();

		sb.append("INSERT INTO userinfo(id  "
				+ ", password  "
				+ ", emailaddress  "
				+ ", namekanji  "
				+ ", namekana  "
				+ ", hiredyyyymm  "
				+ ", position) "
				+ "VALUES ( ");
		sb.append("'").append(id).append("',");
		sb.append("'").append(passWord).append("',");
		sb.append("'").append(emailAddress).append("',");
		sb.append("'").append(nameKanji).append("',");
		sb.append("'").append(nameKana).append("',");
		sb.append("'").append(hiredYyyyMm).append("',");
		sb.append("'").append(position).append("'");
		sb.append(")");
		return sb;
	}

}