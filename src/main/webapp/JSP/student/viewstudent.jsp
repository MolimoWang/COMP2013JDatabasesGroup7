<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.model.Student, com.example.DAO.StudentsDaoImpl" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Student</title>
</head>
<body>
<h1>View Student</h1>

<form method="get" action="viewstudent.jsp">
    <label for="studentId">Student ID:</label>
    <input type="text" id="studentId" name="studentId" required>
    <input type="submit" value="View Student">
</form>
<br>
<form action="studentaction.jsp">
    <input type="submit" value="Return to Student Actions">
</form>

<%
    String studentIdStr = request.getParameter("studentId");

    if (studentIdStr != null) {
        try {
            int studentId = Integer.parseInt(studentIdStr);
            StudentsDaoImpl studentsDao = new StudentsDaoImpl();
            Student student = studentsDao.findById(studentId);

            if (student != null) {
                out.println("<p>Student ID: " + student.getStudentId() + "</p>");
                out.println("<p>Name: " + student.getName() + "</p>");
                out.println("<p>Paper ID: " + student.getPaperId() + "</p>");
            } else {
                out.println("<p>No student found with ID " + studentId + ".</p>");
            }
        } catch (NumberFormatException e) {
            out.println("<p>Invalid input format. Please ensure the Student ID is correctly filled.</p>");
        }
    } else {
        out.println("<p>Please provide a Student ID.</p>");
    }
%>
</body>
</html>

