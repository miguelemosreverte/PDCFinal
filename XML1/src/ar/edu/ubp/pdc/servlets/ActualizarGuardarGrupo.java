package ar.edu.ubp.pdc.servlets;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ActualizarGuardarGrupo
 */
@WebServlet("/actualizar_guardar_grupos.jsp")
public class ActualizarGuardarGrupo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActualizarGuardarGrupo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameterMap().size() !=0     && 
		   request.getParameter("cod_area") != null && 
		   request.getParameter("nom_grupo")!= null &&
	       request.getParameter("exclusivo")!= null && 
		   request.getParameter("vigente")  != null &&
		   request.getParameter("nro_grupo")!= null) 
		{
			Connection conn;
			CallableStatement stmt;		
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=pdc", "sa", "98Thk7oo08L#F");
				conn.setAutoCommit(false);
				stmt = null;
				try {
					  stmt = conn.prepareCall("{CALL dbo.act_grupo_contacto(?,?,?,?,?)}");
					  
					  if(!request.getParameter("nro_grupo").equals("")){
						  stmt.setInt(2, Integer.parseInt(request.getParameter("nro_grupo")));
					  }
					  else {
						  stmt.setNull(2, java.sql.Types.TINYINT);
					  }
					  stmt.setString(1, request.getParameter("cod_area"));
					  stmt.setString(3, request.getParameter("nom_grupo"));
					  stmt.setString(4, (request.getParameter("exclusivo").equals("true"))?"S":"N");
					  stmt.setString(5, (request.getParameter("vigente").equals("true"))?"S":"N");
					  stmt.execute();
					  conn.commit();
					  stmt.close();
					
				}
				catch(SQLException e) {
					conn.rollback();
					this.printError(e.getMessage(), request, response);
				}
				finally {
					conn.setAutoCommit(true);
					conn.close();
				}
			} 
			catch (ClassNotFoundException | SQLException e) {
				this.printError(e.getMessage(), request, response);
			}
	 	  }
	}
	
	private void printError(String error, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setStatus(400);
		request.setAttribute("error", error);
		this.gotoPage("/error.jsp", request, response);
    }

    private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(address);
		                  dispatcher.forward(request, response);
    }

}




