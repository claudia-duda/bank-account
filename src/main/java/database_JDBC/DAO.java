package database_JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DAO {
	
	protected ConnectionFactory connectionFactory;
	protected Connection connection;
	protected String sql;
	
	public DAO() {
		this.connectionFactory = ConnectionFactory.getInstance();
		this.connection = this.connectionFactory.getConnection();
	}
	protected ResultSet getFirstElement(int id) {
		try {
			PreparedStatement ps = this.connection.prepareStatement(this.sql);
			ps.setInt(1, id);
			ResultSet result= ps.executeQuery();
			if(result.first()) {
				return result;
			}
			return null;
			
		}catch (Exception e) {

			e.printStackTrace();
			return null;
		}	
	}
	protected ResultSet getFirstElement(String CPF) {
		try {
			PreparedStatement ps = this.connection.prepareStatement(this.sql);
			ps.setString(1, CPF);
			ResultSet result= ps.executeQuery();
			if(result.first() != false ) {
				return result;
			}
			return null;
		}catch (Exception e) {
			System.out.print("problems into "+ e.getMessage());
			e.printStackTrace();
			return null;
		}	
	}
	protected void actionById(int id) throws SQLException {
		try {
			PreparedStatement ps = this.connection.prepareStatement(this.sql);
			ps.setInt(1, id);
			ps.execute();
		}catch (Exception e) {
			this.connection.rollback();
			e.printStackTrace();
		}
	}
}
