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
				<th>Nom</th>
				<th>Prenom</th>
			</tr>
			<c:forEach items="${technicians}" var="technician">
				<tr>
					<td>${technician.id}</td>
					<td>${technician.firstName}</td>
					<td>${technician.lastName}</td>
					
					<td><a href="edit.html?id=${technician.id}">éditer</a>
						<a href="delete.html?id=${technician.id}">supprimer</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>