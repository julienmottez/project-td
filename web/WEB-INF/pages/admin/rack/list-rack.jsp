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
		<label style="color: red;"><c:out value="${error}" /></label>
	</c:if>

	<table>
		<tbody>
			<tr>
				<th>Id</th>
				<th>Ligne</th>
				<th>Colonne</th>
				<th>Distributor_id</th>
				<th>Type_Rack</th>
			</tr>
			<c:forEach items="${racks}" var="rack">
				<tr>
					<td>${rack.id}</td>
					<td>${rack.ligneDistributor}</td>
					<td>${rack.colonneDistributor}</td>
					<td>${rack.distributor.id}</td>
					<td>${rack.typeRack.name}</td>
					<td><a href="edit.html?id=${rack.id}">éditer</a> <a
						href="delete.html?id=${rack.id}">supprimer</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<a href="new.html">new rack</a>


</body>
</html>