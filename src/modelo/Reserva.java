package modelo;

import java.sql.Date;

public class Reserva {
	private Integer id;
	private Date fechaE;
	private Date fechaS;
	private String valor;
	private String pago;
	
	public Reserva(Integer id, Date fechaE, Date fechaS, String valor, String pago) {
		super();
		this.id = id;
		this.fechaE = fechaE;
		this.fechaS = fechaS;
		this.valor = valor;
		this.pago = pago;
	}
	
	public Reserva(Date fechaE, Date fechaS, String valor, String pago) {
		super();
		this.fechaE = fechaE;
		this.fechaS = fechaS;
		this.valor = valor;
		this.pago = pago;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getFechaE() {
		return fechaE;
	}
	public Date getFechaS() {
		return fechaS;
	}
	public String getValor() {
		return valor;
	}
	public String getPago() {
		return pago;
	}
	
	
}
