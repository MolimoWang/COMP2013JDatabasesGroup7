<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.PapersDaoImpl,com.example.model.Paper" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert Paper</title>
</head>
<body>
<h1>Insert a New Paper</h1>
<form method="post" action="insertpaper.jsp">
    <label for="paperId">Paper ID:</label>
    <input type="text" id="paperId" name="paperId" required><br>

    <label for="title">Title:</label>
    <input type="text" id="title" name="title" required><br>

    <label for="year">Year:</label>
    <input type="text" id="year" name="year" required><br>

    <label for="subjectId">Subject ID:</label>
    <input type="text" id="subjectId" name="subjectId" required><br>

    <input type="submit" value="Insert">
</form>
<br>
<form action="../../dashboard.jsp">
    <input type="submit" value="Return to Dashboard">
</form>

<%
    if ("POST".equalsIgnoreCase(request.getMethod())) {
        String paperIdStr = request.getParameter("paperId");
        String title = request.getParameter("title");
        String yearStr = request.getParameter("year");
        String subjectIdStr = request.getParameter("subjectId");

        if (paperIdStr != null && title != null && yearStr != null && subjectIdStr != null) {
            try {
                int paperId = Integer.parseInt(paperIdStr);
                int year = Integer.parseInt(yearStr);
                int subjectId = Integer.parseInt(subjectIdStr);

                Paper paper = new Paper();
                paper.setPaperId(paperId);
                paper.setTitle(title);
                paper.setYear(year);
                paper.setSubjectId(subjectId);

                PapersDaoImpl papersDao = new PapersDaoImpl();
                papersDao.insert(paper);

                out.println("<p>Paper inserted successfully.</p>");
            } catch (NumberFormatException e) {
                out.println("<p>Invalid input format. Please ensure all fields are correctly filled.</p>");
            }
        } else {
            out.println("<p>Please fill out all fields.</p>");
        }
    }
%>
</body>
</html>
