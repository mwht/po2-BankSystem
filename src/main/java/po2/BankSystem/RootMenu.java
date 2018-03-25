package po2.BankSystem;

import java.util.Scanner;

public class RootMenu extends Menu {
	private ClientStorageEngine cse;
	private Scanner in;
	
	public RootMenu() {
		cse = new ClientStorageEngine("clients.csv");
		in = new Scanner(System.in);
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
		if(id < 1 || id > 7) {
			String prompt = "";
			while(!(prompt.equals("y") || prompt.equals("n"))) {
				System.out.print("Commit changes? [y/n] ");
				prompt = in.nextLine();
				if(prompt.equals("y")) cse.commit();
				System.exit(0);
			}
		}
		return null;
	}
}
