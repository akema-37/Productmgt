<%@ page contentType="text/html; charset=Windows-31J"%>
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
<title>ユーザーログイン </title>
</head>
<body style="background: #ccffcc; text-align: center">
	<h1 style="background: #00ffff">商品管理システム(仮)</h1>
	<form method="post" action="UserAuthServlet">
		<table border="0" align="center">
			<tr>
				<th align="right">ユーザID：</th>
				<td><input type="text" name="userid" size="15" /></td>
			</tr>
			<tr>
				<th align="right">パスワード：</th>
				<td><input type="password" name="password" size="15" /></td>
			</tr>
		</table>
		<input type="submit" value="ログイン" /> <input type="reset" value="取消" />
	</form>
	<p>
		<a href="http://localhost:8080/Productmgt2/user_registration.jsp"><input
			type="button" value="ユーザー登録"></a>
</body>
</html>