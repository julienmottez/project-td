<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>${action}refrigerator</title>
<link href="../../style/bootstrap-3.3.5-dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="../../style/main.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<h1>${action}refrigerator</h1>

		<div class="row">
			<div class="col-sm-6 col-sm-offset-3">
				<table class="table table-bordered">
					<c:forEach var="refrigerator" items="${refrigerators}">
						<tr>
							<td>#${refrigerator.id} in distributor [${refrigerator.distributor.id}]</td>
							<td><a href="edit.html?id=${refrigerator.id}">edit</a></td>
							<td><a href="delete.html?id=${refrigerator.id}">remove</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>