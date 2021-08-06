package exceptions;

/*
 * BusinessException has relation to validateWithdraw located on Withdraw class. It's responsible to return some message about the problem
 */
public class BusinessException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public BusinessException(String msg) {
		super(msg);
	}
}
