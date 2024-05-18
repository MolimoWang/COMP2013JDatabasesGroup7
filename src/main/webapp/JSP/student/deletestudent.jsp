<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.StudentsDaoImpl, com.example.DAO.PapersDaoImpl, com.example.model.Student" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Student</title>
</head>
<body>
<h1>Delete a Student</h1>
<form method="post" action="deletestudent.jsp">
    <label for="studentId">Student ID:</label>
    <input type="text" id="studentId" name="studentId" required><br>

    <input type="submit" value="Delete">
</form>
<br>
<form action="studentaction.jsp">
    <input type="submit" value="Return to Student Actions">
</form>

<%
    if ("POST".equalsIgnoreCase(request.getMethod())) {
        String studentIdStr = request.getParameter("studentId");

        if (studentIdStr != null) {
            try {
                int studentId = Integer.parseInt(studentIdStr);

                StudentsDaoImpl studentsDao = new StudentsDaoImpl();
                Student student = studentsDao.findById(studentId);
                if (student != null) {
                    int paperId = student.getPaperId();

                    // Delete the student
                    studentsDao.deleteById(studentId);

                    // Delete the associated paper
                    PapersDaoImpl papersDao = new PapersDaoImpl();
                    papersDao.deleteById(paperId);

                    out.println("<p>Student and associated paper deleted successfully.</p>");
                } else {
                    out.println("<p>No student found with ID " + studentId + ".</p>");
                }
            } catch (NumberFormatException e) {
                out.println("<p>Invalid input format. Please ensure the Student ID is correctly filled.</p>");
            }
        } else {
            out.println("<p>Please provide a Student ID.</p>");
        }
    }
%>
</body>
</html>

