<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>${action} refrigerator</title>
<link href="../../style/bootstrap-3.3.5-dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="../../style/main.css" rel="stylesheet">
</head>
<body>
	<div class="container">

		<div class="row">
			<div class="col-sm-offset-3 col-sm-6">
				<h1>${action} refrigerator</h1>

				<c:if test="${not empty error}">
					<div class="alert alert-danger" role="alert">
						<c:out value="${error}" />
					</div>
				</c:if>

				<f:form action="save.html" method="post"
					commandName="refrigerator">

					<f:hidden path="id" />

					<div class="form-group">
						<label>Cold level</label>

						<div class="row">
							<div class="col-sm-6">
								<f:input path="coldLevel.value" class="form-control" type="number" step="0.5" />
							</div>

							<div class="col-sm-6">
								<f:select path="coldLevel.unit" class="form-control">
									<f:options items="${temperature_units}" />
								</f:select>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label>Brand</label>

						<f:input path="brand" class="form-control" />
					</div>

					<div class="form-group">
						<label>Distributor</label>

						<f:select path="distributor.id">
							<f:options items="${distributors}" itemValue="id" />
						</f:select>
					</div>
					
					<button type="submit" class="btn">${action}</button>
				</f:form>
			</div>
		</div>
	</div>
</body>
</html>