package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Reserva;

public class ReservaDAO {
	private Connection connection;
	
	public ReservaDAO(Connection connection) {
		this.connection=connection;
	}
	
	public void guardar(Reserva reserva) {
		String sql ="INSERT INTO RESERVACIONES (fecha_entrada, fecha_salida, valor, pago)"
				+"VALUES(?, ?, ?, ?)";
		
		try (PreparedStatement pstm=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			pstm.setDate(1, reserva.getFechaE());
			pstm.setDate(2, reserva.getFechaS());
			pstm.setString(3, reserva.getValor());
			pstm.setString(4, reserva.getPago());
			
			pstm.executeUpdate();
			try (ResultSet rst=pstm.getGeneratedKeys()){
				while(rst.next()) {
					reserva.setId(rst.getInt(1));
				}
			} 
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	

	public List<Reserva> buscar() {
		List<Reserva> reservas = new ArrayList<Reserva>();
		try {
			String sql = "SELECT id, fecha_entrada, fecha_salida, valor, pago FROM reservaciones";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();

				transformarResultSetEnReserva(reservas, pstm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public List<Reserva> buscar(String id) {
		List<Reserva> reservas = new ArrayList<Reserva>();
		try {
			String sql = "SELECT id, fecha_entrada, fecha_salida, valor, pago FROM reservaciones WHERE id=?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setInt(1, Integer.valueOf(id));
				pstm.execute();

				transformarResultSetEnReserva(reservas, pstm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	private void transformarResultSetEnReserva(List<Reserva> reservas, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Reserva produto = new Reserva(rst.getInt(1), rst.getDate(2), rst.getDate(3), rst.getString(4), rst.getString(5));

				reservas.add(produto);
			}
		}
	}
	
	public void Eliminar(int id) {
		try {
			String sql="DELETE FROM reservaciones WHERE ID=?";
			try(PreparedStatement pstm=connection.prepareStatement(sql)){
				pstm.setInt(1, id);
				pstm.execute();
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void Modificar(Date fecha_E, Date fecha_S, String valor, String pago, String id) {
		try {
			String sql="UPDATE reservaciones SET fecha_entrada=?,fecha_salida=?,valor=?,pago=? WHERE id=?";
			try(PreparedStatement pstm = connection.prepareStatement(sql)){
				pstm.setDate(1, fecha_E);
				pstm.setDate(2, fecha_S);
				pstm.setString(3, valor);
				pstm.setString(4, pago);
				pstm.setInt(5, Integer.valueOf(id));
				pstm.execute();
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
