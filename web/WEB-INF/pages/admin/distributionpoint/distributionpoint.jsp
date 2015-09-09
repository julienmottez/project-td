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
	<form:form action="save.html" commandName="distributionPoint" method="POST">
		<form:hidden path="id" />
		<label>Address:</label>
		<label>zip:</label>
		<form:input path="address.zip" id="zip" />
		<form:errors path="address.zip" cssclass="error"></form:errors>
				<label>street:</label>
			<form:input path="address.street" id="street" />
		<form:errors path="address.street" cssclass="error"></form:errors>
				<label>Town:</label>
			<form:input path="address.tawn" id="tawn" />
		<form:errors path="address.tawn" cssclass="error"></form:errors>
		<label>Production Manager:</label>
		<form:select path="productionManager" id="productionManager" />
		<c:forEach items="${productionManagers}" var="productionManager">
		<c:out value="${productionManager.id} "/>
		<c:out value="${productionManager.firstName} "/>
	
		</c:forEach>
		<label>Sector:</label>
		<form:select path="sectordp" id="sectordp" />
		<c:forEach items="${sectordps}" var="productionManager">
		<c:out value="${sectordps.id} "/>
		
	
		</c:forEach>
		
		<br />
		
		<input type="submit" value="Submit" />
	</form:form>
</body>
</html>

