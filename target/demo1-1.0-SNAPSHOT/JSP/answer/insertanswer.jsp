<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.AnswersDaoImpl, com.example.model.Answer" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert Answer</title>
</head>
<body>
<h1>Insert a New Answer</h1>
<form method="post" action="insertanswer.jsp">
  <label for="answerId">Answer ID:</label>
  <input type="text" id="answerId" name="answerId" required><br>

  <label for="text">Text:</label>
  <input type="text" id="text" name="text" required><br>

  <input type="submit" value="Insert">
</form>
<br>
<form action="answeraction.jsp">
  <input type="submit" value="Return to Answer Actions">
</form>

<%
  if ("POST".equalsIgnoreCase(request.getMethod())) {
    String answerIdStr = request.getParameter("answerId");
    String text = request.getParameter("text");

    if (answerIdStr != null && text != null && !text.isEmpty()) {
      try {
        int answerId = Integer.parseInt(answerIdStr);

        Answer answer = new Answer();
        answer.setAnswerId(answerId);
        answer.setText(text);

        AnswersDaoImpl answersDao = new AnswersDaoImpl();
        answersDao.insert(answer);

        out.println("<p>Answer inserted successfully.</p>");
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

