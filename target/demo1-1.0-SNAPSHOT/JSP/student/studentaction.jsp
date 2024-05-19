<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Student Actions</title>

  <!-- Import Bootstrap CSS from CDN -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<!-- Use Bootstrap classes to center and align items in the page -->
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div>
        <!-- Use Bootstrap class to center the title -->
        <h1 class="text-center">Student Actions</h1>
        <!-- Use Bootstrap classes to style the list -->
        <ul class="list-group text-center">
          <li class="list-group-item"><a href="insertstudent.jsp">Insert Student</a></li>
          <li class="list-group-item"><a href="deletestudent.jsp">Delete Student</a></li>
          <li class="list-group-item"><a href="viewstudent.jsp">View Student</a></li>
          <li class="list-group-item"><a href="viewallstudents.jsp">View All Students</a></li>
        </ul>
        <br>
        <!-- Button to return to the dashboard -->
        <form action="../dashboard.jsp" class="mt-3">
          <input type="submit" value="Return to Dashboard" class="btn btn-secondary d-block mx-auto">
        </form>
    </div>
</div>
</body>
</html>