package rik.productos;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.*;


/**
 * Servlet implementation class ServletPruebas
 */
@WebServlet("/ServletPruebas")
public class ServletPruebas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Definir o establecer el DataSource, va el nombre del pool de conexiones
	
	@Resource(name="jdbc/productos")
     private DataSource miPool;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPruebas() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//creamos objeto printWritter
		
		PrintWriter salida=response.getWriter();
		
		response.setContentType("text/plain");
		
		//creamos conexion con BBDD
		
		Connection miConexion=null;
		Statement miStatement=null;
		ResultSet miResultset=null;
		try {
			miConexion=miPool.getConnection();
			String miSql="select * from productos";
			miStatement=miConexion.createStatement();
			miResultset=miStatement.executeQuery(miSql);
			while(miResultset.next()) {
				String nombreArticulo=miResultset.getString(3);
				salida.println(nombreArticulo);
			}
			
		}catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
