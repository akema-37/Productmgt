<%@ page contentType="text/html; charset=Windows-31J"
	import="java.sql.*, javax.naming.*, javax.sql.*, java.text.*, java.util.*"%>
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
<title>���[�U���o�^</title>
</head>
<body style="background: #ccffcc; text-align: center">
	<h1 style="background: #00ffff">���[�U���o�^</h1>
	<h2>���[�U�o�^</h2>
	<p>
		���[�U�[��:<jsp:getProperty name="userbeans" property="aName" /></p>
	<form method="POST"
		action="<%=request.getContextPath()%>/UserInsertServlet">
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

		<table border="0" align="center">
			<tr>
				<th style="text-align: right;">���[�U�[ID (�K�{)�F</th>
				<td><input type="text" name="id" size="20" /> (��F00000001)</td>
			</tr>
			<tr>
				<th style="text-align: right;">�p�X���[�h (�K�{)�F</th>
				<td><input type="text" name="password" size="20" /> (��F1234abcd)</td>
			</tr>
			<tr>
				<th style="text-align: right;">���[���A�h���X (�K�{)�F</th>
				<td><input type="text" name="emailaddress" size="20" /> (��Fuser@gmail.com)</td>
			</tr>
			<tr>
				<th style="text-align: right;">���[�U�[��(����) (�K�{)�F</th>
				<td><input type="text" name="namekanji" size="20" /> (��F���[�U�[ ���Y)</td>
			</tr>
			<tr>
				<th style="text-align: right;">���[�U�[��(�J�i) (�K�{)�F</th>
				<td><input type="text" name="namekana" size="20" /> (��F���[�U�[ �^���E)</td>
			</tr>
			<tr>
				<th style="text-align: right;">���ДN�� (�K�{)�F</th>
				<td><input type="text" name="hiredyyyymm" size="20" /> (��F201607)</td>
			</tr>
			<tr>
				<th style="text-align: right;">��E (�K�{)�F</th>
				<td><select name="position">
						<option value="0">0:�{�ЎЈ�</option>
						<option value="1">1:�X��</option>
						<option value="2">2:�Ј�</option>
						<option value="3">3:�p�[�g�܂��̓o�C�g</option>
				</select></td>
			</tr>
		</table>

		<input type="submit" value="�o�^" />

		<a href="http://localhost:8080/Productmgt2/login.jsp"><input
			type="button" value="���O�C����ʂ֖߂�"></a>
	</form>
</body>
</html>



