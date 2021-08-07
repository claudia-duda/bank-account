package database_JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Date;

import entities.User;

/*
 * Responsible to manipulate user table into the mysql
 * */
public class DAOUser extends DAO{
	
	
	public User getUser(int id) throws SQLException {
		
			ResultSet result = getResult(id);
			User user = new User(
					result.getString("CPF"),
					result.getString("fullName"),
					this.LocalDateConverter(result.getDate("birthday"))
					);		
			return user;
		
	}
	private void prepareStatementToUser(String CPF, Date birthday, String fullName) throws SQLException {
		try {
			PreparedStatement ps = this.connection.prepareStatement(this.sql);
			ps.setString(1, CPF);
			ps.setDate(2, birthday);
			ps.setString(3,fullName);
			
			ps.execute();
		} catch (SQLException e) {
			this.connection.rollback();
			e.printStackTrace();
		}
	}
	public void insert(User user) throws SQLException {
		this.sql = "INSERT INTO user (CPF,birthday,fullName) VALUES (? , ? , ?)";
		
		this.prepareStatementToUser(
				user.getCPF(), 
				Date.valueOf(user.getBirthday()),
				user.getFullName()
		);
		
	}
	
	public void edit(User user) throws SQLException {
		this.sql = "UPDATE user SET CPF = ?, birthday = ?, fullName = ?";
		this.prepareStatementToUser(
				user.getCPF(), 
				Date.valueOf(user.getBirthday()),
				user.getFullName()
		);
	}
	public void delete(int id) throws SQLException {
		this.sql = "DELETE FROM user WHERE id= ?";
		try {
			PreparedStatement ps = this.connection.prepareStatement(this.sql);
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