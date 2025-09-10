<!DOCTYPE html>
<html>
<head>
    <title>Registration Form</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap 4 CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

    <!-- Fixed footer style -->
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

<script>
    function handlenamechange() {
    var name = document.getElementById("userName").value;
    if (name == "") {
    document.getElementById("error").innerHTML = "Name is empty";
    } else if (name.length <= 3) {
    document.getElementById("error").innerHTML = "Name should contain more than 3 characters";
    } else {
    document.getElementById("error").innerHTML = "";
    }
    checkAllValid();
    }




function validatePassword() {
    const password = document.getElementById("password").value;
    const confirm = document.getElementById("confirmPassword").value;

    const strongRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_]).{8,16}$/;

    if (password === "") {
        document.getElementById("passwordError").innerText = "Password should not be empty";
    } else if (!strongRegex.test(password)) {
        document.getElementById("passwordError").innerText =
            "Password must be 8-16 characters and include uppercase, lowercase, number, and special character";
    } else {
        document.getElementById("passwordError").innerText = "";
    }

    if (confirm === "") {
        document.getElementById("confirmError").innerText = "Please confirm your password";
    } else if (password !== confirm) {
        document.getElementById("confirmError").innerText = "Passwords do not match";
    } else {
        document.getElementById("confirmError").innerText = "";
    }
    checkAllValid();
}

    function handleAdress() {
    var address1 = document.getElementById("adressLine1").value;
    var address2 = document.getElementById("adressLine2").value;

    if (address1 == "") {
    document.getElementById("adressError").innerHTML = "Address Line 1 should not be empty";
    } else if (address1.length < 8) {
    document.getElementById("adressError").innerHTML = "Address Line 1 should be at least 8 characters";
    } else {
    document.getElementById("adressError").innerHTML = "";
    }

    if (address2 == "") {
    document.getElementById("adressError1").innerHTML = "Address Line 2 should not be empty";
    } else if (address2.length < 8) {
    document.getElementById("adressError1").innerHTML = "Address Line 2 should be at least 8 characters";
    } else {
    document.getElementById("adressError1").innerHTML = "";
    }
    checkAllValid();
    }



   function handleEmailCheck() {
    var email = document.getElementById("email").value.trim();
    var errorSpan = document.getElementById("emailerror");

    if (email === "") {
        errorSpan.innerText = "Email should not be empty";
        checkAllValid();
        return;
    }

    var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailPattern.test(email)) {
        errorSpan.innerText = "Please enter a valid email address";
        checkAllValid();
        return;
    }

    const request = new XMLHttpRequest();
    request.open("GET", "http://localhost:8080/project/api/emailCheck?email=" + email);

    request.onload = function () {
        if (request.status === 200) {
            var response = request.responseText.trim();
            if (response === "false") {
                errorSpan.innerText = ""; // Email not present, okay to proceed
            } else {
                errorSpan.innerText = "Email Already Present";
            }
            checkAllValid();
        }
    };

    request.send();
}




  function contactChange() {
    var contactNumber = document.getElementById("contactNumber").value.trim();

    if (contactNumber === "") {
        document.getElementById("lengthError").innerText = "Contact number should not be empty";
        checkAllValid();
        return;
    }

    if (contactNumber.length !== 10) {
        document.getElementById("lengthError").innerText = "Contact number should be exactly 10 digits";
        checkAllValid();
        return;
    }

    if (!/^[6-9]\d{9}$/.test(contactNumber)) {
        document.getElementById("lengthError").innerText = "Enter a valid contact number (starts with 6-9)";
        checkAllValid();
        return;
    }

    const request = new XMLHttpRequest();
    request.open("GET", "http://localhost:8080/project/api/contactCheck?contactNumber=" + contactNumber);

    request.onload = function () {
        if (request.status === 200) {
            var response = request.responseText.trim();
            if (response === "false") {
                document.getElementById("lengthError").innerText = "";
            } else {
                document.getElementById("lengthError").innerText = "Contact Already Present";
            }
            checkAllValid();
        }
    };

    request.send();
}



     function alternateChange() {
        var contact = document.getElementById("inputAlternateContact3").value;
        var errorSpan = document.getElementById("alternateContactError");

        if (contact.length !== 10) {
            errorSpan.innerText = "Contact number must be 10 digits";
        } else {
            errorSpan.innerText = "";
        }
        checkAllValid();
    }

    function cityChange() {
        var city = document.getElementById("inputCity").value.trim();
        var errorSpan = document.getElementById("cityError");

        if (city === "") {
            errorSpan.innerText = "City name should not be empty";
        } else if (!/^[A-Za-z\s]+$/.test(city)) {
            errorSpan.innerText = "City name should contain only letters";
        } else {
            errorSpan.innerText = "";
        }
        checkAllValid();
    }

    function validateState() {
        var state = document.getElementById("inputState").value;
        var errorSpan = document.getElementById("stateError");

        if (state === "Choose...") {
            errorSpan.innerText = "Please select a valid state";
        } else {
            errorSpan.innerText = "";
        }
        checkAllValid();
    }

       function validatePinCode() {
        var pin = document.getElementById("inputZip").value.trim();
        var errorSpan = document.getElementById("pinError");

        if (pin === "") {
            errorSpan.innerText = "PIN code should not be empty";
        } else if (!/^\d{6}$/.test(pin)) {
            errorSpan.innerText = "PIN code must be exactly 6 digits";
        } else {
            errorSpan.innerText = "";
        }
        checkAllValid();
    }

  function checkAllValid() {
    const get = id => document.getElementById(id).value.trim();
    const hasError = id => document.getElementById(id).innerText.trim() !== "";

    const allValid =
        get("userName").length > 3 &&
        /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_]).{8,16}$/.test(get("password")) &&
        get("password") === get("confirmPassword") &&
        get("adressLine1").length >= 8 &&
        get("adressLine2").length >= 8 &&
        /^[A-Za-z\s]+$/.test(get("inputCity")) &&
        get("inputState") !== "Choose..." &&
        /^\d{6}$/.test(get("inputZip")) &&
        /^[6-9]\d{9}$/.test(get("contactNumber")) &&
        /^[6-9]\d{9}$/.test(get("inputAlternateContact3")) &&
        /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(get("email")) &&
        !hasError("error") &&
        !hasError("passwordError") &&
        !hasError("confirmError") &&
        !hasError("adressError") &&
        !hasError("adressError1") &&
        !hasError("cityError") &&
        !hasError("stateError") &&
        !hasError("pinError") &&
        !hasError("lengthError") &&
        !hasError("alternateContactError") &&
        !hasError("emailerror");

    console.log("All valid? ", allValid); // Debug here

    document.getElementById("signUpBtn").disabled = !allValid;
}





