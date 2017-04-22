package com.rosy.sample;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  詳細情報取得処理クラス
 *  DBに接続し、詳細情報の取得処理を行います。
 *  @version 1.1
 *  @author akema
 */
public class DetailLogic implements Serializable {
	// コンストラクタ
	public DetailLogic() {
	}

	/**
	 * 商品ID
	 */
	private String productId;

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

	// 詳細メソッドの定義
	/**
	 * 商品詳細表示処理
	 * @return 商品リスト
	 * @throws Exception
	 */
	public List<Map<String, String>> detailSearch() throws Exception {

		// SQL文の組み立て
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM product WHERE 1=1");

		// 商品IDが空白の場合
		String strProductId = getProductId();
		if (strProductId == null || "".equals(strProductId)) {
			// 何もしない
		} else {
			// 商品IDが空白以外の場合、検索条件に設定する
			sb.append(" AND productid = '").append(strProductId).append("'");
		}

		String query = sb.toString();

		Connection db = DriverManager.getConnection(
                "jdbc:mysql://localhost/productmgt","root", "root");
		PreparedStatement ps = null;
		List<Map<String, String>> results = new ArrayList<>();

		try {
//			// データソースの取得
//			Context context = new InitialContext();
//			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/Productmgt");

//			// DBに接続します。
//			db = ds.getConnection();

			// パラメータ付き SQL 文をデータベースに送るための PreparedStatement オブジェクトを作成します。
			ps = db.prepareStatement(query);
			// 問い合わせを行います
			ResultSet rs = ps.executeQuery();
			DecimalFormat nformat = new DecimalFormat("#,###");
			while (rs.next()) {
				Map<String, String> resultValues = new HashMap<>();
				resultValues.put("productid", rs.getString("productid"));
				resultValues.put("productname", rs.getString("productname"));
				resultValues.put("productnamekana",rs.getString("productnamekana"));
				resultValues.put("productkbnA", rs.getString("productkbnA"));
				resultValues.put("productkbnB", rs.getString("productkbnB"));
				resultValues.put("productkbnC", rs.getString("productkbnC"));
				resultValues.put("costprice",nformat.format(rs.getInt("costprice")));
				resultValues.put("sellingprice",nformat.format(rs.getInt("sellingprice")));
				resultValues.put("supplier", rs.getString("supplier"));
				resultValues.put("maker", rs.getString("maker"));
				results.add(resultValues);
			}

			resultMessage = "商品ID：" + productId + "の項目を編集し、更新ボタンを押して下さい。";

			rs.close();
			ps.close();
			db.close();
		} catch (SQLException ie) {
			// どうしようもない状況の為、そのままThrow
			ie.printStackTrace();
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
		return results;
	}
}
