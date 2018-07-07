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
 * Servlet implementation class Validar
 */
@WebServlet("/Validar.jsp")
public class Validar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Validar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if(request.getParameter("chasis")!=null && request.getParameter("chasis")!="") {
		Connection conn = null;
		CallableStatement cstmt = null;	
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=pdc", "sa", "98Thk7oo08L#F");
			conn.setAutoCommit(true);
			cstmt = conn.prepareCall("{CALL dbo.val_vehiculo(?,?,?)}");
			cstmt.setString(1, request.getParameter("chasis"));
			if(request.getParameter("patente")!=null && request.getParameter("patente")!="") {
			    cstmt.setString(2, request.getParameter("patente"));
			}
			else {
			    cstmt.setNull(2, java.sql.Types.VARCHAR);	
			}
			cstmt.registerOutParameter(3, java.sql.Types.CHAR);	
		    cstmt.execute();
		    String res = cstmt.getString(3);
		    conn.close();
			cstmt.close();
		    
			request.setAttribute("result", res);
			gotoPage("/mostrar_icono.jsp", request, response);	
		   } 
		    catch (ClassNotFoundException | SQLException e) {
		    	this.printError(e.getMessage(), request, response);
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
