<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Question Actions</title>

    <!-- Import Bootstrap CSS from CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <!-- Import custom CSS -->
    <link href="../../resources/css/styles.css" rel="stylesheet">
    <link href="../../resources/css/question.css" rel="stylesheet">
</head>
<body>
<!-- Use Bootstrap classes to center and align items in the page -->
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div>
        <!-- Use Bootstrap class to center the title -->
        <h1 class="text-center">Question Actions</h1>
        <ul class="list-group list-group-flush text-center">
            <li class="list-group-item"><a href="insertquestion.jsp">Insert Question</a></li>
            <li class="list-group-item"><a href="deletequestion.jsp">Delete Question</a></li>
            <li class="list-group-item"><a href="viewquestion.jsp">View Question</a></li>
            <li class="list-group-item"><a href="changeanswer.jsp">Change answer</a></li>
        </ul>
        <br>
        <form action="../dashboard.jsp">
            <input type="submit" value="Return to Dashboard" class="btn btn-secondary d-block mx-auto">
        </form>
    </div>
</div>
</body>
</html>