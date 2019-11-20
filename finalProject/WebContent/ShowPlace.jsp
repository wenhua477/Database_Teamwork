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
	
		<table class="table" border="1">
            <tr>
                <th>Place Id</th>
                <th>Name</th>
                <th>Latitude</th>
                <th>Longitude</th>
                <th>State</th>
                <th>Capacity</th>
                <th>Type</th>
<!--                 <th>Recommendations</th>
                <th>Rating</th> -->               
            </tr>
            <c:forEach items="${places}" var="place" >
                <tr>
                    <td><c:out value="${place.getPlaceId()}" /></td>
                    <td><c:out value="${place.getName()}" /></td>
                    <td><c:out value="${place.getLatitude()}" /></td>
                    <td><c:out value="${place.getLongitude()}" /></td>
                    <td><c:out value="${place.getState()}" /></td>
                    <td><c:out value="${place.getCapacity()}" /></td>
                    <td><c:out value="${place.getType()}" /></td>
                </tr>
            </c:forEach>
       	</table>
	</form>
	<br/>
</body>
</html>
