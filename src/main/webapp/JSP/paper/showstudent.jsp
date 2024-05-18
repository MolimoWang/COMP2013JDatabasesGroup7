<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.StudentsDaoImpl, com.example.model.Student" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Show Students</title>
</head>
<body>
<h1>Students Associated with Paper</h1>

<%
    String paperIdStr = request.getParameter("paperId");

    if (paperIdStr != null) {
        try {
            int paperId = Integer.parseInt(paperIdStr);
            StudentsDaoImpl studentsDao = new StudentsDaoImpl();
            List<Student> students = studentsDao.findByPaperId(paperId);

            if (students.isEmpty()) {
                out.println("<p>No students found for Paper ID: " + paperId + "</p>");
            } else {
                out.println("<ul>");
                for (Student student : students) {
                    out.println("<li>Student ID: " + student.getStudentId() + ", Name: " + student.getName() + ", Paper ID: " + student.getPaperId() + "</li>");
                }
                out.println("</ul>");
            }
        } catch (NumberFormatException e) {
            out.println("<p>Invalid Paper ID format.</p>");
        }
    } else {
        out.println("<p>No Paper ID provided.</p>");
    }
%>

<!-- Button to return to the paper results page -->
<form action="paperresult.jsp">
    <input type="submit" value="Return to Paper Results">
</form>
</body>
</html>

