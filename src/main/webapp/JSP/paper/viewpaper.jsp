<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Papers</title>
</head>
<body>
<h1>View Papers</h1>
<form method="post" action="paperresult.jsp">
    <label for="title">Title:</label>
    <input type="text" id="title" name="title"><br>

    <label for="subject">Subject:</label>
    <input type="text" id="subject" name="subject"><br>

    <label for="year">Year:</label>
    <input type="text" id="year" name="year"><br>

    <input type="submit" value="Search">
    <input type="submit" name="allPapers" value="View All Papers">
</form>
</body>
</html>

