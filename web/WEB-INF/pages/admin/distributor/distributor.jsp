<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liste des distributeurs</title>
</head>
<body>
	<c:if test="${not empty error}">
		<label style="color: red;"><c:out value="${error}" /></label>
	</c:if>

	<form:form action="save.html" commandName="distributor" method="POST">
		<form:hidden path="id" />

		<label>Rue:</label>
		<form:input path="address.street" id="street" />
		<br />

		<label>Ville:</label>
		<form:input path="address.tawn" id="tawn" />
		<br />

		<label>Code Postal:</label>
		<form:input path="address.zip" id="zip" />
		<br />

		<label>Sector:</label>

		<form:select path="sector.id" id="sector">
			<form:options items="${sectors}" itemValue="id" itemLabel="name" />
		</form:select>


		<br />
		<br />
		<input type="submit" value="valider" />
	</form:form>
</body>
</html>