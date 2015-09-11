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
				<th>Name</th>
				<th>Quantité</th>
				<th>Rack</th>
				
			</tr>
			<c:forEach items="${typeRacks}" var="typeRack">
				<tr>
					<td>${typeRack.id}</td>
					<td>${typeRack.name}</td>
					<td>${typeRack.quantity}</td>
					<td><a href="listRackTypeRack.html?id=${typeRack.id}">Liste Rack</a></td>
					<td><a href="edit.html?id=${typeRack.id}">éditer</a> <a
						href="delete.html?id=${typeRack.id}">supprimer</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br />

	<a href="new.html">new type Rack</a>
	<br />
	<br />
	<a href="/td-frigo/admin/rack/list.html">List
		rack</a>


</body>
</html>