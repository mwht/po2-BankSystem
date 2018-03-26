package po2.BankSystem;

public class PrintAllClientsOperation extends Operation {
	
	public PrintAllClientsOperation() {
		
	}

	@Override
	public boolean commit() {
		return true;
	}

	@Override
	public boolean rollback() {
		return true;
	}

	@Override
	public void perform() {
		System.out.println("ID	|	Name	|	Surname	|	PESEL	|	Address	|	Balance");
		System.out.println("---------------------------------------------------------------");
	}

	@Override
	public boolean isOperationPrivileged() {
		return false;
	}

}
