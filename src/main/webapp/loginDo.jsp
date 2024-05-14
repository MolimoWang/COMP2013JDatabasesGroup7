<%@ page language="java" contentType="text/html; charset=utf-8" import="java.sql.*" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<meta charset="UTF-8">
</head>
<body>
<%
String username = request.getParameter("username");
String password = request.getParameter("password"); // 添加获取password的代码

final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // 推荐使用新的驱动类名
final String DB_URL = "jdbc:mysql://localhost/EMP?serverTimezone=Asia/Shanghai";
final String USER = "root";
final String PASS = "N2y7c3t8wsh$";

Connection conn = null;
PreparedStatement stmt = null;
ResultSet rs = null; // 应为ResultSet
try {
   Class.forName(JDBC_DRIVER);
   conn = DriverManager.getConnection(DB_URL, USER, PASS);
   String sql = "SELECT * FROM t_user WHERE username= ? AND password=?";
   stmt = conn.prepareStatement(sql); // 正确传递SQL
   stmt.setString(1, username);
   stmt.setString(2, password);
   rs = stmt.executeQuery();

   if(rs.next()){
	   response.sendRedirect("dashboard.jsp");
   } else {
       out.println("Username or password error");
   }
} catch(SQLException se) {
   se.printStackTrace();
} catch(Exception e) {
   e.printStackTrace();
} finally {
   try { if (rs != null) rs.close(); } catch (SQLException se) { se.printStackTrace(); }
   try { if (stmt != null) stmt.close(); } catch (SQLException se) { se.printStackTrace(); }
   try { if (conn != null) conn.close(); } catch (SQLException se) { se.printStackTrace(); }
}
%>
</body>
</html>
