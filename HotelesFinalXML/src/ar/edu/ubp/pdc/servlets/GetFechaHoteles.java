package ar.edu.ubp.pdc.servlets;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ar.edu.ubp.pdc.beans.FechaHotelesBean;


/**
 * Servlet implementation class GetFechaHoteles
 */
@WebServlet("/getFechasHoteles.jsp")
public class GetFechaHoteles extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFechaHoteles() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		if(request.getParameterMap().size()>0) {
			Connection conn;
			CallableStatement stmt;
			ResultSet result;
			SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy");
		    SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
		    FechaHotelesBean fh;
		    LinkedList<FechaHotelesBean> listaFechas;
		    LinkedList<String> listaHoteles;
			try {
				Integer nroLocalidad = Integer.parseInt(request.getParameter("localidades"));
				String fechaDesdeFormateada = formatter.format(parser.parse(request.getParameter("fecha_desde")));
				String fechaHastaFormateada = formatter.format(parser.parse(request.getParameter("fecha_hasta")));
				String tipoHabitacion = request.getParameter("tipos_habitaciones");
				
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=pdc", "sa", "98Thk7oo08L#F");
				conn.setAutoCommit(false);
				stmt = conn.prepareCall("{CALL dbo.get_fechas_hoteles(?,?,?,?)}");
				stmt.setInt(1,nroLocalidad );
				stmt.setString(2, fechaDesdeFormateada);
				stmt.setString(3, fechaHastaFormateada);
				stmt.setString(4, tipoHabitacion);
				stmt.execute();
				result  = stmt.getResultSet();
				conn.commit();
				listaFechas = new LinkedList<FechaHotelesBean>();
				listaHoteles = new LinkedList<String>();
				while(result.next()){
					fh = new FechaHotelesBean();
					fh.setDesc_tipo_habitacion(result.getString("desc_tipo_habitacion"));
					fh.setFecha(result.getString("fecha"));
					fh.setHab_disponibles(result.getInt("hab_disponibles"));
					fh.setHotel(result.getString("hotel"));
					fh.setTipo_habitacion(result.getString("tipo_habitacion"));
					fh.setNro_hotel(result.getInt("nro_hotel"));
					listaFechas.add(fh);
					if(!listaHoteles.contains(fh.getHotel())){
						listaHoteles.add(fh.getHotel());
					}
				}
				stmt.close();
				conn.close();
				request.setAttribute("listadoFechas", listaFechas);
				request.setAttribute("listadoHoteles", listaHoteles);
				request.setAttribute("reservas", request.getParameter("reservas"));
				this.gotoPage("/mostrar_hoteles.jsp", request, response);
			} 
			catch (ClassNotFoundException | SQLException | ParseException e) {
				this.printError(e.getMessage(), request, response);
			}
	  }
	}
   
	
	private void printError(String error, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setStatus(400);
		request.setAttribute("error", error);
		this.gotoPage("/error.jsp", request, response);
	}

	private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(address);
		                  dispatcher.forward(request, response);
	}
}


