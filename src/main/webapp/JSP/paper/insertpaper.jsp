<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.PapersDaoImpl,com.example.model.Paper" %>
<%@ page import="java.sql.SQLIntegrityConstraintViolationException" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert Paper</title>

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
        <h1 class="text-center">Insert a New Paper</h1>
        <!-- Use Bootstrap classes to style the form -->
        <form method="post" action="insertpaper.jsp" class="text-center">
            <label for="paperId">Paper ID:</label>
            <input type="text" id="paperId" name="paperId" required class="form-control"><br>

            <label for="title">Title:</label>
            <input type="text" id="title" name="title" required class="form-control"><br>

            <label for="year">Year:</label>
            <input type="text" id="year" name="year" required class="form-control"><br>

            <label for="subjectId">Subject ID:</label>
            <input type="text" id="subjectId" name="subjectId" required class="form-control"><br>

            <input type="submit" value="Insert" class="btn btn-success">
        </form>
        <br>
        <!-- Use Bootstrap classes to style the button -->
        <form action="paperaction.jsp">
            <input type="submit" value="Return to Paperaction" class="btn btn-primary d-block mx-auto">
        </form>
    </div>
</div>

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

                out.println("<p class='text-center text-success'>Paper inserted successfully.</p>");
            } catch (NumberFormatException e) {
                out.println("<p class='text-center text-danger'>Invalid input format. Please ensure all fields are correctly filled.</p>");
            } catch (SQLIntegrityConstraintViolationException e) {
                out.println("<p class='text-center text-danger'>Subject ID does not exist. Please create the subject first.</p>");
            } catch (Exception e) {
                out.println("<p class='text-center text-danger'>Error occurred while inserting the paper. Please try again later.</p>");
                e.printStackTrace();
            }
        } else {
            out.println("<p class='text-center text-danger'>Please fill out all fields.</p>");
        }
    }
%>
</body>
</html>