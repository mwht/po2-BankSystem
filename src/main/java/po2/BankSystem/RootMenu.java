package po2.BankSystem;

public class RootMenu extends Menu {
	private ClientStorageEngine cse;
	
	public RootMenu() {
		cse = new ClientStorageEngine("clients.csv");
	}
	
	@Override
	public String getMenuTitle() {
		return "Bank System - main menu";
	}
	
	@Override
	public String getOptionString(int id) {
		switch(id) {
			case 1:
				return "New client";
			case 2:
				return "Delete client";
			case 3:
				return "Transfer money to client";
			case 4:
				return "Cashout money for client";
			case 5:
				return "Transfer from client to client";
			case 6:
				return "Display all clients";
			case 7:
				return "Find client...";
			default:
				return "Cancel";
		}
	}
	
	@Override
	public int getOptionsCount() {
		return 7;
	}
}
