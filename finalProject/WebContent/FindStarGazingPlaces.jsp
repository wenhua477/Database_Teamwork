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
<title>Find Stargazing Places</title>
</head>
<body class="mr-2 ml-2">
	<h2>Search for Stargazing Places</h2>
	<form action="findstargazingplace" method="post">
		<br/>
	    <div class="form-group form-inline">
	      <!-- <label class="text-secondary col-1">Location</label> -->
	      <label>Latitude</label>
	      <input class = "form-control" id="latitude" name="latitude" value="${fn:escapeXml(param.latitude)}">

	      <label>Longitude</label>
	      <input class = "form-control" id="longitude" name="longitude" value="${fn:escapeXml(param.longitude)}">
	      <label>Radius</label>
	      <input class = "form-control" id="radius" name="radius" value="${fn:escapeXml(param.radius)}">
	    </div>
	    <div class="form-group form-inline offset-md-1">
			<input class="btn btn-info mr-2" type="submit">
		</div>
		<div>
			<span class="form-text text-info" id="successMessage"><b>${messages.success}</b></span>
		</div>
		<BR/>
		<table class="table" border="1">
            <tr>
                <th>Place Id</th>
                <th>Latitude</th>
                <th>Longitude</th>
                <th>State</th>
                <th>Distance</th>
                <th>Detail</th>

            </tr>
            <c:forEach items="${places}" var="place" >
                <tr>
                    <td><c:out value="${place.getPlaceId()}" /></td>
                    <td><c:out value="${place.getLatitude()}" /></td>
                    <td><c:out value="${place.getLongitude()}" /></td>
                    <td><c:out value="${place.getState()}" /></td>
                    <td><c:out value="${place.getDistance()}" /></td>
                    <td><a href="stargazingplace?placeid=<c:out value="${place.getPlaceId()}"/>">View detail</a></td>

                </tr>
            </c:forEach>
       	</table>
	</form>
	<br/>
</body>
</html>
