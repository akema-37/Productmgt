<%@ page contentType="text/html; charset=Windows-31J"
	pageEncoding="windows-31j"
	import="java.sql.*, javax.naming.*, javax.sql.*, java.text.*, java.util.*"%>
<jsp:useBean id="userbeans" scope="session"
	class="com.rosy.sample.UserBean" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J" />
<title>���i���ڍ�</title>
</head>
<body style="background: #ccffcc; text-align: center">
	<h1 style="background: #00ffff">���i���ڍ�</h1>
	<form method="POST"
		action="<%=request.getContextPath()%>/UpdateServlet">
		<h2>���i�ڍ׉��</h2>
		<p>
			���[�U�[��:<jsp:getProperty name="userbeans" property="aName" /></p>
		<%
			String resultMessage = (String) request
					.getAttribute("resultMessage");
		%><!-- �X�V�チ�b�Z�[�W -->
		<p>
			<%=resultMessage%></p>
		<!-- ���b�Z�[�W�\�� -->

		<table border="1" align="center">
			<tr style="background: #c0c0c0">
				<th>���iID</th>
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
				<th><input type="submit" value="�X�V" /></th>
				<th><a href="http://localhost:8080/Productmgt2/search.jsp"><input
						type="button" value="������ʂ֖߂�"></a></th>
			</tr>
		</table>
	</form>
</body>
</html>