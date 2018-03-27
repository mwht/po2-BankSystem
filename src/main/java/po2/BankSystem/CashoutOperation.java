package po2.BankSystem;

/**
 * CashoutOperation is {@link Operation} that cashes out money from {@link Client}'s account.
 * 
 * @author Sebastian Madejski
 *
 */
public class CashoutOperation extends Operation {
	
	private ClientStorageEngine cse;
	private Client c;
	private double balance;

	/**
	 * Constructor for CashoutOperation.
	 * 
	 * Gets instance of {@link ClientStorageEngine} and sets the commit message.
	 */
	public CashoutOperation() {
		cse = ClientStorageEngine.getInstance();
		c = null;
		setCommitMessage("Confirm money cashout? [y/n] ");
	}
	
	/**
	 * Commit changes to database. Print out message in case of error.
	 * 
	 * @return State (true/false) wheter operation was successful.
	 */
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

	/**
	 * Cancel the operation.
	 * 
	 * @return Always true.
	 */
	@Override
	public boolean rollback() {
		System.out.println("Cashout cancelled.");
		return true;
	}

	/**
	 * Get client ID from input and amount of money to be cashed out.
	 */
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
