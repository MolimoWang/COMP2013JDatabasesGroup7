<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
    String action = request.getParameter("action");
%>
<html>
<head>
    <title>Select Year</title>
</head>
<body>
    <h1><%= action.equalsIgnoreCase("upload") ? "Upload Papers" : "View Papers" %></h1>
    <h2>Select Year:</h2>
    <ul>
        <li><a href="<%= action %>.jsp?year=1">Year 1</a></li>
        <li><a href="<%= action %>.jsp?year=2">Year 2</a></li>
        <li><a href="<%= action %>.jsp?year=3">Year 3</a></li>
        <li><a href="<%= action %>.jsp?year=4">Year 4</a></li>
    </ul>
</body>
</html>

</html>