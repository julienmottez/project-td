<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Production Manager List</title>
</head>
<body>

	<h3>Production Manager List</h3>
	<c:if test="${not empty error}">
		<label style="color: red;"><c:out value="${error}" /></label>
	</c:if>

	<table border="1" width="50%">
		<tbody>
			<tr>
				<th>Id</th>
				<th>Address</th>
				<th>firstName</th>
				<th>lastName</th>

			</tr>
			<c:forEach items="${productionManagers}" var="productionManager">
				<tr>
					<td>${productionManager.id}</td>
					<td>${productionManager.address.street}
						${productionManager.address.zip}
						${productionManager.address.tawn}</td>
					<td>${productionManager.firstName}</td>
					<td>${productionManager.lastName}</td>

					<td><a href="edit.html?id=${productionManager.id}">Edit</a> <a
						href="delete.html?id=${productionManager.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="new.html">New distribution manager</a>

</body>
</html>