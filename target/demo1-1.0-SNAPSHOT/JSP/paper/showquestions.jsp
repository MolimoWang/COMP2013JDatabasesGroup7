<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.QuestionsDaoImpl, com.example.model.Question, com.example.DAO.AnswersDaoImpl, com.example.model.Answer" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Questions and Answers</title>
</head>
<body>
<h1>Questions and Answers</h1>

<%
  String paperIdStr = request.getParameter("paperId");
  QuestionsDaoImpl questionsDao = new QuestionsDaoImpl();
  AnswersDaoImpl answersDao = new AnswersDaoImpl();

  if (paperIdStr != null) {
    int paperId = Integer.parseInt(paperIdStr);
    List<Question> questions = questionsDao.findByPaperId(paperId);

    if (questions.isEmpty()) {
      out.println("<p>No questions found for this paper.</p>");
    } else {
      out.println("<h2>Questions for Paper ID: " + paperId + "</h2>");
      out.println("<ul>");
      for (Question question : questions) {
        out.println("<li>Question ID: " + question.getQuestionId() + ", Text: " + question.getText());
        out.println("<form method='get' action='showquestions.jsp'>");
        out.println("<input type='hidden' name='questionId' value='" + question.getQuestionId() + "'>");
        out.println("<input type='hidden' name='paperId' value='" + paperId + "'>");
        out.println("<input type='submit' value='Show Answer'>");
        out.println("</form>");
        out.println("</li>");

        String questionIdStr = request.getParameter("questionId");
        if (questionIdStr != null && questionIdStr.equals(String.valueOf(question.getQuestionId()))) {
          int questionId = Integer.parseInt(questionIdStr);
          Answer answer = answersDao.findByQuestionId(questionId);
          if (answer != null) {
            out.println("<p>Answer: " + answer.getText() + "</p>");
          } else {
            out.println("<p>No answer found for this question.</p>");
          }
        }
      }
      out.println("</ul>");
    }
  } else {
    out.println("<p>Invalid paper ID.</p>");
  }
%>

<!-- Button to return to the previous page -->
<form action="paperresult.jsp">
  <input type="submit" value="Return to Paper Results">
</form>
</body>
</html>

