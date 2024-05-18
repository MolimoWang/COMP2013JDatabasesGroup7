<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.StudentsDaoImpl, com.example.model.Student" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert Student</title>
</head>
<body>
<h1>Insert a New Student</h1>
<form method="post" action="insertstudent.jsp">
    <label for="studentId">Student ID:</label>
    <input type="text" id="studentId" name="studentId" required><br>

    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br>

    <label for="paperId">Paper ID:</label>
    <input type="text" id="paperId" name="paperId" required><br>

    <input type="submit" value="Insert">
</form>
<br>
<form action="studentaction.jsp">
    <input type="submit" value="Return to Student Actions">
</form>

<%
    if ("POST".equalsIgnoreCase(request.getMethod())) {
        String studentIdStr = request.getParameter("studentId");
        String name = request.getParameter("name");
        String paperIdStr = request.getParameter("paperId");

        if (studentIdStr != null && name != null && paperIdStr != null) {
            try {
                int studentId = Integer.parseInt(studentIdStr);
                int paperId = Integer.parseInt(paperIdStr);

                Student student = new Student();
                student.setStudentId(studentId);
                student.setName(name);
                student.setPaperId(paperId);

                StudentsDaoImpl studentsDao = new StudentsDaoImpl();
                studentsDao.insert(student);

                out.println("<p>Student inserted successfully.</p>");
            } catch (NumberFormatException e) {
                out.println("<p>Invalid input format. Please ensure all fields are correctly filled.</p>");
            }
        } else {
            out.println("<p>Please fill out all fields.</p>");
        }
    }
%>
</body>
</html>

