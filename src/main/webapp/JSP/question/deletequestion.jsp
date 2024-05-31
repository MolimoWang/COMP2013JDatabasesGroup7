<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.QuestionsDaoImpl" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Question</title>

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
        <h1 class="text-center">Delete a Question</h1>

        <form method="post" class="text-center">
            <label for="questionId">Question ID:</label>
            <input type="text" id="questionId" name="questionId" class="form-control" required><br>

            <input type="submit" value="Delete" class="btn btn-danger">
        </form>

        <%
            // Process the delete operation
            if ("POST".equalsIgnoreCase(request.getMethod())) {
                String questionIdStr = request.getParameter("questionId");

                if (questionIdStr != null) {
                    try {
                        int questionId = Integer.parseInt(questionIdStr);

                        QuestionsDaoImpl questionsDao = new QuestionsDaoImpl();
                        questionsDao.deleteById(questionId);

                        out.println("<p class='text-center'>Question deleted successfully.</p>");
                    } catch (NumberFormatException e) {
                        out.println("<p class='text-center text-danger'>Invalid input format. Please enter a valid Question ID.</p>");
                    } catch (Exception e) {
                        out.println("<p class='text-center text-danger'>Error occurred while deleting the question. Please try again later.</p>");
                        e.printStackTrace();
                } else {
                    out.println("<p class='text-center text-danger'>Please fill out the Question ID field.</p>");
                }
            }
        %>

        <!-- Button to return to the question actions page -->
        <form action="questionaction.jsp" class="mt-3">
            <input type="submit" value="Return to Question Actions" class="btn btn-secondary d-block mx-auto">
        </form>
    </div>
</div>
</body>
</html>