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
				<th>Nom</th>
				<th>Lignes</th>
				<th>Colonnes</th>

			</tr>
			<c:forEach items="${typeDistributors}" var="typeDistributor">
				<tr>
					<td>${typeDistributor.id}</td>
					<td>${typeDistributor.name}</td>
					<td>${typeDistributor.numberLines}</td>
					<td>${typeDistributor.numberColumns}</td>

					<td><a href="edit.html?id=${typeDistributor.id}">éditer</a> <a
						href="delete.html?id=${typeDistributor.id}">supprimer</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<br />
	<br />
	<a href="new.html">New Distributor Type </a>
	<br />
	<br />
	<!-- 	http://localhost:8080/td-frigo/admin/distributor/list.html -->
	<a href="/td-frigo/admin/distributor/list.html">Distributor List</a>


</body>
</html>