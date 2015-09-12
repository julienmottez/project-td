<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

   function changeFunc($i) {
    alert($i);
   }

  </script>
</head>
<body>
	<c:if test="${not empty error}">
		<label style="color: red;"><c:out value="${error}" /></label>
	</c:if>
	<form:form action="save.html" commandName="technician" method="POST" class="form-horizontal">
		<form:hidden path="id" />
		
		<div class="form-group">
		<label>First Name:</label>
		<form:input path="firstName" id="nom" />
		</div>
	<br />
	<div class="form-group">
		<label>Last Name:</label>
		<form:input path="lastName" id="prenom" />
		</div>
	<br />
	<div class="form-group">
 		<label>Sector:</label>

		<form:select path="sector.id" id="sector">
			<form:options items="${sectors}" itemValue="id" itemLabel="name" />
		</form:select>
		</div>
	<br />	
			<div class="form-group">
				<label class="control-label col-sm-2" for="distributor">Distributors : </label>
				<div class="col-sm-6">
					<form:select size="10" id="distributor" class="form-control" multiple="true" path="distributors">
					     <form:options items="${distributors}" itemValue="id" itemLabel="label" />
					</form:select>
				</div>
			</div>
		<br />
</br>
</br>
		<input type="submit" value="Submit" />
	</form:form>
</body>
</html>