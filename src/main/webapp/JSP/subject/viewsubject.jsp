<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.SubjectsDaoImpl, com.example.model.Subject" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>View Subject</title>
</head>
<body>
<h1>View a Subject</h1>
<form method="post">
  <label for="subjectId">Subject ID:</label>
  <input type="text" id="subjectId" name="subjectId" required><br>

  <input type="submit" value="Search">
</form>
<br>
<form action="subjectaction.jsp">
  <input type="submit" value="Return to Subject Actions">
</form>

<%
  if ("POST".equalsIgnoreCase(request.getMethod())) {
    String subjectIdStr = request.getParameter("subjectId");

    if (subjectIdStr != null && !subjectIdStr.isEmpty()) {
      try {
        int subjectId = Integer.parseInt(subjectIdStr);

        SubjectsDaoImpl subjectsDao = new SubjectsDaoImpl();
        Subject subject = subjectsDao.findById(subjectId);

        if (subject != null) {
          out.println("<h2>Subject Details:</h2>");
          out.println("<p>Subject ID: " + subject.getSubjectId() + "</p>");
          out.println("<p>Name: " + subject.getName() + "</p>");
        } else {
          out.println("<p>No subject found with the given ID.</p>");
        }
      } catch (NumberFormatException e) {
        out.println("<p>Invalid input format. Please enter a valid integer for Subject ID.</p>");
      } catch (Exception e) {
        out.println("<p>Error occurred while retrieving subject. Please try again later.</p>");
        e.printStackTrace();
      }
    } else {
      out.println("<p>Please fill out the Subject ID field.</p>");
    }
  }
%>
</body>
</html>

