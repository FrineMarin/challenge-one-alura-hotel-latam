package modelo;

import java.sql.Date;

public class Huesped {
	private int id;
	private String nombre;
	private String apellido;
	private Date cumple;
	private String nacionalidad;
	private String telefono;
	private int idReserva;
	
	

	public Huesped(int id) {
		super();
		this.id = id;
	}



	public void setId(int id) {
		this.id = id;
	}
	


	public Huesped(int id, String nombre, String apellido, Date cumple, String nacionalidad, String telefono,
			int idReserva) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cumple = cumple;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.idReserva = idReserva;
	}



	public Huesped(String nombre, String apellido, Date cumple, String nacionalidad, String telefono,
			int idReserva) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.cumple = cumple;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.idReserva = idReserva;
	}



	public int getId() {
		return id;
	}



	public String getNombre() {
		return nombre;
	}



	public String getApellido() {
		return apellido;
	}



	public Date getCumple() {
		return cumple;
	}



	public String getNacionalidad() {
		return nacionalidad;
	}



	public String getTelefono() {
		return telefono;
	}



	public int getIdReserva() {
		return idReserva;
	}



	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}
	
	
	
	
	
	
	
	
	
}
