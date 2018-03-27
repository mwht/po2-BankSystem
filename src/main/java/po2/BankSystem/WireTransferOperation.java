package po2.BankSystem;

/**
 * WireTransferOperation is {@link Operation} that transferes money from account to account.
 * 
 * @author Sebastian Madejski
 *
 */
public class WireTransferOperation extends Operation {
	private Client src,dst;
	private ClientStorageEngine cse;
	private double balance;
	
	/**
	 * Constructor for WireTransferOperation.
	 * 
	 * Gets instance of {@link ClientStorageEngine} and sets commit message.
	 */
	public WireTransferOperation() {
		src = null;
		dst = null;
		cse = ClientStorageEngine.getInstance();
		setCommitMessage("Perform wire transfer? [y/n] ");
	}
	
	/**
	 * Performs actual money transfer. Prints error message in case of error.
	 * 
	 * @return state (true/false) whether operation was successful.
	 */
	@Override
	public boolean commit() {
		try {
			src.subBalance(balance);
			dst.addBalance(balance);
			if(!cse.commit()) {
				System.out.println("Can't write changes to file!");
			}
		} catch(InvalidBalanceException ib) {
			System.out.println("Can't wire transfer money: "+ib.getMessage());
		}
		return true;
	}

	/**
	 * Cancels the operation.
	 * 
	 * @return Always true.
	 */
	@Override
	public boolean rollback() {
		System.out.println("Wire transfer cancelled.");
		return true;
	}

	/**
	 * Gets information about transferer and transferee and balance to transfer.
	 */
	@Override
	public void perform() {
		int srcID,dstID;
		
		srcID = getIntFromInput("Transferer ID: ");
		try {
			src = cse.findClient(srcID, Client.ClientCriteria.ID);
			System.out.print(src);
		} catch(ClientNotFoundException cnf) {
			System.out.println("Transferer not found!");
			return;
		}
		
		dstID = getIntFromInput("Transferee ID: ");
		try {
			dst = cse.findClient(dstID, Client.ClientCriteria.ID);
			System.out.print(dst);
		} catch(ClientNotFoundException cnf) {
			System.out.println("Transferee not found!");
			return;
		}
		
		balance = getDoubleFromInput("Balance to transfer: ");
		
	}

	@Override
	public boolean isOperationPrivileged() {
		return (src != null && dst != null);
	}

}
