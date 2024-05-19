<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.StudentsDaoImpl, com.example.DAO.PapersDaoImpl, com.example.model.Student" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Student</title>

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
        <h1 class="text-center">Delete a Student</h1>

        <form method="post" action="deletestudent.jsp" class="text-center">
            <label for="studentId">Student ID:</label>
            <input type="text" id="studentId" name="studentId" class="form-control" required><br>

            <input type="submit" value="Delete" class="btn btn-danger">
        </form>
        <br>
        <!-- Button to return to the student actions page -->
        <form action="studentaction.jsp" class="mt-3">
            <input type="submit" value="Return to Student Actions" class="btn btn-secondary d-block mx-auto">
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

                            out.println("<p class='text-center'>Student and associated paper deleted successfully.</p>");
                        } else {
                            out.println("<p class='text-center text-danger'>No student found with ID " + studentId + ".</p>");
                        }
                    } catch (NumberFormatException e) {
                        out.println("<p class='text-center text-danger'>Invalid input format. Please ensure the Student ID is correctly filled.</p>");
                    }
                } else {
                    out.println("<p class='text-center text-danger'>Please provide a Student ID.</p>");
                }
            }
        %>
    </div>
</div>
</body>
</html>