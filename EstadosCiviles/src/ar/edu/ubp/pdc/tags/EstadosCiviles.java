package ar.edu.ubp.pdc.tags;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;


public class EstadosCiviles extends SimpleTagSupport {

	private String nombre;
	private String valor;
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();
		
		
		String nom = (this.nombre   == null || this.nombre.equals("") ? "cod_estado_civil" : this.nombre);
		String val = (this.valor == null || this.valor.equals("") ?  "Debe seleccionar un estado civil" : this.valor);
		
		JspWriter out = this.getJspContext().getOut();
		
		 out.println("<select name=\"" + nom +"\">");
		 out.println("<option value=\"\" "+ ((val.equals("Debe seleccionar un estado civil"))?"selected":"") + ">Debe seleccionar un estado civil.</option>");
		 out.println("<option value=\"soltero\" "+  ((val.equals("soltero"))?"selected":"") + " >Soltero</option>");
     	 out.println("<option value=\"casado\" "+   ((val.equals("casado"))?"selected":"") + ">Casado</option>");
     	 out.println("<option value=\"separado\" "+ ((val.equals("separado"))?"selected":"") + ">Separado</option>");
     	 out.println("<option value=\"divorciado\" "+ ((val.equals("divorciado"))?"selected":"") + ">Divorciado</option>");
     	 out.println("<option value=\"viudo\" "+ ((val.equals("viudo"))?"selected":"") + ">Viudo</option>");
      	 out.println("<option value=\"otro\" "+ ((val.equals("otro"))?"selected":"") + ">Otro</option>");
		 out.println("</select>");
		
		  	     
	}
}
