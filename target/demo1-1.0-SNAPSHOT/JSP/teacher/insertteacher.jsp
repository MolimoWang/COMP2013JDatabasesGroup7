<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.TeachersDaoImpl, com.example.model.Teacher" %>
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
        <h1 class="text-center">Insert a New Teacher</h1>

        <!-- Form to insert a new teacher -->
        <form method="post" action="insertteacher.jsp" class="mt-4">
            <!-- Input field for Teacher ID -->
            <div class="mb-3">
                <label for="teacherId" class="form-label">Teacher ID:</label>
                <input type="text" id="teacherId" name="teacherId" class="form-control" required>
            </div>
            <!-- Input field for Name -->
            <div class="mb-3">
                <label for="name" class="form-label">Name:</label>
                <input type="text" id="name" name="name" class="form-control" required>
            </div>
            <!-- Input field for Subject ID -->
            <div class="mb-3">
                <label for="subjectId" class="form-label">Subject ID:</label>
                <input type="text" id="subjectId" name="subjectId" class="form-control" required>
            </div>
            <!-- Submit button -->
            <input type="submit" value="Insert" class="btn btn-primary d-block mx-auto">
        </form>

        <!-- Button to return to the teacher actions page -->
        <form action="teacheraction.jsp" class="mt-3">
            <input type="submit" value="Return to Teacher Actions" class="btn btn-secondary d-block mx-auto">
        </form>

        <!-- Server-side code to insert a new teacher -->
        <%
            if ("POST".equalsIgnoreCase(request.getMethod())) {
                String teacherIdStr = request.getParameter("teacherId");
                String name = request.getParameter("name");
                String subjectIdStr = request.getParameter("subjectId");

                if (teacherIdStr != null && name != null && subjectIdStr != null) {
                    try {
                        int teacherId = Integer.parseInt(teacherIdStr);
                        int subjectId = Integer.parseInt(subjectIdStr);

                        Teacher teacher = new Teacher();
                        teacher.setTeacherId(teacherId);
                        teacher.setName(name);
                        teacher.setSubjectId(subjectId);

                        TeachersDaoImpl teachersDao = new TeachersDaoImpl();
                        teachersDao.insert(teacher);

                        out.println("<div class='alert alert-success mt-3'>Teacher inserted successfully.</div>");
                    } catch (NumberFormatException e) {
                        out.println("<div class='alert alert-danger mt-3'>Invalid input format. Please ensure all fields are correctly filled.</div>");
                    }
                } else {
                    out.println("<div class='alert alert-warning mt-3'>Please fill out all fields.</div>");
                }
            }
        %>
    </div>
</div>
</body>
</html>