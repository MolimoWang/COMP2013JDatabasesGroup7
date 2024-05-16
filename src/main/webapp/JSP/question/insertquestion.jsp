<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.QuestionsDaoImpl, com.example.model.Question" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert Question</title>
</head>
<body>
<h1>Insert a New Question</h1>
<form method="post" action="insertquestion.jsp">
    <label for="questionId">Question ID:</label>
    <input type="text" id="questionId" name="questionId" required><br>

    <label for="paperId">Paper ID:</label>
    <input type="text" id="paperId" name="paperId" required><br>

    <label for="text">Text:</label>
    <input type="text" id="text" name="text" required><br>

    <label for="answerId">Answer ID:</label>
    <input type="text" id="answerId" name="answerId" required><br>

    <input type="submit" value="Insert">
</form>
<br>
<form action="questionaction.jsp">
    <input type="submit" value="Return to Question Actions">
</form>

<%
    if ("POST".equalsIgnoreCase(request.getMethod())) {
        String questionIdStr = request.getParameter("questionId");
        String paperIdStr = request.getParameter("paperId");
        String text = request.getParameter("text");
        String answerIdStr = request.getParameter("answerId");

        if (questionIdStr != null && paperIdStr != null && text != null && answerIdStr != null) {
            try {
                int questionId = Integer.parseInt(questionIdStr);
                int paperId = Integer.parseInt(paperIdStr);
                int answerId = Integer.parseInt(answerIdStr);

                Question question = new Question();
                question.setQuestionId(questionId);
                question.setPaperId(paperId);
                question.setText(text);
                question.setAnswerId(answerId);

                QuestionsDaoImpl questionsDao = new QuestionsDaoImpl();
                questionsDao.insert(question);

                out.println("<p>Question inserted successfully.</p>");
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

