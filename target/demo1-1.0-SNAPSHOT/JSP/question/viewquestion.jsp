<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.QuestionsDaoImpl, com.example.model.Question" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>View Question</title>
</head>
<body>
<h1>View Question</h1>
<form method="post" action="viewquestion.jsp">
  <label for="questionId">Question ID:</label>
  <input type="text" id="questionId" name="questionId"><br>

  <input type="submit" value="Search">
</form>
<br>
<form action="questionaction.jsp">
  <input type="submit" value="Return to Question Actions">
</form>

<%
  if ("POST".equalsIgnoreCase(request.getMethod())) {
    String questionIdStr = request.getParameter("questionId");

    QuestionsDaoImpl questionsDao = new QuestionsDaoImpl();
    Question question = null;

    if (questionIdStr != null && !questionIdStr.isEmpty()) {
      try {
        int questionId = Integer.parseInt(questionIdStr);
        question = questionsDao.findById(questionId);
      } catch (NumberFormatException e) {
        out.println("<p>Invalid Question ID format. Please enter a valid integer.</p>");
      }
    }

    if (question != null) {
      out.println("<h2>Question Details:</h2>");
      out.println("<p>Question ID: " + question.getQuestionId() + "</p>");
      out.println("<p>Paper ID: " + question.getPaperId() + "</p>");
      out.println("<p>Text: " + question.getText() + "</p>");
      out.println("<p>Answer ID: " + question.getAnswerId() + "</p>");
    } else {
      out.println("<p>No question found with the given criteria.</p>");
    }
  }
%>
</body>
</html>
