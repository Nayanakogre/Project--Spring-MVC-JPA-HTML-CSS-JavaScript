<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.net.URLEncoder" %>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

<style>
    .custom-navbar {
        background-color: LightSeaGreen;
    }

    .custom-toggler {
        background-color: white;
        border: none;
        outline: none;
        padding: 6px 10px;
        border-radius: 4px;
        cursor: pointer;
    }

    .custom-hamburger {
        width: 20px;
        height: 2px;
        background-color: black;
        margin: 3px 0;
    }

    .dropdown-menu-right {
        right: 0;
        left: auto;
    }

    .navbar-brand img {
        height: 40px;
        width: auto;
    }
</style>

<nav class="navbar navbar-expand-lg custom-navbar navbar-dark">
    <a class="navbar-brand d-flex align-items-center" href="#">
        <img src="https://raw.githubusercontent.com/X-workzDev01/xworkzwebsite/master/src/main/webapp/assets/images/Logo.png" alt="Logo">
    </a>

    <div class="ml-auto d-flex align-items-center">
        <!-- Email Display -->
        <span class="text-white mr-2">
            <%
                String email = (String) request.getAttribute("email");
                out.print(email != null ? email : "Guest");
            %>
        </span>

        <!-- Hamburger Menu Dropdown -->
        <div class="dropdown">
            <button class="custom-toggler" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <div class="custom-hamburger"></div>
                <div class="custom-hamburger"></div>
                <div class="custom-hamburger"></div>
            </button>
            <div class="dropdown-menu dropdown-menu-right mt-2" aria-labelledby="dropdownMenuButton" style="min-width: 150px;">

                <%
                if (email != null && !email.isEmpty()) {
                String encodedEmail = URLEncoder.encode(email, "UTF-8");
                %>
                <a class="dropdown-item" href="/project/getByEmail?email=<%= encodedEmail %>">Update</a>

                <form action="<%= request.getContextPath() %>/softDelete" method="post">

                <input type="hidden" name="email" value="<%= email %>">
                    <button type="submit" class="dropdown-item text-danger" style="width: 100%; text-align: left;">Delete</button>
                </form>
                <%
                } else {
                %>
                <a class="dropdown-item disabled" href="#">Update</a>
                <a class="dropdown-item text-danger disabled" href="#">Delete</a>
                <%
                }
                %>

                <a class="dropdown-item" href="/settings">Settings</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item text-danger" href="/logout">Logout</a>
            </div>
        </div>
    </div>
</nav>

<!-- Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
