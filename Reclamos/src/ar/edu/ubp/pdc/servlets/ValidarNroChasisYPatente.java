package ar.edu.ubp.pdc.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ubp.pdc.classes.*;

/**
 * Servlet implementation class Registrar
 */
@WebServlet("/validar_nro_chasis_y_patente.jsp")
public class ValidarNroChasisYPatente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidarNroChasisYPatente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(request.getParameterMap().keySet().toString());
		try {
				String res = ValidarNroChasisYPatenteDB
							.validarNroChasisYPatente(
							request.getParameter("nro_chasis"),
							request.getParameter("nro_patente"));
			
				request.setAttribute("responseFromDB", res);
				gotoPage("/mostrar_validado.jsp", request, response);
		
		}catch(Exception e) {
			this.printError(e.getMessage(), request, response);
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
