<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<title>Update a User</title>
</head>
<body>
	<nav
		class="navbar navbar-expand-lg navbar-light bg-light justify-content-between">
	<a class="navbar-brand" href="findstargazingplace">Home</a>
	<div class="collapse navbar-collapse" id="navbarText">
		<span class="navbar-text ml-auto"><a class="nav-link"
			href="${Logstatehref}">${Logstate}</a></span>
	</div>
	</nav>
	<div class="mr-2 ml-2">
		<h2>Update User</h2>
		<form action="userupdate" method="post">
			<div class="form-group form-inline">
				<label class="text-secondary col-2" for="userid">User Id</label> <input
					class="form-control" id="userid" name="userid"
					value="${fn:escapeXml(param.userid)}">
			</div>
			<div class="form-group form-inline">
				<label class="text-secondary col-2" for="lastname">New
					LastName</label> <input class="form-control" id="lastname" name="lastname"
					value="">
			</div>
			<div class="form-group form-inline offset-md-2">
				<input class="btn btn-info ml-2 mr-2" type="submit">
			</div>
		</form>
		<br />
		<br />
		<p>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
		<div id="findusers">
			<a href="findusers">Go Back To Find Users</a>
		</div>
	</div>

</body>
</html>