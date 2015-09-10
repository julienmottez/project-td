<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liste des types de distributeurs</title>
</head>
<body>
	<c:if test="${not empty error}">
		<label style="color: red;"><c:out value="${error}" /></label>
	</c:if>

	<form:form action="save.html" commandName="typeDistributor" method="POST">
		<form:hidden path="id" />
		
		
		<label>Nom :</label>
		<form:input path="name" id="name" />
		<br />

		<label>Nombre Colonnes : :</label>
		<form:input path="numberColumns" id="numberColumns" />
		<br />

		<label>Nombre Lignes :</label>
		<form:input path="numberLines" id="numberLines" />
		<br />

		<br />
		<input type="submit" value="valider" />
	</form:form>
</body>
</html>