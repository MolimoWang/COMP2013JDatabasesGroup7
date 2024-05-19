<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.AnswersDaoImpl, com.example.model.Answer" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Answer</title>

    <!-- Import Bootstrap CSS from CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <!-- Import custom CSS -->
    <link href="../../resources/css/styles.css" rel="stylesheet">
    <link href="../../resources/css/answer.css" rel="stylesheet">
</head>
<body>
<!-- Use Bootstrap classes to center and align items in the page -->
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div>
        <!-- Use Bootstrap class to center the title -->
        <h1 class="text-center">Update Answer for Question</h1>

        <form method="post" class="text-center">
            <label for="questionId">Question ID:</label>
            <input type="text" id="questionId" name="questionId" class="form-control" required><br>
            <label for="answerText">New Answer Text:</label>
            <input type="text" id="answerText" name="answerText" class="form-control" required><br>

            <input type="submit" value="Update" class="btn btn-primary">
        </form>

        <%
            // Process the update operation
            if ("POST".equalsIgnoreCase(request.getMethod())) {
                String questionIdStr = request.getParameter("questionId");
                int questionId = Integer.parseInt(questionIdStr);
                String newAnswerText = request.getParameter("answerText");

                // Find the answer by question ID and update it
                AnswersDaoImpl answersDao = new AnswersDaoImpl();
                Answer answer = answersDao.findByQuestionId(questionId);

                if (answer != null) {
                    // Create a new Answer object, containing the new answer text and the existing answer ID
                    Answer newAnswer = new Answer();
                    newAnswer.setAnswerId(answer.getAnswerId());
                    newAnswer.setText(newAnswerText);

                    // Update the answer
                    answersDao.update(newAnswer);
                    out.println("<p class='text-center'>Answer updated successfully.</p>");
                } else {
                    out.println("<p class='text-center'>No answer found for question ID: " + questionId + "</p>");
                }
            }
        %>

        <!-- Button to return to the viewquestion page -->
        <form action="questionaction.jsp" class="mt-3">
            <input type="submit" value="Return to Question action" class="btn btn-secondary d-block mx-auto">
        </form>
    </div>
</div>
</body>
</html>