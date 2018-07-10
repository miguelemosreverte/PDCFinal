package ar.edu.ubp.pdc.beans;

public class FechaRowBean {
	//<PK>
	private Integer nro_hotel;
	private String  fecha;
	private String  tipo_habitacion;
	//</PK>
	
	

	private Integer habitaciones_disponibles;
	private Integer cantidad; 
	
	
	
	public String getPrimaryKey() {
		return this.getNro_hotel() + "_" + this.getFecha() + "_" + this.getTipo_habitacion();
	}
	
	public String toJSON() {
		return 
				"{" 
					+ "\"nro_hotel\"" + ":" +  this.getNro_hotel() + ","
					+ "\"fecha\"" + ":" +  this.getFecha() + ","
					+ "\"tipo_habitacion\"" + ":" +  this.getTipo_habitacion() + ","
					+ "\"habitaciones_disponibles\"" + ":" +  this.getHabitaciones_disponibles() + ","
					+ "\"cantidad\"" + ":" + this.getCantidad()
					+ "}";
	}
	
	@Override
	public String toString() { return this.toJSON();}
	
	
	public Integer getNro_hotel() {
		return nro_hotel;
	}

	public void setNro_hotel(Integer nro_hotel) {
		this.nro_hotel = nro_hotel;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getTipo_habitacion() {
		return tipo_habitacion;
	}

	public void setTipo_habitacion(String tipo_habitacion) {
		this.tipo_habitacion = tipo_habitacion;
	}

	public Integer getHabitaciones_disponibles() {
		return habitaciones_disponibles;
	}

	public void setHabitaciones_disponibles(Integer habitaciones_disponibles) {
		this.habitaciones_disponibles = habitaciones_disponibles;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public FechaRowBean() {
		// TODO Auto-generated constructor stub
	}

}
