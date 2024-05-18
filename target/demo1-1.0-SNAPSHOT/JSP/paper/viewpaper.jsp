<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Papers</title>

    <!-- Import Bootstrap CSS from CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<!-- Use Bootstrap classes to center and align items in the page -->
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div>
        <!-- Use Bootstrap class to center the title -->
        <h1 class="text-center">View Papers</h1>
        <!-- Use Bootstrap classes to style the form -->
        <form method="post" action="paperresult.jsp" class="text-center">
            <label for="title">Title:</label>
            <input type="text" id="title" name="title" class="form-control"><br>

            <label for="subject">Subject:</label>
            <input type="text" id="subject" name="subject" class="form-control"><br>

            <label for="year">Year:</label>
            <input type="text" id="year" name="year" class="form-control"><br>

            <label for="teacher">Teacher:</label>
            <input type="text" id="teacher" name="teacher" class="form-control"><br>

            <input type="submit" value="Search" class="btn btn-primary">
            <input type="submit" name="allPapers" value="View All Papers" class="btn btn-secondary">
        </form>
        <br>
        <!-- Use Bootstrap classes to style the button -->
        <form action="paperaction.jsp">
            <input type="submit" value="Return to Paper action" class="btn btn-secondary d-block mx-auto">
        </form>
    </div>
</div>
</body>
</html>