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
<title>Delete a User</title>
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
		<h2>${messages.title}</h2>
		<form action="userdelete" method="post">
		<p>
			<div class="form-group form-inline" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
				<label class="text-secondary" for="userid">UserId</label>
				<input class="form-control ml-1" id="userid" name="userid" value="${fn:escapeXml(param.userid)}">
			</div>
		</p>
		<p>
			<span class="form-text text-info" id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<input class="btn btn-info ml-5 mr-2" type="submit">
			</span>
		</p>
		</form>
		<br />
		<br />
		<div id="findusers">
			<a href="findusers">Go Back To Find Users</a>
		</div>
	</div>
</body>
</html>