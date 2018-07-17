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

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public Integer getPersonal() {
		return personal;
	}

	public void setPersonal(Integer personal) {
		this.personal = personal;
	}
	private String nombre_html;
	private String tipo_propietario;
	private Integer area;
	private Integer personal;
	
	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();
		JspWriter out = this.getJspContext().getOut();		
		LinkedList<PropietariosBean> propietarios;
		Integer nro_propietario;
		try {
			propietarios = ListaPropietariosDB.getPropietarios(this.tipo_propietario, this.personal, this.area);
			
			if(this.area==null && this.personal==null) {
				out.println("<select name=\""+this.nombre_html+"\" required>");
				for(PropietariosBean prop : propietarios) {
					if(prop.getNro_area()==null && prop.getNro_leg_personal()==null) {
						out.println("<option value=\"\" >" + prop.getNombre() +"</option>");
					}else {
				        nro_propietario = ((prop.getNro_area()!=null)?prop.getNro_area():prop.getNro_leg_personal());
					    out.println("<option value=\""+nro_propietario+"\">" + prop.getNombre() + "</option>");
					}
				}
				out.println("</select>");
			}
			else {
				if(this.area!=null || this.personal!=null) {
					out.println("<select name=\""+this.nombre_html+"\" required>");
					for(PropietariosBean prop : propietarios) {
						if(prop.getNro_area()==null && prop.getNro_leg_personal()==null) {
							out.println("<option value=\"\" >" + prop.getNombre() +"</option>");
						}else {
					        nro_propietario = ((prop.getNro_area()!=null)?prop.getNro_area():prop.getNro_leg_personal());
						    out.println("<option value=\""+nro_propietario+"\"" +((prop.getSeleccionado().equals("S")) ?"selected":"")+" >" + prop.getNombre() + "</option>");
						}
					}
					out.println("</select>");
				}
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			out.println(e.getMessage());
		}
		
	}
 } 
