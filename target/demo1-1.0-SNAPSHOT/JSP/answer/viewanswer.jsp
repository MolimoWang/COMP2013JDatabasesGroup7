<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.AnswersDaoImpl, com.example.model.Answer" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Answer</title>

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
        <h1 class="text-center">View Answer</h1>
        <!-- Use Bootstrap classes to style the form -->
        <form method="post" action="viewanswer.jsp" class="text-center">
            <label for="answerId">Answer ID:</label>
            <input type="text" id="answerId" name="answerId" class="form-control"><br>

            <label for="questionId">Question ID:</label>
            <input type="text" id="questionId" name="questionId" class="form-control"><br>

            <input type="submit" value="Search" class="btn btn-primary">
        </form>
        <br>
        <!-- Use Bootstrap classes to style the button -->
        <form action="answeraction.jsp">
            <input type="submit" value="Return to Answer Actions" class="btn btn-secondary d-block mx-auto">
        </form>
    </div>
</div>

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
                out.println("<p class='text-center text-danger'>Invalid Answer ID format. Please enter a valid integer.</p>");
            }
        } else if (questionIdStr != null && !questionIdStr.isEmpty()) {
            try {
                int questionId = Integer.parseInt(questionIdStr);
                answer = answersDao.findByQuestionId(questionId);
            } catch (NumberFormatException e) {
                out.println("<p class='text-center text-danger'>Invalid Question ID format. Please enter a valid integer.</p>");
            }
        }

        if (answer != null) {
            out.println("<h2 class='text-center'>Answer Details:</h2>");
            out.println("<p class='text-center'>Answer ID: " + answer.getAnswerId() + "</p>");
            out.println("<p class='text-center'>Text: " + answer.getText() + "</p>");
        } else {
            out.println("<p class='text-center text-danger'>No answer found with the given criteria.</p>");
        }
    }
%>
</body>
</html>