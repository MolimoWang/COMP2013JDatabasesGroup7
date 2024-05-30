<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.PapersDaoImpl, com.example.model.Paper" %>
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
            int pageNum = 1; // Change 'page' to 'pageNum'
            int count = 5;

            if (request.getParameter("pageNum") != null) { // Change 'page' to 'pageNum'
                pageNum = Integer.parseInt(request.getParameter("pageNum")); // Change 'page' to 'pageNum'
            }

            if (allPapers != null) {
                papers = papersDao.findWithPagination((pageNum - 1) * count, count);
                out.println("<h2 class='text-center'>All Papers:</h2>");
            } else {
                String title = request.getParameter("title");
                String subjectName = request.getParameter("subject");
                String yearStr = request.getParameter("year");
                String teacher = request.getParameter("teacher");

                papers = papersDao.findByDynamicConditions(title, subjectName, yearStr, teacher, (pageNum - 1) * count, count);

                out.println("<h2 class='text-center'>Search Results:</h2>");
            }

            if (papers.isEmpty()) {
                out.println("<p class='text-center'>No papers found.</p>");
            } else {
                out.println("<ul class='list-group text-center'>");
                for (Paper paper : papers) {
                    out.println("<li class='list-group-item'>");
                    out.println("Paper ID: " + paper.getPaperId());
                    out.println("<br/>Title: " + paper.getTitle());
                    out.println("<br/>Year: " + paper.getYear());
                    out.println("<br/>Subject ID: " + paper.getSubjectId());
                    out.println("</li>");
                }
                out.println("</ul>");
            }

            int totalPapers = papersDao.count();
            int totalPages = (int) Math.ceil((double) totalPapers / count);

            out.println("<nav aria-label='Page navigation example'>");
            out.println("<ul class='pagination justify-content-center'>");
            for (int i = 1; i <= totalPages; i++) {
                if (i == pageNum) { // Change 'page' to 'pageNum'
                    out.println("<li class='page-item active'><a class='page-link' href='?pageNum=" + i + "'>" + i + "</a></li>"); // Change 'page' to 'pageNum'
                } else {
                    out.println("<li class='page-item'><a class='page-link' href='?pageNum=" + i + "'>" + i + "</a></li>"); // Change 'page' to 'pageNum'
                }
            }
            out.println("</ul>");
            out.println("</nav>");
        %>

        <!-- Button to return to the viewpaper page -->
        <form action="viewpaper.jsp">
            <input type="submit" value="Return to View Papers" class="btn btn-primary d-block mx-auto">
        </form>
    </div>
</div>
</body>
</html>