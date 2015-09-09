<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ page import="fr.treeptik.entity.SectorManager" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List of sectors</title>
</head>
<body style="font-family: Arial; font-size:smaller;">	
	<table bgcolor="lightblue" width="50" height="50" align="left" style="border-collapse: collapse;" border="1" bordercolor="#006699">
		<tbody>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Manager</th>
				<th>Distributor</th>
				<th>Point Distribution</th>
				<th>Actions</th>
			</tr>
			<c:forEach items="${sectors}" var="sector">
				<tr>
					<td>${sector.id}</td>
					<td>${sector.name}</td>
					<td>${sector.managerSector.firstName}
					    ${sector.managerSector.lastName}</td>
					<td colspan="2">${sector.distributionPoints.address.zip}
					    ${sector.distributionPoints.address.street}
					    ${sector.distributionPoints.address.tawn}</td>  
					<td colspan="2">${sector.distributors.address.zip}
					    ${sector.distributors.address.street}
					    ${sector.distributors.address.tawn}</td>    
					
					
				
					<td><a href="edit.html?id=${sector.id}">Update</a>
						<a href="delete.html?id=${sector.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</br>	  
</br>
</br> 
</br>

 <tr>  
  <td colspan="3" align="center">  
	<a href="new.html">New Sector</a>
  </td>  
     </tr>  
	
	<a href="#" onclick="javascript:history.back()">Return</a>
</body>
</html>