<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.AnswersDaoImpl, com.example.DAO.QuestionsDaoImpl, com.example.model.Answer, com.example.model.Question" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert Answer</title>

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
        <h1 class="text-center">Insert a New Answer</h1>
        <!-- Use Bootstrap classes to style the form -->
        <form method="post" action="insertanswer.jsp" class="text-center">
          <label for="answerId">Answer ID:</label>
          <input type="text" id="answerId" name="answerId" required class="form-control"><br>

          <label for="text">Text:</label>
          <input type="text" id="text" name="text" required class="form-control"><br>

          <label for="questionId">Question ID:</label>
          <input type="text" id="questionId" name="questionId" required class="form-control"><br>

          <input type="submit" value="Insert" class="btn btn-success">
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
    String text = request.getParameter("text");
    String questionIdStr = request.getParameter("questionId");

    if (answerIdStr != null && text != null && !text.isEmpty() && questionIdStr != null) {
      try {
        int answerId = Integer.parseInt(answerIdStr);
        int questionId = Integer.parseInt(questionIdStr);

        Answer answer = new Answer();
        answer.setAnswerId(answerId);
        answer.setText(text);

        AnswersDaoImpl answersDao = new AnswersDaoImpl();
        answersDao.insert(answer, questionId); // Pass the questionId to the insert method

        out.println("<p class='text-center text-success'>Answer inserted successfully.</p>");
      } catch (NumberFormatException e) {
        out.println("<p class='text-center text-danger'>Invalid input format. Please ensure all fields are correctly filled.</p>");
      } catch (Exception e) {
        out.println("<p class='text-center text-danger'>Failed to insert answer. Please check your input.</p>");
      }
    } else {
      out.println("<p class='text-center text-danger'>Please fill out all fields.</p>");
    }
  }
%>

</body>
</html>