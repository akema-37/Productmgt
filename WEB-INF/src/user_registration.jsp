<%@ page contentType="text/html; charset=Windows-31J"
	pageEncoding="windows-31j"
	import="java.sql.*, javax.naming.*, javax.sql.*, java.text.*, java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J" />
<title>���[�U�[�o�^</title>
</head>
<body style="background: #ccffcc; text-align: center">
	<h1 style="background: #00ffff">���i�Ǘ��V�X�e��</h1>
	<h2>���O�C�����</h2>
	<%
		String resultMessage = (String) request
		.getAttribute("resultMessage");
		if (resultMessage == null) {
			resultMessage = "";
		}
	%><!-- �X�V�チ�b�Z�[�W -->
	<p>
		<%=resultMessage%>
	</p>

	<!-- ���b�Z�[�W�\�� -->
	<h2>���[�U�[�o�^</h2>
	<form method="POST"
		action="<%=request.getContextPath()%>/UserInsertServlet">
		<table border="0" align="center">
			<tr>
				<th style="text-align: right;">ID�F</th>
				<td><input type="text" name="id" size="20" /></td>
			</tr>
			<tr>
				<th style="text-align: right;">�p�X���[�h�F</th>
				<td><input type="text" name="password" size="20" /></td>
			</tr>
			<tr>
				<th style="text-align: right;">���[���A�h���X�F</th>
				<td><input type="text" name="emailAddress" size="20" /></td>
			</tr>
			<tr>
				<th style="text-align: right;">�ڋq��(����)�F</th>
				<td><input type="text" name="nameKanji" size="20" /></td>
			</tr>
			<tr>
				<th style="text-align: right;">�ڋq��(�J�i)�F</th>
				<td><input type="text" name="nameKana" size="20" /></td>
			</tr>
			<tr>
				<th style="text-align: right;">���ДN���F</th>
				<td><input type="text" name="hiredYyyymm" size="20" /></td>
			</tr>
			<tr>
				<th style="text-align: right;">��E�F</th>
				<td><select name="position">
						<option value="0">0:�{�ЎЈ�</option>
						<option value="1">1:�X��</option>
						<option value="2">2:�Ј�</option>
						<option value="3">3:�p�[�g�܂��̓o�C�g</option>
				</select></td>
			</tr>
		</table>
		<input type="submit" value="�o�^" /> <input type="reset" value="���" />
		<p>
			<a href="http://localhost:8080/Productmgt2/login.jsp"><input
				type="button" value="���O�C���y�[�W�֖߂�"></a>
	</form>
</body>
</html>