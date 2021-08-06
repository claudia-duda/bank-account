package entities;

import java.io.Serializable;
import java.time.LocalDate;

/*
 * The Account has all actions and user information from a basic account
 */
public class Account implements Serializable{

	private static final long serialVersionUID = 1L;
	private Double balance;
	private Double withdrawLimit;
	private UserData userData;
	public Account() {
	}
	//the main construct responsible to get all important information to an account
	public Account(Double balance, Double withdrawLimit, String holder, String CPF, LocalDate birthday){
	
		this.balance = balance;
		this.withdrawLimit = withdrawLimit;
		this.userData = new UserData(holder,CPF,birthday);
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
	
	public String getuserData() {
		return this.userData.toString();
	}
	public void setUserData(UserData user) {
		this.userData = user;
	}
	public String getCPF() {
		return this.userData.getCPF();
	}

	//return the important attributes
	@Override
	public String toString() {
		return "Conta [saldo= " + balance + " limite conta= " + withdrawLimit + userData + "]";
	}
	//the way how the text is saving the account data
	public String formatStringTxt() {
		return "Account = ;" + balance + ";" + withdrawLimit + ";"+ userData.formatStringTxt() ;
	}
		

	
}
