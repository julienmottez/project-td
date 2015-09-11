<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${action} un utilisateur</title>
<link href="../../style/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>


	<header>
		<h1>${action} un secteur</h1>
	</header>

	<article>


		<c:if test="${not empty error}">
			<label style="color: red;"><c:out value="${error}" /></label>
		</c:if>
		<form:form action="save.html" commandName="sector" method="POST" class="form-horizontal">
			<form:hidden path="id" />

			<div class="form-group">
				<label class="control-label col-sm-2" for="name">Nom :</label>
				<div class="col-sm-10">
					<form:input id="name" path="name" class="form-control" placeholder="Nom" />
				</div>
			</div>
			
			<div class="form-group"> 
				<label class="control-label col-sm-2" for="area">Zone : </label>
				<div class="col-sm-10">
					<select id="area" class="form-control" path="area">
					    <c:forEach items="${areas}" var="area">
					        <option value="${area.id}">${area.toString()}</option>
					    </c:forEach>
					</select>
				</div>
			</div>
			
			<div class="form-group"> 
				<label class="control-label col-sm-2" for="area">Manager : </label>
				<div class="col-sm-10">
					<select id="manager" class="form-control" path="manager">
					    <c:forEach items="${managers}" var="manager">
					        <option value="${manager.id}">${manager.toString()}</option>
					    </c:forEach>
					</select>
				</div>
			</div>
			
			
			<div class="form-group"> 
				<label class="control-label col-sm-2" for="area">Manager : </label>
				<div class="col-sm-10">
					<select size="10" id="distributeur_disponible" class="form-control" path="distributor" multiple>
					    <c:forEach items="${distributors}" var="distributor">
					        <option value="${distributor.id}">${distributor.toString()}</option>
					    </c:forEach>
					</select>
				</div>
			</div>
			
			
			
			
		
			
			
			
			
			
			

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" class="btn btn-default" value="valider" />
				</div>
			</div>
		</form:form>




	</article>






</body>
</html>