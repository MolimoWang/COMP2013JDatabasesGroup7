<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.StudentsDaoImpl, com.example.model.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.DAO.PersonDaoImpl" %>
<%@ page import="com.example.model.Person" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Show Students</title>

    <!-- Import Bootstrap CSS from CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <!-- Import custom CSS -->
    <link href="../../resources/css/styles.css" rel="stylesheet">
    <link href="../../resources/css/paper.css" rel="stylesheet">
</head>
<body>
<!-- Use Bootstrap classes to center and align items in the page -->
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div>
        <!-- Use Bootstrap class to center the title -->
        <h1 class="text-center">Students Associated with Paper</h1>

        <%
            String paperIdStr = request.getParameter("paperId");

            if (paperIdStr != null) {
                try {
                    int paperId = Integer.parseInt(paperIdStr);
                    StudentsDaoImpl studentsDao = new StudentsDaoImpl();
                    List<Student> students = studentsDao.findByPaperId(paperId);

                    if (students.isEmpty()) {
                        out.println("<p class='text-center'>No students found for Paper ID: " + paperId + "</p>");
                    } else {
                        out.println("<ul class='list-group text-center'>");
                        for (Student student : students) {
                            PersonDaoImpl personDao = new PersonDaoImpl();
                            Person person = personDao.findById(student.getPersonId());

                            out.println("<li class='list-group-item'>Student ID: " + student.getStudentId() + ", Name: " + person.getName() + "</li>");
                        }
                        out.println("</ul>");
                    }
                } catch (NumberFormatException e) {
                    out.println("<p class='text-center text-danger'>Invalid Paper ID format.</p>");
                }
            } else {
                out.println("<p class='text-center text-danger'>No Paper ID provided.</p>");
            }
        %>

        <!-- Button to return to the paper results page -->
        <form action="paperresult.jsp">
            <input type="hidden" name="title" value="<%= request.getParameter("title") %>">
            <input type="hidden" name="subject" value="<%= request.getParameter("subject") %>">
            <input type="hidden" name="year" value="<%= request.getParameter("year") %>">
            <input type="hidden" name="teacher" value="<%= request.getParameter("teacher") %>">
            <input type="submit" value="Return to Paper Results" class="btn btn-secondary d-block mx-auto">
        </form>
    </div>
</div>
</body>
</html>