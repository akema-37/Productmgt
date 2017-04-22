<%@ page contentType="text/html; charset=Windows-31J"
	import="java.sql.*, javax.naming.*, javax.sql.*, java.text.*, com.rosy.*"%>
<jsp:useBean id="userbeans" scope="session"
	class="com.rosy.sample.UserBean" />
	<%!
// サーブレットのinitメソッドに相当
public void jspInit() {
    try {
        // JDBCドライバをロード
        Class.forName("com.mysql.jdbc.Driver");
    } catch (Exception e) {
        e.printStackTrace();
    }
}
%>
<html>
<head>
<title>商品分類検索</title>
</head>
<body style="background: #ccffcc; text-align: center">
	<h1 style="background: #00ffff">商品管理システム(仮)</h1>
	<h2>商品検索</h2>
	<p>
		ユーザー名:<jsp:getProperty name="userbeans" property="aName" /></p>
	<form action="<%=request.getContextPath()%>/SearchServlet"
		method="POST">
		<table border="0" align="center">
			<tr>
				<th align="right">大分類：</th>
				<td><select name="productKbnA">
						<option value="" selected="selected"></option>
						<option value="01">食料品</option>
						<option value="02">生活雑貨</option>
						<option value="03">書籍</option>
				</select></td>
			</tr>
			<tr>
				<th align="right">中分類：</th>
				<td><select name="productKbnB">
						<option value="" selected="selected"></option>
						<option value="01">食肉・乳製品</option>
						<option value="02">菓子類</option>
						<option value="03">飲料</option>
						<option value="99">その他</option>
						<option value="01">化粧品・整髪料</option>
						<option value="02">生活用品</option>
						<option value="99">その他</option>
						<option value="01">雑誌</option>
						<option value="02">単行本</option>
						<option value="99">その他</option>
				</select></td>
			</tr>
			<tr>
				<th align="right">小分類：</th>
				<td><select name="productKbnC">
						<option value="" selected="selected"></option>
						<option value="01">乳製品</option>
						<option value="02">乳製品</option>
						<option value="99">その他</option>
						<option value="01">玩具付き菓子</option>
						<option value="02">和菓子</option>
						<option value="03">洋菓子</option>
						<option value="99">その他</option>
						<option value="01">清涼飲料品</option>
						<option value="02">酒類</option>
						<option value="03">その他</option>
						<option value="99">その他</option>
						<option value="01">女性用化粧品</option>
						<option value="02">男性用化粧品</option>
						<option value="02">コミック</option>
						<option value="99">その他</option>
						<option value="99">その他</option>
				</select></td>
			</tr>
			<tr>
				<th align="right">商品名検索：</th>
				<td><input type="text" name="productName" size="28"
					maxlength="18" /></td>
			</tr>
		</table>

		<input type="submit" value="検索" />
	</form>
	<table border="0" align="center">
		<tr>
			<th><a href="http://localhost:8080/Productmgt2/insert.jsp"><input
					type="submit" value="商品登録" /> </a></th>
			<th><a href="http://localhost:8080/Productmgt2/login.jsp"><input
					type="submit" value="トップへ戻る" /> </a></th>
		</tr>
	</table>
</body>
</html>