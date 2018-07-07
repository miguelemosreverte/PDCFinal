package ar.edu.ubp.pdc.beans;

public class CategoriaBean {

	public int getNro_categoria() {
		return nro_categoria;
	}

	public void setNro_categoria(int nro_categoria) {
		this.nro_categoria = nro_categoria;
	}

	public String getNom_categoria() {
		return nom_categoria;
	}

	public void setNom_categoria(String nom_categoria) {
		this.nom_categoria = nom_categoria;
	}

	private int nro_categoria;		
	private String nom_categoria;
	
	public CategoriaBean() {
		// TODO Auto-generated constructor stub
	}

}
