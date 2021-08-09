package entities;

import java.io.Serializable;

/*
 * The Account has all actions and user information from a basic account
 */
public class Account implements Serializable{

	private static final long serialVersionUID = 1L;
	private Double balance, withdrawLimit;
	private User user;
	private Integer id;
	
	public Account() {
	}
	
	//the main construct responsible to get all important information to an account
	public Account(Integer id, Double balance, Double withdrawLimit, User user){
	
		this.balance = balance;
		this.withdrawLimit = withdrawLimit;
		this.user = user; 
		this.id = id;
	}
	public Account(Double balance, Double withdrawLimit, User user){
		
		this.balance = balance;
		this.withdrawLimit = withdrawLimit;
		this.user = user; 
	}
	
	public Double getBalance() {
		return this.balance;
	}
	
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getWithdrawLimit() {
		return this.withdrawLimit;
	}

	public void setWithdrawLimit(Double withdrawLimit) {
		this.withdrawLimit = withdrawLimit;
	}
	
	public User getUser() {
		return this.user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getUser_id() {
		return this.user.getUserId();
	}
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	//return the important attributes
	@Override
	public String toString() {
		return "Conta [id= " + id + "saldo= " + balance + " limite conta= " + withdrawLimit +" Nome completo= " + user.getFullName() + "]";
	}
	//the way how the text is saving the account data
	public String formatStringTxt() {
		return "Account = ;" + id +";" + balance + ";" + withdrawLimit + ";" + this.user.getFullName();
	}
		

	
}
