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

	<form:form action="save.html" commandName="productionmanager" method="POST">
		<form:hidden path="id" />

		<label>FirstName:</label>
		<form:input path="firstName" id="firstName" />
		<br />
		<label>LastName:</label>
		<form:input path="LastName" id="LastName" />
		<br />
		<input type="submit" value="valider" />
	</form:form>
</body>
</html>