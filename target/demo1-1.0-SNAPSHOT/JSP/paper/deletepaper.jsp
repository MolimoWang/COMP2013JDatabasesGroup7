<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.PapersDaoImpl"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Paper</title>

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
        <h1 class="text-center">Delete Paper</h1>
        <!-- Use Bootstrap classes to style the form -->
        <form method="post" class="text-center">
            <label for="paperId">Enter Paper ID to delete:</label>
            <input type="text" id="paperId" name="paperId" required class="form-control"><br>

            <input type="submit" value="Delete" class="btn btn-danger">
        </form>
        <br>
        <!-- Use Bootstrap classes to style the button -->
        <form action="viewpaper.jsp">
            <input type="submit" value="Return to View Papers" class="btn btn-secondary d-block mx-auto">
        </form>
    </div>
</div>

<%
    if ("POST".equalsIgnoreCase(request.getMethod())) {
        String paperIdStr = request.getParameter("paperId");

        if (paperIdStr != null && !paperIdStr.isEmpty()) {
            try {
                int paperId = Integer.parseInt(paperIdStr);

                PapersDaoImpl papersDao = new PapersDaoImpl();
                papersDao.deleteById(paperId);

                out.println("<p class='text-center text-success'>Paper with ID " + paperId + " has been deleted successfully.</p>");
            } catch (NumberFormatException e) {
                out.println("<p class='text-center text-danger'>Invalid Paper ID format. Please enter a valid integer.</p>");
            } catch (Exception e) {
                out.println("<p class='text-center text-danger'>Error occurred while deleting paper. Please try again later.</p>");
                e.printStackTrace();
            }
        } else {
            out.println("<p class='text-center text-danger'>Please enter Paper ID to delete.</p>");
        }
    }
%>
</body>
</html>