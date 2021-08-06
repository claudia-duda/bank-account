package database;

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
	public void insert(Account account) throws SQLException {
		String sql = "INSERT INTO account (balance, withdrawLimit, User_id) VALUES (?, ?, ?, ?)";
		
		
		try {
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.setDouble(1,account.getBalance());
			ps.setDouble(2, account.getWithdrawLimit());
			//ps.setObject(3, account.getuserData());
			
			ps.execute();
		} catch (SQLException e) {
			this.connection.rollback();
			e.printStackTrace();
		}
		
	}
	public Account getAccount(int id) {
		String sql = "SELECT * FROM account WHERE id = ?";
		
		try {
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet result= ps.executeQuery();
			Account account = new Account();
			
			result.first();
			account.setBalance(result.getDouble("balance"));
			account.setWithdrawLimit(result.getDouble("withdrawLimit"));
			//account.setUserData(result.getObject("User_id"));
			return account;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		
	}
}
