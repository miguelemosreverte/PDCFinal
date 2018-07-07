package ar.edu.ubp.pdc.tags;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import ar.edu.ubp.pdc.beans.Seleccion;

public class CustomSelectTagHandler extends SimpleTagSupport {

	private String funcionJS;

	public void setfuncionJS(String funcionJS) {
		this.funcionJS = funcionJS;
	}

	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();
		JspWriter out = this.getJspContext().getOut();
		Connection conn;
		CallableStatement cstmt;
		ResultSet result;
		LinkedList<Seleccion> listaSelecciones;
		Seleccion seleccion;
		try {
		 	  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
		      conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=pdc","sa", "98Thk7oo08L#F");
		      conn.setAutoCommit(true);
			  cstmt = conn.prepareCall("{call dbo.get_carreras()}"); 
			  cstmt.execute();
			  result = cstmt.getResultSet();
	          listaSelecciones = new LinkedList<Seleccion>();
				while(result.next()) {
					seleccion = new Seleccion();
					seleccion.setNombre(result.getString("nom_carrera"));
					seleccion.setCodigo(Integer.toString(result.getInt("cod_carrera")));
					listaSelecciones.add(seleccion);
				}
			  
			
			  cstmt.close();  
			  conn.close();
			  out.println("<label for=\"icarreras\">Carreras</label>");
			  out.println("<select id=\"icarreras\" name=\"carreras\" class=\"form-control\" required onchange=\""+ funcionJS +"\">");
			  out.println(" <option value=\"\" selected=\"selected\">Debe seleccionar una carrera</option>");
			  for(Seleccion s : listaSelecciones) {
				out.println("<option value=\""+s.getCodigo()+"\">" +s.getNombre()+ " </option>");
			  }
			  out.println("</select>");
	
	    } catch (Exception e) {
				e.printStackTrace();
	    }
		
		
    }
	
}
