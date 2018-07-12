package ar.edu.ubp.pdc.beans;

public class RecursoBean {



	private String desc_recurso;
	private String cod_tipo_recurso;
	private String tipo_propietario;
	private String nro_leg_personal;
	private String nro_area;
	private String vigente;
	private String nro_recurso;
	
	
	

	@Override
	public String toString() {
		return "RecursoBean [desc_recurso=" + desc_recurso + ", cod_tipo_recurso=" + cod_tipo_recurso
				+ ", tipo_propietario=" + tipo_propietario + ", nro_leg_personal=" + nro_leg_personal + ", nro_area="
				+ nro_area + ", vigente=" + vigente + ", nro_recurso=" + nro_recurso + "]";
	}



	
	public String getDesc_recurso() {
		return desc_recurso;
	}




	public void setDesc_recurso(String desc_recurso) {
		this.desc_recurso = desc_recurso;
	}




	public String getCod_tipo_recurso() {
		return cod_tipo_recurso;
	}




	public void setCod_tipo_recurso(String cod_tipo_recurso) {
		this.cod_tipo_recurso = cod_tipo_recurso;
	}




	public String getTipo_propietario() {
		return tipo_propietario;
	}




	public void setTipo_propietario(String tipo_propietario) {
		this.tipo_propietario = tipo_propietario;
	}




	public String getNro_leg_personal() {
		return nro_leg_personal;
	}




	public void setNro_leg_personal(String nro_leg_personal) {
		this.nro_leg_personal = nro_leg_personal;
	}




	public String getNro_area() {
		return nro_area;
	}




	public void setNro_area(String nro_area) {
		this.nro_area = nro_area;
	}




	public String getVigente() {
		return vigente;
	}




	public void setVigente(String vigente) {
		this.vigente = vigente;
	}




	public String getNro_recurso() {
		return nro_recurso;
	}




	public void setNro_recurso(String nro_recurso) {
		this.nro_recurso = nro_recurso;
	}




	public RecursoBean() {
		// TODO Auto-generated constructor stub
	}

}
