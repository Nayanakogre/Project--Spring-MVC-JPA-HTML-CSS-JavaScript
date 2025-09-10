<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false" %>


<html>
<head>
    <title>Download Image</title>
</head>
<body>
<h2>Download Image</h2>
<form method="get" action="${pageContext.request.contextPath}/downloadImagee">
    Enter Email: <input type="email" name="email" required />
    <input type="submit" value="Download" />
</form>
</body>
</html>

