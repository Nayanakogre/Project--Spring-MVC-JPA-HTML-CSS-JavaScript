<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="com.xworkz.project.dto.AdminDto" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html xmlns:c="http://www.w3.org/1999/XSL/Transform">
<head>
    <title>Admin Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS CDN -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

    <style>
        body {
            background-color: #f8f9fa;
        }

        .login-container {
            margin-top: 100px;
        }

        .card {
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px #ccc;
        }

        .btn-otp {
            width: 100%;
        }
    </style>
</head>
<body>
<%@ include file="adminnavbar.jsp" %>
<script>
    function handleEmailCheck() {
     var email = document.getElementById("email").value.trim();
     var errorSpan = document.getElementById("emailerror");

     if (email === "") {
         errorSpan.innerText = "Email should not be empty";
         return;
     }

     var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
     if (!emailPattern.test(email)) {
         errorSpan.innerText = "Please enter a valid email address";
         return;
     }

     const request = new XMLHttpRequest();
     request.open("GET", "http://localhost:8080/project/api/checkEmail?email=" + encodeURIComponent(email));


     request.onload = function () {
         if (request.status === 200) {
             var response = request.responseText.trim();
             if (response === "true") {
                 errorSpan.innerText = "";
             } else {
                 errorSpan.innerText = "Email not found!";
             }
         }
     };

     request.send();
 }

  function sendOtp(){
    console.log("OTp function:");
    var email=document.getElementById("email").value;
    const request=new XMLHttpRequest();
    request.open("POST","http://localhost:8080/project/api/sendotp?email="+encodeURIComponent(email));
    request.onload=function()
    {
    if(request==200)
    {
    var response=request.responseText.trim();
    if(response=="true")
    {
    errorSpan.innerText="";
    }else{
    errorSpan.innerText="OTP not sent!";
    }
    }
    };
    request.send();
    }

</script>

<div class="container login-container">
     <span class="text-white mr-2">
            <%
                String email = (String) request.getAttribute("email");
                out.print(email != null ? email : "Guest");
            %>
        </span>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <h3 class="text-center mb-4">Admin OTP Login</h3>

                <!-- Email Form: Generate OTP -->
                <form action="verifyOtp" method="post">
                    <div class="form-group">
                        <label for="email">Email Address</label>
                        <input type="email" class="form-control" name="email" id="email" onchange="handleEmailCheck()" required>
                        <span id="emailerror" class="text-danger small"></span>
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn btn-primary btn-otp" onclick="sendOtp()">Generate OTP</input>
                    </div>

                <!-- OTP Verification Form -->
                <div id="otpSection" class="mt-4">

                        <div class="form-group">
                            <label for="otp">Enter OTP</label>
                            <input type="text" class="form-control" name="otp" id="otp"required>
                        </div>
                        <input type="submit" class="btn btn-success btn-otp" >Verify OTP</input>
                    </form>
                </div>

                <div class="text-center text-success mt-3">
                    <c:if test="${not empty otpSent}">
                        <div class="alert alert-success text-center">${otpSent}</div>
                    </c:if>
                    <c:if test="${not empty otpError}">
                        <div class="alert alert-danger text-center">${otpError}</div>
                    </c:if>
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger text-center">${error}</div>
                    </c:if>
                    <c:if test="${not empty message}">
                        <div class="alert alert-success text-center">${message}</div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- JavaScript to show OTP field -->
<script>
    function showOtpField() {
        document.getElementById("otpSection").style.display = "block";
    }
</script>

<!-- Bootstrap JS CDN -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<%@ include file="footer.jsp" %>
</body>
</html>
