<%@ page contentType="text/html; charset=Windows-31J"
	pageEncoding="windows-31j"
	import="java.sql.*, javax.naming.*, javax.sql.*, java.text.*, java.util.*"%>
<jsp:useBean id="userbeans" scope="session"
	class="com.rosy.sample.UserBean" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J" />
<title>商品情報登録</title>
</head>
<body style="background: #ccffcc; text-align: center">
	<h1 style="background: #00ffff">商品情報登録</h1>
	<h2>商品登録</h2>
	<p>
		ユーザー名:<jsp:getProperty name="userbeans" property="aName" /></p>
	<form method="POST"
		action="<%=request.getContextPath()%>/InsertServlet">
		<!-- 登録後メッセージ -->
		<%
			String resultMessage = (String) request
					.getAttribute("resultMessage");
			if (resultMessage != null) {
		%>
		<p>
			<%=resultMessage%></p>
		<!-- resultMessageがnullではない場合はメッセージ表示 -->
		<%
			}
		%>

		<table border="1" align="center">
			<tr style="background: #c0c0c0">
				<th>商品ID(必須)</th>
				<th>商品名</th>
				<th>商品カナ</th>
				<th>大分類</th>
				<th>中分類</th>
				<th>小分類</th>
				<th>仕入れ値</th>
				<th>販売価格</th>
				<th>仕入先</th>
				<th>販売社</th>
			</tr>

			<tr>

				<td><input type="text" name="productid" size="20" /></td>
				<td><input type="text" name="productname" size="20" /></td>
				<td><input type="text" name="productnamekana" size="20" /></td>
				<td><input type="text" name="productkbnA" size="7" /></td>
				<td><input type="text" name="productkbnB" size="7" /></td>
				<td><input type="text" name="productkbnC" size="7" /></td>
				<td><input type="text" name="costprice" size="7" /></td>
				<td><input type="text" name="sellingprice" size="7" /></td>
				<td><input type="text" name="supplier" size="7" /></td>
				<td><input type="text" name="maker" size="15" /></td>

			</tr>

		</table>

		<input type="submit" value="登録" />

		<a href="http://localhost:8080/Productmgt2/search.jsp"><input
			type="button" value="検索画面へ戻る"></a>
	</form>
</body>
</html>



