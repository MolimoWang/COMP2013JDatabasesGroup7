<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.TeachersDaoImpl, com.example.model.Teacher" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert Teacher</title>
</head>
<body>
<h1>Insert a New Teacher</h1>
<form method="post" action="insertteacher.jsp">
    <label for="teacherId">Teacher ID:</label>
    <input type="text" id="teacherId" name="teacherId" required><br>

    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br>

    <label for="subjectId">Subject ID:</label>
    <input type="text" id="subjectId" name="subjectId" required><br>

    <input type="submit" value="Insert">
</form>
<br>
<form action="teacheraction.jsp">
    <input type="submit" value="Return to Teacher Actions">
</form>

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

                out.println("<p>Teacher inserted successfully.</p>");
            } catch (NumberFormatException e) {
                out.println("<p>Invalid input format. Please ensure all fields are correctly filled.</p>");
            }
        } else {
            out.println("<p>Please fill out all fields.</p>");
        }
    }
%>
</body>
</html>
