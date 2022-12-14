package rik.productos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import javax.sql.DataSource;

public class ModeloProductos {

	private DataSource origenDatos;
	
	public ModeloProductos(DataSource origenDatos){
		
		this.origenDatos=origenDatos;
		
	}
	
	public List<Productos> getProductos() throws Exception{
		List<Productos> productos=new ArrayList<>();
		Connection miConexion=null;
		Statement miStatement=null;
		ResultSet miResultset=null;
		
		//------------estatblecer conexion----------------
		miConexion=origenDatos.getConnection();
		
		//----------------crear sentencia sql------------------
		String instruccionSql="select * from productos";
		miStatement=miConexion.createStatement();
		
		//-----------------ejecutar sql--------------------------
		miResultset=miStatement.executeQuery(instruccionSql);
		
		
		//-------------------recorrer el resulset obtenido-----------------------
		while(miResultset.next()) {
			String cArt=miResultset.getString("codigoarticulo");
			String seccion=miResultset.getString("seccion");
			String nArt=miResultset.getString("nombrearticulo");
			double precio=miResultset.getDouble("precio");
			Date fecha=miResultset.getDate("fecha");
			String importado=miResultset.getString("importado");
			String pOrig=miResultset.getString("paisorigen");
			
			Productos temProd=new Productos(cArt,seccion,nArt,precio,fecha,importado,pOrig);
			
			productos.add(temProd);
		}
		
		return productos;
	}

	public void agregarElNuevoProducto(Productos nuevoProducto) throws Exception{
		//insertara el producto que le llega a la BBDD
		
		Connection miConexion=null;
		PreparedStatement miStatement=null;
		
		
		//obtener la conexion
		try {
			
			miConexion=origenDatos.getConnection();
			
			
		
		
		// crear instruccion sql que inserte el producto
		String sql="insert into productos (codigoarticulo, seccion, nombrearticulo, precio, fecha, importado, paisorigen)"
                      + "VALUES (?,?,?,?,?,?,?)";
		miStatement=miConexion.prepareStatement(sql);
		
		// establecer parametros para el producto
		miStatement.setString(1, nuevoProducto.getcArt());
		miStatement.setString(2, nuevoProducto.getSeccion());
		miStatement.setString(3, nuevoProducto.getnArt());
		miStatement.setDouble(4, nuevoProducto.getPrecio());
		java.util.Date utilDate=nuevoProducto.getFecha();
		java.sql.Date fechaConvertida=new java.sql.Date(utilDate.getTime());		
		miStatement.setDate(5, fechaConvertida);				
		miStatement.setString(6, nuevoProducto.getImportado());
		miStatement.setString(7, nuevoProducto.getpOrig());
		
		
		// ejecutar la instruccion sql
		miStatement.execute();		
		
        }catch  (Exception e){
        	e.printStackTrace();
			
		}finally {  // cerramos statement y conexion a bbdd
			miStatement.close();
			miConexion.close();
		}
	}

	public Productos getProducto(String codigoarticulo) {
		
		Productos elProducto=null;
		Connection miConexion=null;
		PreparedStatement miStatement=null;
		ResultSet miResultset=null;
		String cArticulo=codigoarticulo;
		
		try {
					
		// establecer la conexion con BBDD
		miConexion=origenDatos.getConnection();
		
		
		
		// crear sql que busque producto
		String sql="select * from productos where codigoarticulo=?";
		
		// crear consulta preparada
		miStatement=miConexion.prepareStatement(sql);
		
		//  establecer parametros de la consulta
		miStatement.setString(1, cArticulo);
		
		// ejecutar consulta
		miResultset=miStatement.executeQuery();
		
		
		// obtener los datos de la respuesta
		if(miResultset.next()) {
			
			String c_art=miResultset.getString("codigoarticulo");
			String seccion=miResultset.getString("seccion");
			String nArt=miResultset.getString("nombrearticulo");
			double precio=miResultset.getDouble("precio");
			Date fecha=miResultset.getDate("fecha");
			String importado=miResultset.getString("importado");
			String pOrig=miResultset.getString("paisorigen");
			
			elProducto=new Productos(c_art,seccion,nArt,precio,fecha,importado,pOrig);
			
			
		}else {
			throw new Exception("NO hemos encontrado el producto con codigo= " + cArticulo);
		}
		
		
		}catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		
		return elProducto;
	}

	public void actualizarProducto(Productos productoActualizado) throws Exception {
		
		Connection miConexion=null;
		PreparedStatement miStatement=null;
		
		// establecer conexion con BBDD
		miConexion=origenDatos.getConnection();
		
		try {
		
		// crear sentencia sql
		String sql="update productos set seccion=?,nombrearticulo=?, precio=?, fecha=?, importado=?,"+
		           "paisorigen=? where codigoarticulo=?";
		
		// crear la consulta preparada
		miStatement=miConexion.prepareStatement(sql);
		
		//  establecer los parametros
		miStatement.setString(1, productoActualizado.getSeccion());
		miStatement.setString(2, productoActualizado.getnArt());
		miStatement.setDouble(3, productoActualizado.getPrecio());
		java.util.Date utilDate=productoActualizado.getFecha();
		java.sql.Date fechaConvertida=new java.sql.Date(utilDate.getTime());				
		miStatement.setDate(4, fechaConvertida);
		miStatement.setString(5, productoActualizado.getImportado());
		miStatement.setString(6, productoActualizado.getpOrig());
		miStatement.setString(7, productoActualizado.getcArt());
		
		// ejecutar la instruccion sql
		miStatement.execute();
		
	}finally { // cerramos todo
		miStatement.close();
		miConexion.close();
		}
	}

	public void eliminarProducto(String codArticulo) throws Exception {
		
		
		Connection miConexion=null;
		PreparedStatement miStatement=null;
		//  establecer la conexion con la BBDD
		
		try {
		
		miConexion=origenDatos.getConnection();
		
		
		// crear instruccion sql de eliminacion
		String sql="delete from productos where codigoarticulo=?";
		
		// preparar consulta
		miStatement=miConexion.prepareStatement(sql);
		
		//establecer parametros de la consulta
		miStatement.setString(1, codArticulo);
		
		//  ejecutar sentencia sql
		miStatement.execute();
	}finally { // cerrando el statement y la conexion
		miStatement.close();
		miConexion.close();
	}
	
	}
}
