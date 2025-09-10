<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Forgot Password</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script>
        function checkEmail() {
            const email = document.getElementById("email").value.trim();
            const error = document.getElementById("emailError");
            const submitBtn = document.getElementById("sendOtpBtn");

            if (email === "") {
                error.innerText = "Please enter email";
                submitBtn.disabled = true;
                return;
            }

            const xhr = new XMLHttpRequest();
            xhr.open("GET", "http://localhost:8080/project/api/emailCheck?email=" + encodeURIComponent(email), true);
            xhr.onload = function () {
                if (xhr.status === 200) {
                    if (xhr.responseText.trim() === "true") {
                        error.innerText = "";
                        submitBtn.disabled = false;
                    } else {
                        error.innerText = "Email is not registered";
                        submitBtn.disabled = true;
                    }
                }
            };
            xhr.send();
        }
    </script>
</head>
<body class="bg-light">

<%@ include file="navbar.jsp" %>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6 col-sm-8">
            <div class="card shadow rounded">
                <div class="card-header text-center bg-primary text-white">
                    <h4>Forgot Password</h4>
                </div>

                <div class="card-body">
                    <form action="sendOtpPassword" method="post">
                        <div class="form-group">
                            <label for="email">Enter your registered Email:</label>
                            <input type="email" name="email" id="email" class="form-control"
                                   oninput="checkEmail()" required>
                            <span id="emailError" style="color: red;"></span>
                        </div>

                        <%
                        String msg = (String) request.getAttribute("message");
                        if (msg != null && !msg.isEmpty()) {
                        %>
                        <div class="text-danger text-center"><%= msg %></div>
                        <%
                        }
                        %>

                        <div class="text-center">
                            <button type="submit" class="btn btn-success" id="sendOtpBtn" disabled>Send OTP</button>
                        </div>
                    </form>

                    <%
                    Boolean showOtp = (Boolean) request.getAttribute("showOtpForm");
                    if (showOtp != null && showOtp) {
                    %>
                    <form action="verifyUserOtp" method="post" class="mt-3">
                        <input type="hidden" name="email" value="<%= request.getAttribute("email") %>" />

                        <div class="form-group">
                            <label for="otp">Enter OTP:</label>
                            <input type="text" name="otp" id="otp" class="form-control" maxlength="6" required />
                        </div>

                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Verify OTP</button>
                        </div>
                    </form>
                    <%
                    }
                    %>
                </div>

                <div class="card-footer text-center">
                    <a href="signIn.jsp">Back to Sign In</a>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>

</body>
</html>
