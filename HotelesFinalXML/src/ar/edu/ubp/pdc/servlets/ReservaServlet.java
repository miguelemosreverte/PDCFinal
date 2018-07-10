package ar.edu.ubp.pdc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import ar.edu.ubp.pdc.beans.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ReservaServlet
 */
@WebServlet("/reservar.jsp")
public class ReservaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		if (request.getParameterMap().size()>0) {
		   
		response.setContentType("text/html;charset=ISO-8859-1");
        //PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);  
       
    	HashMap<String, FechaRowBean> reservas = 
    			(session.getAttribute("reservas")!=null)?
    			(HashMap<String, FechaRowBean>) session.getAttribute("reservas")
    			: new HashMap<String, FechaRowBean>();
    			
    	FechaRowBean fb = new FechaRowBean();
    	
    	Integer nro_hotel = Integer.parseInt(request.getParameter("nro_hotel"));
    	String fecha = request.getParameter("fecha");
    	String tipo_habitacion = request.getParameter("tipo_habitacion");

    	Integer habitaciones_disponibles = Integer.parseInt(request.getParameter("habitaciones_disponibles"));
    	Integer cantidad = Integer.parseInt(request.getParameter("cantidad"));
    	
    	
    	// <PK>
    	fb.setNro_hotel(nro_hotel);
    	fb.setFecha(fecha);
    	fb.setTipo_habitacion(tipo_habitacion);  
    	// </PK>    	
    	fb.setHabitaciones_disponibles(habitaciones_disponibles);
    	fb.setCantidad(cantidad); 
    	
    	String pk =  fb.getPrimaryKey();
    	reservas.put(pk,fb);
    	
    	session.setAttribute("reservas", reservas);	
    	//this.gotoPage("/mostrar_hoteles.jsp",request, response);
    	//System.out.println()
    	response.sendRedirect("./mostrar_hoteles.jsp");
        
        	
	  }
	}
	private void printError(String error, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setAttribute("error", error);
		this.gotoPage("/error.jsp", request, response);
	}

	private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(address);
		                  dispatcher.forward(request, response);
	}

}
