<%@ page contentType="text/html; charset=Windows-31J"%>
<%!
// �T�[�u���b�g��init���\�b�h�ɑ���
public void jspInit() {
    try {
        // JDBC�h���C�o�����[�h
        Class.forName("com.mysql.jdbc.Driver");
    } catch (Exception e) {
        e.printStackTrace();
    }
}
%>
<html>
<head>
<title>���[�U�[���O�C�� </title>
</head>
<body style="background: #ccffcc; text-align: center">
	<h1 style="background: #00ffff">���i�Ǘ��V�X�e��(��)</h1>
	<form method="post" action="UserAuthServlet">
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