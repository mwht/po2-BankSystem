package po2.BankSystem;

public class RootMenu extends Menu {
	private ClientStorageEngine cse;
	
	public RootMenu() {
		cse = ClientStorageEngine.getInstance();
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
				return "Exit";
		}
	}
	
	@Override
	public int getOptionsCount() {
		return 7;
	}
	
	@Override
	public Object onOption(int id) {
		switch(id) {
			case 1:
				new NewClientOperation().run();
				break;
			case 2:
				new DeleteClientOperation().run();
				break;
			case 3:
				new TransferMoneyOperation().run();
				break;
			case 4:
				new CashoutOperation().run();
				break;
			case 5:
				new WireTransferOperation().run();
				break;
			case 6:
				new PrintAllClientsOperation().run();
				break;
			case 7:
				new PrintClientInfoOperation().run();
				break;
			default:
				if(Operation.prompt("Commit changes? [y/n] ")) cse.commit();
				System.exit(0);
				break;
		}
		return null;
	}
}
