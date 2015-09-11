<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ajouter un type rack</title>
<link href="../../style/bootstrap-3.3.5-dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
<header>
		<h1>Ajouter un type rack</h1>
	</header>
	<c:if test="${not empty error}">
		<label style="color: red;"><c:out value="${error}" /></label>
	</c:if>
	<form:form action="save.html" commandName="typeRack" method="POST" class="form-horizontal">
		<form:hidden path="id" />
		
		
		<div class="form-group">
			<label class="control-label col-sm-2" for="colonneDistributor">Name :</label>
			<div class="col-sm-6">
				<form:input path="name" id="name"
					class="form-control" />
			</div>
		</div>
		
		
		<div class="form-group">
			<label class="control-label col-sm-2" for="colonneDistributor">quantity :</label>
			<div class="col-sm-6">
				<form:input path="quantity" id="quantity"
					class="form-control" />
			</div>
		</div>
		
		<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
		<input type="submit" value="valider" />
		</div>
			</div>
	</form:form>
</body>
</html>