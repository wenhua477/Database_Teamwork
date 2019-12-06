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
<title>Find a Stargazing User</title>
</head>
<body>

	<nav
		class="navbar navbar-expand-lg navbar-light bg-light justify-content-between">
	<a class="navbar-brand" href="findstargazingplace">Home</a>
	<c:if test="${Logstate == 'Log Out'}">
	
	<div class="collapse navbar-collapse" id="navbarText">
	
	<a class="navbar-brand" href="showuser">UserProfile</a>
	</div>
	</c:if>
	<div class="collapse navbar-collapse" id="navbarText">
		<span class="navbar-text ml-auto"><a class="nav-link"
			href="${Logstatehref}">${Logstate}</a></span>
	</div>
	
	</nav>
	<c:if test="${Logstate != 'Log Out' || username != 'admin'}">
		<h2>Only administrator can visit this page.</h2>
		<a href="login">Log in here</a>
	</c:if>

	<c:if test="${Logstate == 'Log Out' && username == 'admin'}">
	<div class="mr-2 ml-2">
		<h2>Search for a Stargazing User by UserName</h2>
		<form action="findusers" method="post">
			<br />
			<div class="form-group form-inline">
				<label class="text-secondary mr-2" for="username">UserName</label>
				<input class="form-control" id="username" name="username"
					value="${fn:escapeXml(param.username)}"> <input
					class="btn btn-info ml-2 mr-2" type="submit">
			</div>
			<div>
				<span class="form-text text-info" id="successMessage"><b>${messages.success}</b></span>
			</div>
		</form>
		<br /> <br />
		<h3>Matching User</h3>
		<table class="table" border="1">
			<tr>
				<th>UserName</th>
				<th>FirstName</th>
				<th>LastName</th>
				<th>UserId</th>
				<th>State</th>
				<th>Level</th>
				<th>Delete User</th>
				<th>Update User</th>
				<th>View Detail</th>
			</tr>
			<c:forEach items="${users}" var="user">
				<tr>
					<td><c:out value="${user.getUserName()}" /></td>
					<td><c:out value="${user.getFirstName()}" /></td>
					<td><c:out value="${user.getLastName()}" /></td>
					<td><c:out value="${user.getUserId()}" /></td>
					<td><c:out value="${user.getState()}" /></td>
					<td><c:out value="${user.getUserLevel().name()}" /></td>



					<td><a
						href="userdelete?userid=<c:out value="${user.getUserId()}"/>">Delete</a></td>
					<td><a
						href="userupdate?userid=<c:out value="${user.getUserId()}"/>">Update</a></td>
					<td><a
						href="showuser?userid=<c:out value="${fn:escapeXml(user.getUserId())}"/>">View
								detail</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</c:if>


</body>
</html>
