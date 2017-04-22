<%@ page contentType="text/html; charset=Windows-31J"
pageEncoding="windows-31j"
	import="java.util.*, java.sql.*, javax.naming.*, javax.sql.*, java.text.*"%>
<%
	int count = 1;
%>
<jsp:useBean id="userbeans" scope="session"
	class="com.rosy.sample.UserBean" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J" />
<title>商品情報一覧</title>
</head>

<body style="background: #ccffcc; text-align: center">
	<h1 style="background: #00ffff">商品管理システム(仮)</h1>
	<h2>商品一覧</h2>
	<p>
		ユーザー名:<jsp:getProperty name="userbeans" property="aName" /></p>
	<form method="POST"
		action="<%=request.getContextPath()%>/DetailServlet">

		<%
			String errMsg = (String) request.getAttribute("errMsg");
			if (errMsg != null && errMsg.length() > 0) {
		%>
		<div style="background: #f2dede">
			<span style="color: #ff0000;"><%=errMsg%></span>

		</div>
		<%
			}
		%>
		<table border="1" align="center">
			<tr style="background: #c0c0c0">
				<th></th>
				<th>商品ID</th>
				<th>商品名</th>
				<th>売値</th>
				<th>原価</th>
				<th>販売社</th>
			</tr>

			<%
				int first = -1;
				List<Map<String, String>> listResults = (List<Map<String, String>>) request.getAttribute("results");
				if (listResults != null && listResults.size() > 0) {
					for (Map<String, String> values : listResults) {
						first++;
						if (first == 0) {
			%>
			<tr>
				<td><input type="radio" name="select" value="<%=values.get("productid")%>" checked /></td>
				<%
					} else {
				%>

			<tr>
				<td><input type="radio" name="select"
					value="<%=values.get("productid")%>" /></td>
				<%
					}
				%>
				<td><%=values.get("productid")%></td>
				<td><%=values.get("productname")%></td>
				<td align="right"><%=values.get("sellingprice")%></td>
				<td align="right"><%=values.get("costprice")%></td>
				<td><%=values.get("maker")%> <input type="hidden"
					name="productid" value="<%=values.get("productid")%>" /></td>
			</tr>
			<%
				}
				} else {
			%>
			<p>該当データが存在しません</p>
			<%
				}
			%>
		</table>


		<input type="submit" value="詳細" /> <input type="button" value="戻る"
			onClick="history.back()">
	</form>

</body>
</html>