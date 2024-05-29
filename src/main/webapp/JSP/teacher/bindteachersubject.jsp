<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.model.Teacher" %>
<%@ page import="com.example.DAO.*" %>
<%@ page import="com.example.model.Subject" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert a New Teacher</title>

    <!-- Import Bootstrap CSS from CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <!-- Import custom CSS -->
    <link href="../../resources/css/styles.css" rel="stylesheet">
    <link href="../../resources/css/teacher.css" rel="stylesheet">
</head>
<body>
<!-- Use Bootstrap classes to center and align items in the page -->
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div>
        <!-- Use Bootstrap class to center the title -->
        <h1 class="text-center">Bind Teacher to Subject</h1>

        <!-- Form to insert a new teacher -->
        <form method="post" action="bindteachersubject.jsp" class="text-center">
            <label for="teacherId">Teacher ID:</label>
            <input type="text" id="teacherId" name="teacherId" class="form-control" required><br>
            <label for="subjectId">Subject ID:</label>
            <input type="text" id="subjectId" name="subjectId" class="form-control" required><br>

            <input type="submit" value="Bind" class="btn btn-danger">
        </form>

        <!-- Button to return to the teacher actions page -->
        <form action="teacheraction.jsp" class="mt-3">
            <input type="submit" value="Return to Teacher Actions" class="btn btn-secondary d-block mx-auto">
        </form>

        <!-- Server-side code to insert a new teacher -->
        <%
            if ("POST".equalsIgnoreCase(request.getMethod())) {
                String teacherIdStr = request.getParameter("teacherId");
                String subjectIdStr = request.getParameter("subjectId");

                if (teacherIdStr != null && subjectIdStr != null) {
                    try {
                        int teacherId = Integer.parseInt(teacherIdStr);
                        int subjectId = Integer.parseInt(subjectIdStr);

                        TeachersDaoImpl teachersDao = new TeachersDaoImpl();
                        SubjectsDaoImpl subjectsDao = new SubjectsDaoImpl();
                        Teacher teacher = teachersDao.findById(teacherId);
                        Subject subject = subjectsDao.findById(subjectId);
                        if (teacher != null && subject != null) {
                            TeacherSubjectsDao teacherSubjectsDao= new TeacherSubjectsDaoImpl();
                            teacherSubjectsDao.insert(teacherId,subjectId);


                            out.println("<p class='text-center'>Teacher and subject are bound successfully.</p>");
                        } else {
                            if(teacher == null){
                                out.println("<p class='text-center text-danger'>No teacher found with ID " + teacherId + ".</p>");
                            }
                            if(subject == null){
                                out.println("<p class='text-center text-danger'>No subject found with ID " + subjectId + ".</p>");
                            }

                        }
                    } catch (NumberFormatException e) {
                        out.println("<p class='text-center text-danger'>Invalid input format. Please ensure the Teacher ID or Subject ID is correctly filled.</p>");
                    }
                } else {
                    out.println("<p class='text-center text-danger'>Please provide Teacher ID and Subject ID.</p>");
                }
            }
        %>
    </div>
</div>
</body>
</html>
