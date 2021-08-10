package database_JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.sql.Date;

import entities.User;

/*
 * Responsible to manipulate user table into the mysql
 * */
public class DAOUser extends DAO{
	
	public DAOUser() {
		super();
	}
	//get user using your id
	public User getUserData(int id) throws SQLException {
		this.sql = "SELECT * FROM user WHERE id = ?";
		ResultSet result = getFirstElement(id);
		
		if (result != null){
			LocalDate birthday = this.LocalDateConverter(result.getDate("birthday"));
			
			User user = new User(
					result.getString("CPF"),
					result.getString("fullName"),
					birthday,
					result.getInt("id")
					);		
			return user;			
		}
		return null;
	}
	//get the first element using CPF 
	private ResultSet getFirstElement(String CPF) {
		try {
			PreparedStatement ps = this.connection.prepareStatement(this.sql);
			ps.setString(1, CPF);
			ResultSet result= ps.executeQuery();
			if(result.first() != false ) {
				return result;
			}
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
		
	//search for an user by your CPF and return the id from it
	public Integer findUser(String CPF) throws SQLException {
		this.sql = "SELECT id FROM user WHERE CPF = ?";
		Integer id = null;
		ResultSet result = getFirstElement(CPF);
		
		if(result != null) {
			id =  result.getInt("id");
		}
		return id;
	}
	
	// responsible to prepare state changing their values
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
	//insert an element into database
	public void insert(User user) throws SQLException {
		this.sql = "INSERT INTO user (CPF,birthday,fullName) VALUES (? , ? , ?)";
		
		this.prepareStatementToUser(
				user.getCPF(), 
				Date.valueOf(user.getBirthday()),
				user.getFullName()
		);
		
		
	}
	//edit the user into the database
	public void edit(User user) throws SQLException {
		this.sql = "UPDATE user SET CPF = ?, birthday = ?, fullName = ?";
		this.prepareStatementToUser(
				user.getCPF(), 
				Date.valueOf(user.getBirthday()),
				user.getFullName()
		);
	}
	//delete the user using your id to find
	public void delete(int id) throws SQLException {
		this.sql = "DELETE FROM user WHERE id= ?";
		this.actionById(id);
	}
	//search and get all users putting into a list 
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
		
	}
	
	
	public LocalDate LocalDateConverter(Date date) {
		
		return date.toLocalDate();
	}
	
}