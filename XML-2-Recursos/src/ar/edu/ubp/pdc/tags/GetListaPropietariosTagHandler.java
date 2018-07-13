package ar.edu.ubp.pdc.tags;

import ar.edu.ubp.pdc.classes.ListaPropietariosDB;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import ar.edu.ubp.pdc.beans.PropietariosBean;

public class GetListaPropietariosTagHandler extends SimpleTagSupport {
	public GetListaPropietariosTagHandler() {
		// TODO Auto-generated constructor stub
	}
	private String nombre_html;
	private String tipo_propietario;
	private String area;
	private String personal;
	
	public String getNombre_html() {
		return nombre_html;
	}

	public void setNombre_html(String nombre_html) {
		this.nombre_html = nombre_html;
	}

	public String getTipo_propietario() {
		return tipo_propietario;
	}

	public void setTipo_propietario(String tipo_propietario) {
		this.tipo_propietario = tipo_propietario;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPersonal() {
		return personal;
	}

	public void setPersonal(String personal) {
		this.personal = personal;
	}

	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();
		JspWriter out = this.getJspContext().getOut();		
		LinkedList<PropietariosBean> recursos;
		try {
			recursos = ListaPropietariosDB.getPropietarios(this.tipo_propietario, this.personal, this.area);
			out.println(this.createSelect(recursos, this.nombre_html));
		} catch (ClassNotFoundException | SQLException e) {
			out.println(e.getMessage());
		}
		
	}
	
	private String createOptionHTML(PropietariosBean propietario) {
		String nombre = propietario.getNombre();	
		String result =  "<option value=\""+ nombre +"\" >"+ nombre +"</option>";
		return result;
	}
	private String createSelect(LinkedList<PropietariosBean> recursos, String nameHTML) {
		String result = "<select name="+nameHTML+">";
		for(PropietariosBean recurso : recursos) {
			result += this.createOptionHTML(recurso);
		}
		result += "</select>";
		return result;
		
	}
	


}
