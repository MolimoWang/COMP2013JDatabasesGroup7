<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.TeachersDaoImpl, com.example.model.Teacher" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>View Teacher</title>
</head>
<body>
<h1>View Teacher</h1>
<form method="post" action="viewteacher.jsp">
  <label for="teacherId">Teacher ID:</label>
  <input type="text" id="teacherId" name="teacherId"><br>

  <input type="submit" value="Search">
</form>
<br>
<form action="teacheraction.jsp">
  <input type="submit" value="Return to Teacher Actions">
</form>

<%
  if ("POST".equalsIgnoreCase(request.getMethod())) {
    String teacherIdStr = request.getParameter("teacherId");

    TeachersDaoImpl teachersDao = new TeachersDaoImpl();
    Teacher teacher = null;

    if (teacherIdStr != null && !teacherIdStr.isEmpty()) {
      try {
        int teacherId = Integer.parseInt(teacherIdStr);
        teacher = teachersDao.findById(teacherId);
      } catch (NumberFormatException e) {
        out.println("<p>Invalid Teacher ID format. Please enter a valid integer.</p>");
      }
    }

    if (teacher != null) {
      out.println("<h2>Teacher Details:</h2>");
      out.println("<p>Teacher ID: " + teacher.getTeacherId() + "</p>");
      out.println("<p>Name: " + teacher.getName() + "</p>");
      out.println("<p>Subject ID: " + teacher.getSubjectId() + "</p>");
    } else {
      out.println("<p>No teacher found with the given criteria.</p>");
    }
  }
%>
</body>
</html>
