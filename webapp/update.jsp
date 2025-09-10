<%@ page language="java" import="com.xworkz.project.dto.SignUpDto" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Update Profile</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

    <style>
        footer {
            background-color: LightSeaGreen;
            color: white;
            padding: 10px 0;
            position: fixed;
            width: 100%;
            bottom: 0;
        }
    </style>
</head>
<body>

<%@ include file="navbar.jsp" %>

<%
SignUpDto dto = (SignUpDto) request.getAttribute("dto");
if (dto != null) {
%>

<div class="container mt-5 mb-5 pb-5">
    <div class="card shadow p-4 col-md-8 mx-auto">
        <h4 class="text-center mb-4">Update Profile</h4>
        <form action="update" method="post">
            <input type="hidden" name="id" value="<%= dto.getId() %>">

            <div class="form-group">
                <label>User Name</label>
                <input type="text" name="userName" class="form-control" value="<%= dto.getUserName() %>" required>
            </div>

            <div class="form-group">
                <label>Contact Number</label>
                <input type="number" name="contactNumber" class="form-control" value="<%= dto.getContactNumber() %>" required>
            </div>

            <div class="form-group">
                <label>Alternate Contact Number</label>
                <input type="number" name="alternateContactNumber" class="form-control" value="<%= dto.getAlternateContactNumber() %>">
            </div>

            <div class="form-group">
                <label>Email</label>
                <input type="email" name="email" class="form-control" value="<%= dto.getEmail() %>" readonly>
            </div>

<!--            <div class="form-group">-->
<!--                <label>Password</label>-->
<!--                <input type="password" name="password" class="form-control" value="<%= dto.getPassword() %>" required>-->
<!--            </div>-->

            <div class="form-group">
                <label>Confirm Password</label>
                <input type="password" name="confirmPassword" class="form-control" value="<%= dto.getPassword() %>" required>
            </div>

            <div class="form-group">
                <label>Address Line 1</label>
                <input type="text" name="adressLine1" class="form-control" value="<%= dto.getAdressLine1() %>" required>
            </div>

            <div class="form-group">
                <label>Address Line 2</label>
                <input type="text" name="adressLine2" class="form-control" value="<%= dto.getAdressLine2() %>" required>
            </div>

            <div class="form-row">
                <div class="form-group col-md-6">
                    <label>City</label>
                    <input type="text" name="city" class="form-control" value="<%= dto.getCity() %>" required>
                </div>

                <div class="form-group col-md-4">
                    <label>State</label>
                    <select name="state" class="form-control">
                        <option selected><%= dto.getState() %></option>
                        <option>Karnataka</option>
                        <option>Kerala</option>
                        <option>Tamil Nadu</option>
                        <!-- Add others -->
                    </select>
                </div>

                <div class="form-group col-md-2">
                    <label>PIN Code</label>
                    <input type="text" name="pin" class="form-control" value="<%= dto.getPin() %>" required>
                </div>
            </div>

            <div class="d-flex justify-content-center">
                <button type="submit" class="btn btn-success mx-2">Update</button>
                <a href="/profile" class="btn btn-secondary mx-2">Cancel</a>
            </div>
        </form>
    </div>
</div>

<%
} else {
%>
<h3 class="text-center text-danger mt-5">User data not available for update.</h3>
<%
}
%>

<%@ include file="footer.jsp" %>
</body>
</html>
