<%@ page contentType="text/html; charset=Windows-31J"
	pageEncoding="windows-31j"
	import="java.sql.*, javax.naming.*, javax.sql.*, java.text.*, java.util.*"%>
<jsp:useBean id="userbeans" scope="session"
	class="com.rosy.sample.UserBean" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J" />
<title>���i���o�^</title>
</head>
<body style="background: #ccffcc; text-align: center">
	<h1 style="background: #00ffff">���i���o�^</h1>
	<h2>���i�o�^</h2>
	<p>
		���[�U�[��:<jsp:getProperty name="userbeans" property="aName" /></p>
	<form method="POST"
		action="<%=request.getContextPath()%>/InsertServlet">
		<!-- �o�^�チ�b�Z�[�W -->
		<%
			String resultMessage = (String) request
					.getAttribute("resultMessage");
			if (resultMessage != null) {
		%>
		<p>
			<%=resultMessage%></p>
		<!-- resultMessage��null�ł͂Ȃ��ꍇ�̓��b�Z�[�W�\�� -->
		<%
			}
		%>

		<table border="1" align="center">
			<tr style="background: #c0c0c0">
				<th>���iID(�K�{)</th>
				<th>���i��</th>
				<th>���i�J�i</th>
				<th>�啪��</th>
				<th>������</th>
				<th>������</th>
				<th>�d����l</th>
				<th>�̔����i</th>
				<th>�d����</th>
				<th>�̔���</th>
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

		<input type="submit" value="�o�^" />

		<a href="http://localhost:8080/Productmgt2/search.jsp"><input
			type="button" value="������ʂ֖߂�"></a>
	</form>
</body>
</html>



