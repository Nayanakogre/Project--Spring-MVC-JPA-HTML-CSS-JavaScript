<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Image Options</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 30px;
            text-align: center;
        }

        h2 {
            margin-bottom: 40px;
        }

        .button {
            padding: 12px 25px;
            margin: 15px;
            font-size: 16px;
            cursor: pointer;
            border: none;
            background-color: #4CAF50;
            color: white;
            border-radius: 6px;
            text-decoration: none;
        }

        .button:hover {
            background-color: #45a049;
        }

        .container {
            margin-top: 80px;
        }
    </style>
</head>

<body>

<!--<%@ include file="planeNavbar.jsp" %>-->

<div class="container">
    <h2>Choose an Option</h2>

    <!-- Upload Page -->
    <a href="imageUpload.jsp" class="button">Upload File</a>

    <!-- Download Page -->
    <a href="download.jsp" class="button">Download File</a>
</div>

<!--<%@ include file="footer.jsp" %>-->

</body>
</html>
