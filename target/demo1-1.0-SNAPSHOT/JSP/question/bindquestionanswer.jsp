<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.QuestionsDaoImpl, com.example.model.Question" %>
<%@ page import="com.example.model.Answer" %>
<%@ page import="com.example.DAO.AnswersDaoImpl" %>
<%@ page import="com.example.DAO.QuestionAnswersDaoImpl" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert Question</title>

    <!-- Import Bootstrap CSS from CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <!-- Import custom CSS -->
    <link href="../../resources/css/styles.css" rel="stylesheet">
    <link href="../../resources/css/question.css" rel="stylesheet">
</head>
<body>
<!-- Use Bootstrap classes to center and align items in the page -->
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div>
        <!-- Use Bootstrap class to center the title -->
        <h1 class="text-center">Bind Question to Answer</h1>

        <form method="post" action="bindquestionanswer.jsp" class="text-center">
            <label for="questionId">Question ID:</label>
            <input type="text" id="questionId" name="questionId" class="form-control" required><br>
            <label for="answerId">Answer ID:</label>
            <input type="text" id="answerId" name="answerId" class="form-control" required><br>

            <input type="submit" value="Bind" class="btn btn-danger">
        </form>
        <br>
        <br>
        <form action="questionaction.jsp">
            <input type="submit" value="Return to Question Actions" class="btn btn-secondary d-block mx-auto">
        </form>

        <%
            // Process the insert operation
            if ("POST".equalsIgnoreCase(request.getMethod())) {
                String questionIdStr = request.getParameter("questionId");
                String answerIdStr = request.getParameter("answerId");

                if (questionIdStr != null  && answerIdStr != null) {
                    try {
                        int questionId = Integer.parseInt(questionIdStr);
                        int answerId = Integer.parseInt(answerIdStr);
                        QuestionsDaoImpl questionsDao = new QuestionsDaoImpl();
                        AnswersDaoImpl answersDao = new AnswersDaoImpl();
                        Question question = new Question();
                        question.setQuestionId(questionId);
                        Answer answer = new Answer();
                        answer.setAnswerId(answerId);
                        QuestionAnswersDaoImpl questionAnswersDao = new QuestionAnswersDaoImpl();
                        questionAnswersDao.insert(questionId,answerId);
                        out.println("<p class='text-center'>Question inserted successfully.</p>");
                    } catch (NumberFormatException e) {
                        out.println("<p class='text-center text-danger'>Invalid input format. Please ensure all fields are correctly filled.</p>");
                    }
                } else {
                    out.println("<p class='text-center text-danger'>Please fill out all fields.</p>");
                }
            }
        %>
    </div>
</div>
</body>
</html>