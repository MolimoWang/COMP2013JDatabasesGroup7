<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.SubjectsDaoImpl, com.example.model.Subject" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Subjects</title>

    <!-- Import Bootstrap CSS from CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<!-- Use Bootstrap classes to center and align items in the page -->
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div>
        <!-- Use Bootstrap class to center the title -->
        <h1 class="text-center">All Subjects</h1>

        <%
            SubjectsDaoImpl subjectsDao = new SubjectsDaoImpl();
            List<Subject> subjects = subjectsDao.findAll();

            if (subjects.isEmpty()) {
                out.println("<p class='text-center'>No subjects found.</p>");
            } else {
                out.println("<ul class='list-group text-center'>");
                for (Subject subject : subjects) {
                    out.println("<li class='list-group-item'>Subject ID: " + subject.getSubjectId() + ", Name: " + subject.getName() + "</li>");
                }
                out.println("</ul>");
            }
        %>

        <!-- Button to return to the subject actions page -->
        <form action="subjectaction.jsp" class="mt-3">
            <input type="submit" value="Return to Subject Actions" class="btn btn-secondary d-block mx-auto">
        </form>
    </div>
</div>
</body>
</html>