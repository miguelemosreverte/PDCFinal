package ar.edu.ubp.pdc.classes;

import java.util.Date;

public class Tarea {

	private String    nomTarea;
	private Date      fechaTarea;
	private Prioridad prioridad;
	private String    nomResponsable;
	
	public Tarea() { }
	
	public Tarea(String nomTarea, Date fechaTarea, Prioridad prioridad) {
		this.nomTarea   = nomTarea;
		this.fechaTarea = fechaTarea;
		this.prioridad  = prioridad;
	}

	public Tarea(String nomTarea, Date fechaTarea, Prioridad prioridad, String nomResponsable) {
		this(nomTarea, fechaTarea, prioridad);
		this.nomResponsable = nomResponsable;
	}
	
	public String getNomTarea() {
		return nomTarea;
	}
	
	public void setNomTarea(String nomTarea) {
		this.nomTarea = nomTarea;
	}
	
	public Date getFechaTarea() {
		return fechaTarea;
	}
	
	public void setFechaTarea(Date fechaTarea) {
		this.fechaTarea = fechaTarea;
	}
	
	public Prioridad getPrioridad() {
		return prioridad;
	}
	
	public void setPrioridad(Prioridad prioridad) {
		this.prioridad = prioridad;
	}
	
	public String getNomResponsable() {
		return nomResponsable;
	}
	
	public void setNomResponsable(String nomResponsable) {
		this.nomResponsable = nomResponsable;
	}
	
}
