package po2.BankSystem;

import java.util.Scanner;

public class NewClientOperation extends Operation {
	private Scanner in;
	private Client c;
	private ClientStorageEngine cse;
	
	public NewClientOperation() {
		in = new Scanner(System.in);
		cse = ClientStorageEngine.getInstance();
		c = null;
	}

	@Override
	public boolean commit() {
		cse.addClient(c);
		cse.commit();
		System.out.println("Client added to database successfully.");
		return true;
	}

	@Override
	public boolean rollback() {
		System.out.println("New client addition cancelled.");
		return false;
	}

	@Override
	public void perform() {
		System.out.println("Adding new client to system:");
		System.out.println("----------------------------");
		String name,surname,address;
		int id = 0;
		long pesel = 0L;
		id = getIntFromInput("ID: ");
		while(cse.findClient(id, Client.ClientCriteria.ID) != null) {
			System.out.println("Client with specified ID already exists in database. Please pick another ID.");
			id = getIntFromInput("ID: ");
		}
		name = getStringFromInput("Name: ");
		surname = getStringFromInput("Surname: ");
		pesel = getLongFromInput("PESEL: ");
		address = getStringFromInput("Address: ");
		c = new Client(id,name,surname,pesel,address,0);
	}

	@Override
	public boolean isOperationPrivileged() {
		return true;
	}
}
