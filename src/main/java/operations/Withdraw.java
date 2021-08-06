package operations;

import exceptions.BusinessException;
/*
 * Withdraw is responsible to decrease the balance from some account
 */
public class Withdraw extends Operation{

	private double balance;
	private double amount;
	private double withdrawLimit;
	
	//the main construct to get the important values to finish the operation
	public Withdraw(double balance, double withdrawLimit) {
		this.balance = balance;
		this.withdrawLimit = withdrawLimit;
	}

	//throw a business exception if the amount isn't available or the amount is bigger than withdraw limit
	private void validateWithdraw() {
		if (this.amount > this.withdrawLimit) {
			throw new BusinessException("Erro de saque: A quantia excede o limite de saque");
		} 
		if (this.amount > this.balance) {
			throw new BusinessException("Erro de saque: Saldo insuficiente");
		}
	}
	//the main method to decrease the balance accordingly 
	@Override
	public double action(double balance, double value) {
		this.amount = value;
		validateWithdraw();
		return balance-= value;
	}
	
}
