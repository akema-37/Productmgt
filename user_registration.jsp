<%@ page contentType="text/html; charset=Windows-31J"
	import="java.sql.*, javax.naming.*, javax.sql.*, java.text.*, java.util.*"%>
<jsp:useBean id="userbeans" scope="session"
	class="com.rosy.sample.UserBean" />
<%!
// サーブレットのinitメソッドに相当
public void jspInit() {
    try {
        // JDBCドライバをロード
        Class.forName("com.mysql.jdbc.Driver");
    } catch (Exception e) {
        e.printStackTrace();
    }
}
%>
<html>
<head>
<title>ユーザ情報登録</title>
</head>
<body style="background: #ccffcc; text-align: center">
	<h1 style="background: #00ffff">ユーザ情報登録</h1>
	<h2>ユーザ登録</h2>
	<p>
		ユーザー名:<jsp:getProperty name="userbeans" property="aName" /></p>
	<form method="POST"
		action="<%=request.getContextPath()%>/UserInsertServlet">
		<!-- 登録後メッセージ -->
		<%
			String resultMessage = (String) request
					.getAttribute("resultMessage");
			if (resultMessage != null) {
		%>
		<p>
			<%=resultMessage%></p>
		<!-- resultMessageがnullではない場合はメッセージ表示 -->
		<%
			}
		%>

		<table border="0" align="center">
			<tr>
				<th style="text-align: right;">ユーザーID (必須)：</th>
				<td><input type="text" name="id" size="20" /> (例：00000001)</td>
			</tr>
			<tr>
				<th style="text-align: right;">パスワード (必須)：</th>
				<td><input type="text" name="password" size="20" /> (例：1234abcd)</td>
			</tr>
			<tr>
				<th style="text-align: right;">メールアドレス (必須)：</th>
				<td><input type="text" name="emailaddress" size="20" /> (例：user@gmail.com)</td>
			</tr>
			<tr>
				<th style="text-align: right;">ユーザー名(漢字) (必須)：</th>
				<td><input type="text" name="namekanji" size="20" /> (例：ユーザー 太郎)</td>
			</tr>
			<tr>
				<th style="text-align: right;">ユーザー名(カナ) (必須)：</th>
				<td><input type="text" name="namekana" size="20" /> (例：ユーザー タロウ)</td>
			</tr>
			<tr>
				<th style="text-align: right;">入社年月 (必須)：</th>
				<td><input type="text" name="hiredyyyymm" size="20" /> (例：201607)</td>
			</tr>
			<tr>
				<th style="text-align: right;">役職 (必須)：</th>
				<td><select name="position">
						<option value="0">0:本社社員</option>
						<option value="1">1:店長</option>
						<option value="2">2:社員</option>
						<option value="3">3:パートまたはバイト</option>
				</select></td>
			</tr>
		</table>

		<input type="submit" value="登録" />

		<a href="http://localhost:8080/Productmgt2/login.jsp"><input
			type="button" value="ログイン画面へ戻る"></a>
	</form>
</body>
</html>



