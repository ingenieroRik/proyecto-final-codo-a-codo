<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

Hemos llegado a la lista de futuros clientes!!!

<table>
	<tr>
	
	<th>Nombre</th>
	<th>Apeliido</th>
	<th>Email</th>
	
	</tr>
	
	<c:forEach var="clienteTemp" items="${clientes }"/>
	
	<tr>
	    <td>${ clienteTemp.nombre}</td>
	  	<td>${ clienteTemp.apellido}</td>
		<td>${ clienteTemp.email}</td>
	</tr>
</table>

</body>
</html>