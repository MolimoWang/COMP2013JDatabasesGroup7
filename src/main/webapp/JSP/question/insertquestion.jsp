<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.QuestionsDaoImpl, com.example.model.Question" %>
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
        <h1 class="text-center">Insert a New Question</h1>

        <form method="post" action="insertquestion.jsp" class="text-center">
            <label for="questionId">Question ID:</label>
            <input type="text" id="questionId" name="questionId" class="form-control" required><br>

            <label for="paperId">Paper ID:</label>
            <input type="text" id="paperId" name="paperId" class="form-control" required><br>

            <label for="text">Text:</label>
            <input type="text" id="text" name="text" class="form-control" required><br>

            <input type="submit" value="Insert" class="btn btn-primary">
        </form>
        <br>
        <form action="questionaction.jsp">
            <input type="submit" value="Return to Question Actions" class="btn btn-secondary d-block mx-auto">
        </form>

        <%
            // Process the insert operation
            if ("POST".equalsIgnoreCase(request.getMethod())) {
                String questionIdStr = request.getParameter("questionId");
                String paperIdStr = request.getParameter("paperId");
                String text = request.getParameter("text");

                if (questionIdStr != null && paperIdStr != null && text != null) {
                    try {
                        int questionId = Integer.parseInt(questionIdStr);
                        int paperId = Integer.parseInt(paperIdStr);

                        Question question = new Question();
                        question.setQuestionId(questionId);
                        question.setPaperId(paperId);
                        question.setText(text);

                        QuestionsDaoImpl questionsDao = new QuestionsDaoImpl();
                        boolean insertSuccess = questionsDao.insert(question);

                        if (insertSuccess) {
                            out.println("<p class='text-center'>Question inserted successfully.</p>");
                        } else {
                            out.println("<p class='text-center text-danger'>Failed to insert question. Please check your input.</p>");
                        }
                    } catch (NumberFormatException e) {
                        out.println("<p class='text-center text-danger'>Invalid input format. Please ensure all fields are correctly filled.</p>");
                    } catch (Exception e) {
                        out.println("<p class='text-center text-danger'>An error occurred. Please try again.</p>");
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