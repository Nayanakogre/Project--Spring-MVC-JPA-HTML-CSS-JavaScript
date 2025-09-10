<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html xmlns:c="http://www.w3.org/1999/XSL/Transform">
<head>
    <title>Upload Image</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f5f5f5;
            margin: 50px;
        }
        .upload-box {
            width: 400px;
            margin: 0 auto;
            padding: 25px;
            background: #fff;
            border: 1px solid #ddd;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .upload-box h2 {
            text-align: center;
        }
        .preview {
            text-align: center;
            margin-top: 15px;
        }
        .preview img {
            max-width: 100%;
            height: auto;
            border-radius: 8px;
        }
    </style>
</head>
<body>

<div class="upload-box">
    <h2>Upload Image</h2>

    <form action="${pageContext.request.contextPath}/uploadImage" method="post" enctype="multipart/form-data">
        <input type="email" name="email" required />
        <input type="file" name="image" accept="image/*" required />
        <input type="submit">Upload</input>
    </form>


    <c:if test="${not empty message}">
        <p style="color: green; text-align: center;">${message}</p>
    </c:if>

    <c:if test="${not empty filePath}">
        <div class="preview">
            <p><strong>Uploaded Image:</strong></p>
            <img src="${pageContext.request.contextPath}/${filePath}" alt="Uploaded Image">
        </div>
    </c:if>
</div>
<c:if test="${not empty message}">
    <p style="color: green; text-align: center;">${message}</p>
</c:if>


</body>
</html>
