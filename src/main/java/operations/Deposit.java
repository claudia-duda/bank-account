package operations;

import exceptions.InvalidBillException;
/*
 * Deposit is responsible to increase the balance from some account
 */
 
public class Deposit extends  Operation{

	private double amount;
	
	//an optional method responsible for depositing paper money into an account in person
	private double presentialDeposit() throws InvalidBillException {
		if(this.amount % 2 == 0 || this.amount % 2 == 1) {
			
			return amount;			
		}
		else {
			throw new InvalidBillException(amount);
		}
	}
	//the main method to increase the balance accordingly 
	@Override
	public double action(double balance, double value) {
		this.amount = value;
		presentialDeposit();
		return amount +=balance;
	}
}
