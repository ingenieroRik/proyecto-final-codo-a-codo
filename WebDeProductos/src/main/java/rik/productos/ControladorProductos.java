package rik.productos;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ControladorProductos
 */
@WebServlet("/ControladorProductos")
public class ControladorProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ModeloProductos modeloProductos;
	@Resource(name="jdbc/productos")
    private DataSource miPool;
	
	//crear metodo init
	@Override
	public void init() throws ServletException {
		
		super.init();
		
		try {
			modeloProductos=new ModeloProductos(miPool);
		}catch (Exception e) {
			
			throw new ServletException(e);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//leer parametro que llega del formulario
		String elComando=request.getParameter("instruccion");
		
		// si no se envia el parametro, listar productos
		if (elComando==null) elComando="listar";
		
		// redirigir el flujo de ejcucion el metodo adecuado
		switch (elComando) {
		
		case "listar":
			
			obtenerProductos(request, response);
			break;
			
		case "insertarBBDD":
			agregarProductos(request, response);
			break;
			
		case "cargar":
			try {
			cargaProductos(request, response);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			break;
			
		case "actualizarBBDD":
			try {
				actualizaProductos(request, response);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			break;
			
		case "Eliminar":
			try {
				eliminarProductos(request, response);
			} catch (Exception e) {
		
				e.printStackTrace();
			}
				break;
				
			
		default:
		    obtenerProductos(request, response);
		
		}
				
						
	}
	

	private void eliminarProductos(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//  capturar el codigoarticulo
		String CodArticulo=request.getParameter("CArticulo");
		
		//  borrar producto de la BBDD
		modeloProductos.eliminarProducto(CodArticulo);
		
		//  volver al listado de productos con el cod articulo borrado
		obtenerProductos(request, response);
		
		
		
		
	}
	private void actualizaProductos (HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//leer los datos que le vienen del formulario actualizar
		String CodArticulo=request.getParameter("CArt");
		String seccion=request.getParameter("seccion");
		String NombreArticulo=request.getParameter("NArt");		
		
		
		SimpleDateFormat formatoFecha=new SimpleDateFormat("yyyy-MM-dd");
		Date Fecha=null;
		try {
			Fecha=formatoFecha.parse(request.getParameter("fecha"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		double Precio=Double.parseDouble(request.getParameter("precio"));
		String Importado=request.getParameter("importado");
		String PaisOrigen=request.getParameter("pOrig");
		
		// crear un objeto tipo <Producto con la info del formulario
		Productos ProductoActualizado=new Productos(CodArticulo,seccion, NombreArticulo,Precio, Fecha, Importado, PaisOrigen);
		
		// actualizar la BBDD con la info del obj producto
		modeloProductos.actualizarProducto(ProductoActualizado);
		
		
		// volver al listado con la info actualizada
		obtenerProductos(request, response);
		
			
		
	}
	private void cargaProductos(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		// leer el codigo articulo que viene del listado
		String codigoArticulo=request.getParameter("CArticulo");
		
		
		// enviar cod articulo al modelo
		Productos elProducto=modeloProductos.getProducto(codigoArticulo);
		
		//colocar atributo correspondiente al cod articulo
		request.setAttribute("ProductoActualizar", elProducto);
		
		//enviar Producto al formulario de actualizar(jsp)
		RequestDispatcher dispatcher=request.getRequestDispatcher("/atualizarProducto.jsp");
		dispatcher.forward(request, response);
		
	}
	private void agregarProductos(HttpServletRequest request, HttpServletResponse response) {
		
		// leer la informacion del productos que viene del formulario
				String CodArticulo=request.getParameter("CArt");
				String seccion=request.getParameter("seccion");
				String NombreArticulo=request.getParameter("NArt");		
				
				
				SimpleDateFormat formatoFecha=new SimpleDateFormat("yyyy-MM-dd");
				Date Fecha=null;
				try {
					Fecha=formatoFecha.parse(request.getParameter("fecha"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				double Precio=Double.parseDouble(request.getParameter("precio"));
				String Importado=request.getParameter("importado");
				String PaisOrigen=request.getParameter("pOrig");
				
				// crear un objeto de tipo Producto
				Productos NuevoProducto=new Productos(CodArticulo,seccion, NombreArticulo,Precio, Fecha, Importado, PaisOrigen);
				
				
				
				// enviar el objeto al modelo y despues insertar el objeto Producto aen la BBDD
				try {
					modeloProductos.agregarElNuevoProducto(NuevoProducto);
				} catch (Exception e) {
			
					e.printStackTrace();
				}
				
				
				// volver al listado de Productos
				obtenerProductos(request, response);
		
		
	}
	private void obtenerProductos(HttpServletRequest request, HttpServletResponse response) {
		
		//obtener la lista de productos desde el modelo
		List<Productos> productos;		
		try {
			productos=modeloProductos.getProductos();
			
			//agregar lista de productos al request
			request.setAttribute("listaProductos", productos);
			
			
			//enviar ese request a la pagina JSP
			RequestDispatcher miDispatcher=request.getRequestDispatcher("/ListaProductos.jsp");
			miDispatcher.forward(request,response);
		}catch (Exception e){
			
			e.printStackTrace();
		}
	}

	
}
