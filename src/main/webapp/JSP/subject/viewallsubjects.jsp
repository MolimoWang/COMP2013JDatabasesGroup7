<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.SubjectsDaoImpl, com.example.model.Subject" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Subjects</title>
</head>
<body>
<h1>All Subjects</h1>

<%
    SubjectsDaoImpl subjectsDao = new SubjectsDaoImpl();
    List<Subject> subjects = subjectsDao.findAll();

    if (subjects.isEmpty()) {
        out.println("<p>No subjects found.</p>");
    } else {
        out.println("<ul>");
        for (Subject subject : subjects) {
            out.println("<li>Subject ID: " + subject.getSubjectId() + ", Name: " + subject.getName() + "</li>");
        }
        out.println("</ul>");
    }
%>

<!-- Button to return to the subject actions page -->
<form action="subjectaction.jsp">
    <input type="submit" value="Return to Subject Actions">
</form>
</body>
</html>

