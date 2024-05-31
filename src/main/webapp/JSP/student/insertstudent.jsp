<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.DAO.StudentsDaoImpl, com.example.model.Student,com.example.DAO.PersonDaoImpl, com.example.model.Person" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert Student</title>

    <!-- Import Bootstrap CSS from CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <!-- Import custom CSS -->
    <link href="../../resources/css/styles.css" rel="stylesheet">
    <link href="../../resources/css/student.css" rel="stylesheet">
</head>
<body>
<!-- Use Bootstrap classes to center and align items in the page -->
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div>
        <!-- Use Bootstrap class to center the title -->
        <h1 class="text-center">Insert a New Student</h1>

        <form method="post" action="insertstudent.jsp" class="text-center">
            <label for="personId">Person ID:</label>
            <input type="text" id="personId" name="personId" class="form-control" required><br>

            <label for="name">Name:</label>
            <input type="text" id="name" name="name" class="form-control" required><br>

            <label for="studentId">Student ID:</label>
            <input type="text" id="studentId" name="studentId" class="form-control" required><br>


            <input type="submit" value="Insert" class="btn btn-primary">
        </form>
        <br>
        <!-- Button to return to the student actions page -->
        <form action="studentaction.jsp" class="mt-3">
            <input type="submit" value="Return to Student Actions" class="btn btn-secondary d-block mx-auto">
        </form>

        <%
            if ("POST".equalsIgnoreCase(request.getMethod())) {
                String personIdStr = request.getParameter("personId");
                String name = request.getParameter("name");
                String studentIdStr = request.getParameter("studentId");


                if (personIdStr != null && name != null && studentIdStr != null) {
                    try {
                        int personId = Integer.parseInt(personIdStr);
                        int studentId = Integer.parseInt(studentIdStr);

                        // Insert into Person table
                        Person person = new Person();
                        person.setPersonId(personId);
                        person.setName(name);

                        PersonDaoImpl personDao = new PersonDaoImpl();
                        personDao.insert(person);

                        //Insert into Student table
                        Student student = new Student();
                        student.setStudentId(studentId);
                        student.setPersonId(personId);

                        StudentsDaoImpl studentsDao = new StudentsDaoImpl();
                        studentsDao.insert(student);

                        out.println("<p class='text-center'>Student inserted successfully.</p>");
                    } catch (NumberFormatException e) {
                        out.println("<p class='text-center text-danger'>Invalid input format. Please ensure all fields are correctly filled.</p>");
                    } catch (Exception e) {
                        out.println("<p class='text-center text-danger'>An error occurred. Please try again.</p>");
                    }
                } else {
                    out.println("<p class='text-center text-danger'>Please fill out all fields.</p>");
                }
            }
        %>
    </div>
</div>
</body>
</html>