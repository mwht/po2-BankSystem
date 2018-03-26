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
		ClientCriteriaMenu ccm = new ClientCriteriaMenu();
		ccm.display();
		System.out.println(Client.ClientCriteria.values()[ccm.getSelectedCriteria()]);
	}

	@Override
	public boolean isOperationPrivileged() {
		return false;
	}

}
