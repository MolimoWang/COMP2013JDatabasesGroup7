<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.AnswersDaoImpl, com.example.model.Answer" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Answer</title>
</head>
<body>
<h1>View Answer</h1>
<form method="post" action="viewanswer.jsp">
    <label for="answerId">Answer ID:</label>
    <input type="text" id="answerId" name="answerId"><br>

    <label for="questionId">Question ID:</label>
    <input type="text" id="questionId" name="questionId"><br>

    <input type="submit" value="Search">
</form>
<br>
<form action="answeraction.jsp">
    <input type="submit" value="Return to Answer Actions">
</form>

<%
    if ("POST".equalsIgnoreCase(request.getMethod())) {
        String answerIdStr = request.getParameter("answerId");
        String questionIdStr = request.getParameter("questionId");

        AnswersDaoImpl answersDao = new AnswersDaoImpl();
        Answer answer = null;

        if (answerIdStr != null && !answerIdStr.isEmpty()) {
            try {
                int answerId = Integer.parseInt(answerIdStr);
                answer = answersDao.findById(answerId);
            } catch (NumberFormatException e) {
                out.println("<p>Invalid Answer ID format. Please enter a valid integer.</p>");
            }
        } else if (questionIdStr != null && !questionIdStr.isEmpty()) {
            try {
                int questionId = Integer.parseInt(questionIdStr);
                answer = answersDao.findByQuestionId(questionId);
            } catch (NumberFormatException e) {
                out.println("<p>Invalid Question ID format. Please enter a valid integer.</p>");
            }
        }

        if (answer != null) {
            out.println("<h2>Answer Details:</h2>");
            out.println("<p>Answer ID: " + answer.getAnswerId() + "</p>");
            out.println("<p>Text: " + answer.getText() + "</p>");
        } else {
            out.println("<p>No answer found with the given criteria.</p>");
        }
    }
%>
</body>
</html>

