package po2.BankSystem;

public class DeleteClientOperation extends Operation {
	
	ClientStorageEngine cse;
	Client target;

	public DeleteClientOperation() {
		setCommitMessage("Delete client? [y/n] ");
		cse = ClientStorageEngine.getInstance();
		target = null;
	}
	
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

	@Override
	public boolean rollback() {
		System.out.println("Client deletion cancelled.");
		return true;
	}

	@Override
	public void perform() {
		System.out.println("Delete client from database:");
		System.out.println("----------------------------");
		int id = getIntFromInput("ID: ");
		try {
			target = cse.findClient(id, Client.ClientCriteria.ID);
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
