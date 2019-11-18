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
	<form action="findPlaces" method="post">
		<br/>
	    <div class="form-group form-inline">
	      <label class="text-secondary col-1">Location</label>
	      <input class = "form-control" id="location" name="location" value="${fn:escapeXml(param.location)}">
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
                <th>Name</th>
                <th>Type</th>
                <th>CrimeRate</th>
                <th>Weather</th>
                <th>Temperate</th>
                <th>Elevation</th>
            </tr>
            <c:forEach items="${places}" var="place" >
                <tr>
                    <td><c:out value="${place.getUserName()}" /></td>
                    <td><c:out value="${place.getFirstName()}" /></td>
                    <td><c:out value="${place.getLastName()}" /></td>
                    <td><c:out value="${place.getUserId()}" /></td>
                    <td><c:out value="${place.getState()}" /></td>
                    <td><c:out value="${place.getUserLevel().name()}" /></td>



                    <td><a href="userdelete?userid=<c:out value="${place.getUserId()}"/>">Delete</a></td>
                    <td><a href="userupdate?userid=<c:out value="${place.getUserId()}"/>">Update</a></td>
                </tr>
            </c:forEach>
       	</table>
	</form>
	<br/>
</body>
</html>
