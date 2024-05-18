<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.TeachersDaoImpl" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Delete Teacher</title>
</head>
<body>
<h1>Delete a Teacher</h1>
<form method="post" action="deleteteacher.jsp">
  <label for="teacherId">Teacher ID:</label>
  <input type="text" id="teacherId" name="teacherId" required><br>

  <input type="submit" value="Delete">
</form>
<br>
<form action="teacheraction.jsp">
  <input type="submit" value="Return to Teacher Actions">
</form>

<%
  if ("POST".equalsIgnoreCase(request.getMethod())) {
    String teacherIdStr = request.getParameter("teacherId");

    if (teacherIdStr != null) {
      try {
        int teacherId = Integer.parseInt(teacherIdStr);

        TeachersDaoImpl teachersDao = new TeachersDaoImpl();
        teachersDao.deleteById(teacherId);

        out.println("<p>Teacher deleted successfully.</p>");
      } catch (NumberFormatException e) {
        out.println("<p>Invalid input format. Please enter a valid Teacher ID.</p>");
      }
    } else {
      out.println("<p>Please fill out the Teacher ID field.</p>");
    }
  }
%>
</body>
</html>

