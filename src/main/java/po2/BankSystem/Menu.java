package po2.BankSystem;

import java.util.Scanner;

public class Menu {
	
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
	
	public void display() {
		boolean optionSelected;
		boolean menuExit = false;
		int optionID;
		Scanner in = new Scanner(System.in);
		while(!menuExit) {
			optionSelected = false;
			System.out.println(prettyHeader(getMenuTitle()));
			for(int i=0;i<getOptionsCount();i++) {
				System.out.println((i+1)+") "+getOptionString(i+1));
			}
			System.out.println("other number) "+getOptionString(0));
			System.out.println();
			while(!optionSelected) {
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
	
	public String getMenuTitle() {
		throw new UnsupportedOperationException("menu title not set");
	}
	
	public int getOptionsCount() {
		throw new UnsupportedOperationException("unknown number of options");
	}
	
	public String getOptionString(int id) {
		throw new UnsupportedOperationException("unknown strings");
	}
	
	public Object onOption(int id) {
		throw new UnsupportedOperationException("option not implemented in menu");
	}
}
