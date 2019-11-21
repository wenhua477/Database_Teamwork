<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Show Stargazing Places</title>
</head>
<body class="mr-2 ml-2">
	<h2>Stargazing Places Info</h2>
	<form action="showplace" method="post">
		<br/>
		<div>
			<span class="form-text text-info" id="successMessage"><b>${messages.success}</b></span>
		</div>
		<br/>
		<div>
		<p><Strong>Place Id:</Strong> ${place.getPlaceId()}</p>
		<p><Strong>Name:</Strong> ${place.getName()}</p>
		<p><Strong>Latitude:</Strong> ${place.getLatitude()}</p>
		<p><Strong>Longitude:</Strong> ${place.getLongitude()}</p>
		<p><Strong>State:</Strong> ${place.getState()}</p>
<%-- 		<p><Strong>Capacity:</Strong> ${place.getCapacity()}</p> --%>
 		<p><Strong>Type:</Strong> ${place.getType()}</p>	
		</div>
	</form>
<!-- 	<br/> -->
</body>
</html>
