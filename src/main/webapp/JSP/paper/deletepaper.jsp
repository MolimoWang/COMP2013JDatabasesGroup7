<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.PapersDaoImpl"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Paper</title>
</head>
<body>
<h1>Delete Paper</h1>
<form method="post">
    <label for="paperId">Enter Paper ID to delete:</label>
    <input type="text" id="paperId" name="paperId" required><br>

    <input type="submit" value="Delete">
</form>

<%
    if ("POST".equalsIgnoreCase(request.getMethod())) {
        String paperIdStr = request.getParameter("paperId");

        if (paperIdStr != null && !paperIdStr.isEmpty()) {
            try {
                int paperId = Integer.parseInt(paperIdStr);

                PapersDaoImpl papersDao = new PapersDaoImpl();
                papersDao.deleteById(paperId);

                out.println("<p>Paper with ID " + paperId + " has been deleted successfully.</p>");
            } catch (NumberFormatException e) {
                out.println("<p>Invalid Paper ID format. Please enter a valid integer.</p>");
            } catch (Exception e) {
                out.println("<p>Error occurred while deleting paper. Please try again later.</p>");
                e.printStackTrace();
            }
        } else {
            out.println("<p>Please enter Paper ID to delete.</p>");
        }
    }
%>
</body>
</html>


