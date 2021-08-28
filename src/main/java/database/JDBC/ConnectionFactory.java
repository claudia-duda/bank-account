package database.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;

/*
 * Connection using JDBC and mysql
 */
public class ConnectionFactory {
	
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	
	//path to the database
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3308/bank?serverTimezone=UTC";
	
	private static ConnectionFactory instance;
	private Connection connection;
	
	//call and build a connection as soon as created
	public ConnectionFactory() {
		this.builderConnection();
	}
	
	public static ConnectionFactory getInstance() {
		if (instance == null) {
			instance = new ConnectionFactory();
		}
		return instance;
	}
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public void builderConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection(DATABASE_URL,USERNAME,PASSWORD);
			
		}catch (Exception e) {
			
			System.out.println("Erro ao conectar: "+ e.getMessage());
			
		}
		
	}
}
