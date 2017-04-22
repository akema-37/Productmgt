<%@ page contentType="text/html; charset=Windows-31J"
	pageEncoding="windows-31j"
	import="java.sql.*, javax.naming.*, javax.sql.*, java.text.*, java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J" />
<title>ユーザー登録</title>
</head>
<body style="background: #ccffcc; text-align: center">
	<h1 style="background: #00ffff">商品管理システム</h1>
	<h2>ログイン画面</h2>
	<%
		String resultMessage = (String) request
		.getAttribute("resultMessage");
		if (resultMessage == null) {
			resultMessage = "";
		}
	%><!-- 更新後メッセージ -->
	<p>
		<%=resultMessage%>
	</p>

	<!-- メッセージ表示 -->
	<h2>ユーザー登録</h2>
	<form method="POST"
		action="<%=request.getContextPath()%>/UserInsertServlet">
		<table border="0" align="center">
			<tr>
				<th style="text-align: right;">ID：</th>
				<td><input type="text" name="id" size="20" /></td>
			</tr>
			<tr>
				<th style="text-align: right;">パスワード：</th>
				<td><input type="text" name="password" size="20" /></td>
			</tr>
			<tr>
				<th style="text-align: right;">メールアドレス：</th>
				<td><input type="text" name="emailAddress" size="20" /></td>
			</tr>
			<tr>
				<th style="text-align: right;">顧客名(漢字)：</th>
				<td><input type="text" name="nameKanji" size="20" /></td>
			</tr>
			<tr>
				<th style="text-align: right;">顧客名(カナ)：</th>
				<td><input type="text" name="nameKana" size="20" /></td>
			</tr>
			<tr>
				<th style="text-align: right;">入社年月：</th>
				<td><input type="text" name="hiredYyyymm" size="20" /></td>
			</tr>
			<tr>
				<th style="text-align: right;">役職：</th>
				<td><select name="position">
						<option value="0">0:本社社員</option>
						<option value="1">1:店長</option>
						<option value="2">2:社員</option>
						<option value="3">3:パートまたはバイト</option>
				</select></td>
			</tr>
		</table>
		<input type="submit" value="登録" /> <input type="reset" value="取消" />
		<p>
			<a href="http://localhost:8080/Productmgt2/login.jsp"><input
				type="button" value="ログインページへ戻る"></a>
	</form>
</body>
</html>