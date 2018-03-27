package po2.BankSystem;

/**
 * RootMenu is the main menu of the system. Upon exit, the application ends.
 * 
 * @author Sebastian Madejski
 *
 */
public class RootMenu extends Menu {
	private ClientStorageEngine cse;
	
	/**
	 * Constructor for RootMenu.
	 * 
	 * Gets the instance of {@link ClientStorageEngine}.
	 */
	public RootMenu() {
		cse = ClientStorageEngine.getInstance();
	}
	
	/**
	 * Gets the menu title.
	 * 
	 * @return "Bank System - main menu" string.
	 */
	@Override
	public String getMenuTitle() {
		return "Bank System - main menu";
	}
	
	/**
	 * Gets the option string for queried ID.
	 */
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
	
	
	/**
	 * Get the options count.
	 */
	@Override
	public int getOptionsCount() {
		return 7;
	}
	
	/**
	 * Create and run respective {@link Operation}s. If operation does not exist, ask whether to commit changes and exit the menu.
	 * 
	 * @return Nothing. (null)
	 */
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
				exit();
				break;
		}
		return null;
	}
}
