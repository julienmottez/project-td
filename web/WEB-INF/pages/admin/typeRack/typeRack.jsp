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
	<form:form action="save.html" commandName="typeRack" method="POST">
		<form:hidden path="id" />

		<label>Name :</label>
		<form:input path="name" id="name" />
		<br />

		<label>quantity :</label>
		<form:input path="quantity" id="quantity" />
		<br />
		
<!-- 		<label>type:</label> -->
<%-- 		<form:select path="typeRack.id" id="typeRack"> --%>
<%-- 			<form:option value="-" label="--Please Select" /> --%>
<%-- 			<form:options items="${typeRack}" itemValue="id" itemLabel="typeRack" /> --%>
<%-- 		</form:select> --%>


		<br />
		<input type="submit" value="valider" />
	</form:form>
</body>
</html>