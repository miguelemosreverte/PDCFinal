package ar.edu.ubp.pdc.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;


/*
insert into dbo.reclamos(nro_chasis, dominio, km, apellido, nombre, email, telefono, contactar, reclamo)
values(NULL, NULL, NULL, 'APELLIDO1', 'NOMBRE1', 'nombre1.apellido1@ubp.edu.ar', NULL, 'S', 'RECLAMO'),
      ('8F8693A675F14DE9A', 'MDA277', NULL, 'APELLIDO2', 'NOMBRE2', 'nombre2.apellido2@ubp.edu.ar', '03514144444', 'S', 'RECLAMO');
go

 * 
 * */
/**
 * Servlet implementation class Validar
 */
@WebServlet("/Registrar.jsp")
public class Registrar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Registrar() {
        super();

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn;
		PreparedStatement stmt;		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=pdc", "sa", "98Thk7oo08L#F");
			conn.setAutoCommit(false);
			try {
				stmt = conn.prepareStatement("insert into dbo.reclamos(nro_chasis, dominio, km, apellido, nombre, email, telefono, contactar, reclamo)" + 
						"values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
				stmt.setString(1, request.getParameter("chasis"));
				if(request.getParameter("chasis")!=null && request.getParameter("chasis")!="") {
					if(request.getParameter("patente")!=null && request.getParameter("patente")!="") {
						stmt.setString(2, request.getParameter("patente"));
					}else {
						stmt.setNull(2, java.sql.Types.VARCHAR);
					}
				
					if(request.getParameter("km")!=null && request.getParameter("km")!="") {
						stmt.setInt(3, Integer.parseInt(request.getParameter("km")));
					}else {
						stmt.setNull(3, java.sql.Types.INTEGER);
					}
				}else {
					stmt.setNull(1, java.sql.Types.VARCHAR);
					stmt.setNull(2, java.sql.Types.VARCHAR);
					stmt.setNull(3, java.sql.Types.INTEGER);
				}

				stmt.setString(4, request.getParameter("apellido"));
				stmt.setString(5, request.getParameter("nombre"));
				stmt.setString(6, request.getParameter("email"));
				
				
				if(request.getParameter("telefono")!=null && request.getParameter("telefono")!="") {
					stmt.setString(7, request.getParameter("telefono"));
				}else {
					stmt.setNull(7, java.sql.Types.VARCHAR);
				}
				stmt.setString(8, request.getParameter("contactar"));
				stmt.setString(9, request.getParameter("reclamo"));
				
				stmt.execute();
				conn.commit();
				stmt.close();
				gotoPage("/resultado.jsp", request, response);	
				
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
