package po2.BankSystem;

public class InvalidBalanceException extends Exception {
	public InvalidBalanceException() { super(); }
	public InvalidBalanceException(String message) { super(message); }
}