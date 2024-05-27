<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
   <meta charset="UTF-8">
   <title>Register</title>
   <style>
      body {
         font-family: Arial, sans-serif;
         background-color: #f0f2f5;
         display: flex;
         justify-content: center;
         align-items: center;
         height: 100vh;
         margin: 0;
      }
      .container {
         background-color: #fff;
         padding: 20px;
         border-radius: 10px;
         box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
         width: 300px;
      }
      .container h1 {
         text-align: center;
         margin-bottom: 20px;
      }
      .container input[type="text"], .container input[type="password"] {
         width: calc(100% - 22px);
         padding: 10px;
         margin: 10px 0;
         border: 1px solid #ccc;
         border-radius: 5px;
      }
      .container input[type="submit"] {
         width: 100%;
         padding: 10px;
         border: none;
         border-radius: 5px;
         background-color: #007bff;
         color: white;
         font-size: 16px;
         cursor: pointer;
      }
      .container input[type="submit"]:hover {
         background-color: #0056b3;
      }
   </style>
</head>
<body>

<div class="container">
   <h1>Register</h1>
   <form method="post">
      <input type="text" name="username" placeholder="username" required>
      <input type="password" name="password" placeholder="password" required>
      <input type="submit" value="register">
   </form>
</div>

<%
   if (request.getMethod().equalsIgnoreCase("POST")) {
      String username = request.getParameter("username");
      String password = request.getParameter("password");

      Connection conn = null;
      PreparedStatement pstmt = null;

      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         conn = DriverManager.getConnection("jdbc:mysql://localhost/exampaperdatabase?serverTimezone=Asia/Shanghai", "root", "N2y7c3t8wsh$");

         String sql = "INSERT INTO t_user (username, password) VALUES (?, ?)";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, username);
         pstmt.setString(2, password);
         int result = pstmt.executeUpdate();

         if (result > 0) {
            out.println("<div class='container'><h1>register successfully</h1><p>welcome，" + username + "！</p></div>");
            response.setHeader("Refresh", "3; URL=loginDo.jsp");
         } else {
            out.println("<div class='container'><h1>fail to register</h1><p>please try again</p></div>");
         }
      } catch (Exception e) {
         e.printStackTrace();
         out.println("<div class='container'><h1>fail to register</h1><p>please try again</p></div>");
      } finally {
         try {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   }
%>

</body>
</html>
