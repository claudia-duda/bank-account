package entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/*
 * The Account has all actions and user information from a basic account
 */
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SequenceGenerator(name = "account_sequence", sequenceName = "account_sequence", initialValue = 1, allocationSize = 1)
@Table(name = "account")
@Entity
public class Account implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "account_sequence")
	@Id
	private Integer id;
	private Double balance, withdrawLimit;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User user;
	
	public Account() {
	}
	
	//TODO att documentation
	/**
	 * the main construct responsible to get all important information to an account
	 * @param id
	 * @param balance
	 * @param withdrawLimit
	 * @param user
	 */
	public Account(Integer id, Double balance, Double withdrawLimit, User user){
	
		this.balance = balance;
		this.withdrawLimit = withdrawLimit;
		this.user = user; 
		this.id = id;
	}
	/**
	 * 
	 * @param balance
	 * @param withdrawLimit
	 * @param user
	 */
	
	public Account(Double balance, Double withdrawLimit, User user){
		
		this.balance = balance;
		this.withdrawLimit = withdrawLimit;
		this.user = user; 
	}
	
	//return the important attributes
	@Override
	public String toString() {
		return "Conta [id= " + id + " saldo= " + balance + " limite conta= " + withdrawLimit +" Nome completo= " + user.getFullName() + "]";
	}
	//the way how the text is saving the account data
	public String formatStringTxt() {
		return "Account = ;" + id +";" + balance + ";" + withdrawLimit + ";" + this.user.getFullName();
	}
		

	
	
}

