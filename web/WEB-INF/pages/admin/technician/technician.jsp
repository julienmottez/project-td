<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<c:if test="${not empty error}">
		<label style="color: red;"><c:out value="${error}" /></label>
	</c:if>
	<form:form action="save.html" commandName="technician" method="POST">
		<form:hidden path="id" />
		<label>First Name:</label>
		<form:input path="firstName" id="nom" />
	
		<label>Last Name:</label>
		<form:input path="lastName" id="prenom" />
	
 		<label>Sector:</label>

		<form:select path="sector.id" id="sector">
			<form:options items="${sectors}" itemValue="id" itemLabel="name" />
		</form:select>
		
		<label>Distributor :</label>
		<form:select  path="distributors" id="distributor">
			<form:options items="${distributorss}" itemValue="id" itemLabel="id" />
		</form:select>
</br>
</br>
		<input type="submit" value="Submit" />
	</form:form>
</body>
</html>