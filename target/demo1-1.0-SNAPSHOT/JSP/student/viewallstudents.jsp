<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.StudentsDaoImpl, com.example.model.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.DAO.PersonDaoImpl" %>
<%@ page import="com.example.model.Person" %>
<%@ page import="com.example.DAO.StudentPapersDaoImpl" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>All Students</title>

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
        <h1 class="text-center">All Students</h1>

        <%
          StudentsDaoImpl studentsDao = new StudentsDaoImpl();
          List<Student> students;
          int pageNum = 1;
          int count = 5;

          if (request.getParameter("pageNum") != null) {
              pageNum = Integer.parseInt(request.getParameter("pageNum"));
          }

          students = studentsDao.findWithPagination((pageNum - 1) * count, count);

          if (students.isEmpty()) {
            out.println("<p class='mt-4 text-center'>No students found.</p>");
          } else {
            out.println("<ul class='list-group list-group-flush text-center mt-3'>");
            for (Student student : students) {
              PersonDaoImpl personDao = new PersonDaoImpl();
              Person person = personDao.findById(student.getPersonId());

              StudentPapersDaoImpl studentPapersDao = new StudentPapersDaoImpl();
              List<Integer> paperIds = studentPapersDao.findPaperIdsByStudentId(student.getStudentId());

              out.println("<li class='list-group-item'>");
              out.println("Student ID: " + student.getStudentId() + ", Name: " + person.getName() + "<br>");
              out.println("Paper IDs: " + paperIds.toString());
              out.println("</li>");

            }
            out.println("</ul>");
          }

          int totalStudents = studentsDao.count();
          int totalPages = (int) Math.ceil((double) totalStudents / count);

          out.println("<nav aria-label='Page navigation example'>");
          out.println("<ul class='pagination justify-content-center'>");
          for (int i = 1; i <= totalPages; i++) {
              if (i == pageNum) {
                  out.println("<li class='page-item active'><a class='page-link' href='?pageNum=" + i + "'>" + i + "</a></li>");
              } else {
                  out.println("<li class='page-item'><a class='page-link' href='?pageNum=" + i + "'>" + i + "</a></li>");
              }
          }
          out.println("</ul>");
          out.println("</nav>");
        %>

        <!-- Button to return to the student actions page -->
        <form action="studentaction.jsp" class="mt-3">
          <input type="submit" value="Return to Student Actions" class="btn btn-secondary d-block mx-auto">
        </form>
    </div>
</div>
</body>
</html>