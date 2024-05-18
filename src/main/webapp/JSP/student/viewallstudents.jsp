<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.StudentsDaoImpl, com.example.model.Student" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>All Students</title>
</head>
<body>
<h1>All Students</h1>

<%
  StudentsDaoImpl studentsDao = new StudentsDaoImpl();
  List<Student> students = studentsDao.findAll();

  if (students.isEmpty()) {
    out.println("<p>No students found.</p>");
  } else {
    out.println("<ul>");
    for (Student student : students) {
      out.println("<li>Student ID: " + student.getStudentId() + ", Name: " + student.getName() + ", Paper ID: " + student.getPaperId() + "</li>");
    }
    out.println("</ul>");
  }
%>

<!-- Button to return to the student actions page -->
<form action="studentaction.jsp">
  <input type="submit" value="Return to Student Actions">
</form>
</body>
</html>
