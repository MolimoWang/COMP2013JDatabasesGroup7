<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.TeachersDaoImpl, com.example.model.Teacher" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View All Teachers</title>
</head>
<body>
<h1>All Teachers</h1>

<%
    TeachersDaoImpl teachersDao = new TeachersDaoImpl();
    List<Teacher> teachers = teachersDao.findAll();

    if (teachers.isEmpty()) {
        out.println("<p>No teachers found.</p>");
    } else {
        out.println("<ul>");
        for (Teacher teacher : teachers) {
            out.println("<li>Teacher ID: " + teacher.getTeacherId() + ", Name: " + teacher.getName() + ", Subject ID: " + teacher.getSubjectId() + "</li>");
        }
        out.println("</ul>");
    }
%>

<!-- Button to return to the teacher actions page -->
<form action="teacheraction.jsp">
    <input type="submit" value="Return to Teacher Actions">
</form>
</body>
</html>
