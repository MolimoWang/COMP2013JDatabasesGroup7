<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.SubjectsDaoImpl, com.example.model.Subject" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Subject</title>

    <!-- Import Bootstrap CSS from CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<!-- Use Bootstrap classes to center and align items in the page -->
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div>
        <!-- Use Bootstrap class to center the title -->
        <h1 class="text-center">View a Subject</h1>

        <form method="post" class="text-center mt-3">
            <label for="subjectId">Subject ID:</label>
            <input type="text" id="subjectId" name="subjectId" class="form-control" required>
            <input type="submit" value="Search" class="btn btn-primary mt-3">
        </form>

        <!-- Button to return to the subject actions page -->
        <form action="subjectaction.jsp" class="mt-3">
            <input type="submit" value="Return to Subject Actions" class="btn btn-secondary d-block mx-auto">
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
                            out.println("<h2 class='text-center mt-3'>Subject Details:</h2>");
                            out.println("<p class='text-center'>Subject ID: " + subject.getSubjectId() + "</p>");
                            out.println("<p class='text-center'>Name: " + subject.getName() + "</p>");
                        } else {
                            out.println("<p class='text-center text-danger mt-3'>No subject found with the given ID.</p>");
                        }
                    } catch (NumberFormatException e) {
                        out.println("<p class='text-center text-danger mt-3'>Invalid input format. Please enter a valid integer for Subject ID.</p>");
                    } catch (Exception e) {
                        out.println("<p class='text-center text-danger mt-3'>Error occurred while retrieving subject. Please try again later.</p>");
                        e.printStackTrace();
                    }
                } else {
                    out.println("<p class='text-center text-danger mt-3'>Please fill out the Subject ID field.</p>");
                }
            }
        %>
    </div>
</div>
</body>
</html>