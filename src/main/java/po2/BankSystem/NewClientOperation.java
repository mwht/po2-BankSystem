package po2.BankSystem;

import java.util.regex.Pattern;

/**
 * NewClientOperation is {@link Operation} that adds new client to database.
 * 
 * @author Sebastian Madejski
 */
public class NewClientOperation extends Operation {
	private Client c;
	private ClientStorageEngine cse;
	
	/**
	 * Constructor for NewClientOperation.
	 * 
	 * Gets instance of {@link ClientStorageEngine} and sets commit message.
	 */
	public NewClientOperation() {
		cse = ClientStorageEngine.getInstance();
		c = null;
		setCommitMessage("Add client to system? [y/n] ");
	}

	/**
	 * Add {@link Client} to database and commit changes.
	 * 
	 * @return Status (true/false) whether operation was successful.
	 */
	@Override
	public boolean commit() {
		cse.addClient(c);
		cse.commit();
		System.out.println("Client added to database successfully.");
		return true;
	}
	
	/**
	 * Cancel addition of client.
	 * 
	 * @return Always true.
	 */
	@Override
	public boolean rollback() {
		System.out.println("New client addition cancelled.");
		return false;
	}

	/**
	 * Get data for new client for user and create {@link Client} object using these data.
	 */
	@Override
	public void perform() {
		System.out.println("Adding new client to system:");
		System.out.println("----------------------------");
		String name,surname,address;
		int id = 0;
		String pesel = "";
		id = getIntFromInput("ID: ");
		try {
			while(cse.findClient(id, Client.ClientCriteria.ID) != null) {
				System.out.println("Client with specified ID already exists in database. Please pick another ID.");
				id = getIntFromInput("ID: ");
			}
		} catch (ClientNotFoundException e) {
			// we expect client not to exist at this point
		}
		name = getStringFromInput("Name: ");
		surname = getStringFromInput("Surname: ");
		while(!Pattern.matches("\\d{11}",pesel)) {
			pesel = getStringFromInput("PESEL: ");
			if(!Pattern.matches("\\d{11}",pesel)) {
				System.out.println("Invalid PESEL.");
			}
		}
		address = getStringFromInput("Address: ");
		c = new Client(id,name,surname,pesel,address,0);
	}

	/**
	 * Returns always true since this operation is always privileged.
	 * 
	 * @return Always true.
	 */
	@Override
	public boolean isOperationPrivileged() {
		return true;
	}
}
