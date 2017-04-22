package com.rosy.sample;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  商品情報登録処理クラス
 *  DBに接続し、商品情報の登録処理を行います。
 *  @version 1.1
 *  @author akema
 */
public class InsertLogic implements Serializable {

	// コンストラクタ
	public InsertLogic() {
	}

	/**
	 * 商品ID
	 */
	private String productId;
	/**
	 * 商品名
	 */
	private String productName;
	/**
	 * 商品カナ
	 */
	private String productnameKana;
	/**
	 * 大分類
	 */
	private String productKbnA;
	/**
	 * 中分類
	 */
	private String productKbnB;
	/**
	 * 小分類
	 */
	private String productKbnC;
	/**
	 * 仕入れ値
	 */
	private String costPrice;
	/**
	 * 販売価格
	 */
	private String sellingPrice;
	/**
	 * 仕入先
	 */
	private String supplier;
	/**
	 * 販売社
	 */
	private String maker;

	/**
	 * 結果メッセージ
	 */
	private String resultMessage;

	/**
	 * 商品IDを取得します。
	 * @return 商品ID
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * 商品IDを設定します。
	 * @param productId 商品ID
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * 商品名を取得します。
	 * @return 商品名
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * 商品名を設定します。
	 * @param productName 商品名
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * 商品カナを取得します。
	 * @return 商品カナ
	 */
	public String getProductnameKana() {
		return productnameKana;
	}

	/**
	 * 商品カナを設定します。
	 * @param productnameKana 商品カナ
	 */
	public void setProductnameKana(String productnameKana) {
		this.productnameKana = productnameKana;
	}

	/**
	 * 大分類を取得します。
	 * @return 大分類
	 */
	public String getProductKbnA() {
		return productKbnA;
	}

	/**
	 * 大分類を設定します。
	 * @param productKbnA 大分類
	 */
	public void setProductKbnA(String productKbnA) {
		this.productKbnA = productKbnA;
	}

	/**
	 * 中分類を取得します。
	 * @return 中分類
	 */
	public String getProductKbnB() {
		return productKbnB;
	}

	/**
	 * 中分類を設定します。
	 * @param productKbnB 中分類
	 */
	public void setProductKbnB(String productKbnB) {
		this.productKbnB = productKbnB;
	}

	/**
	 * 小分類を取得します。
	 * @return 小分類
	 */
	public String getProductKbnC() {
		return productKbnC;
	}

	/**
	 * 小分類を設定します。
	 * @param productKbnC 小分類
	 */
	public void setProductKbnC(String productKbnC) {
		this.productKbnC = productKbnC;
	}

	/**
	 * 仕入れ値を取得します。
	 * @return 仕入れ値
	 */
	public String getCostPrice() {
		return costPrice;
	}

	/**
	 * 仕入れ値を設定します。
	 * @param costPrice 仕入れ値
	 */
	public void setCostPrice(String costPrice) {
		this.costPrice = costPrice;
	}

	/**
	 * 販売価格を取得します。
	 * @return 販売価格
	 */
	public String getSellingPrice() {
		return sellingPrice;
	}

