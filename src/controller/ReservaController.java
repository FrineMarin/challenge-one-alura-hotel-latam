package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import dao.ReservaDAO;
import factory.ConnectionFactory;
import modelo.Huesped;
import modelo.Reserva;

public class ReservaController {
	private ReservaDAO reservaDAO;
	
	public ReservaController() {
		Connection connection=new ConnectionFactory().recuperarConexion();
		this.reservaDAO=new ReservaDAO(connection);	
	}
	
	public void guardar(Reserva reserva) {
		try {
			this.reservaDAO.guardar(reserva);
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public List<Reserva> buscar() {
		return this.reservaDAO.buscar();
	}
	
	public void Eliminar(int id) {
		this.reservaDAO.Eliminar(id);
	}
	
	public void Modificar(Date fecha_E, Date fecha_S, String valor, String pago, String id) {
		this.reservaDAO.Modificar(fecha_E, fecha_S, valor, pago, id);
	}
	
	public List<Reserva> buscar(String id){
		return this.reservaDAO.buscar(id);
	}
}
