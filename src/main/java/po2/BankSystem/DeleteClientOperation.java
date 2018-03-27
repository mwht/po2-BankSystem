package po2.BankSystem;

/**
 * DeleteClientOperation is {@link Operation} that removes client from database.
 * 
 * @author Sebastian Madejski
 *
 */
public class DeleteClientOperation extends Operation {
	
	ClientStorageEngine cse;
	Client target;
	
	/**
	 * Constructor for DeleteClientOperation.
	 * 
	 * Gets instance of {@link ClientStorageEngine} and sets the commit message.
	 */
	public DeleteClientOperation() {
		setCommitMessage("Delete client? [y/n] ");
		cse = ClientStorageEngine.getInstance();
		target = null;
	}
	
	/**
	 * Deletes the client and commits changes to database. Prints error message on failure.
	 * 
	 * @return state (true/false) whether the operation was successful.
	 */
	@Override
	public boolean commit() {
		if(cse.deleteClient(target)) {
			if(cse.commit()) {
				System.out.println("Client deleted successfully.");
				return true;
			} else {
				System.out.println("Can't write changes to file!");
				return false;
			}
		} else {
			System.out.println("Can't delete client from database!");
			return false;
		}
	}
	
	/**
	 * Cancel the operation.
	 * 
	 * @return Always true.
	 */
	@Override
	public boolean rollback() {
		System.out.println("Client deletion cancelled.");
		return true;
	}
	
	/**
	 * Gets the client ID from user and find the client to delete.
	 */
	@Override
	public void perform() {
		System.out.println("Delete client from database:");
		System.out.println("----------------------------");
		int id = getIntFromInput("ID: ");
		try {
			target = cse.findClient(id, Client.ClientCriteria.ID);
			System.out.print(target);
		} catch(ClientNotFoundException cnf) {
			System.out.println("Error: " + cnf.getMessage());
		}
	}

	@Override
	public boolean isOperationPrivileged() {
		/* if target is not found, we can't delete anything, so we 
		   can't touch the database and operation doesn't have to be privileged */
		return (target != null);
	}

}
