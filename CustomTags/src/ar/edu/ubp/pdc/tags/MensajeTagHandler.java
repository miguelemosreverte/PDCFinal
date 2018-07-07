package ar.edu.ubp.pdc.tags;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MensajeTagHandler extends SimpleTagSupport {

	private String apellido;
	private String nombre;
	
	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();
		
		String nom = (this.nombre   == null ? "" : this.nombre);
		String ape = (this.apellido == null ? "" : this.apellido);
		
		JspWriter out = this.getJspContext().getOut();
		  	      out.println("Este es un mensaje para \"" + (nom.equals("") && ape.equals("") ? "alguien" : (nom + " " + ape).trim()) + "\"");
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
	
}
