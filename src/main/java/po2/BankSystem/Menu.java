package po2.BankSystem;

import java.util.Scanner;

/**
 * Menu is class representing generic menu providing methods for displaying the menu
 * and handling the option selection.
 * 
 * @author Sebastian Madejski
 *
 */
public abstract class Menu {
	
	private boolean menuRunning = true;
	private Scanner in;
	
	/**
	 * Format string into ASCII-art style drawing
	 * header.
	 * 
	 * @param str String to be formatted
	 * @return Formatted print-ready string
	 */
	public static String prettyHeader(String str) {
		StringBuffer buf = new StringBuffer();
		int length = str.length();
		buf.append("+");
		for(int i=0;i<length+2;i++) buf.append("-");
		buf.append("+\n");
		
		buf.append("| ");
		buf.append(str.toUpperCase());
		buf.append(" |\n");
		
		buf.append("+");
		for(int i=0;i<length+2;i++) buf.append("-");
		buf.append("+\n");
		return buf.toString();
	}
	
	/**
	 * Tell the menu to exit when it gets the control back.
	 */
	public void exit() {
		menuRunning = false;
	}
	
	/**
	 * Display menu and run main loop.
	 * 
	 * This method takes away control from program until option is selected
	 * by user.
	 */
	public void display() {
		boolean optionSelected;
		int optionID;
		in = new Scanner(System.in);
		while(menuRunning) {
			optionSelected = false;
			System.out.println(prettyHeader(getMenuTitle()));
			for(int i=0;i<getOptionsCount();i++) {
				System.out.println((i+1)+") "+getOptionString(i+1));
			}
			System.out.println("other number) "+getOptionString(0));
			System.out.println("q) exit");
			System.out.println();
			while(!optionSelected && menuRunning) {
				System.out.print("Select an option: ");
				String temp = in.nextLine();
				if(temp.length() > 0) {
					try {
						if(temp.equals("q")) {
							onExit();
							exit();
							break;
						}
						optionID = Integer.parseInt(temp);
						if(optionID >= 0) {
							onOption(optionID);
							optionSelected = true;
						}
					} catch(NumberFormatException nfe) {
						System.out.println("NumberFormatException caught (most likely non-number was given as an input): "+nfe.getLocalizedMessage());
						System.out.println(prettyHeader(getMenuTitle()));
						for(int i=0;i<getOptionsCount();i++) {
							System.out.println((i+1)+") "+getOptionString(i+1));
						}
						System.out.println("other number) "+getOptionString(0));
						
						System.out.println("q) exit");
						System.out.println();
					}
				}
			}
		}
	}
	
	/**
	 * Get the menu title. 
	 * 
	 * Needs to be implemented in subclass.
	 * @return title of current menu
	 */
	public abstract String getMenuTitle();
	
	/**
	 * Get the option count for current menu
	 * 
	 * Needs to be implemented in subclass.
	 * @return option count in current menu
	 */
	public abstract int getOptionsCount();
	
	/**
	 * Get the option string for queried ID in menu.
	 * 
	 * Method has to take "default" option into the account, queried when
	 * asked for undefined action.
	 * 
	 * <pre>
	 * {@code
	 * public String getOptionString(int id) {
	 * 	if(id == 1) {
	 * 		return "Option 1";
	 * 	} else if(id == 2) {
	 * 		return "Option 2";
	 * 	}
	 *  [...]
	 *  } else if(id == 9) {
	 *  	return "Option 9";
	 *  } else {
	 *  	return "Other options"; // this will be displayed when queried for undefined action
	 *  }
	 * }
	 * </pre>
	 * 
	 * @param id Identifier of currently queried menu item.
	 * @return String for currently queried menu item.
	 */
	public abstract String getOptionString(int id);
	
	/**
	 * Handles the selection of currently selected menu item.
	 * 
	 * Example:
	 * <pre>
	 * {@code
	 * public Object onOption(int id) {
	 * 	if(id == 1) {
	 * 		System.println("Option 1 selected.");
	 * 	} else if(id == 2) {
	 * 		System.println("Option 2 selected.");
	 * 	} else {
	 * 		System.println("Unknown option.");
	 * 	}
	 * 	return null;
	 * }
	 * }
	 * </pre>
	 * @param id Currently selected menu action.
	 * @return user-defined object
	 */
	public abstract Object onOption(int id);
	
	public abstract void onExit();
}
