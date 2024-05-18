<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.SubjectsDaoImpl, com.example.model.Subject"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert Subject</title>
</head>
<body>
<h1>Insert a New Subject</h1>
<form method="post">
    <label for="subjectId">Subject ID:</label>
    <input type="text" id="subjectId" name="subjectId" required><br>

    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br>

    <input type="submit" value="Insert">
</form>
<br>
<form action="subjectaction.jsp">
    <input type="submit" value="Return to Subject Actions">
</form>

<%
    if ("POST".equalsIgnoreCase(request.getMethod())) {
        String subjectIdStr = request.getParameter("subjectId");
        String name = request.getParameter("name");

        if (subjectIdStr != null && name != null && !subjectIdStr.isEmpty() && !name.isEmpty()) {
            try {
                int subjectId = Integer.parseInt(subjectIdStr);

                Subject subject = new Subject();
                subject.setSubjectId(subjectId);
                subject.setName(name);

                SubjectsDaoImpl subjectsDao = new SubjectsDaoImpl();
                subjectsDao.insert(subject);

                out.println("<p>Subject inserted successfully.</p>");
            } catch (NumberFormatException e) {
                out.println("<p>Invalid input format. Please enter a valid integer for Subject ID.</p>");
            } catch (Exception e) {
                out.println("<p>Error occurred while inserting subject. Please try again later.</p>");
                e.printStackTrace();
            }
        } else {
            out.println("<p>Please fill out all fields.</p>");
        }
    }
%>
</body>
</html>
