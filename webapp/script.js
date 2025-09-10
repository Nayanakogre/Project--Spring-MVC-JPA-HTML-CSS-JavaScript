
        function handlenamechange() {
            var name = document.getElementById("userName").value;
            if (name === "") {
                document.getElementById("error").innerHTML = "Name is empty";
            } else if (name.length <= 3) {
                document.getElementById("error").innerHTML = "Name should contain more than 3 characters";
            } else {
                document.getElementById("error").innerHTML = "";
            }
        }



        function handlePasswordLength() {
    var password = document.getElementById("password").value;
    var confirm = document.getElementById("confirmPassword").value;

    if (password === "") {
        document.getElementById("passwordError").innerHTML = "Password should not be empty";
    } else if (password.length < 8 || password.length > 16) {
        document.getElementById("passwordError").innerHTML = "Password length should be between 8 and 16";
    } else {
        document.getElementById("passwordError").innerHTML = "";
    }

    if (confirm === "") {
        document.getElementById("confirmError").innerHTML = "Confirm password should not be empty";
    } else if (confirm.length < 8 || confirm.length > 16) {
        document.getElementById("confirmError").innerHTML = "Confirm password length should be between 8 and 16";
    } else if (password !== confirm) {
        document.getElementById("confirmError").innerHTML = "Passwords do not match";
    } else {
        document.getElementById("confirmError").innerHTML = "";
    }
}
        function handleAdress() {
    var address1 = document.getElementById("adressLine1").value;
    var address2 = document.getElementById("adressLine2").value;

    if (address1 === "") {
        document.getElementById("adressError").innerHTML = "Address Line 1 should not be empty";
    } else if (address1.length < 8) {
        document.getElementById("adressError").innerHTML = "Address Line 1 should be at least 8 characters";
    } else {
        document.getElementById("adressError").innerHTML = "";
    }

    if (address2 === "") {
        document.getElementById("adressError1").innerHTML = "Address Line 2 should not be empty";
    } else if (address2.length < 8) {
        document.getElementById("adressError1").innerHTML = "Address Line 2 should be at least 8 characters";
    } else {
        document.getElementById("adressError1").innerHTML = "";
    }
}



             function handleEmailCheck(){
               var email =  document.getElementById("email").value;
                if(email!=""){
                     //invoke the API
                     console.log(email);
                    const request = new XMLHttpRequest();
                     request.open("GET","http://localhost:8080/project/api/emailCheck?email="+email);
                     console.log(request);
                     request.onload = function(){
                         if(request.status === 200){
                         console.log("request:"+request);
                         console.log(request.responseText);
                         var response = request.responseText;
                         if(response == false){
                             document.getElementById("emailerror").innerText = "Email is not present";
                           }else{
                              document.getElementById("emailerror").innerText = "Email Already Present";
                           }
                            }
                         }
                        request.send();
                }

                }


                function contactValidate()
                {
               var contact= document.getElementById("contactNumber").value
               if(contact!="")
               {
               console.log(contact);

               }
                }
