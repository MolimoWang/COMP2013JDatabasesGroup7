<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.AnswersDaoImpl" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Answer</title>
</head>
<body>
<h1>Delete an Answer</h1>
<form method="post" action="deleteanswer.jsp">
    <label for="answerId">Answer ID:</label>
    <input type="text" id="answerId" name="answerId" required><br>

    <input type="submit" value="Delete">
</form>
<br>
<form action="answeraction.jsp">
    <input type="submit" value="Return to Answer Actions">
</form>

<%
    if ("POST".equalsIgnoreCase(request.getMethod())) {
        String answerIdStr = request.getParameter("answerId");

        if (answerIdStr != null) {
            try {
                int answerId = Integer.parseInt(answerIdStr);

                AnswersDaoImpl answersDao = new AnswersDaoImpl();
                answersDao.deleteById(answerId);

                out.println("<p>Answer deleted successfully.</p>");
            } catch (NumberFormatException e) {
                out.println("<p>Invalid input format. Please enter a valid Answer ID.</p>");
            }
        } else {
            out.println("<p>Please fill out the Answer ID field.</p>");
        }
    }
%>
</body>
</html>

