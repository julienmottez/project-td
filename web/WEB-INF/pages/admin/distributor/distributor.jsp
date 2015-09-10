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

		<label>Street :</label>
		<form:input path="address.street" id="street" />
		<br />

		<label>Town :</label>
		<form:input path="address.tawn" id="tawn" />
		<br />

		<label>ZIP:</label>
		<form:input path="address.zip" id="zip" />
		<br />

		<label>Distributor Type :</label>
		<form:select path="typeDistributor.id" id="typeDistributor">
			<form:options items="${typeDistributors}" itemValue="id"
				itemLabel="id" />
		</form:select>
		<br />
		<label>Technician:</label>
		<form:select path="technician.id" id="technicians">
			<form:options items="${technicians}" itemValue="id"
				itemLabel="firstName" />
		</form:select>


		<br />
		<br />
		<input type="submit" value="valider" />
	</form:form>
</body>
</html>