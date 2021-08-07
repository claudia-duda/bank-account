package database_JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Date;

import entities.User;

public class DAOUser {
	private ConnectionFactory connectionFactory;
	private Connection connection;
	private String sql;
	public DAOUser() {
		this.connectionFactory = ConnectionFactory.getInstance();
		this.connection = this.connectionFactory.getConnection();
	}
	public void insert(User user) throws SQLException {
		sql = "INSERT INTO user (CPF,birthday,fullName) VALUES (? , ? , ?)";
		
		try {
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.setString(1, user.getCPF());
			ps.setDate(2, Date.valueOf(user.getBirthday()));
			ps.setString(3, user.getFullName());
			
			ps.execute();
		} catch (SQLException e) {
			this.connection.rollback();
			e.printStackTrace();
		}
	}
	
	public User getUser(int id) {
		sql = "SELECT * FROM user WHERE id = ?";
			
		try {
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet result= ps.executeQuery();
			User user = new User();
			
			result.first();
			user.setCPF(result.getString("CPF"));
			user.setBirthday(this.LocalDateConverter(result.getDate("birthday")));
			user.setFullName(result.getString("fullName"));
			
			return user;
		}catch (Exception e) {

			e.printStackTrace();
			return null;
		}	
	}
	
	public void edit(User user) throws SQLException {
		sql = "UPDATE user SET CPF = ?, birthday = ?, fullName = ?";
		try {
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.setString(1, user.getCPF());
			ps.setDate(2, Date.valueOf(user.getBirthday()));
			ps.setString(3, user.getFullName());
			
			ps.execute();
		} catch (Exception e) {
			this.connection.rollback();
			e.printStackTrace();
		}
	}
	public void delete(int id) throws SQLException {
		String sql = "DELETE FROM user WHERE id= ?";
		try {
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
		}catch (Exception e) {
			this.connection.rollback();
			e.printStackTrace();
		}
	}
	
	public LocalDate LocalDateConverter(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
}