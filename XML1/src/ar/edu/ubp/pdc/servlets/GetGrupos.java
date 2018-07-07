package ar.edu.ubp.pdc.servlets;

import java.io.IOException;
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
import ar.edu.ubp.pdc.beans.GrupoBean;


/**
 * Servlet implementation class GetAreas
 */
@WebServlet("/get_grupos.jsp")
public class GetGrupos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetGrupos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameterMap().size()!=0 && request.getParameter("cod_area")!=null) {
		response.setContentType("text/html;charset=iso-8859-1");
		Connection conn;
		PreparedStatement pstmt;
		ResultSet result;
		
		LinkedList<GrupoBean> listado = new LinkedList<GrupoBean>();
		GrupoBean gb;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=pdc", "sa", "98Thk7oo08L#F");
			conn.setAutoCommit(true);
			pstmt = conn.prepareStatement("select cod_area,nro_grupo,nom_grupo,exclusivo,vigente from dbo.grupos_contactos where cod_area = ?");
			pstmt.setString(1, request.getParameter("cod_area"));
			result = pstmt.executeQuery();
			while(result.next()) {
				gb = new GrupoBean();
				gb.setCod_area(result.getString("cod_area"));
				gb.setNro_grupo(result.getInt("nro_grupo"));
				gb.setNom_grupo(result.getString("nom_grupo"));
				gb.setExclusivo(result.getString("exclusivo"));
				gb.setVigente(result.getString("vigente"));
				listado.add(gb);
			}
			pstmt.close();
			conn.close();
			request.setAttribute("listado", listado);
			this.gotoPage("/mostrar_listado.jsp", request, response);
		  }
		
		catch (ClassNotFoundException | SQLException e) {
			this.printError(e.getMessage(), request, response);
		}
 	  }
		
	}
	
	private void printError(String error, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setAttribute("error", error);
			this.gotoPage("/error.jsp", request, response);
	}

	private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(address);
			                  dispatcher.forward(request, response);
	}

}
