<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.xworkz.project.dto.SignUpDto" %>

<html>
<head>
    <title>Admin - View Users</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <style>
        .navbar-brand img {
            height: 40px;
        }
    </style>
</head>
<body>

<%@ include file="adminnavbar.jsp" %>

<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: LightSeaGreen;">
    <a class="navbar-brand" href="#">
        <img src="https://raw.githubusercontent.com/X-workzDev01/xworkzwebsite/master/src/main/webapp/assets/images/Logo.png" alt="Logo">
    </a>
</nav>

<div class="container mt-4">
    <h2 class="text-center mb-4">Registered Users</h2>

    <table class="table table-bordered table-striped table-hover">
        <thead class="thead-dark">
        <tr>
            <th>User Name</th>
            <th>Contact</th>
            <th>Alternate Contact</th>
            <th>Email</th>
            <th>Address Line 1</th>
            <th>Address Line 2</th>
            <th>City</th>
            <th>State</th>
            <th>PIN</th>

        </tr>
        </thead>
        <tbody>
        <%
        List<SignUpDto> dtolist = (List<SignUpDto>) request.getAttribute("dto");
            if (dtolist != null) {
            for (SignUpDto dto : dtolist) {
            %>
            <tr>
                <td><%= dto.getUserName() %></td>
                <td><%= dto.getContactNumber() %></td>
                <td><%= dto.getAlternateContactNumber() %></td>
                <td><%= dto.getEmail() %></td>
                <td><%= dto.getAdressLine1() %></td>
                <td><%= dto.getAdressLine2() %></td>
                <td><%= dto.getCity() %></td>
                <td><%= dto.getState() %></td>
                <td><%= dto.getPin() %></td>
            </tr>
            <%
            }
            } else {
            %>
            <tr>
                <td colspan="9" class="text-center">No data available.</td>
            </tr>
            <%
            }
            %>
        </tbody>
    </table>

    <div class="text-center">
        <a href="AdminPage.jsp" class="btn btn-outline-info">Back to Admin Page</a>
    </div>
</div>

<%@ include file="footer.jsp" %>

</body>
</html>
