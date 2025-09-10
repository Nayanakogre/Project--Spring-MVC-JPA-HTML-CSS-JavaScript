<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Reset Password</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>

<body class="bg-light">

<%@ include file="navbar.jsp" %>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6 col-sm-8">
            <div class="card shadow rounded">
                <div class="card-header text-center bg-success text-white">
                    <h4>Reset Password</h4>
                </div>

                <div class="card-body">
                    <form onsubmit="return validateAndSubmit()">
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" class="form-control" id="email" name="email"
                                   value="${email}" />
                        </div>

                        <div class="form-group">
                            <label for="newPassword">New Password</label>
                            <input type="password" class="form-control" id="newPassword" required />
                        </div>

                        <div class="form-group">
                            <label for="confirmPassword">Confirm Password</label>
                            <input type="password" class="form-control" id="confirmPassword" required />
                        </div>

                        <div id="responseMessage" class="text-center mb-2"></div>

                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Update Password</button>
                        </div>
                    </form>
                </div>

                <div class="card-footer text-center">
                    <a href="signIn.jsp">Back to Sign In</a>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    function validateAndSubmit() {
        const email = document.getElementById("email").value.trim();
        const newPassword = document.getElementById("newPassword").value.trim();
        const confirmPassword = document.getElementById("confirmPassword").value.trim();
        const responseDiv = document.getElementById("responseMessage");

        if (newPassword !== confirmPassword) {
            responseDiv.classList.remove("text-success");
            responseDiv.classList.add("text-danger");
            responseDiv.innerText = "Passwords do not match.";
            return false;
        }

        const xhr = new XMLHttpRequest();
        xhr.open("POST", "${pageContext.request.contextPath}/api/updatePassword", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        const params = "email=" + encodeURIComponent(email) +
                       "&newPassword=" + encodeURIComponent(newPassword) +
                       "&confirmPassword=" + encodeURIComponent(confirmPassword);

        xhr.onload = function () {
            if (xhr.status === 200) {
                if (xhr.responseText.trim() === "true") {
                    responseDiv.classList.remove("text-danger");
                    responseDiv.classList.add("text-success");
                    responseDiv.innerText = "Password updated successfully.";
                    setTimeout(() => {
                        window.location.href = "signIn.jsp";
                    }, 2000);
                } else {
                    responseDiv.classList.remove("text-success");
                    responseDiv.classList.add("text-danger");
                    responseDiv.innerText = "Failed to update password. Try again.";
                }
            } else {
                responseDiv.classList.add("text-danger");
                responseDiv.innerText = "Server error.";
            }
        };

        xhr.onerror = function () {
            responseDiv.classList.add("text-danger");
            responseDiv.innerText = "Request failed.";
        };

        xhr.send(params);
        return false;
    }
</script>

<%@ include file="footer.jsp" %>

</body>
</html>
