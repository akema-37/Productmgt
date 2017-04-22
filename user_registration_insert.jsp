<%@ page contentType="text/html; charset=UTF-8"
	import="java.sql.*, javax.naming.*, javax.sql.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	Context context = new InitialContext();
	DataSource ds = (DataSource) context
			.lookup("java:comp/env/jdbc/Productmgt");
	Connection db = ds.getConnection();
	PreparedStatement ps = db
			.prepareStatement("INSERT INTO UserInfo(id, password, emailaddress, namekanji, namekana, hiredyyyymm, position) VALUES(? ,? ,? ,? ,? ,? ,?)");
	ps.setString(1, request.getParameter("id"));
	ps.setString(2, request.getParameter("password"));
	ps.setString(3, request.getParameter("emailaddress"));
	ps.setString(4, request.getParameter("namekanji"));
	ps.setString(5, request.getParameter("namekana"));
	ps.setString(6, request.getParameter("hiredyyyymm"));
	ps.setString(7, request.getParameter("position"));
	ps.executeUpdate();
	ps.close();
	db.close();
	response.sendRedirect("user_registration.jsp");
%>