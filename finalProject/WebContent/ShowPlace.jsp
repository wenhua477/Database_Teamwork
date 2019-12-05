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
<title>Show Stargazing Places</title>
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

	<div class="mr-2 ml-2">
		<h2>Stargazing Places Info</h2>

		<br />
		<div>
			<span class="form-text text-info" id="successMessage"><b>${messages.success}</b></span>
		</div>
		<br />
		<div>
			<p>
				<Strong>Place Id:</Strong> ${place.getPlaceId()}
			</p>
			<p>
				<Strong>Name:</Strong> ${place.getName()}
			</p>
			<p>
				<Strong>Latitude:</Strong> ${place.getLatitude()}
			</p>
			<p>
				<Strong>Longitude:</Strong> ${place.getLongitude()}
			</p>
			<p>
				<Strong>State:</Strong> ${place.getState()}
			</p>
			<p>
				<Strong>fipId:</Strong> ${place.getFips()}
			</p>
			<p>
				<Strong>Type:</Strong> ${place.getType()}
			</p>
			<p>
				<Strong>Population:</Strong> ${location.getPopulation()}
			</p>
			<p>
				<Strong>Elevation:</Strong> ${location.getElevation()}
			</p>
			<p>
				<Strong>Crime Rate:</Strong> ${county.getCrimeRate()}
			</p>
		</div>
		<a href="CurrentWeather?placeid=<c:out value="${place.getPlaceId()}"/>">Check weather information</a>
		<!-- 	<br/> -->


		<br />
		<!-- show comments list -->
<%--  	  <div class="row">
			<c:forEach items="${reviews}" items = "${users}" var="review" var="user"  >	
				<div class="col-md-12">
					<span style="float: right"><fmt:formatDate
							value="${review.getCreatedTime()}" type="date" /></span> <span
						class="text-primary">${review.getContent()}</span> <span
						class="text">(${review.getRating()})</span>
						 <span
						class="text">(${review.getUserId()})</span>
						<span
						class="text">(${user.getUserName()})</span>
				</div>
			</c:forEach>
		</div> 
		
		
		<div>
 --%>



<c:forEach var="user" items="${users}" varStatus="status">
  <tr>
  					<span style="float: right"><fmt:formatDate
							value="${reviews[status.index].getCreatedTime()}" type="date" />
							</span>
          <td>${reviews[status.index].getRating()}</td>
      <td>${reviews[status.index].getContent()}</td>
						<span class="text">(${user.getUserName()})</span>

  </tr>
  <br/>
</c:forEach>
		</div>
		
		
		
		
		<!-- </div>  -->

		<br />
		<!--  add comment-->
		<c:if test="${Logstate == 'Log Out'}">
			<form action="addComment" method="post">
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
			</form>
		</c:if>

		<!--  add recommendation-->
		<c:if test="${Logstate == 'Log Out'}">
			<form action="addrecommendation" method="post">
					<div class="form-group col-md-2">
						<input class="btn btn-info mt-4" id="placeid" name="placeid" type="submit" value="Like!" />
					</div>
			</form>
		</c:if>

	</div>
	<div>
	<c:if test="${place.getPlaceId() > 1}">
			<a href="showplace?placeid=<c:out value="${place.getPlaceId()-1}"/>">  Previous Stargazing Place</a>
	</c:if>
	</div>


	<c:if test="${place.getPlaceId() < 2925}">
			<a href="showplace?placeid=<c:out value="${place.getPlaceId()+1}"/>">Next Stargazing Place</a>
	</c:if>
	  <br/>
	    <br/>
	

</body>

</html>
