package ar.edu.ubp.pdc.beans;

import java.io.Serializable;

public class RecursoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cod_tipo_recurso == null) ? 0 : cod_tipo_recurso.hashCode());
		result = prime * result + ((desc_recurso == null) ? 0 : desc_recurso.hashCode());
		result = prime * result + ((nro_area == null) ? 0 : nro_area.hashCode());
		result = prime * result + ((nro_leg_personal == null) ? 0 : nro_leg_personal.hashCode());
		result = prime * result + ((nro_recurso == null) ? 0 : nro_recurso.hashCode());
		result = prime * result + ((tipo_propietario == null) ? 0 : tipo_propietario.hashCode());
		result = prime * result + ((vigente == null) ? 0 : vigente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecursoBean other = (RecursoBean) obj;
		if (cod_tipo_recurso == null) {
			if (other.cod_tipo_recurso != null)
				return false;
		} else if (!cod_tipo_recurso.equals(other.cod_tipo_recurso))
			return false;
		if (desc_recurso == null) {
			if (other.desc_recurso != null)
				return false;
		} else if (!desc_recurso.equals(other.desc_recurso))
			return false;
		if (nro_area == null) {
			if (other.nro_area != null)
				return false;
		} else if (!nro_area.equals(other.nro_area))
			return false;
		if (nro_leg_personal == null) {
			if (other.nro_leg_personal != null)
				return false;
		} else if (!nro_leg_personal.equals(other.nro_leg_personal))
			return false;
		if (nro_recurso == null) {
			if (other.nro_recurso != null)
				return false;
		} else if (!nro_recurso.equals(other.nro_recurso))
			return false;
		if (tipo_propietario == null) {
			if (other.tipo_propietario != null)
				return false;
		} else if (!tipo_propietario.equals(other.tipo_propietario))
			return false;
		if (vigente == null) {
			if (other.vigente != null)
				return false;
		} else if (!vigente.equals(other.vigente))
			return false;
		return true;
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

	public String getVigente() {
		return vigente;
	}

	public void setVigente(String vigente) {
		this.vigente = vigente;
	}

	public Integer getNro_recurso() {
		return nro_recurso;
	}

	public void setNro_recurso(Integer nro_recurso) {
		this.nro_recurso = nro_recurso;
	}

	private String desc_recurso;
	private String cod_tipo_recurso;
	private String tipo_propietario;
	private Integer nro_leg_personal;
	private Integer nro_area;
	private String vigente;
	private Integer nro_recurso;
	public RecursoBean() {
		// TODO Auto-generated constructor stub
	}

}
