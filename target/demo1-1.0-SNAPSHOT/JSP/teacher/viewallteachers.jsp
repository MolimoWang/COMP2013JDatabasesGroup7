<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.TeachersDaoImpl, com.example.model.Teacher" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View All Teachers</title>

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
        <h1 class="text-center">All Teachers</h1>

        <%
            TeachersDaoImpl teachersDao = new TeachersDaoImpl();
            List<Teacher> teachers = teachersDao.findAll();

            if (teachers.isEmpty()) {
                out.println("<p class='mt-4 text-center'>No teachers found.</p>");
            } else {
                out.println("<ul class='list-group mt-4'>");
                for (Teacher teacher : teachers) {
                    out.println("<li class='list-group-item'>Teacher ID: " + teacher.getTeacherId() + ", Name: " + teacher.getName() + ", Subject ID: " + teacher.getSubjectId() + "</li>");
                }
                out.println("</ul>");
            }
        %>

        <!-- Button to return to the teacher actions page -->
        <form action="teacheraction.jsp" class="mt-3">
            <input type="submit" value="Return to Teacher Actions" class="btn btn-secondary d-block mx-auto">
        </form>
    </div>
</div>
</body>
</html>