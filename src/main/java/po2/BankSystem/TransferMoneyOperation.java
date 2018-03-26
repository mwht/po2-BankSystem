package po2.BankSystem;

public class TransferMoneyOperation extends Operation {
	
	private ClientStorageEngine cse;
	private Client c;

	public TransferMoneyOperation() {
		cse = ClientStorageEngine.getInstance();
		c = null;
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
		int id = getIntFromInput("Client ID: ");
		try {
			c = cse.findClient(id, Client.ClientCriteria.ID);
			
		}
	}

	@Override
	public boolean isOperationPrivileged() {
		return true;
	}

}
