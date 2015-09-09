<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Distribution Point List</title>
</head>
<body>
<c:if test="${not empty error}">
	<label style="color:red;"><c:out value="${error}"/></label>
</c:if>
	
	<table>
		<tbody>
			<tr>
				<th>Id</th>
				<th>Address</th>
				<th>Distributeur manager</th>
			
			</tr>
			<c:forEach items="${distributionpoints}" var="distributionpoint">
				<tr>
					<td>${distributionpoint.id}</td>
					<td>${distributionpoint.address.zip}
					${distributionpoint.address.street}
					${distributionpoint.address.tawn}</td>
					<td>${distributionpoint.productionManager}</td>
			
					<td><a href="edit.html?id=${distributionpoint.id}">Edit</a>
						<a href="delete.html?id=${distributionpoint.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="new.html">New  distribution Point</a>

</body>
</html>