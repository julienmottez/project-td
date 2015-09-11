<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ajouter un rack</title>
<link href="../../style/bootstrap-3.3.5-dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
<header>
		<h1>Ajouter un rack</h1>
	</header>
	<c:if test="${not empty error}">
		<label style="color: red;"><c:out value="${error}" /></label>
	</c:if>

	<form:form action="save.html" commandName="rack" method="POST" class="form-horizontal">
		<form:hidden path="id" />

		<div class="form-group">
			<label class="control-label col-sm-2" for="colonneDistributor">Numero
				Colonne :</label>
			<div class="col-sm-6">
				<form:input path="colonneDistributor" id="colonneDistributor"
					class="form-control" />
			</div>
		</div>



		<div class="form-group">
			<label class="control-label col-sm-2" for="ligneDistributor">Numero
				Ligne :</label>
			<div class="col-sm-6">
				<form:input path="ligneDistributor" id="ligneDistributor"
					class="form-control" />
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-sm-2" for="typeRack.id">type
				rack:</label>
			<div class="col-sm-6">
				<form:select path="typeRack.id" id="typeRack" class="form-control">
					<form:options items="${typeRacks}" itemValue="id" itemLabel="name" />
				</form:select>
			</div>
		</div>


		<div class="form-group">
			<label class="control-label col-sm-2" for="distributor">distrib
				id :</label>
			<div class="col-sm-6">
				<form:select path="distributor.id" id="distributor"
					class="form-control">
					<form:options items="${distributors}" itemValue="id" itemLabel="id" />
				</form:select>
			</div>
		</div>

<div class="form-group">
		<label class="control-label col-sm-2" for="drink.id"">drink id :</label>
		<div class="col-sm-6">
		<form:select path="drink.id" id="drink" class="form-control">
			<form:options items="${drinks}" itemValue="id" itemLabel="id" />
		</form:select>
		</div>
		</div>

		<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
		<input type="submit" value="valider" class="btn btn-default"/>
		</div>
			</div>
	</form:form>
</body>
</html>