<%@ page contentType="text/html; charset=Windows-31J"
	import="java.sql.*, javax.naming.*, javax.sql.*, java.text.*, com.rosy.*"%>
<jsp:useBean id="userbeans" scope="session"
	class="com.rosy.sample.UserBean" />
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
<title>���i���ތ���</title>
</head>
<body style="background: #ccffcc; text-align: center">
	<h1 style="background: #00ffff">���i�Ǘ��V�X�e��(��)</h1>
	<h2>���i����</h2>
	<p>
		���[�U�[��:<jsp:getProperty name="userbeans" property="aName" /></p>
	<form action="<%=request.getContextPath()%>/SearchServlet"
		method="POST">
		<table border="0" align="center">
			<tr>
				<th align="right">�啪�ށF</th>
				<td><select name="productKbnA">
						<option value="" selected="selected"></option>
						<option value="01">�H���i</option>
						<option value="02">�����G��</option>
						<option value="03">����</option>
				</select></td>
			</tr>
			<tr>
				<th align="right">�����ށF</th>
				<td><select name="productKbnB">
						<option value="" selected="selected"></option>
						<option value="01">�H���E�����i</option>
						<option value="02">�َq��</option>
						<option value="03">����</option>
						<option value="99">���̑�</option>
						<option value="01">���ϕi�E������</option>
						<option value="02">�����p�i</option>
						<option value="99">���̑�</option>
						<option value="01">�G��</option>
						<option value="02">�P�s�{</option>
						<option value="99">���̑�</option>
				</select></td>
			</tr>
			<tr>
				<th align="right">�����ށF</th>
				<td><select name="productKbnC">
						<option value="" selected="selected"></option>
						<option value="01">�����i</option>
						<option value="02">�����i</option>
						<option value="99">���̑�</option>
						<option value="01">�ߋ�t���َq</option>
						<option value="02">�a�َq</option>
						<option value="03">�m�َq</option>
						<option value="99">���̑�</option>
						<option value="01">���������i</option>
						<option value="02">���</option>
						<option value="03">���̑�</option>
						<option value="99">���̑�</option>
						<option value="01">�����p���ϕi</option>
						<option value="02">�j���p���ϕi</option>
						<option value="02">�R�~�b�N</option>
						<option value="99">���̑�</option>
						<option value="99">���̑�</option>
				</select></td>
			</tr>
			<tr>
				<th align="right">���i�������F</th>
				<td><input type="text" name="productName" size="28"
					maxlength="18" /></td>
			</tr>
		</table>

		<input type="submit" value="����" />
	</form>
	<table border="0" align="center">
		<tr>
			<th><a href="http://localhost:8080/Productmgt2/insert.jsp"><input
					type="submit" value="���i�o�^" /> </a></th>
			<th><a href="http://localhost:8080/Productmgt2/login.jsp"><input
					type="submit" value="�g�b�v�֖߂�" /> </a></th>
		</tr>
	</table>
</body>
</html>