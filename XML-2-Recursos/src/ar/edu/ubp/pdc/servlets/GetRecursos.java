package ar.edu.ubp.pdc.servlets;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.edu.ubp.pdc.beans.RecursoBean;
import ar.edu.ubp.pdc.classes.ListaRecursosDB;

@WebServlet("/getRecursos.jsp")
public class GetRecursos extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    public  GetRecursos() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		 try {
			 LinkedList<RecursoBean> current = null;
			 final LinkedList<RecursoBean> recursos = ListaRecursosDB.getRecursos();
			 if(request.getSession().getAttribute("current")!=null){
		     HttpSession sess = request.getSession();
		     current = (LinkedList <RecursoBean>)sess.getAttribute("current");
		     for(RecursoBean rb :current){
		      System.out.println(rb.getNro_recurso());
		     }
		    }
	        request.setAttribute("recursos",  recursos); 
	        request.setAttribute("current", current);
			gotoPage("/mostrar_recursos.jsp", request, response);
		 } catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			printError(e.getMessage(), request, response);
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
