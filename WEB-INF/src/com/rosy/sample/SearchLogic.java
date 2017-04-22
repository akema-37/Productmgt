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
	 *  商品一覧情報検索処理クラス
	 *  DBに接続し、商品一覧情報の検索処理を行います。
	 *  @version 1.1
	 *  @author akema
	 */
	public class SearchLogic implements Serializable {

	// コンストラクタ
	public SearchLogic() {
	}

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
	 * 商品名
	 */
	private String productName;

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
	 * @param productKbnB  中分類
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
	 * 小分類設定します。
	 * @param productKbnC 小分類
	 */
	public void setProductKbnC(String productKbnC) {
		this.productKbnC = productKbnC;
	}

	/**
	 * 商品名を取得します。
	 * @return 商品名
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * 商品名設定します。
	 * @param productName 商品名
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 *  一覧情報処理
	 *  SQL文を作成し、一覧に表示する商品名を取得します。
	 *  @return 商品リスト
	 *  @throws Exception
	 */
	public List<Map<String, String>> listSearch() throws Exception {

		// DB接続用コネクション
		Connection db = DriverManager.getConnection(
                "jdbc:mysql://localhost/productmgt","root", "root");

		// パラメータ付き SQL 文をデータベースに送るためのプリペアーステートメント
		PreparedStatement ps = null;

		// 検索結果を入れる用のリスト
		List<Map<String, String>> results = new ArrayList<>();

		try {
			// SQL文の組み立て(条件によってSQL文を変える)
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT * FROM product WHERE 1=1");

			// 商品区分Aが空白の場合
			if (productKbnA == null || "".equals(productKbnA)) {
				// 何もしない
			} else {
				// 商品区分Aが空白以外の場合、検索条件に設定する
				sb.append("AND productkbna = '").append(productKbnA).append("'");
			}

			// 商品区分Bが空白の場合
			if (productKbnB == null || "".equals(productKbnB)) {
				// 何もしない
			} else {
				// 商品区分Bが空白以外の場合、検索条件に設定する
				sb.append(" AND productkbnb = '").append(productKbnB).append("'");
			}

			// 商品区分Cが空白の場合
			if (productKbnC == null || "".equals(productKbnC)) {
				// 何もしない
			} else {
				// 商品区分Cが空白以外の場合、検索条件に設定する
				sb.append(" AND productkbnc = '").append(productKbnC).append("'");
			}

			// 商品名が空白の場合
			if (productName == null || "".equals(productName)) {
				// 何もしない
			} else {
				// 商品名が空白以外の場合、検索条件に設定する
				sb.append(" AND  productname LIKE '%").append(productName).append("%'");
			}
			sb.append(" ORDER BY productid ASC");


			// 作成したSQL文を変数に入れる
			String query = sb.toString();

//			// データソースの取得
//			Context context = new InitialContext();
//
//			// データベース名を設定
//			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/Productmgt");
//
//			// DBに接続
//			db = ds.getConnection();

			// 作成した、SQL文をプリペアーステートメントにセット
			ps = db.prepareStatement(query);

			// 問い合わせ実行
			ResultSet rs = ps.executeQuery();

			// 金額表示用のフォーマットを作成
			DecimalFormat nformat = new DecimalFormat("#,###");

			// 検索結果の取得(取得するデータがない場合はfalseが返り、繰り返しを抜ける)
			// for(boolean i = true; i == rs.next();){ の部分は while (rs.next()) { と書いても同じ動きになる
			for(boolean i = true; i == rs.next();){
				Map<String, String> resultValues = new HashMap<>();
				resultValues.put("productid", rs.getString("productid")); // 商品ID
				resultValues.put("productid", rs.getString("productid")); // 商品ID
				resultValues.put("productname", rs.getString("productname"));
				resultValues.put("sellingprice",nformat.format(rs.getInt("sellingprice")));
				resultValues.put("costprice",nformat.format(rs.getInt("costprice")));
				resultValues.put("maker", rs.getString("maker"));

				// 検索結果をresultsにセット
				results.add(resultValues);
			}

			// Close処理
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
