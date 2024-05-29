<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.model.Student, com.example.DAO.StudentsDaoImpl" %>
<%@ page import="com.example.DAO.PersonDaoImpl" %>
<%@ page import="com.example.model.Person" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.DAO.StudentPapersDaoImpl" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Student</title>

    <!-- Import Bootstrap CSS from CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <!-- Import custom CSS -->
    <link href="../../resources/css/styles.css" rel="stylesheet">
    <link href="../../resources/css/student.css" rel="stylesheet">
</head>
<body>
<!-- Use Bootstrap classes to center and align items in the page -->
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div>
        <!-- Use Bootstrap class to center the title -->
        <h1 class="text-center">View Student</h1>

        <form method="get" action="viewstudent.jsp" class="text-center mt-3">
            <label for="studentId">Student ID:</label>
            <input type="text" id="studentId" name="studentId" class="form-control" required>
            <input type="submit" value="View Student" class="btn btn-primary mt-3">
        </form>
        <form action="studentaction.jsp" class="mt-3">
            <input type="submit" value="Return to Student Actions" class="btn btn-secondary d-block mx-auto">
        </form>

        <%
            String studentIdStr = request.getParameter("studentId");

            if (studentIdStr != null) {
                try {
                    int studentId = Integer.parseInt(studentIdStr);
                    StudentsDaoImpl studentsDao = new StudentsDaoImpl();
                    Student student = studentsDao.findById(studentId);

                    if (student != null) {
                        PersonDaoImpl personDao = new PersonDaoImpl();
                        Person person = personDao.findById(student.getStudentId());

                        StudentPapersDaoImpl studentPapersDao = new StudentPapersDaoImpl();
                        List<Integer> paperIds = studentPapersDao.findPaperIdsByStudentId(student.getStudentId());

                        out.println("<p class='text-center mt-3'>Student ID: " + student.getStudentId() + "</p>");
                        out.println("<p class='text-center'>Name: " + person.getName() + "</p>");
                        out.println("<p class='text-center'>Paper ID: " + paperIds.toString() + "</p>");
                    } else {
                        out.println("<p class='text-center text-danger mt-3'>No student found with ID " + studentId + ".</p>");
                    }
                } catch (NumberFormatException e) {
                    out.println("<p class='text-center text-danger mt-3'>Invalid input format. Please ensure the Student ID is correctly filled.</p>");
                }
            } else {
                out.println("<p class='text-center text-danger mt-3'>Please provide a Student ID.</p>");
            }
        %>
    </div>
</div>
</body>
</html>