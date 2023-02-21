package factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.management.RuntimeErrorException;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	public DataSource dataSource;
	
	public ConnectionFactory() {
		ComboPooledDataSource comboPooled = new ComboPooledDataSource(); 
		comboPooled.setJdbcUrl("jdbc:mysql://localhost/hotel_reservaciones?useTimeZone=true&serverTimeZone=UTC");
		comboPooled.setUser("root");
		comboPooled.setPassword("");
		
		this.dataSource=comboPooled;
	}
	
	public Connection recuperarConexion(){
		try {
			return this.dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
	}
	
}
