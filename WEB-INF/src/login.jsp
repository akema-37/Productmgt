<%@ page contentType="text/html; charset=Windows-31J"
	pageEncoding="windows-31j"
	import="java.sql.*, javax.naming.*, javax.sql.*, java.text.*, java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J" />
<title>���[�U�[���O�C��</title>
</head>
<body style="background: #ccffcc; text-align: center">
	<h1 style="background: #00ffff">���i�Ǘ��V�X�e��(��)</h1>
	<form method="post" action="ControlServlet">
		<table border="0" align="center">
			<tr>
				<th align="right">���[�UID�F</th>
				<td><input type="text" name="userid" size="15" /></td>
			</tr>
			<tr>
				<th align="right">�p�X���[�h�F</th>
				<td><input type="password" name="password" size="15" /></td>
			</tr>
		</table>
		<input type="submit" value="���O�C��" /> <input type="reset" value="���" />
	</form>
	<p>
		<a href="http://localhost:8080/Productmgt2/user_registration.jsp"><input
			type="button" value="���[�U�[�o�^"></a>
</body>
</html>