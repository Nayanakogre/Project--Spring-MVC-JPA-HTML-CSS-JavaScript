<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html xmlns:c="http://www.w3.org/1999/XSL/Transform">
<head>
    <title>Verify OTP</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6 col-sm-8">
            <div class="card shadow rounded">
                <div class="card-header text-center bg-info text-white">
                    <h4>OTP Verification</h4>
                </div>
                <div class="card-body">
                    <form action="verifyUserOtp" method="post">
                        <input type="hidden" name="email" value="${email}" />

                        <div class="form-group">
                            <label for="otp">Enter OTP:</label>
                            <input type="text" name="otp" id="otp" class="form-control" maxlength="6" required />
                        </div>

                        <c:if test="${not empty message}">
                            <div class="text-danger text-center">${message}</div>
                        </c:if>

                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Verify OTP</button>
                        </div>
                    </form>
                </div>
                <div class="card-footer text-center">
                    <a href="forgotPassword.jsp">Back</a>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
