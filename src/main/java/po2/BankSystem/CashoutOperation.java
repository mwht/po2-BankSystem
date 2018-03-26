package po2.BankSystem;

public class CashoutOperation extends Operation {
	
	private ClientStorageEngine cse;
	private Client c;
	private double balance;

	public CashoutOperation() {
		cse = ClientStorageEngine.getInstance();
		c = null;
		setCommitMessage("Confirm money cashout? [y/n] ");
	}
	
	@Override
	public boolean commit() {
		try {
			c.subBalance(balance);
			if(!cse.commit()) {
				System.out.println("Can't write changes to file!");
			}
		} catch(InvalidBalanceException ib) {
			System.out.println("Error occured during money transfer: " + ib.getMessage());
			return false;
		}
		System.out.println("Money cashed out successfully.");
		return true;
	}

	@Override
	public boolean rollback() {
		return true;
	}

	@Override
	public void perform() {
		System.out.println("Cashout from account:");
		System.out.println("---------------------");
		int id = getIntFromInput("Client ID: ");
		try {
			c = cse.findClient(id, Client.ClientCriteria.ID);
			System.out.print(c);
			balance = getDoubleFromInput("Balance: ");
		} catch(ClientNotFoundException csf) {
			System.out.println("Client not found!");
		}
	}

	@Override
	public boolean isOperationPrivileged() {
		return (c != null);
	}

}
