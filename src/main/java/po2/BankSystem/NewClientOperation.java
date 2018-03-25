package po2.BankSystem;

import java.util.Scanner;

public class NewClientOperation extends Operation {
	private Scanner in;
	
	public NewClientOperation() {
		in = new Scanner(System.in);
	}

	@Override
	public boolean commit() {
		return false;
	}

	@Override
	public boolean rollback() {
		return false;
	}

	@Override
	public void perform() {
		
	}
}
