package ar.edu.ubp.pdc.tags;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import ar.edu.ubp.pdc.beans.CategoriaBean;

public class CritBusqTagHandler extends SimpleTagSupport {

	private String funcionJS;

	public void setfuncionJS(String funcionJS) {
		this.funcionJS = funcionJS;
	}

	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();
		JspWriter out = this.getJspContext().getOut();
		
		Connection conn;
		CallableStatement stmt;	
		ResultSet result;
		LinkedList<CategoriaBean> categorias;
		CategoriaBean categoria;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=pdcvideos", "sa", "98Thk7oo08L#F");
			conn.setAutoCommit(true);
			stmt = conn.prepareCall("{CALL dbo.get_categorias_videos()}");
			result = stmt.executeQuery();
			categorias = new LinkedList<CategoriaBean>();
			while(result.next()) {
			 categoria = new CategoriaBean();
			 categoria.setNom_categoria(result.getString("nom_categoria"));
			 categoria.setNro_categoria(result.getInt("nro_categoria"));
			 categorias.add(categoria);
			}
			stmt.close();
	        conn.close();
	        
	        out.println("<form-group>");
		    out.println("<input type=\"text\" name=\"string_busqueda\" value=\"\">");
		    out.println("</form-group>");
		    out.println("<br></br>");
		    out.println("<form-group>");
		    out.println("<input type=\"radio\"  name=\"categoria\"  value=\"\" checked>Todos");
            for(CategoriaBean c :categorias) {
            out.println("<input type=\"radio\"  name=\"categoria\"  value=\""+c.getNro_categoria()+"\" checked>"+c.getNom_categoria());		
            }
		    out.println("</form-group>");
		    out.println("<br></br>");
		    out.println("<button type=\"submit\" name=\"botonBuscar\" onclick=\"" +this.funcionJS +"\" class=\"btn btn-primary\">Buscar</button>");

		    out.println("<br></br>");
			
		   } 
		    catch (ClassNotFoundException | SQLException e) {
			out.println(e.getMessage());
		   }
	}
  
 }
	

