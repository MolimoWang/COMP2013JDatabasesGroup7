<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.QuestionsDaoImpl" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Question</title>
</head>
<body>
<h1>Delete a Question</h1>
<form method="post" action="deletequestion.jsp">
    <label for="questionId">Question ID:</label>
    <input type="text" id="questionId" name="questionId" required><br>

    <input type="submit" value="Delete">
</form>
<br>
<form action="questionaction.jsp">
    <input type="submit" value="Return to Question Actions">
</form>

<%
    if ("POST".equalsIgnoreCase(request.getMethod())) {
        String questionIdStr = request.getParameter("questionId");

        if (questionIdStr != null) {
            try {
                int questionId = Integer.parseInt(questionIdStr);

                QuestionsDaoImpl questionsDao = new QuestionsDaoImpl();
                questionsDao.deleteById(questionId);

                out.println("<p>Question deleted successfully.</p>");
            } catch (NumberFormatException e) {
                out.println("<p>Invalid input format. Please enter a valid Question ID.</p>");
            }
        } else {
            out.println("<p>Please fill out the Question ID field.</p>");
        }
    }
%>
</body>
</html>

