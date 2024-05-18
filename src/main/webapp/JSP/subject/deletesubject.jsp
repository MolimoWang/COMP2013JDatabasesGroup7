<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.SubjectsDaoImpl" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Delete Subject</title>
</head>
<body>
<h1>Delete a Subject</h1>
<form method="post">
  <label for="subjectId">Subject ID:</label>
  <input type="text" id="subjectId" name="subjectId" required><br>

  <input type="submit" value="Delete">
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
        subjectsDao.deleteById(subjectId);

        out.println("<p>Subject deleted successfully.</p>");
      } catch (NumberFormatException e) {
        out.println("<p>Invalid input format. Please enter a valid integer for Subject ID.</p>");
      } catch (Exception e) {
        out.println("<p>Error occurred while deleting subject. Please try again later.</p>");
        e.printStackTrace();
      }
    } else {
      out.println("<p>Please fill out the Subject ID field.</p>");
    }
  }
%>
</body>
</html>

