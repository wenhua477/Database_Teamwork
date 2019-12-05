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
<title>Find Stargazing Places</title>
</head>
<body>
  <% String uname = (String) session.getAttribute("username");
    if (null == uname) {
    session.setAttribute("Logstate", "Login ");
    session.setAttribute("Logstatehref", "login");
    } %>
	<nav
		class="navbar navbar-expand-lg navbar-light bg-light justify-content-between">
	<a class="navbar-brand" href="findstargazingplace">Home</a>
	<div class="collapse navbar-collapse" id="navbarText">
		<span class="navbar-text ml-auto"><a class="nav-link" href="${Logstatehref}">${Logstate}</a></span>
	</div>
	</nav>

	<div class="mr-2 ml-2">
		<h2>Search for Stargazing Places</h2>
		<form action="findstargazingplace" method="post">
			<br />
			<div class="form-group form-inline">
				<label class="text-secondary col-1">Location</label> <input
					class="form-control col-4" id="location" name="location"
					value="${fn:escapeXml(param.location)}"> <label
					class="text-secondary col-1">Radius</label> <input
					class="form-control" id="radius" name="radius"
					value="${fn:escapeXml(param.radius)}">
			</div>
			<div class="form-group form-inline offset-md-1">
				<input class="btn btn-info mr-2" type="submit">
			</div>
			<div>
				<span class="form-text text-info" id="successMessage"><b>${messages.success}</b></span>
			</div>
			<BR /> <BR /> 
			<a href="top10visitedplaces" />>Check Top 10 Visited Places</a> <BR /> 
			<a href="top10recommendedplaces" />>Check Top 10 Recommended Places</a> <BR /> 
			<a href="top10ratingplaces" />>Check Top 10 Rating Places</a> <BR />
			<a href="top10byElevation"?sessionid=<c:out value="${fn:escapeXml(session.getId())}"  />>Check Top 10 Places by Elevation</a> 

<%-- 			<form action="addComment" method="post">
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="comment">Comment</label> <input type="text"
							class="form-control" id="comment" name="comment">
					</div>
					<div class="form-group col-md-4">
						<label for="rating">Rating</label> <select id="rating"
							class="form-control" name="rating">
							<option selected>Choose Rate</option>
							<option value=5.0 selected>5.0</option>
							<option value=4.0>4.0</option>
							<option value=3.0>3.0</option>
							<option value=2.0>2.0</option>
							<option value=1.0>1.0</option>
						</select>
					</div>
					<input type="hidden" class="form-control" id="placeid"
						name="placeid" value="${place.getPlaceId()}">
					<div class="form-group col-md-2">
						<input class="btn btn-info mt-4" type="submit" value="Add Review" />
					</div>
				</div>
			</form> --%>
	
			
			
			<BR /> <BR />
			
			
			<table class="table" border="1">
				<tr>
					<th>Place Id</th>
					<th>Latitude</th>
					<th>Longitude</th>
					<th>State</th>
					<th>Distance</th>
					<th>Detail</th>

				</tr>
				<c:forEach items="${places}" var="place">
					<tr>
						<td><c:out value="${place.getPlaceId()}" /></td>
						<td><c:out value="${place.getLatitude()}" /></td>
						<td><c:out value="${place.getLongitude()}" /></td>
						<td><c:out value="${place.getState()}" /></td>
						<td><c:out value="${place.getDistance()}" /></td>
						<td><a
							href="showplace?placeid=<c:out value="${fn:escapeXml(place.getPlaceId())}"/>">View
								detail</a></td>

					</tr>
				</c:forEach>
			</table>
		</form>
		<br />
	</div>
</body>
</html>
