<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List of Technicians</title>
</head>
<body>
<c:if test="${not empty error}">
	<label style="color:red;"><c:out value="${error}"/></label>
</c:if>
<br />
	<h1> List Technicians</h1>
	<table style="border-collapse: collapse;" border="1">
		<tbody>
			<tr>
				<th>Id</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Sector</th>
			</tr>
			<c:forEach items="${technicians}" var="technician">
				<tr>
					<td>${technician.id}</td>
					<td>${technician.firstName}</td>
					<td>${technician.lastName}</td>
					<td>${technician.sector.name}</td>
					
					<td width="500" height="20"><a href="edit.html?id=${technician.id}">Edit</a>
						<a href="delete.html?id=${technician.id}">Delete</a>
						<a href="../distributor/list.html">Distributor</a></td>
						
				</tr>
			</c:forEach>
		</tbody> 
	</table>
	</br>	  
 

 <tr>  
  <td colspan="3" align="center">  
	<a href="new.html">New Technician</a>
  </td>  
     </tr>
	
	
</body>
</html>