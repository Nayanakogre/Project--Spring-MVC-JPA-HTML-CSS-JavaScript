<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign In</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <style>
        .signin-form {
            max-width: 400px;
            margin: auto;
            padding: 30px;
            border: 1px solid #dee2e6;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            background-color: #ffffff;
        }
    </style>
</head>
<body>
<%@ include file="navbar.jsp" %>

<div class="container mt-5">
    <div class="signin-form">
        <h3 class="text-center mb-4">Sign In</h3>

        <form action="signin" method="post" id="signinForm">
            <div class="form-group">
                <label for="emailAddress">Email address</label>
                <input type="email" name="email" class="form-control" id="emailAddress" onchange="handleEmailCheck()" required>
                <span id="emailError" style="color:red;"></span>
            </div>

            <div class="form-group">
                <label for="passwordInput">Password</label>
                <input type="password" name="password" class="form-control" id="passwordInput" oninput="checkAllValid()" required>
            </div>

            <%
            String msg = (String) request.getAttribute("message");
            if (msg != null && !msg.isEmpty()) {
            %>
            <h5 class="text-center text-danger"><%= msg %></h5>
            <%
            }
            %>

            <div class="d-flex justify-content-center mt-3">
                <button type="submit" class="btn btn-primary btn-sm mx-2" id="submitBtn" disabled>Sign In</button>
                <button type="reset" class="btn btn-secondary btn-sm mx-2">Reset</button>
            </div>
            <a href="forgotPassword.jsp">Forgot Password?</a>

        </form>
    </div>
</div>

<script>
    let emailValid = false;

    function handleEmailCheck() {
        const email = document.getElementById("emailAddress").value.trim();
        const errorSpan = document.getElementById("emailError");

        if (email === "") {
            errorSpan.innerText = "Email should not be empty";
            emailValid = false;
            checkAllValid();
            return;
        }

        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(email)) {
            errorSpan.innerText = "Please enter a valid email address";
            emailValid = false;
            checkAllValid();
            return;
        }else{
        errorSpan.innerText = "";
        emailValid = true;
        }


        const request = new XMLHttpRequest();
        request.open("GET", "http://localhost:8080/project/api/emailCheck?email=" + encodeURIComponent(email));

        request.onload = function () {
            if (request.status == 200) {
                const response = request.responseText.trim();
                if (response === "true") {
                    errorSpan.innerText = "";
                    emailValid = true;
                } else {
                    errorSpan.innerText = "Email does not exist";
                    emailValid = false;
                }
                checkAllValid();
            }
        };

        request.send();
    }

    function checkAllValid() {
        const password = document.getElementById("passwordInput").value.trim();
        const submitBtn = document.getElementById("submitBtn");

        if (emailValid && password !== "") {
            submitBtn.disabled = false;
        } else {
            submitBtn.disabled = true;
        }
    }

    document.addEventListener("DOMContentLoaded", function () {
        document.getElementById("signinForm").addEventListener("reset", function () {
            emailValid = false;
            document.getElementById("submitBtn").disabled = true;
            document.getElementById("emailError").innerText = "";
        });
    });
</script>

<%@ include file="footer.jsp" %>

</body>
</html>
