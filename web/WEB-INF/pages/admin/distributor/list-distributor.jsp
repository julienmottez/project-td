<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liste des distributeurs</title>
</head>
<body>
	<h2>Liste des distributeurs :</h2>
	<table border="1" width="80%">
		<tr>
			<th>id</th>
			<th>rue</th>
			<th>ville</th>
			<th>cp</th>
			<th>technician</th>
			<th>sector</th>
			<th>modification</th>
		</tr>
		<c:forEach items="${distributors}" var="distributor">
			<tr>
				<td>${distributor.id}</td>
				<td>${distributor.address.street}</td>
				<td>${distributor.address.tawn}</td>
				<td>${distributor.address.zip}</td>
				<td>${distributor.technician.firstName}</td>
				<td>${distributor.sector.id}</td>
				<td><a href="edit.html?id=${distributor.id}">éditer</a> <a
					href="delete.html?id=${distributor.id}">supprimer</a></td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<br />
	<a href="new.html">New Distributor</a>
	<br />
	<br />
	<a href="/td-frigo/admin/typeDistributor/list.html">List
		distributor type</a>


</body>
</html>