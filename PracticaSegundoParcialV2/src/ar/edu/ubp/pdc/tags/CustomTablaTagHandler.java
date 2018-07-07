package ar.edu.ubp.pdc.tags;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Iterator;
import java.util.LinkedList;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import ar.edu.ubp.pdc.beans.AlumnoBean;


public class CustomTablaTagHandler extends BodyTagSupport {

	private static final long serialVersionUID = 3154148448049573092L;
	private LinkedList<AlumnoBean> listado;
	private Iterator<AlumnoBean>   iterator;
	private String cod;
	
	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public CustomTablaTagHandler() throws JspException {
		super();
	
	
	}
	
	/*
	 * Se invoca cuando el motor JSP encuentra el tag de activaci�n pero despu�s de haber 
	 * inicializado los atributos de la clase
	 */
	@Override
	public int doStartTag() throws JspException {
		Connection conn;
		PreparedStatement stmt;
		ResultSet result;
		AlumnoBean alumno;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=pdc", "sa", "98Thk7oo08L#F");
			conn.setAutoCommit(true);
			
			stmt = conn.prepareStatement(""
					+ "select nom_alumno,apellido_alumno,legajo_alumno"  
					+	" from dbo.alumnos"  
					+	" where cod_carrera = ?;"
					+ "");
			
			stmt.setInt(1, Integer.parseInt(this.cod));	
			result = stmt.executeQuery();
			listado = new LinkedList<AlumnoBean>();
			while(result.next()) {
				alumno = new AlumnoBean();
				alumno.setNombre(result.getString("nom_alumno"));
			    alumno.setApellido(result.getString("apellido_alumno"));
			    alumno.setLegajo(result.getInt("legajo_alumno"));
			    
				listado.add(alumno);
			}
			stmt.close();
			conn.close();
			 
			this.iterator = this.listado.iterator();
			if(this.getProximoAlumnoBean()) {
	            return EVAL_BODY_BUFFERED;
	        } 
	        return SKIP_BODY;
		} 
		catch (ClassNotFoundException | SQLException ex) {
			throw new JspException(ex);
		}
	
	}
	
	
	/*
	 * Se invoca cuando el motor JSP termina de procesar el cuerpo de contenidos del tag
	 */
	@Override
	public int doAfterBody() throws JspException {
		JspWriter out = this.bodyContent.getEnclosingWriter();
	    try {
	    	this.bodyContent.writeOut(out);
		    this.bodyContent.clearBody();
		    if(this.getProximoAlumnoBean()) {
		    	return EVAL_BODY_AGAIN;
		    }
		    return SKIP_BODY;
	    } 
	    catch (IOException ex) {
	    	throw new JspException(ex);
	    }	      
	}

	/*
	 * Se invoca cuando el motor JSP finaliza el procesamiento del tag
	 */
	@Override
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}
	
    private boolean getProximoAlumnoBean() {
        if(this.iterator.hasNext()) {
        	AlumnoBean alumno = AlumnoBean.class.cast(this.iterator.next());
            this.pageContext.setAttribute("nombre",   alumno.getNombre());
            this.pageContext.setAttribute("apellido", alumno.getApellido() );
            this.pageContext.setAttribute("legajo",   alumno.getLegajo().toString());
            return true; //EVAL_BODY_TAG
        }
        return false; //SKIP_BODY
    }
    
}
