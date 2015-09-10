<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<c:if test="${not empty error}">
	<label style="color:red;"><c:out value="${error}"/></label>
</c:if>
	
	<table>
		<tbody>
			<tr>
				<th>Id</th>
			</tr>
			<c:forEach items="${drinks}" var="drink">
				<tr>
					<td>${drink.id}</td>
					<td>${drink.coldStorage}</td>
					<c:forEach items="${drink.distributionPoints}" var="distributionPoint">
					<td>${distributionPoint.id}</td>
					</c:forEach>
					<td><a href="edit.html?id=${drink.id}">éditer</a>
						<a href="delete.html?id=${drink.id}">supprimer</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table><br />
		<a href="new.html">New Drink</a>
	
</body>
</html>