
package ar.edu.ubp.pdc.tags;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import ar.edu.ubp.pdc.beans.LocalidadBean;
public class LocalidadesTagHandler extends SimpleTagSupport {

	private String nombreElementoHTML;
	private String codigoProvincia;
	
	public String getNombreElementoHTML() {
		return nombreElementoHTML;
	}
	public void setNombreElementoHTML(String nombreElementoHTML) {
		this.nombreElementoHTML = nombreElementoHTML;
	}
	public String getCodigoProvincia() {
		return codigoProvincia;
	}
	public void setCodigoProvincia(String codigoProvincia) {
		this.codigoProvincia = codigoProvincia;
	}
	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();
		JspWriter out = this.getJspContext().getOut();
		Connection conn;
		PreparedStatement stmt;	
		ResultSet result;
		LinkedList<LocalidadBean> localidades;
		LocalidadBean localidad;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=pdc", "sa", "98Thk7oo08L#F");
			conn.setAutoCommit(true);
			stmt = conn.prepareStatement( " select nro_localidad, localidad" + 
								          " from dbo.localidades (nolock)" + 
								 		  " where cod_provincia = ?" + 
								 		  " order by localidad" );
			stmt.setString(1, this.codigoProvincia);
			result = stmt.executeQuery();
			
			localidades= new LinkedList<LocalidadBean>();
			while(result.next()) {
				localidad = new LocalidadBean();
				localidad.setLocalidad(result.getString("localidad"));
				localidad.setNro_localidad(result.getInt("nro_localidad"));
				localidades.add(localidad);
			}
			
			if (!localidades.isEmpty())
			{
				out.println("<select name=\""+this.nombreElementoHTML+"\">");	
				out.println("<option value=\"\">Seleccione localidad</option>");
				
				for (LocalidadBean l : localidades) {
					out.println("<option value=\""+l.getNro_localidad()+ "\">"+l.getLocalidad()+"</option>");
				}
				out.println("</select>");
				
		    } 
		} 
		catch (ClassNotFoundException | SQLException e) {
			out.println(e.getMessage());
		}
	}
  
 }
	

