<%@ page contentType="text/html; charset=Windows-31J"
	pageEncoding="windows-31j"
	import="java.sql.*, javax.naming.*, javax.sql.*, java.text.*, java.util.*"%>
<jsp:useBean id="userbeans" scope="session"
	class="com.rosy.sample.UserBean" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J" />
<title>€iξρo^</title>
</head>
<body style="background: #ccffcc; text-align: center">
	<h1 style="background: #00ffff">€iξρo^</h1>
	<h2>€io^</h2>
	<p>
		[U[Ό:<jsp:getProperty name="userbeans" property="aName" /></p>
	<form method="POST"
		action="<%=request.getContextPath()%>/InsertServlet">
		<!-- o^γbZ[W -->
		<%
			String resultMessage = (String) request
					.getAttribute("resultMessage");
			if (resultMessage != null) {
		%>
		<p>
			<%=resultMessage%></p>
		<!-- resultMessageͺnullΕΝΘ’κΝbZ[W\¦ -->
		<%
			}
		%>

		<table border="1" align="center">
			<tr style="background: #c0c0c0">
				<th>€iID(K{)</th>
				<th>€iΌ</th>
				<th>€iJi</th>
				<th>εͺή</th>
				<th>ͺή</th>
				<th>¬ͺή</th>
				<th>dόκl</th>
				<th>ΜΏi</th>
				<th>dόζ</th>
				<th>ΜΠ</th>
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

		<input type="submit" value="o^" />

		<a href="http://localhost:8080/Productmgt2/search.jsp"><input
			type="button" value="υζΚΦίι"></a>
	</form>
</body>
</html>



