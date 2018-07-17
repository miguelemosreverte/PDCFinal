package ar.edu.ubp.pdc.beans;

public class PropietariosBean {

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getNro_leg_personal() {
		return nro_leg_personal;
	}
	public void setNro_leg_personal(Integer nro_leg_personal) {
		this.nro_leg_personal = nro_leg_personal;
	}
	public Integer getNro_area() {
		return nro_area;
	}
	public void setNro_area(Integer nro_area) {
		this.nro_area = nro_area;
	}
	public String getSeleccionado() {
		return seleccionado;
	}
	public void setSeleccionado(String seleccionado) {
		this.seleccionado = seleccionado;
	}
	public Integer getNro_orden() {
		return nro_orden;
	}
	public void setNro_orden(Integer nro_orden) {
		this.nro_orden = nro_orden;
	}
	@Override
	public String toString() {
		return "PropietariosBean [nombre=" + nombre + ", nro_leg_personal=" + nro_leg_personal + ", nro_area="
				+ nro_area + ", seleccionado=" + seleccionado + ", nro_orden=" + nro_orden + "]";
	}
	private String nombre;
	private Integer nro_leg_personal;
	private Integer nro_area;
	private String seleccionado;
	private Integer nro_orden;
	
	public PropietariosBean() {
		// TODO Auto-generated constructor stub
	}

}
	


