package ar.edu.ubp.pdc.servlets;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ar.edu.ubp.pdc.beans.*;


/**
 * Servlet implementation class GruposServlet
 */
@WebServlet("/grupos_servlet.jsp")
public class GruposServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GruposServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		if(request.getParameter("cod_area")!=null && !request.getParameter("cod_area").equals("")) {
		Connection conn;
		ResultSet result;
		LinkedList<GrupoRegistradoBean> grupos;
		GrupoRegistradoBean g;
		PreparedStatement stmt;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=pdc", "sa", "98Thk7oo08L#F");
			conn.setAutoCommit(true);
			stmt = conn.prepareStatement("select cod_area," + 
					"       nro_grupo," + 
					"       nom_grupo," + 
					"       exclusivo," + 
					"       vigente" + 
					"  from dbo.grupos_contactos" + 
					" where cod_area = ?");
			stmt.setString(1, request.getParameter("cod_area"));
			result = stmt.executeQuery();
			//cod_area, nro_grupo, nom_grupo , exclusivo, vigente
			grupos = new LinkedList<GrupoRegistradoBean>();
			while(result.next()) {
				g = new GrupoRegistradoBean();
				g.setCod_area(result.getString("cod_area"));
				g.setNro_grupo(result.getInt("nro_grupo"));
				g.setNom_grupo(result.getString("nom_grupo"));
				g.setExclusivo(result.getString("exclusivo"));
				g.setVigente(result.getString("vigente"));
				grupos.add(g);
			}
			request.setAttribute("grupos", grupos);
			this.gotoPage("/mostrar_grupos.jsp", request, response);
		}
		catch (ClassNotFoundException | SQLException e) {
			response.setStatus(400);
			this.printError(e.getMessage(),request,response);
		
		}
	 }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn;
		CallableStatement stmt;		
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=pdc", "sa","98Thk7oo08L#F");
			conn.setAutoCommit(false);
			/*
			 * execute dbo.act_grupo_contacto @cod_area='ADM', @nro_grupo=1, @nom_grupo='Alumnos Presenciales 2015', @exclusivo='S', @vigente='S'
			 */
			try {
				stmt = conn.prepareCall("{CALL dbo.act_grupo_contacto (?, ?, ?, ?, ?)}");
				stmt.setString(1, request.getParameter("cod_area"));
				if(!request.getParameter("nro_grupo").equals("")) {
					stmt.setString(2, request.getParameter("nro_grupo"));
				}
				else {
					stmt.setNull(2, java.sql.Types.VARCHAR);
					
				}
				stmt.setString(3, request.getParameter("nom_grupo"));
				
				if(request.getParameter("exclusivo").toLowerCase().equals("true")) {
					stmt.setString(4, "S");
				}
				else {
					stmt.setString(4, "N");
				}
				if(request.getParameter("vigente").toLowerCase().equals("true")) {
					stmt.setString(5, "S");
				}
				else {
					stmt.setString(5, "N");
				}
				
				stmt.execute();
				conn.commit();
				stmt.close();
				//System.out.println("llego");
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
