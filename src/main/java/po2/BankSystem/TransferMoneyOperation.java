package po2.BankSystem;

/**
 * TransferMoneyOperation is {@link Operation}, that transfers some amount of money to bank account with specified ID.
 * 
 * @author Sebastian Madejski
 *
 */
public class TransferMoneyOperation extends Operation {
	
	private ClientStorageEngine cse;
	private Client c;
	private double balance;

	/**
	 * Constructor for TransferMoneyOperation
	 */
	public TransferMoneyOperation() {
		cse = ClientStorageEngine.getInstance();
		c = null;
		setCommitMessage("Confirm money transfer? [y/n] ");
	}
	
	/**
	 * Transfer money and commit changes to database.
	 * 
	 * @return status (true/false) whether operation succeded.
	 */
	@Override
	public boolean commit() {
		try {
			c.addBalance(balance);
			if(!cse.commit()) {
				System.out.println("Can't write changes to file!");
			}
		} catch(InvalidBalanceException ib) {
			System.out.println("Error occured during money transfer: " + ib.getMessage());
			return false;
		}
		System.out.println("Money transferred successfully.");
		return true;
	}

	/**
	 * Cancel operation ant print out message.
	 * 
	 * @return always true
	 */
	@Override
	public boolean rollback() {
		System.out.println("Money transfer cancelled.");
		return true;
	}

	/**
	 * Get ID and amount of money to be transfered from user.
	 */
	@Override
	public void perform() {
		System.out.println("Money transfer to account:");
		System.out.println("--------------------------");
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
