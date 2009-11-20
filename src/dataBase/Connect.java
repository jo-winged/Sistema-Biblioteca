package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	private static Connect self = null;
	Connection c;
	
	public Connection getC() {
		return c;
	}
	public void setC(Connection c) {
		this.c = c;
	}
	
	public Connect() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		this.c = DriverManager.getConnection(
				"jdbc:postgresql://localhost/dbLibrary", 
				"postgres", 
				"joseane");
	}
	public static Connect New() throws ClassNotFoundException, SQLException{
		if (Connect.self == null)
			Connect.self = new Connect();
		return Connect.self;
	}
}
