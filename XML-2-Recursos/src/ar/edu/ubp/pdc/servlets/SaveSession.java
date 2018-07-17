package ar.edu.ubp.pdc.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.edu.ubp.pdc.beans.RecursoBean;

/**
 * Servlet implementation class SaveSession
 */
@WebServlet("/saveSession.jsp")
public class SaveSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveSession() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	 try {
		HttpSession session       = request.getSession(true);
		String[]nro_recursos      = (String[])request.getParameterValues("nro_recurso");
		String[]desc_recursos     = (String[])request.getParameterValues("desc_recurso");
		String[]tipo_recursos     = (String[])request.getParameterValues("tipo_recurso");
		String[]tipo_propietarios = (String[])request.getParameterValues("tipo_propietario");
		String[]nro_propietarios   = (String[])request.getParameterValues("propietario");	   
		String[]vigentes          = (String[])request.getParameterValues("vigente");
		LinkedList<RecursoBean> recursos = new LinkedList<>();
		RecursoBean rec = null;
		for(int i = 0;i<nro_recursos.length;i++) {
			 rec = new RecursoBean();
			 rec.setNro_recurso(Integer.parseInt(nro_recursos[i]));
			 rec.setDesc_recurso(desc_recursos[i]);
			 rec.setCod_tipo_recurso(tipo_recursos[i]);
			 rec.setTipo_propietario(tipo_propietarios[i]);
			if(tipo_propietarios[i].equals("P")) {
			 rec.setNro_leg_personal(Integer.parseInt(nro_propietarios[i]));
			 rec.setNro_area(null);
		 	}else if(tipo_propietarios[i].equals("A")){
		 	 rec.setNro_leg_personal(null); 
		 	 rec.setNro_area(Integer.parseInt(nro_propietarios[i]));    
		 	} 
	         rec.setVigente(vigentes[i]);
	         recursos.add(rec);
		}
		request.getSession(true).setAttribute("current", recursos);
		gotoPage("/getRecursos.jsp",request,response);
		//doGet(request, response);
	  }finally {
		  
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
