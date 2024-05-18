<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Answer Actions</title>

    <!-- Import Bootstrap CSS from CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<!-- Use Bootstrap classes to center and align items in the page -->
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div>
        <!-- Use Bootstrap class to center the title -->
        <h1 class="text-center">Answer Actions</h1>
        <!-- Use Bootstrap classes to style the table -->
        <table class="table table-striped table-hover">
            <tr>
                <!-- Use Bootstrap class to center the text in the table cell -->
                <td class="text-center"><a href="insertanswer.jsp">Insert Answer</a></td>
            </tr>
            <tr>
                <td class="text-center"><a href="deleteanswer.jsp">Delete Answer</a></td>
            </tr>
            <tr>
                <td class="text-center"><a href="viewanswer.jsp">View Answer</a></td>
            </tr>
        </table>
        <br>
        <!-- Use Bootstrap classes to style the button -->
        <form action="../../dashboard.jsp">
            <input type="submit" value="Return to Dashboard" class="btn btn-primary d-block mx-auto">
        </form>
    </div>
</div>
</body>
</html>