package database_JDBC;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import entities.Account;
/*
 * Responsible to manipulate account table into the mysql
 * */
public class DAOAccount extends DAO{


	public Account getAccountData(int id) throws SQLException {
		this.sql = "SELECT * FROM account WHERE id = ?";
		
			ResultSet result = getResult(id);
			Account account = new Account(
					result.getDouble("balance"),
					result.getDouble("withdrawLimit"),
					result.getInt("User_id")
			);
			return account;
		
	}
	private void prepareStatementToAccount(Double balance, Double withdrawLimit,Integer userId) throws SQLException {
		try {
			PreparedStatement ps = this.connection.prepareStatement(this.sql);
			ps.setDouble(1,balance);
			ps.setDouble(2, withdrawLimit);
			ps.setInt(3, userId);
			ps.execute();
		
		}catch (SQLException e) {
			this.connection.rollback();
			e.printStackTrace();
		}
	}	
	public void insert(Account account) throws SQLException {
		this.sql = "INSERT INTO account (balance, withdrawLimit, User_id) VALUES (?, ?, ?, ?)";

		this.prepareStatementToAccount(
				account.getBalance(), 
				account.getWithdrawLimit(), 
				account.getuserId()
		);
	}

	public void edit(Account account) throws SQLException {
		this.sql = "UPDATE account SET balance = ?, withdrawLimit = ?, User_id = ?";
		
		this.prepareStatementToAccount(
				account.getBalance(), 
				account.getWithdrawLimit(), 
				account.getuserId()
		);
	}
	
	public void delete(int id) throws SQLException {
		this.sql = "DELETE FROM account WHERE id= ?";
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
