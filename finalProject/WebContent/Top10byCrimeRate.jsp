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
<title>Top 10 by Crime</title>
</head>
<body >


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
		<h2>Sort query Result by CrimeRate</h2>
		<BR/>
		<table class="table" border="1">
            <tr>
                <th>Latitude</th>
                <th>Longitude</th>
                <th>State</th>
                <th>Crime Rate</th>
                <th>Population</th>
                <th>Elevation</th>
                <th>Detail</th>

            </tr>
            <c:forEach items="${places}" var="place" >
                <tr>
                    <td><c:out value="${place.getLatitude()}" /></td>
                    <td><c:out value="${place.getLongitude()}" /></td>
                    <td><c:out value="${place.getState()}" /></td>
 
                    <td><c:out value="${place.getCrimeRate()}" /></td>  
                    <td><c:out value="${place.getPopulation()}" /></td>
                    <td><c:out value="${place.getElevation()}" /></td>       
                              
                    <td><a href="showplace?placeid=<c:out value="${place.getPlaceId()}"/>">View detail</a></td>

                </tr>
            </c:forEach> 
       	</table>
	</form>
	<br/>
	</div>
</body>
</html>
