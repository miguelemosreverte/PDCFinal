package ar.edu.ubp.pdc.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ubp.pdc.beans.RecursoBean;
import ar.edu.ubp.pdc.classes.ListaRecursosDB;

/**
 * Servlet implementation class getRecursos
 */
@WebServlet("/getRecursos.jsp")
public class getRecursos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getRecursos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try {
			final LinkedList<RecursoBean> recursos = ListaRecursosDB.getRecursos();
			final String rec= request.getParameter("tipo_recurso");			
			final List<RecursoBean> recursosFiltrados = (List<RecursoBean>) recursos.stream()
															.filter((r)->r.getCod_tipo_recurso().equals(rec))
															.collect(Collectors.toList());
			
			request.setAttribute("recursos",  recursosFiltrados);
			
			
						
			
			gotoPage("/mostrar_recursos.jsp", request, response);
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			printError(e.toString(), request, response);
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
