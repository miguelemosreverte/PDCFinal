package ar.edu.ubp.pdc.beans;

public class GrupoRegistradoBean {

	private String cod_area;
	public String getCod_area() {
		return cod_area;
	}

	public void setCod_area(String cod_area) {
		this.cod_area = cod_area;
	}

	public Integer getNro_grupo() {
		return nro_grupo;
	}

	public void setNro_grupo(Integer nro_grupo) {
		this.nro_grupo = nro_grupo;
	}

	public String getNom_grupo() {
		return nom_grupo;
	}

	public void setNom_grupo(String nom_grupo) {
		this.nom_grupo = nom_grupo;
	}

	public String getExclusivo() {
		return exclusivo;
	}

	public void setExclusivo(String exclusivo) {
		this.exclusivo = exclusivo;
	}

	public String getVigente() {
		return vigente;
	}

	public void setVigente(String vigente) {
		this.vigente = vigente;
	}

	private Integer nro_grupo;
	private String nom_grupo;
	private String exclusivo;
	private String vigente;
	
	public GrupoRegistradoBean() {
		// TODO Auto-generated constructor stub
	}

}
