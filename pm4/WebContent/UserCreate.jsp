<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a User</title>
</head>
<body>
	<h1>Create User</h1>
	<form action="usercreate" method="post">
		<p>
			<label for="username">UserName</label>
			<input id="username" name="username" value="">
		</p>
		<p>
			<label for="firstname">FirstName</label>
			<input id="firstname" name="firstname" value="">
		</p>
		<p>
			<label for="lastname">LastName</label>
			<input id="lastname" name="lastname" value="">
		</p>
		<p>
			<label for="email">FirstName</label>
			<input id="email" name="email" value="">
		</p>
		<p>
			<label for="phone">FirstName</label>
			<input id="phone" name="phone" value="">
		</p>
		<p>
			<label for="street">FirstName</label>
			<input id="street" name="street" value="">
		</p>
		<p>
			<label for="city">FirstName</label>
			<input id="city" name="city" value="">
		</p>
		<p>
			<label for="state">FirstName</label>
			<input id="state" name="state" value="">
		</p>
		<p>
			<label for="zip">FirstName</label>
			<input id="zip" name="zip" value="">
		</p>
		<p>
			<label for="password">FirstName</label>
			<input id="password" name="password" value="">
		</p>

		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>