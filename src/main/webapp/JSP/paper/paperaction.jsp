<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Paper Actions</title>
</head>
<body>
<h1>Paper Actions</h1>
<ul>
    <li><a href="viewpaper.jsp">View Papers</a></li>
    <li><a href="insertpaper.jsp">Insert Paper</a></li>
    <li><a href="deletepaper.jsp">Delete Paper</a></li>
</ul>

<%-- 获取参数，决定要执行的操作 --%>
<% String action = request.getParameter("action"); %>

<%-- 根据参数执行相应的操作 --%>
<% if (action != null) {
    if ("viewpaper".equals(action)) {
%>
<%-- 重定向到 viewpaper.jsp --%>
<% response.sendRedirect("viewpaper.jsp"); %>
<%  } else if ("insertpaper".equals(action)) { %>
<%-- 重定向到 insertpaper.jsp --%>
<% response.sendRedirect("insertpaper.jsp"); %>
<%  } else if ("deletepaper".equals(action)) { %>
<%-- 重定向到 deletepaper.jsp --%>
<% response.sendRedirect("deletepaper.jsp"); %>
<%  }
} %>
<form action="../../dashboard.jsp">
    <input type="submit" value="Return to View Papers">
</form>
</body>
</html>
