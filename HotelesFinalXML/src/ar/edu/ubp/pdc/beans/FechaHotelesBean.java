package ar.edu.ubp.pdc.beans;

public class FechaHotelesBean {	

	
	public String getHotel() {
		return hotel;
	}

	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getDesc_tipo_habitacion() {
		return desc_tipo_habitacion;
	}

	public void setDesc_tipo_habitacion(String desc_tipo_habitacion) {
		this.desc_tipo_habitacion = desc_tipo_habitacion;
	}

	public String getTipo_habitacion() {
		return tipo_habitacion;
	}

	public void setTipo_habitacion(String tipo_habitacion) {
		this.tipo_habitacion = tipo_habitacion;
	}

	public Integer getHab_disponibles() {
		return hab_disponibles;
	}

	public void setHab_disponibles(Integer hab_disponibles) {
		this.hab_disponibles = hab_disponibles;
	}

	public Integer getNro_hotel() {
		return nro_hotel;
	}

	public void setNro_hotel(Integer nro_hotel) {
		this.nro_hotel = nro_hotel;
	}
	
	private String  hotel;
	private String fecha;
	private String desc_tipo_habitacion;
	private String tipo_habitacion;
	private Integer hab_disponibles;
	private Integer nro_hotel;
	
	public FechaHotelesBean() {
		// TODO Auto-generated constructor stub
	}
	

}
