<%@ page contentType="text/html; charset=Windows-31J"
	pageEncoding="windows-31j"
	import="java.sql.*, javax.naming.*, javax.sql.*, java.text.*, java.util.*"%>
<jsp:useBean id="userbeans" scope="session"
	class="com.rosy.sample.UserBean" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J" />
<title>商品情報詳細</title>
</head>
<body style="background: #ccffcc; text-align: center">
	<h1 style="background: #00ffff">商品情報詳細</h1>
	<form method="POST"
		action="<%=request.getContextPath()%>/UpdateServlet">
		<h2>商品詳細画面</h2>
		<p>
			ユーザー名:<jsp:getProperty name="userbeans" property="aName" /></p>
		<%
			String resultMessage = (String) request
					.getAttribute("resultMessage");
		%><!-- 更新後メッセージ -->
		<p>
			<%=resultMessage%></p>
		<!-- メッセージ表示 -->

		<table border="1" align="center">
			<tr style="background: #c0c0c0">
				<th>商品ID</th>
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

			<%
				for (Map<String, String> values : ((List<Map<String, String>>) request.getAttribute("results"))) {
			%>


			<tr>
				<td><input type="hidden" name="productid"
					value="<%=values.get("productid")%>" /><%=values.get("productid")%></td>
				<td><input type="text" name="productname" size="20"
					value="<%=values.get("productname")%>" /></td>
				<td><input type="text" name="productnamekana" size="20"
					value="<%=values.get("productnamekana")%>" /></td>
				<td><input type="text" name="productkbnA" size="7"
					value="<%=values.get("productkbnA")%>" /></td>
				<td><input type="text" name="productkbnB" size="7"
					value="<%=values.get("productkbnB")%>" /></td>
				<td><input type="text" name="productkbnC" size="7"
					value="<%=values.get("productkbnC")%>" /></td>
				<td><input type="text" name="costprice" size="7"
					value="<%=values.get("costprice")%>" /></td>
				<td><input type="text" name="sellingprice" size="7"
					value="<%=values.get("sellingprice")%>" /></td>
				<td><input type="text" name="supplier" size="7"
					value="<%=values.get("supplier")%>" /></td>
				<td><input type="text" name="maker" size="15"
					value="<%=values.get("maker")%>" /></td>
			</tr>
			<%
				}
			%>

		</table>

		<table border="0" align="center">
			<tr>
				<th><input type="submit" value="更新" /></th>
				<th><a href="http://localhost:8080/Productmgt2/search.jsp"><input
						type="button" value="検索画面へ戻る"></a></th>
			</tr>
		</table>
	</form>
</body>
</html>