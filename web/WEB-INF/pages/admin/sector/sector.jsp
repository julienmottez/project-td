<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body style="font-family: Arial; font-size:smaller;">
	<c:if test="${not empty error}">
		<label style="color: red;"><c:out value="${error}" /></label>
	</c:if>
<table bgcolor="#B0E0E6" width="1000" height="600" align="center" 
                   style="border-collapse: collapse;" border="1" bordercolor="#006699">
 <tr>  
  <td align="center"><h3>Adding new Sector</h3></td>  
 </tr>

	<tr valign="top" align="center">
		<td align="center">
	<form:form action="save.html" commandName="sector" method="POST">
		 <table width="700" style="border-collapse: collapse;" border="0" bordercolor="#006699" cellspacing="2" cellpadding="2">   
     <tr>  
      <td width="50" align="right">Name</td>  
      <td width="50">  
      <form:input path="name"/></td>  
      <td align="left">  
      <form:errors path="name" cssStyle="color:red"></form:errors>   
      </td>   
     </tr>  
     
     
	    <tr>  
        <td width="50" align="right">Manager</td>  
        <td width="50">  
		<form:input path="managerSector" id="managerSector" /></td>
		<td>
		<form:errors path="managerSector" cssclass="error"></form:errors>
		</td>
		</tr>

        <tr>  
        <td width="50" align="right">Distributor</td>  
        <td width="50"> 
	    <form:select path="distributors" id="distributors" /></td>
		<td><c:forEach items="${distributorss}" var="distributors">
		<c:out value="${distributors.id} "/>
        </c:forEach></td>
        </tr>

   
        <tr>  
        <td width="50" align="right">Point Distribution</td>  
        <td width="50"> 
	    <form:select path="distributionPoints" id="distributionPoints" /></td>
		<td><c:forEach items="${distributionPointss}" var="distributionPoints">
		<c:out value="${distributionPoints.id} "/>

       </c:forEach></td>
       </tr> 
       
       <tr>  
       <td colspan="3" align="center">  
		<input type="submit" value="Submit" /></br>
		&nbsp;&nbsp;  
      <input type="reset" name="" value="Reset">  
      &nbsp;&nbsp; 
       </td>  
     </tr>       
     </table>   
	</form:form>
	</td>
	</tr>
</table>
</body>
</html>