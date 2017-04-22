<%@ page contentType="text/html; charset=Windows-31J"
	pageEncoding="windows-31j"
	import="java.sql.*, javax.naming.*, javax.sql.*, java.text.*, java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J" />
<title>ユーザーログイン</title>
</head>
<body style="background: #ccffcc; text-align: center">
	<h1 style="background: #00ffff">商品管理システム(仮)</h1>
	<form method="post" action="ControlServlet">
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