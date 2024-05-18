<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.PapersDaoImpl, com.example.model.Paper" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Paper Results</title>
</head>
<body>
<h1>Paper Results</h1>

<%
    String allPapers = request.getParameter("allPapers");
    PapersDaoImpl papersDao = new PapersDaoImpl();
    List<Paper> papers;

    if (allPapers != null) {
        papers = papersDao.findAll();
        out.println("<h2>All Papers:</h2>");
    } else {
        String title = request.getParameter("title");
        String subjectName = request.getParameter("subject");
        String yearStr = request.getParameter("year");
        String teacher = request.getParameter("teacher");

        papers = papersDao.findByDynamicConditions(title, subjectName, yearStr, teacher);

        out.println("<h2>Search Results:</h2>");
    }

    if (papers.isEmpty()) {
        out.println("<p>No papers found.</p>");
    } else {
        out.println("<ul>");
        for (Paper paper : papers) {
            out.println("<li>Paper ID: " + paper.getPaperId() + ", Title: " + paper.getTitle() + ", Year: " + paper.getYear() + ", Subject ID: " + paper.getSubjectId());
            out.println("<form method='get' action='showquestions.jsp'>");
            out.println("<input type='hidden' name='paperId' value='" + paper.getPaperId() + "'>");
            out.println("<input type='submit' value='Show Questions'>");
            out.println("</form>");
            out.println("</li>");
        }
        out.println("</ul>");
    }
%>

<!-- Button to return to the viewpaper page -->
<form action="viewpaper.jsp">
    <input type="submit" value="Return to View Papers">
</form>
</body>
</html>




