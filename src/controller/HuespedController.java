package controller;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import dao.HuespedDAO;
import factory.ConnectionFactory;
import modelo.Huesped;
import modelo.Reserva;

public class HuespedController {
	
	private HuespedDAO huespedDAO;
	
	public HuespedController() {
		Connection connection=new ConnectionFactory().recuperarConexion();
		this.huespedDAO=new HuespedDAO(connection);	
	}
	
	public void guardar(Huesped huesped) {
		try {
			this.huespedDAO.guardar(huesped);
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public List<Huesped> buscar() {
		return this.huespedDAO.buscar();
	}
	
	public void Eliminar(int id) {
		this.huespedDAO.Eliminar(id);
	}
	
	public void Modificar(String name, String lName,Date bD,String nacionalidad,String telefono,String idReserva, String id) {
		this.huespedDAO.Modificar(name,lName,bD,nacionalidad,telefono,idReserva,id);
	}
	
	public List<Huesped> buscar(String apellido){
		return this.huespedDAO.buscar(apellido);
	}
}
