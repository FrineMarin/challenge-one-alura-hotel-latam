package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Huesped;
import modelo.Reserva;

public class HuespedDAO {
private Connection connection;
	
	public HuespedDAO(Connection connection) {
		this.connection=connection;
	}
	
	public void guardar(Huesped huesped) {
		String sql ="INSERT INTO HUESPEDES (nombre, apellido, fechaN, nacionalidad, telefono, idReservacion)"
				+"VALUES(?, ?, ?, ?, ?, ?)";
		
		try (PreparedStatement pstm=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			pstm.setString(1, huesped.getNombre());
			pstm.setString(2, huesped.getApellido());
			pstm.setDate(3, huesped.getCumple());
			pstm.setString(4, huesped.getNacionalidad());
			pstm.setString(5, huesped.getTelefono());
			pstm.setInt(6, huesped.getIdReserva());
			
			pstm.executeUpdate();
			try (ResultSet rst=pstm.getGeneratedKeys()){
				while(rst.next()) {
					huesped.setId(rst.getInt(1));
				}
			} 
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Huesped> buscar(){
		List<Huesped> huespedes=new ArrayList<Huesped>();
		try {
			String sql="SELECT id, nombre, apellido, fechaN, nacionalidad, telefono, idReservacion FROM huespedes";
			try(PreparedStatement pstm=connection.prepareStatement(sql)){
				pstm.execute();

				transformarResultSetEnHuesped(huespedes, pstm);
			}
			return huespedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		
			}
		}
	
	public List<Huesped> buscar(String apellido){
		List<Huesped> huespedes=new ArrayList<Huesped>();
		try {
			String sql="SELECT id, nombre, apellido, fechaN, nacionalidad, telefono, idReservacion FROM huespedes WHERE apellido=?";
			try(PreparedStatement pstm=connection.prepareStatement(sql)){
				pstm.setString(1, apellido);
				pstm.execute();

				transformarResultSetEnHuesped(huespedes, pstm);
			}
			return huespedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		
			}
		}
	
	private void transformarResultSetEnHuesped(List<Huesped> huespedes, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Huesped produto = new Huesped(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getDate(4),rst.getString(5),rst.getString(6),rst.getInt(7));

				huespedes.add(produto);
			}
		}
	}
		

	public void Eliminar(int id) {
		try {
			String sql="DELETE FROM huespedes WHERE ID=?";
			try(PreparedStatement pstm=connection.prepareStatement(sql)){
				pstm.setInt(1,id);
				pstm.execute();
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void Modificar(String name, String lName,Date bD,String nacionalidad,String telefono,String idReserva, String id) {
		try {
			String sql="UPDATE huespedes SET nombre=?, apellido=?, fechaN=?,nacionalidad=?,telefono=?,idReservacion=? WHERE id=?";
			try(PreparedStatement pstm = connection.prepareStatement(sql)){
				pstm.setString(1, name);
				pstm.setString(2, lName);
				pstm.setDate(3, bD);
				pstm.setString(4, nacionalidad);
				pstm.setString(5, telefono);
				pstm.setInt(6,Integer.valueOf(idReserva));
				pstm.setInt(7, Integer.valueOf(id));
				pstm.execute();
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

