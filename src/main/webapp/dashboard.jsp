<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>

    <!-- Import Bootstrap CSS from CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<!-- Use Bootstrap classes to center and align items in the page -->
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div>
        <!-- Use Bootstrap class to center the title -->
        <h1 class="text-center">Welcome to the Dashboard</h1>
        <h2 class="text-center">Select an option:</h2>
        <!-- Use Bootstrap classes to style the list -->
        <ul class="list-group list-group-flush text-center mt-3">
            <li class="list-group-item"><a href="JSP/paper/paperaction.jsp" class="text-primary">Papers action</a></li>
            <li class="list-group-item"><a href="JSP/subject/subjectaction.jsp" class="text-primary">Subject Actions</a></li>
            <li class="list-group-item"><a href="JSP/answer/answeraction.jsp" class="text-primary">Answer Actions</a></li>
            <li class="list-group-item"><a href="JSP/question/questionaction.jsp" class="text-primary">Question Actions</a></li>
            <li class="list-group-item"><a href="JSP/teacher/teacheraction.jsp" class="text-primary">Teacher Actions</a></li>
            <li class="list-group-item"><a href="JSP/student/studentaction.jsp" class="text-primary">Student Actions</a></li>
            <li class="list-group-item"><a href="selectYear.jsp?action=upload" class="text-primary">Upload Papers</a></li>
        </ul>
    </div>
</div>
</body>
</html>