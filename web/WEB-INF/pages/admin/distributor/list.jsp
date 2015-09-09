<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	
	<h2>Liste des distributeurs :</h2>
	<table border="1" width="80%">
  <tr>
    <th>id</th>
    <th>technician</th>
    <th>sector</th>
  </tr>
  <c:forEach items="${distributeurs}" var="distributeur">
  <tr>
	<td>${distributeur.id}</td>
	<td>${distributeur.technician.firstName}</td>
	<td>${distributeur.sector.id}</td>
  </tr>
  </c:forEach>
</table>


</body>
</html>