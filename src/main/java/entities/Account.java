package entities;

import java.io.Serializable;
import java.time.LocalDate;

/*
 * The Account has all actions and user information from a basic account
 */
public class Account implements Serializable{

	private static final long serialVersionUID = 1L;
	private Double balance, withdrawLimit;
	private Integer userId;
	
	public Account() {
	}
	
	//the main construct responsible to get all important information to an account
	public Account(Double balance, Double withdrawLimit, Integer userId){
	
		this.balance = balance;
		this.withdrawLimit = withdrawLimit;
		this.userId = userId; 
	}
	
	public Double getBalance() {
		return balance;
	}
	
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getWithdrawLimit() {
		return withdrawLimit;
	}

	public void setWithdrawLimit(Double withdrawLimit) {
		this.withdrawLimit = withdrawLimit;
	}
	
	public Integer getuserId() {
		return this.userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	//return the important attributes
	@Override
	public String toString() {
		return "Conta [saldo= " + balance + " limite conta= " + withdrawLimit +" Id usuário" + userId + "]";
	}
	//the way how the text is saving the account data
	public String formatStringTxt() {
		return "Account = ;" + balance + ";" + withdrawLimit + ";";
	}
		

	
}
