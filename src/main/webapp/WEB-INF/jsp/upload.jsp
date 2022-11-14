<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>

<h1>Spring MVC file upload example</h1>

<form method="POST" action="/upload" enctype="multipart/form-data">
    <input type="file" name="file" /> <input type="submit" value="Submit" />
</form>

<h1>Upload Status</h1>

<h2>Message : ${message}</h2>

<h3>Download File Which You Have Upload : <a href="/download">Download Here</a></h3>

</body>
</html>