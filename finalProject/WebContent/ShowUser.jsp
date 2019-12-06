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
<title>Show User Profile</title>
</head>
<body>

	<nav
		class="navbar navbar-expand-lg navbar-light bg-light justify-content-between">
	<a class="navbar-brand" href="findstargazingplace">Home</a>
	<c:if test="${Logstate == 'Log Out'}">
	
	<div class="collapse navbar-collapse" id="navbarText">
	
	<a class="navbar-brand" href="showuser">UserProfile</a>
	<a class="navbar-brand" href="userupdate">Update Profile</a>
	</div>
	</c:if>
	<div class="collapse navbar-collapse" id="navbarText">
		<span class="navbar-text ml-auto"><a class="nav-link"
			href="${Logstatehref}">${Logstate}</a></span>
	</div>
	
	</nav>

	<div class="mr-2 ml-2">
		<h2>User Profile</h2>

		<br />
		<div>
			<span class="form-text text-info" id="successMessage"><b>${messages.success}</b></span>
		</div>
		<br />
		<div>
			<p>
				<Strong>User Id:</Strong> ${user.getUserId()}
			</p>
			<p>
				<Strong>User Name:</Strong> ${user.getUserName()}
			</p>
			<p>
				<Strong>FirstName:</Strong> ${user.getFirstName()}
			</p>
			<p>
				<Strong>LastName:</Strong> ${user.getLastName()}
			</p>
			<p>
				<Strong>Address:</Strong> ${user.getStreet()} ${user.getCity()} ${user.getState()} ${user.getZip()}
			</p>
			<p>
				<Strong>Email:</Strong> ${user.getEmail()}
			</p>
			<p>
				<Strong>Phone:</Strong> ${user.getPhone()}
			</p>
			<p>
				<Strong>Level:</Strong> ${user.getUserLevel()}
			</p>
		
		</div>

		<br />
		<h3>History Comments</h3>
		<!-- show comments list -->
		<div class="row">
			<c:forEach items="${reviews}" var="review">
				<div class="col-md-12">
					<span style="float: right"><fmt:formatDate
							value="${review.getCreatedTime()}" type="date" /></span> <span
						class="text-primary">${review.getContent()}</span> <span
						class="text">(${review.getRating()})</span>
				</div>
			</c:forEach>
		</div>

		<br />
		<br />
		<h3>History Recommended Places</h3>
		<!-- show recommended places ids -->
		<div class="row">
			<c:forEach items="${places}" var="place">
				<div class="col-md-12">
					<a
						href="showplace?placeid=<c:out value="${fn:escapeXml(place)}"/>">(${place})</a>
				</div>
			</c:forEach>
		</div>

		<br />
		
	</div>

</body>
</html>
