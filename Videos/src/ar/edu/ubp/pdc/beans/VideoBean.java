package ar.edu.ubp.pdc.beans;

public class VideoBean {

	private String nom_categoria;
	public String getNom_categoria() {
		return nom_categoria;
	}

	public void setNom_categoria(String nom_categoria) {
		this.nom_categoria = nom_categoria;
	}

	public int getNro_categoria() {
		return nro_categoria;
	}

	public void setNro_categoria(int nro_categoria) {
		this.nro_categoria = nro_categoria;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCantante() {
		return cantante;
	}

	public void setCantante(String cantante) {
		this.cantante = cantante;
	}

	public String getLink_video() {
		return link_video;
	}

	public void setLink_video(String link_video) {
		this.link_video = link_video;
	}

	public int getNro_video() {
		return nro_video;
	}

	public void setNro_video(int nro_video) {
		this.nro_video = nro_video;
	}

	private int nro_categoria;
    private String titulo;
    private String cantante;
    private String link_video;
	private int nro_video;
	
	public VideoBean() {
		// TODO Auto-generated constructor stub
	}

}
