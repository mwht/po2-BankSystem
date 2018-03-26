package po2.BankSystem;

import java.util.Scanner;

public abstract class Menu {
	
	private boolean menuRunning = true;
	
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
	
	public void exit() {
		menuRunning = false;
	}
	
	public void display() {
		boolean optionSelected;
		int optionID;
		Scanner in = new Scanner(System.in);
		while(menuRunning) {
			optionSelected = false;
			System.out.println(prettyHeader(getMenuTitle()));
			for(int i=0;i<getOptionsCount();i++) {
				System.out.println((i+1)+") "+getOptionString(i+1));
			}
			System.out.println("other number) "+getOptionString(0));
			System.out.println();
			while(!optionSelected && menuRunning) {
				System.out.print("Select an option: ");
				String temp = in.nextLine();
				try {
					optionID = Integer.parseInt(temp);
					if(optionID >= 0) {
						onOption(optionID);
						optionSelected = true;
					}
				} catch(NumberFormatException nfe) {
					System.out.println("NumberFormatException caught (most likely non-number was given as an input): "+nfe.getLocalizedMessage());
				}
			}
		}
	}
	
	public abstract String getMenuTitle();
	
	public abstract int getOptionsCount();
	
	public abstract String getOptionString(int id);
	public abstract Object onOption(int id);
}
