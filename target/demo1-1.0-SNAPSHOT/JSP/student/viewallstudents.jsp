<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.StudentsDaoImpl, com.example.model.Student" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>All Students</title>

  <!-- Import Bootstrap CSS from CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<!-- Use Bootstrap classes to center and align items in the page -->
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div>
        <!-- Use Bootstrap class to center the title -->
        <h1 class="text-center">All Students</h1>

        <%
          StudentsDaoImpl studentsDao = new StudentsDaoImpl();
          List<Student> students = studentsDao.findAll();

          if (students.isEmpty()) {
            out.println("<p class='mt-4 text-center'>No students found.</p>");
          } else {
            out.println("<ul class='list-group list-group-flush text-center mt-3'>");
            for (Student student : students) {
              out.println("<li class='list-group-item'>Student ID: " + student.getStudentId() + ", Name: " + student.getName() + ", Paper ID: " + student.getPaperId() + "</li>");
            }
            out.println("</ul>");
          }
        %>

        <!-- Button to return to the student actions page -->
        <form action="studentaction.jsp" class="mt-3">
          <input type="submit" value="Return to Student Actions" class="btn btn-secondary d-block mx-auto">
        </form>
    </div>
</div>
</body>
</html>