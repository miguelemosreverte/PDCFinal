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
import ar.edu.ubp.pdc.beans.RecursoBean;
import ar.edu.ubp.pdc.classes.ListaRecursosDB;

@WebServlet("/getRecursos.jsp")
public class GetRecursos extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    public  GetRecursos() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		if(request.getParameterMap().size()>0  && request.getParameter("tipo_recurso")!=null) {
		 try {
			final LinkedList<RecursoBean> recursos = ListaRecursosDB.getRecursos();
			final String rec = request.getParameter("tipo_recurso").toUpperCase();
			System.out.println(rec);
			if(rec.equals("")){
				request.setAttribute("recursos",  recursos); 
			}else {
				final LinkedList<RecursoBean> recursosFiltrados = (LinkedList<RecursoBean>) recursos.stream()
															.filter ( r ->
																		r.getCod_tipo_recurso().equals(rec)
																	)
															.collect(Collectors.toCollection(LinkedList::new));
															
				request.setAttribute("recursos",  recursosFiltrados);
			}
			gotoPage("/mostrar_recursos.jsp", request, response);
		 } catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			printError(e.getMessage(), request, response);
		}
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