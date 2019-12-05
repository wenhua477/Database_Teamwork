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
<title>Top 10 Visited Places</title>
</head>
<body class="mr-2 ml-2">

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
                    <td><a href="showplace?placeid=<c:out value="${place.getPlaceId()}"/>">View detail</a></td>

                </tr>
            </c:forEach>
       	</table>
	</form>
	<br/>
</body>
</html>
