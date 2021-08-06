package exceptions;

/*
 * InvalidBillException has relation to presentialDeposit located on Deposit class. It's responsible to return the bill problem
 */
public class InvalidBillException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private double invalidBill;

	public InvalidBillException(double bill) {
		super("Valor que deseja depositar n�o conduz com os valores de notas existentes");
		invalidBill = bill;
	}

	public double getInvalidBill() {
		
		return invalidBill;
	}
	
	
}