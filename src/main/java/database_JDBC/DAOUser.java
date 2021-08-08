package database_JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.LinkedList;
import java.sql.Date;

import entities.User;

/*
 * Responsible to manipulate user table into the mysql
 * */
public class DAOUser extends DAO{
	
	
	public User getUserData(int id) throws SQLException {
		
			ResultSet result = getFirstElement(id);
			
			User user = new User(
					result.getString("CPF"),
					result.getString("fullName"),
					this.LocalDateConverter(result.getDate("birthday")),
					result.getInt("id")
					);		
			return user;
		
	}
	//search for an user by your CPF and return the id from it
	public Integer findUser(String CPF) throws SQLException {
		this.sql = "SELECT * FROM user WHERE CPF = ?";
		ResultSet result = getFirstElement(CPF);
		return result.getInt("id");
		
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
		this.actionById(id);
	}
	public LinkedList<User> allUsers() throws SQLException{
		this.sql = "SELECT * FROM user";
		
		PreparedStatement ps;
		try {
			ps = this.connection.prepareStatement(this.sql);
			ResultSet result = ps.executeQuery();
			
			LinkedList<User> users = new LinkedList<>();
			
			while(result.next()) {
				User user = this.getUserData(result.getInt("id"));
				users.add(user);
			}
			return users;
		} catch (SQLException e) {
			this.connection.rollback();
			e.printStackTrace();
			return null;
		}
		// or
		/*
		 * ResultSet result = this.getFirstElement(1)
		 * if (result != null){
		 *     LinkedList<Account> accounts = new LinkedList<>();
			
				while(result.next()) {
					Account account = this.getAccountData(result.getInt("id"));
					accounts.add(account);
				}
				return accounts;
			}
		 * */
		
	}
	
	
	public LocalDate LocalDateConverter(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
}