package ar.edu.ubp.pdc.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateRecursos
 */
@WebServlet("/updateRecursos.jsp")
public class UpdateRecursos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRecursos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if(request.getParameterMap().size()>0) {
		String[]nro_recursos = (String[])request.getParameterValues("nro_recurso");
		String[]desc_recursos = (String[])request.getParameterValues("desc_recurso");
		String[]tipo_recursos = (String[])request.getParameterValues("tipo_recurso");
		String[]tipo_propietarios = (String[])request.getParameterValues("tipo_propietario");
		String[]nro_propietario = (String[])request.getParameterValues("propietario");	   
		String[]vigentes = (String[])request.getParameterValues("vigente");
		Connection conn;
		PreparedStatement stmt;		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=pdc", "sa","98Thk7oo08L#F");
			conn.setAutoCommit(false);
			try {
				stmt = conn.prepareStatement("update dbo.recursos" + 
						"    set desc_recurso     = ?," + 
						"    cod_tipo_recurso = ?," + 
						"	 nro_leg_personal = ?," + 
						"	 nro_area         = ?," + 
						"	 vigente          = ?" + 
						"    where nro_recurso = ?" );
				 for(int i = 0;i<nro_recursos.length;i++) {
				     stmt.setString(1,desc_recursos[i]);
				     stmt.setString(2,tipo_recursos[i]);
				     if(tipo_propietarios[i].equals("P")) {
				    	 stmt.setInt(3, Integer.parseInt(nro_propietario[i]));
				    	 stmt.setNull(4,java.sql.Types.INTEGER);
				     }else if(tipo_propietarios[i].equals("A")){
				    	 stmt.setNull(3,java.sql.Types.INTEGER);
				         stmt.setInt(4, Integer.parseInt(nro_propietario[i]));
					    
				     } 
				     stmt.setString(5, vigentes[i]);
				     stmt.setInt(6, Integer.parseInt(nro_recursos[i]));
				     stmt.executeUpdate();
				    
				 }
				conn.commit();
				stmt.close();
				System.out.println("success");
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
        
		// TODO Auto-generated method stub
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
