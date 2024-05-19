<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.PapersDaoImpl, com.example.model.Paper" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Paper Results</title>

    <!-- Import Bootstrap CSS from CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <!-- Import custom CSS -->
    <link href="../../resources/css/styles.css" rel="stylesheet">
    <link href="../../resources/css/paper.css" rel="stylesheet">
</head>
<body>
<!-- Use Bootstrap classes to center and align items in the page -->
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div>
        <!-- Use Bootstrap class to center the title -->
        <h1 class="text-center">Paper Results</h1>

        <%
            String allPapers = request.getParameter("allPapers");
            PapersDaoImpl papersDao = new PapersDaoImpl();
            List<Paper> papers;

            if (allPapers != null) {
                papers = papersDao.findAll();
                out.println("<h2 class='text-center'>All Papers:</h2>");
            } else {
                String title = request.getParameter("title");
                String subjectName = request.getParameter("subject");
                String yearStr = request.getParameter("year");
                String teacher = request.getParameter("teacher");

                papers = papersDao.findByDynamicConditions(title, subjectName, yearStr, teacher);

                out.println("<h2 class='text-center'>Search Results:</h2>");
            }

            if (papers.isEmpty()) {
                out.println("<p class='text-center'>No papers found.</p>");
            } else {
                out.println("<ul class='list-group text-center'>");
                for (Paper paper : papers) {
                    out.println("<li class='list-group-item'>Paper ID: " + paper.getPaperId() + ", Title: " + paper.getTitle() + ", Year: " + paper.getYear() + ", Subject ID: " + paper.getSubjectId());
                    out.println("<form method='get' action='showquestions.jsp'>");
                    out.println("<input type='hidden' name='paperId' value='" + paper.getPaperId() + "'>");
                    out.println("<input type='submit' value='Show Questions' class='btn btn-info'>");
                    out.println("</form>");
                    // Add a form to show students associated with the paper
                    out.println("<form method='get' action='showstudent.jsp'>");
                    out.println("<input type='hidden' name='paperId' value='" + paper.getPaperId() + "'>");
                    out.println("<input type='submit' value='Show Students' class='btn btn-info'>");
                    out.println("</form>");
                    out.println("</li>");
                }
                out.println("</ul>");
            }
        %>

        <!-- Button to return to the viewpaper page -->
        <form action="viewpaper.jsp">
            <input type="submit" value="Return to View Papers" class="btn btn-primary d-block mx-auto">
        </form>
    </div>
</div>
</body>
</html>