<%@page import="fr.treeptik.entity.Temperature"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<c:if test="${not empty error}">
		<label style="color: red;"><c:out value="${error}" /></label>
	</c:if>
	<form:form action="save.html" commandName="drink" method="POST">
		<form:hidden path="id" />

		<div>
			From
			<form:input path="coldStorage.from.value" />

			<c:set var="temperaturesUnits" value="<%=Temperature.Unit.values()%>" />
			<select>
				<c:forEach var="unit" items="${temperaturesUnits}">
					<option value="<c:out value="${unit}" />">
						<c:out value="${unit}" />
					</option>
				</c:forEach>
			</select>
		</div>
		
		<div>
			To
			<form:input path="coldStorage.to.value" />

			<c:set var="temperaturesUnits" value="<%=Temperature.Unit.values()%>" />
			<select>
				<c:forEach var="unit" items="${temperaturesUnits}">
					<option value="<c:out value="${unit}" />">
						<c:out value="${unit}" />
					</option>
				</c:forEach>
			</select>
		</div>

		<input type="submit" value="valider" />
	</form:form>
</body>
</html>