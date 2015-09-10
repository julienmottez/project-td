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
		<label>Nom:</label>
		<form:input path="firstName" id="nom" />
		<br />
		<label>Prénom:</label>
		<form:input path="lastName" id="prenom" />
		<br />
 		<label>Sector:</label>

		<form:select path="sector.id" id="sector">
			<form:options items="${sectors}" itemValue="id" itemLabel="name" />
		</form:select>

		<input type="submit" value="valider" />
	</form:form>
</body>
</html>