</script>





</head>
<body>

<!-- Navbar include -->
<%@ include file="navbar.jsp" %>

<!-- Main form container -->
<div class="container mt-5 mb-5 pb-5">
    <div class="card shadow p-4 col-md-8 mx-auto">
        <h4 class="text-center mb-4">Registration Form</h4>
        <form action="signup" method="post">
            <div class="form-group">
                <label for="userName">User Name</label>
                <input type="text" class="form-control" name="userName" id="userName" placeholder="Enter full name" onchange="handlenamechange()">
               <span id="error" style="color:red"></span>
            </div>

            <div class="form-group">
                <label for="contactNumber">Contact Number</label>
                <input type="number" class="form-control" name="contactNumber" id="contactNumber" placeholder="e.g., 9876543210" onchange=" contactChange()">
                <span id="contactError" style="color:red"></span>
                <span id="lengthError" style="color:red"></span>
            </div>

            <div class="form-group">
                <label for="inputAlternateContact3">Alternate Contact Number</label>
                <input type="number" class="form-control" name="alternateContactNumber" id="inputAlternateContact3" placeholder="e.g., 9876543210" onchange=" alternateChange()()">
                <span id="alternateContactError" style="color: red;"></span>
            </div>

            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" name="email" id="email" placeholder="e.g., example@email.com" onchange="handleEmailCheck()">
                <span id="emailerror" style="color:red"></span>
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" name="password" id="password" onchange="handlePasswordLength()">
                <span id="passwordError" style="color:red"></span>
            </div>

            <div class="form-group">
                <label for="confirmPassword">Confirm Password</label>
                <input type="password" name="confirmPassword"class="form-control" id="confirmPassword" onchange="handlePasswordLength()">
                <span id="confirmError" style="color:red"></span>
            </div>

            <!-- ✅ Professional Address Format -->
            <div class="form-group">
                <label for="adressLine1">Address Line 1 <span class="text-muted">(House number, Street name)</span></label>
                <input type="text" name="adressLine1" class="form-control" id="adressLine1" placeholder="e.g., #123, MG Road" onchange="handleAdress()">
                <span id="adressError" style="color:red"></span>
            </div>


            <div class="form-group">
                <label for="adressLine1">Address Line 2 <span class="text-muted">(House number, Street name)</span></label>
               <input type="text" name="adressLine2" class="form-control" id="adressLine2"
                   placeholder="e.g., Near Metro Station, Indiranagar"
                   onchange="handleAdress()">
                <span id="adressError1" style="color:red"></span>
            </div>

            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="inputCity">City</label>
                    <input type="text" name="city" class="form-control" id="inputCity" placeholder="e.g., Bengaluru" onchange="cityChange()">
                    <span id="cityError" style="color: red;"></span>
                </div>
            </div>

            <div class="form-group col-md-4">
                <label for="inputState">State</label>
                <select id="inputState" name="state" class="form-control" onchange="validateState()">
                    <option selected disabled>Choose...</option>
                    <option>Andhra Pradesh</option>
                    <option>Arunachal Pradesh</option>
                    <option>Assam</option>
                    <option>Bihar</option>
                    <option>Chhattisgarh</option>
                    <option>Goa</option>
                    <option>Gujarat</option>
                    <option>Haryana</option>
                    <option>Himachal Pradesh</option>
                    <option>Jharkhand</option>
                    <option>Karnataka</option>
                    <option>Kerala</option>
                    <option>Madhya Pradesh</option>
                    <option>Maharashtra</option>
                    <option>Manipur</option>
                    <option>Meghalaya</option>
                    <option>Mizoram</option>
                    <option>Nagaland</option>
                    <option>Odisha</option>
                    <option>Punjab</option>
                    <option>Rajasthan</option>
                    <option>Sikkim</option>
                    <option>Tamil Nadu</option>
                    <option>Telangana</option>
                    <option>Tripura</option>
                    <option>Uttar Pradesh</option>
                    <option>Uttarakhand</option>
                    <option>West Bengal</option>
                    <option>Andaman and Nicobar Islands</option>
                    <option>Chandigarh</option>
                    <option>Dadra and Nagar Haveli and Daman and Diu</option>
                    <option>Delhi</option>
                    <option>Lakshadweep</option>
                    <option>Puducherry</option>
                    <option>Ladakh</option>
                    <option>Jammu and Kashmir</option>
                </select>
                <span id="stateError" style="color: red;"></span>
            </div>

            <div class="form-group col-md-2">
                <label for="inputZip">PIN Code</label>
                <input type="text" name="pin" class="form-control" id="inputZip" placeholder="e.g., 560001" onchange="validatePinCode()">
                <span id="pinError" style="color: red;"></span>
            </div>

            <div class="form-group form-check">
                <input type="checkbox" id="gridCheck" onchange="checkAllValid()">


                <label class="form-check-label" for="gridCheck">
                    Check me out
                </label>
            </div>

            <!-- ✅ Centered Buttons -->
            <div class="d-flex justify-content-center">
                <button type="submit" id="signUpBtn" class="btn btn-primary" disabled>Sign Up</button>

                <button type="reset" class="btn btn-secondary mx-2">Reset</button>
            </div>
        </form>
    </div>
</div>

<!-- Footer include -->
<%@ include file="footer.jsp" %>

</body>
</html>
