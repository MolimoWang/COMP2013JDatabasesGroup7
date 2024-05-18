<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.TeachersDaoImpl" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Delete Teacher</title>

  <!-- Import Bootstrap CSS from CDN -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div>
        <!-- Title of the page -->
        <h1 class="text-center">Delete a Teacher</h1>

        <!-- Form to delete a teacher -->
        <form method="post" action="deleteteacher.jsp" class="mt-4">
            <div class="mb-3">
                <label for="teacherId" class="form-label">Teacher ID:</label>
                <input type="text" id="teacherId" name="teacherId" class="form-control" required>
            </div>
            <input type="submit" value="Delete" class="btn btn-primary d-block mx-auto">
        </form>

        <!-- Button to return to the teacher actions page -->
        <form action="teacheraction.jsp" class="mt-3">
            <input type="submit" value="Return to Teacher Actions" class="btn btn-secondary d-block mx-auto">
        </form>

        <!-- Server-side code to delete a teacher -->
        <%
            if ("POST".equalsIgnoreCase(request.getMethod())) {
                String teacherIdStr = request.getParameter("teacherId");

                if (teacherIdStr != null) {
                    try {
                        int teacherId = Integer.parseInt(teacherIdStr);

                        TeachersDaoImpl teachersDao = new TeachersDaoImpl();
                        teachersDao.deleteById(teacherId);

                        out.println("<div class='alert alert-success mt-3'>Teacher deleted successfully.</div>");
                    } catch (NumberFormatException e) {
                        out.println("<div class='alert alert-danger mt-3'>Invalid input format. Please enter a valid Teacher ID.</div>");
                    }
                } else {
                    out.println("<div class='alert alert-warning mt-3'>Please fill out the Teacher ID field.</div>");
                }
            }
        %>
    </div>
</div>
</body>
</html>