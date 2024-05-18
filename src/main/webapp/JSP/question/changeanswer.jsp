<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.AnswersDaoImpl, com.example.model.Answer" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Answer</title>
</head>
<body>
<h1>Update Answer for Question</h1>

<form method="post">
    <label for="questionId">Question ID:</label>
    <input type="text" id="questionId" name="questionId" required><br>
    <label for="answerText">New Answer Text:</label>
    <input type="text" id="answerText" name="answerText" required><br>

    <input type="submit" value="Update">
</form>

<%
    // 处理更新操作
    if ("POST".equalsIgnoreCase(request.getMethod())) {
        String questionIdStr = request.getParameter("questionId");
        int questionId = Integer.parseInt(questionIdStr);
        String newAnswerText = request.getParameter("answerText");

        // 通过问题ID查找答案，并更新
        AnswersDaoImpl answersDao = new AnswersDaoImpl();
        Answer answer = answersDao.findByQuestionId(questionId);

        if (answer != null) {
            // 创建新的 Answer 对象，包含新的答案文本和现有的答案ID
            Answer newAnswer = new Answer();
            newAnswer.setAnswerId(answer.getAnswerId());
            newAnswer.setText(newAnswerText);

            // 更新答案
            answersDao.update(newAnswer);
            out.println("<p>Answer updated successfully.</p>");
        } else {
            out.println("<p>No answer found for question ID: " + questionId + "</p>");
        }
    }
%>

<!-- Button to return to the viewquestion page -->
<form action="questionaction.jsp">
    <input type="submit" value="Return to Question action">
</form>

</body>
</html>





