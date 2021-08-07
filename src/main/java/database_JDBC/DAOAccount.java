package database_JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import entities.Account;

public class DAOAccount {
	private ConnectionFactory connectionFactory;
	private Connection connection;
	
	public DAOAccount() {
		this.connectionFactory = ConnectionFactory.getInstance();
		this.connection = this.connectionFactory.getConnection();
	}
	
	public Account getAccount(int id) throws SQLException {
		String sql = "SELECT * FROM account WHERE id = ?";
		
		try {
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet result= ps.executeQuery();
			Account account = new Account();
			
			result.first();
			account.setBalance(result.getDouble("balance"));
			account.setWithdrawLimit(result.getDouble("withdrawLimit"));
			account.setUserId(result.getInt("User_id"));
			return account;
		}catch (SQLException e) {
			this.connection.rollback();
			e.printStackTrace();
			return null;
		}
	}
	
	public void insert(Account account) throws SQLException {
		String sql = "INSERT INTO account (balance, withdrawLimit, User_id) VALUES (?, ?, ?, ?)";
		
		
		try {
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.setDouble(1,account.getBalance());
			ps.setDouble(2, account.getWithdrawLimit());
			ps.setInt(3, account.getuserId());
			
			ps.execute();
		} catch (SQLException e) {
			this.connection.rollback();
			e.printStackTrace();
		}
		
	}

	public void edit(Account account) throws SQLException {
		String sql = "UPDATE account SET balance = ?, withdrawLimit = ?, User_id = ?";
		
		try {
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.setDouble(1,account.getBalance());
			ps.setDouble(2, account.getWithdrawLimit());
			ps.setInt(3, account.getuserId());
			ps.execute();
		} catch (Exception e) {
			this.connection.rollback();
			e.printStackTrace();
		}
	}
	
	public void delete(int id) throws SQLException {
		String sql = "DELETE FROM account WHERE id= ?";
		try {
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
		}catch (Exception e) {
			this.connection.rollback();
			e.printStackTrace();
		}
	}
	
}
