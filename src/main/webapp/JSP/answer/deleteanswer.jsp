<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.AnswersDaoImpl" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Answer</title>

    <!-- Import Bootstrap CSS from CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<!-- Use Bootstrap classes to center and align items in the page -->
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div>
        <!-- Use Bootstrap class to center the title -->
        <h1 class="text-center">Delete an Answer</h1>
        <!-- Use Bootstrap classes to style the form -->
        <form method="post" action="deleteanswer.jsp" class="text-center">
            <label for="answerId">Answer ID:</label>
            <input type="text" id="answerId" name="answerId" required class="form-control"><br>

            <input type="submit" value="Delete" class="btn btn-danger">
        </form>
        <br>
        <!-- Use Bootstrap classes to style the button -->
        <form action="answeraction.jsp">
            <input type="submit" value="Return to Answer Actions" class="btn btn-primary d-block mx-auto">
        </form>
    </div>
</div>

<%
    if ("POST".equalsIgnoreCase(request.getMethod())) {
        String answerIdStr = request.getParameter("answerId");

        if (answerIdStr != null) {
            try {
                int answerId = Integer.parseInt(answerIdStr);

                AnswersDaoImpl answersDao = new AnswersDaoImpl();
                answersDao.deleteById(answerId);

                out.println("<p class='text-center text-success'>Answer deleted successfully.</p>");
            } catch (NumberFormatException e) {
                out.println("<p class='text-center text-danger'>Invalid input format. Please enter a valid Answer ID.</p>");
            } catch (Exception e) {
                out.println("<p class='text-center text-danger'>Error occurred while deleting the answer. Please try again later.</p>");
                e.printStackTrace();
            }
        } else {
            out.println("<p class='text-center text-danger'>Please fill out the Answer ID field.</p>");
        }
    }
%>
</body>
</html>