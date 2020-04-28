<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello World Java EE</title>
</head>
<body>
    <h1>Hello JSP and Servlet!</h1>
	<form action="view" method="get">
    	First Number: <input type="text" name="FirstNumber" size="20"> <br>
    	Second Number: <input type="text" name="SecondNumber" size="20"><br>
    	Operation: <input type="text" name="Operation" size="5"><br>
    	<input type="submit" value="Call Servlet" />
	</form>
</body>
</html>