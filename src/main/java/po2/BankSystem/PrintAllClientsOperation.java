package po2.BankSystem;

public class PrintAllClientsOperation extends Operation {
	
	private Client[] clients;
	private ClientStorageEngine cse;
	
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
