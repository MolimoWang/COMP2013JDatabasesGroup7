<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Paper Actions</title>

    <!-- Import Bootstrap CSS from CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<!-- Use Bootstrap classes to center and align items in the page -->
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div>
        <!-- Use Bootstrap class to center the title -->
        <h1 class="text-center">Paper Actions</h1>
        <!-- Use Bootstrap classes to style the list -->
        <ul class="list-group text-center">
            <li class="list-group-item"><a href="viewpaper.jsp">View Papers</a></li>
            <li class="list-group-item"><a href="insertpaper.jsp">Insert Paper</a></li>
            <li class="list-group-item"><a href="deletepaper.jsp">Delete Paper</a></li>
        </ul>
        <br>
        <!-- Use Bootstrap classes to style the button -->
        <form action="../../dashboard.jsp">
            <input type="submit" value="Return to Dashboard" class="btn btn-primary d-block mx-auto">
        </form>
    </div>
</div>

<%-- Get the parameter to decide the operation to be performed --%>
<% String action = request.getParameter("action"); %>

<%-- Perform the corresponding operation based on the parameter --%>
<% if (action != null) {
    if ("viewpaper".equals(action)) {
%>
<%-- Redirect to viewpaper.jsp --%>
<% response.sendRedirect("viewpaper.jsp"); %>
<%  } else if ("insertpaper".equals(action)) { %>
<%-- Redirect to insertpaper.jsp --%>
<% response.sendRedirect("insertpaper.jsp"); %>
<%  } else if ("deletepaper".equals(action)) { %>
<%-- Redirect to deletepaper.jsp --%>
<% response.sendRedirect("deletepaper.jsp"); %>
<%  }
} %>

</body>
</html>