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
	
	<form:form action="save.html" commandName="rack" method="POST">
		<form:hidden path="id" />

		<label>Numero Colonne :</label>
		<form:input path="colonneDistributor" id="colonneDistributor" />
		<br />

		<label>Numero Ligne :</label>
		<form:input path="ligneDistributor" id="ligneDistributor" />
		<br />

		<label>type rack:</label>
		<form:select path="typeRack.id" id="typeRack">
			<form:option value="-" label="--Please Select" />
			<form:options items="${typeRacks}" itemValue="id" itemLabel="name" />
		</form:select>

		<label>distrib id :</label>
		<form:select path="distributor.id" id="distributor">
			<form:option value="-" label="--Please Select" />
			<form:options items="${distributors}" itemValue="id" itemLabel="id" />
		</form:select>

		<br />
		<input type="submit" value="valider" />
	</form:form>
</body>
</html>