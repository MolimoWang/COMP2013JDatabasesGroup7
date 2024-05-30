<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.QuestionsDaoImpl, com.example.model.Question" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Question</title>

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
        <h1 class="text-center">View Question</h1>

        <form method="post" action="viewquestion.jsp" class="text-center">
            <label for="questionId">Question ID:</label>
            <input type="text" id="questionId" name="questionId" class="form-control" required><br>

            <input type="submit" value="Search" class="btn btn-primary">
        </form>
        <br>
        <form action="questionaction.jsp">
            <input type="submit" value="Return to Question Actions" class="btn btn-secondary d-block mx-auto">
        </form>

        <%
            // Process the search operation
            if ("POST".equalsIgnoreCase(request.getMethod())) {
                String questionIdStr = request.getParameter("questionId");

                QuestionsDaoImpl questionsDao = new QuestionsDaoImpl();
                Question question = null;
                if (questionIdStr != null && !questionIdStr.isEmpty()) {
                    try {
                        int questionId = Integer.parseInt(questionIdStr);
                        question = questionsDao.findById(questionId);
                    } catch (NumberFormatException e) {
                        out.println("<p class='text-center text-danger'>Invalid Question ID format. Please enter a valid integer.</p>");
                    }
                }

                if (question != null) {
                    out.println("<h2 class='text-center'>Question Details:</h2>");
                    out.println("<p class='text-center'>Question ID: " + question.getQuestionId() + "</p>");
                    out.println("<p class='text-center'>Paper ID: " + question.getPaperId() + "</p>");
                    out.println("<p class='text-center'>Text: " + question.getText() + "</p>");
                    if (question.getAnswerId() != null) {
                        out.println("<p class='text-center'>Answer ID: " + question.getAnswerId() + "</p>");
                    } else {
                        out.println("<p class='text-center'>Answer ID: N/A</p>");
                    }
                } else {
                    out.println("<p class='text-center text-danger'>No question found with the given criteria.</p>");
                }
            }
        %>

    </div>
</div>
</body>
</html>
