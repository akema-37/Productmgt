package com.rosy.sample;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *  商品情報更新処理クラス
 *  DBに接続し、商品情報の更新処理を行います。
 *  @version 1.1
 *  @author akema
 */
public class UpdateLogic implements Serializable {

	// コンストラクタ
	public UpdateLogic() {
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
	public void update() throws Exception {

		// DB接続用コネクション
		Connection db = DriverManager.getConnection(
                "jdbc:mysql://localhost/productmgt","root", "root");

		try {
			// 更新用のSQL文の作成
			StringBuilder sb = createQuery();

//			// データソースの取得
//			Context context = new InitialContext();
//			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/Productmgt");
//
//			// DBに接続します。
//			db = ds.getConnection();

			// パラメータ付き SQL 文をデータベースに送るための PreparedStatement オブジェクトを作成します。
			String query = sb.toString();
			PreparedStatement ps = db.prepareStatement(query);

			ps.executeUpdate();

			resultMessage = "商品ID：" + productId + "の更新が完了しました。";

			ps.close();
			db.close();

		} catch (SQLException ie) {
			// どうしようもない状況の為、そのままThrow
			resultMessage = "更新に失敗しました";
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
	 * SQL文処理
	 * @return 更新結果
	 */
	private StringBuilder createQuery(){
		StringBuilder sb = new StringBuilder();

		sb.append("UPDATE product SET ");
		sb.append("productid = '").append(productId).append("'");
		sb.append(", productname = '").append(productName).append("'");
		sb.append(", productnamekana = '").append(productnameKana).append("'");
		sb.append(", productkbna = '").append(productKbnA).append("'");
		sb.append(", productkbnb = '").append(productKbnB).append("'");
		sb.append(", productkbnc = '").append(productKbnC).append("'");
		sb.append(", costprice = ").append(costPrice);
		sb.append(", sellingprice = ").append(sellingPrice);
		sb.append(", supplier = '").append(supplier).append("'");
		sb.append(", maker = '").append(maker).append("'");
		sb.append(" WHERE ");
		sb.append("productid = '").append(productId).append("'");
		return sb;
	}

}