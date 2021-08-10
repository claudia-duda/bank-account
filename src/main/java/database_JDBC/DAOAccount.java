package database_JDBC;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Account;
import entities.User;

/*
 * Responsible to manipulate account table into the mysql
 * */
public class DAOAccount extends DAO{

	public DAOAccount() {
		super();
	}
	//send the account object using your id to find 
	public Account getAccountData(int id) throws SQLException {
		this.sql = "SELECT account.id as id, balance, withdrawLimit,User_id,"
				+ "fullName FROM account INNER JOIN user ON account.User_id = user.id WHERE account.id = ? ";
		
		ResultSet result = getFirstElement(id);
		
		User user = new User();
		user.setFullName(result.getString("fullName"));
		user.setUserId(result.getInt("User_id"));
		
		Account account = new Account(
				result.getInt("id"),
				result.getDouble("balance"),
				result.getDouble("withdrawLimit"),
				user
		);
		return account;
		
	}
	//find an account using your user id
	public Integer findId(Integer userId) throws SQLException {
		this.sql = "SELECT * FROM account WHERE User_id = ?";
		ResultSet result = getFirstElement(userId);
		Account account = this.getAccountData(result.getInt("id"));
		return account.getId();
	}
	//prepare state to account
	private void prepareStatementToAccount(Double balance, Double withdrawLimit,Integer userId, Boolean update ) throws SQLException {
		
		try {
			PreparedStatement ps = this.connection.prepareStatement(this.sql);
			ps.setDouble(1,balance);
			ps.setDouble(2, withdrawLimit);
			ps.setInt(3, userId);
			
			if (update == true) {
				ps.setInt(4, userId);
				
			}
			ps.execute();
			
		}catch (SQLException e) {
			this.connection.rollback();
			e.printStackTrace();
		}
	}	
	//get a account object and put it into the database
	public void insert(Account account) throws SQLException {
		this.sql = "INSERT INTO account (balance, withdrawLimit, User_id) VALUES (? , ? , ?)";
		
		this.prepareStatementToAccount(
				account.getBalance(), 
				account.getWithdrawLimit(), 
				account.getUser_id(),
				false
		);
	}
	//receive the account to edit and update it into the database
	public void edit(Account account) throws SQLException {
		this.sql = "UPDATE account SET balance = ?, withdrawLimit = ?, User_id = ? WHERE User_id = ?";
		
		this.prepareStatementToAccount(
				account.getBalance(), 
				account.getWithdrawLimit(), 
				account.getUser_id(),
				true
		);
	}
	//delete an account using your user id
	public void delete(int userId) throws SQLException {
		this.sql = "DELETE FROM account WHERE User_id= ?";
		this.actionById(userId);
	}
	
	//list accounts
	public LinkedList<Account> allAccounts() throws SQLException{
		this.sql = "SELECT account.id as id, balance, withdrawLimit,User_id,"
				+ "fullName FROM account INNER JOIN user ON account.User_id = user.id";
		
		PreparedStatement ps;
		try {
			ps = this.connection.prepareStatement(this.sql);
			ResultSet result = ps.executeQuery();
			
			LinkedList<Account> accounts = new LinkedList<>();
			
			while(result.next()) {
				Account account = this.getAccountData(result.getInt("id"));
		
				accounts.add(account);
			}
				return accounts;
			
		} catch (SQLException e) {
			this.connection.rollback();
			e.printStackTrace();
			return null;
		}	
	}
}
