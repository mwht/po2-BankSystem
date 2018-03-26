package po2.BankSystem;

public class PrintClientInfoOperation extends Operation {
	
	private ClientStorageEngine cse;
	
	public PrintClientInfoOperation() {
		cse = ClientStorageEngine.getInstance();
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
		String[] prompts = {"ID: ","Name: ","Surname: ","PESEL: ","Address: ","Balance: "};
		ClientCriteriaMenu ccm = new ClientCriteriaMenu();
		ccm.display();
		Client.ClientCriteria crit = Client.ClientCriteria.values()[ccm.getSelectedCriteria()];
		System.out.println("ID	|	Name	|	Surname	|	PESEL	|	Address	|	Balance");
		System.out.println("---------------------------------------------------------------");
	}

	@Override
	public boolean isOperationPrivileged() {
		return false;
	}

}
