<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.TeachersDaoImpl, com.example.model.Teacher" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Teacher</title>

    <!-- Import Bootstrap CSS from CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <!-- Import custom CSS -->
    <link href="../../resources/css/styles.css" rel="stylesheet">
    <link href="../../resources/css/teacher.css" rel="stylesheet">
</head>
<body>
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div>
        <!-- Title of the page -->
        <h1 class="text-center">View Teacher</h1>

        <!-- Form to search for a teacher -->
        <form method="post" action="viewteacher.jsp" class="mt-4">
            <div class="mb-3">
                <label for="teacherId" class="form-label">Teacher ID:</label>
                <input type="text" id="teacherId" name="teacherId" class="form-control">
            </div>
            <input type="submit" value="Search" class="btn btn-primary d-block mx-auto">
        </form>

        <!-- Button to return to the teacher actions page -->
        <form action="teacheraction.jsp" class="mt-3">
            <input type="submit" value="Return to Teacher Actions" class="btn btn-secondary d-block mx-auto">
        </form>

        <!-- Server-side code to search for a teacher -->
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
                        out.println("<div class='alert alert-danger mt-3'>Invalid Teacher ID format. Please enter a valid integer.</div>");
                    }
                }

                if (teacher != null) {
                    out.println("<h2 class='mt-4'>Teacher Details:</h2>");
                    out.println("<p>Teacher ID: " + teacher.getTeacherId() + "</p>");
                    out.println("<p>Name: " + teacher.getName() + "</p>");
                    out.println("<p>Subject ID: " + teacher.getSubjectId() + "</p>");
                } else {
                    out.println("<div class='alert alert-warning mt-3'>No teacher found with the given criteria.</div>");
                }
            }
        %>
    </div>
</div>
</body>
</html>