	/**
	 * 販売価格を設定します。
	 * @param sellingPrice 販売価格
	 */
	public void setSellingPrice(String sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	/**
	 * 仕入先を取得します。
	 * @return 仕入先
	 */
	public String getSupplier() {
		return supplier;
	}

	/**
	 * 仕入先を設定します。
	 *  @param supplier 仕入先
	 */
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	/**
	 * 販売社を取得します。
	 * @return 販売社
	 */
	public String getMaker() {
		return maker;
	}

	/**
	 * 販売社を設定します。
	 * @param maker 販売社
	 */
	public void setMaker(String maker) {
		this.maker = maker;
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
	 * 商品情報更新処理
	 * SQL文を作成し、商品情報を更新します。
	 * @return 更新結果
	 * @throws Exception
	 */
	public void insert() throws Exception {
		// DB接続用コネクション
		Connection db =  DriverManager.getConnection(
                "jdbc:mysql://localhost/productmgt","root", "root");

		// 商品IDの存在チェック
		boolean checkFlag = productIdCheck();
		try {
				// 商品IDが空だった場合は、メッセージを設定しinsert.jspに遷移する。
			if(productId == null || "".equals(productId)){
				resultMessage = "商品IDは、必須項目です。";
			} else if (checkFlag) {

				// 商品IDがDBに存在した場合は、メッセージを設定しinsert.jspに遷移する。
				resultMessage = "商品ID：" + productId + "は、既に登録済みです。";
			} else {

				// 商品IDがDBに存在ない場合は登録処理をする

				// 登録用のSQL文の作成メソッドの呼び出し
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

				ps.executeUpdate();

				resultMessage = "商品ID：" + productId + "の登録が完了しました。";

				ps.close();
				db.close();
			}

		} catch (SQLException ie) {
			// どうしようもない状況の為、そのままThrow
			resultMessage = "登録に失敗しました";
			ie.printStackTrace();

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
	 * 商品ID存在チェック処理
	 * @return 商品IDが存在した場合：true
	 * @return 商品IDが存在ない場合：false
	 * @throws Exception
	 */
	private boolean productIdCheck() throws Exception{
		boolean checkFlag = false;

		Connection db = DriverManager.getConnection(
                "jdbc:mysql://localhost/productmgt","root", "root");
		PreparedStatement ps = null;

		try {
			// 商品IDが存在しない場合は処理を行わない
			if (productId == null || "".equals(productId)) {
				return false;
			} else {

				// 商品IDが存在した場合は、SQL文を組み立てる
				StringBuilder sb = new StringBuilder();
				sb.append("SELECT * FROM product WHERE productid =");
				sb.append("'").append(productId).append("'");

				String query = sb.toString();

//				Context context = new InitialContext();

//				DataSource ds = (DataSource) context
//						.lookup("java:comp/env/jdbc/Productmgt");
//				// DBに接続します。
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
					checkFlag = true;
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
		return checkFlag;
	}

	/**
	 * SQL文作成処理
	 * @return 更新結果
	 */
	private StringBuilder createQuery(){
		StringBuilder sb = new StringBuilder();

		sb.append("INSERT INTO product(   productid  "
				+ ", productname  , productnamekana, productkbna  "
				+ ", productkbnb , productkbnc  "
				+ ", costprice  , sellingprice  , supplier, maker) "
				+ "VALUES (  ");

		// 商品IDが空白の場合
		if (productId == null || "".equals(productId)) {

		} else {
			// 登録条件に設定する
			sb.append("'").append(productId).append("'");
		}

		// 商品名が空白の場合
		if (productName == null || "".equals(productName)) {
			sb.append(", ").append("null");
		} else {
			// 登録条件に設定する
			sb.append(", '").append(productName).append("'");
		}


		// 商品名カナが空白の場合
		if (productnameKana == null || "".equals(productnameKana)) {
			sb.append(", ").append("null");
		} else {
			// 登録条件に設定する
			sb.append(", '").append(productnameKana).append("'");
		}


		// 大区分が空白の場合
		if (productKbnA == null || "".equals(productKbnA)) {
			sb.append(", ").append("null");
		} else {
			// 登録条件に設定する
			sb.append(", '").append(productKbnA).append("'");
		}


		// 中区分が空白の場合
		if (productKbnB == null || "".equals(productKbnB)) {
			sb.append(", ").append("null");
		} else {
			// 登録条件に設定する
			sb.append(", '").append(productKbnB).append("'");
		}


		// 小区分が空白の場合
		if (productKbnC == null || "".equals(productKbnC)) {
			sb.append(", ").append("null");
		} else {
			// 登録条件に設定する
			sb.append(", '").append(productKbnC).append("'");
		}


		// 仕入れ値が空白の場合
		if (costPrice == null || "".equals(costPrice)) {
			sb.append(", ").append("null");
		} else {
			// 登録条件に設定する
			sb.append(", ").append(costPrice);
		}


		// 販売価格が空白の場合
		if (sellingPrice == null || "".equals(sellingPrice)) {
			sb.append(", ").append("null");
		} else {
			// 登録条件に設定する
			sb.append(", ").append(sellingPrice);
		}


		// 仕入先が空白の場合
		if (supplier == null || "".equals(supplier)) {
			sb.append(", ").append("null");
		} else {
			// 登録条件に設定する
			sb.append(", '").append(supplier).append("'");
		}


		// 販売者が空白の場合
		if (maker == null || "".equals(maker)) {
			sb.append(", ").append("null");
		} else {
			// 登録条件に設定する
			sb.append(", '").append(maker).append("'");
		}

		// 最後の閉じカッコ
		sb.append(")");

		return sb;
	}


}