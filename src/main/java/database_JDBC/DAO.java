package database_JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAO {
	
	protected ConnectionFactory connectionFactory;
	protected Connection connection;
	protected String sql;
	
	public DAO() {
		this.connectionFactory = ConnectionFactory.getInstance();
		this.connection = this.connectionFactory.getConnection();
	}
	public ResultSet getResult(int id) {
		try {
			PreparedStatement ps = this.connection.prepareStatement(this.sql);
			ps.setInt(1, id);
			ResultSet result= ps.executeQuery();
			result.first();
			return result;
		}catch (Exception e) {

			e.printStackTrace();
			return null;
		}	
	}
}
