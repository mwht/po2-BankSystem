package po2.BankSystem;

public class InvalidBalanceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidBalanceException() { super(); }
	public InvalidBalanceException(String message) { super(message); }
}