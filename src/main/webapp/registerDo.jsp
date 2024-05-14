<%@ page language="java" contentType="text/html; charset=utf-8" import="java.sql.*" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Registration Process</title>
</head>
<body>
<%
String username = request.getParameter("username");
String password = request.getParameter("password");

Connection conn = null;
PreparedStatement pstmt = null;
try {
   // STEP 2: Register JDBC driver and open a connection
   Class.forName("com.mysql.cj.jdbc.Driver");
   conn = DriverManager.getConnection("jdbc:mysql://localhost/EMP?serverTimezone=Asia/Shanghai", "root", "N2y7c3t8wsh$");

   // STEP 4: Execute a query to insert data
   String sql = "INSERT INTO t_user (username, password) VALUES (?, ?)";
   pstmt = conn.prepareStatement(sql);
   pstmt.setString(1, username);
   pstmt.setString(2, password);
   int result = pstmt.executeUpdate();

   if (result > 0) {
       // If insert is successful, redirect to the login page
       response.sendRedirect("login.html");
   } else {
       out.println("Registration failed. Please try again.");
   }

} catch(SQLException se) {
   // Handle errors for JDBC
   se.printStackTrace();
   out.println("SQL Error: " + se.getMessage());
} catch(Exception e) {
   // Handle errors for Class.forName
   e.printStackTrace();
   out.println("Error: " + e.getMessage());
} finally {
   // finally block used to close resources
   try { if (pstmt != null) pstmt.close(); } catch (SQLException se2) { }
   try { if (conn != null) conn.close(); } catch (SQLException se) {
       se.printStackTrace();
   }
}
%>
</body>
</html>
