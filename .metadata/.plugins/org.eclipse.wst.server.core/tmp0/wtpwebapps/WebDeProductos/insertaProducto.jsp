<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!--------------------styles---------------------------------------->
<style type="text/css">


table {
	width:50%;
	 border:none;
}

input {

	background-color:#39b54a;
	border:none;
	border-radius:3px;
	font-size:24px;
}
.botones{
	display:flex;
	justify-content:space-around;
	flex-direction: row;
	align-items:center;
	
}
.envio {
	background-color:#1259c3;
	color:#ebebeb;
}
.restablecer {
	background-color:#993333;
	color:#ebebeb;
}


</style>
<!-- ----------------------------------------------------------------------------------- -->

</head>
 
<body>
<h1>Insertar Registros</h1>
<form name="form1" method="get" action="ControladorProductos">
<input type="hidden" name="instruccion" value="insertarBBDD">

	<table >
		<tr>
			<td >Codigo Articulo</td>
			<td><label for="CArt"></label>
			<input type="text" name="CArt"></td>
		</tr>
		<tr>
			<td>Seccion</td>
			<td><label for="seccion"></label>
			<input type="text" name="seccion" id="seccion"></td>
		</tr>
		<tr>
			<td>Nombre Articulo</td>
			<td><label for="NArt"></label>
			<input type="text" name="NArt" id="NArt"></td>
		</tr>
		<tr>
			<td>Fecha</td>
			<td><label for="fecha"></label>
			<input type="text" name="fecha" id="fecha"></td>
		</tr>
		<tr>
			<td>Precio</td>
			<td><label for="precio"></label>
			<input type="text" name="precio" id="precio"></td>
		</tr>
		<tr>
			<td>Importado</td>
			<td><label for="importado"></label>
			<input type="text" name="importado" id="importado"></td>	
		</tr>
		<tr>
			<td>Pais de Origen</td>
			<td><label for="pOrig"></label>
			<input type="text" name="pOrig" id="pOrig"></td>
		</tr>
		</table>
		
		<table>
			<tr class="botones">
				<td><input  class="envio" type="submit" name="envio" id="envio" value="Enviar"></td>
				<td><input  class="restablecer" type="reset" name="borrar" id="borrar" value="Restablecer"></td>
			</tr>	
				
		</table>
	


</form>



</body>
</html>