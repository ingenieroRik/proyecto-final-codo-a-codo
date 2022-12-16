<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
   
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>



<!--------------------styles----------------------------------------> 
<style type="text/css">


* {
  text-decoration: none;
  list-style-type: none;
}
.cabecera{

  font-size:1.0em;
  font-weight:bold;
  color:#FFFFFF;
  background-color:#08088A;

}

.filas{
	width: 120px;
  text-align:center;
  background-color:#39b54a;

}


.tabla{
	float:left;
	text-align: center;
}

.boton{
	border-radius:3px;
	border:none;
	background-color:#1259c3;
	margin-top:30px;
	height:30px;
	width:50%;
	color:#ebebeb;

}
#contenedorBoton{
	margin-left:750px;
}

</style>
<!-- ----------------------------------------------------------------------------------- -->

</head>


<body>
	
	<table class="tabla">
	
	<tr>
	
	<td class="cabecera">Codigo Articulo</td>
	<td class="cabecera">Seccion</td>
	<td class="cabecera">Nombre Articulo</td>
	<td class="cabecera">Fecha</td>
	<td class="cabecera">Precio</td>
	<td class="cabecera">Importado</td>
	<td class="cabecera">Pais de Origen</td>
	<td class="cabecera">  Accion  </td>
	
	</tr>
	
	<c:forEach var="tempProd" items="${listaProductos}">
	
	
	<!-- link para cada producto con su campo clave -->	
	<c:url var="linkTemp" value="ControladorProductos">
	
		<c:param name="instruccion" value="cargar">></c:param>
		<c:param name="CArticulo" value="${tempProd.cArt}"></c:param>
		
	</c:url>
	
	
	<!-- link para eliminar cada registro con su campo clave  -->
	<c:url var="linkTempEliminar" value="ControladorProductos">
	
		<c:param name="instruccion" value="Eliminar">></c:param>
		<c:param name="CArticulo" value="${tempProd.cArt}"></c:param>
		
	</c:url>
	
		
	<tr>
		
	<td class="filas">${tempProd.cArt}</td>
	<td class="filas">${tempProd.seccion}</td>
	<td class="filas">${tempProd.nArt}</td>
	<td class="filas">${tempProd.fecha}</td>
	<td class="filas">${tempProd.precio}</td>
	<td class="filas">${tempProd.importado}</td>
	<td class="filas">${tempProd.pOrig}</td>
	<td class="filas"><a href="${linkTemp}">Actualizar</a>&nbsp;&nbsp;<a href="${linkTempEliminar}">Elininar</a></td>
		
	</tr>	
	
	</c:forEach>
	
	
	</table>

	<div id="contenedorBoton">
		<input type="button" value="Insertar Registro" class="boton"  onclick="window.location.href='insertaProducto.jsp' "/>
	
	</div>


</body>
</html>