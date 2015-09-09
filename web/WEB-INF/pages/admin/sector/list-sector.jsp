<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>	
	<table>
		<tbody>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Actions</th>
			</tr>
			<c:forEach items="${sectors}" var="sector">
				<tr>
					<td>${sector.id}</td>
					<td>${sector.name}</td>
				
					<td><a href="edit.html?id=${sector.id}">Ã©diter</a>
						<a href="delete.html?id=${sector.id}">supprimer</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="new.html">New Sector</a>
</body>
</html>