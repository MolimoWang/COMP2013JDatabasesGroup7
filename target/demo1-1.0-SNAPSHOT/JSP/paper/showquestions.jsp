<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.QuestionsDaoImpl, com.example.model.Question, com.example.DAO.AnswersDaoImpl, com.example.model.Answer" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Questions and Answers</title>

  <!-- Import Bootstrap CSS from CDN -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <!-- Import custom CSS -->
    <link href="../../resources/css/styles.css" rel="stylesheet">
    <link href="../../resources/css/paper.css" rel="stylesheet">
</head>
<body>
<!-- Use Bootstrap classes to center and align items in the page -->
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div>
        <!-- Use Bootstrap class to center the title -->
        <h1 class="text-center">Questions and Answers</h1>

        <%
          String paperIdStr = request.getParameter("paperId");
          QuestionsDaoImpl questionsDao = new QuestionsDaoImpl();
          AnswersDaoImpl answersDao = new AnswersDaoImpl();

          if (paperIdStr != null) {
            int paperId = Integer.parseInt(paperIdStr);
            List<Question> questions = questionsDao.findByPaperId(paperId);

            if (questions.isEmpty()) {
              out.println("<p class='text-center'>No questions found for this paper.</p>");
            } else {
              out.println("<h2 class='text-center'>Questions for Paper ID: " + paperId + "</h2>");
              out.println("<ul class='list-group text-center'>");
              for (Question question : questions) {
                out.println("<li class='list-group-item'>Question ID: " + question.getQuestionId() + ", Text: " + question.getText());
                out.println("<form method='get' action='showquestions.jsp'>");
                out.println("<input type='hidden' name='questionId' value='" + question.getQuestionId() + "'>");
                out.println("<input type='hidden' name='paperId' value='" + paperId + "'>");
                out.println("<input type='submit' value='Show Answer' class='btn btn-info'>");
                out.println("</form>");
                out.println("</li>");

                String questionIdStr = request.getParameter("questionId");
                if (questionIdStr != null && questionIdStr.equals(String.valueOf(question.getQuestionId()))) {
                  int questionId = Integer.parseInt(questionIdStr);
                  Answer answer = answersDao.findByQuestionId(questionId);
                  if (answer != null) {
                    out.println("<p class='text-center'>Answer: " + answer.getText() + "</p>");
                  } else {
                    out.println("<p class='text-center'>No answer found for this question.</p>");
                  }
                }
              }
              out.println("</ul>");
            }
          } else {
            out.println("<p class='text-center'>Invalid paper ID.</p>");
          }
        %>

        <!-- Button to return to the previous page -->
        <form action="paperresult.jsp">
          <input type="submit" value="Return to Paper Results" class="btn btn-secondary d-block mx-auto">
        </form>
    </div>
</div>
</body>
</html>