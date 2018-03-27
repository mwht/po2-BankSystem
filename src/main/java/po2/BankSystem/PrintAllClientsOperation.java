package po2.BankSystem;

/**
 * PrintAllClientsOperation is {@link Operation} that prints all the clients in database.
 * 
 * @author Sebastian Madejski
 *
 */
public class PrintAllClientsOperation extends Operation {
	
	private Client[] clients;
	private ClientStorageEngine cse;
	
	/**
	 * Constructor for PrintAllClientsOperation.
	 * 
	 * Gets the instance of {@link ClientStorageEngine} and fetches all record from database
	 */
	public PrintAllClientsOperation() {
		cse = ClientStorageEngine.getInstance();
		clients = cse.getAllClients();
	}

	@Override
	public boolean commit() {
		return true;
	}

	@Override
	public boolean rollback() {
		return true;
	}
	
	/**
	 * Prints all clients from database.
	 */
	@Override
	public void perform() {
		System.out.println("Clients: " + cse.getClientCount());
		System.out.println();
		System.out.println("ID	|	Name	|	Surname	|	PESEL	|	Address	|	Balance");
		System.out.println("---------------------------------------------------------------");
		for(int i=0;i<clients.length;i++) {
			System.out.println(clients[i].getTabulatedInfo());
		}
	}
	
	@Override
	public boolean isOperationPrivileged() {
		return false;
	}

}
