package po2.BankSystem;

import java.io.IOException;
import java.util.Scanner;

public abstract class Operation {
	public abstract boolean commit();
	public abstract boolean rollback();
	public static boolean prompt() {
		int temp = 0;
		while((temp != 'y') || (temp != 'n')) {
			System.out.print("Commit changes? [y/n] ");
			try {
				temp = System.in.read();
				if(temp == '\n') System.out.println();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(temp == 'y') return true;
			else if(temp == 'n') return false;
		}
		return false;
	}
	
	public int getIntFromInput(String prompt) {
		int result;
		Scanner in = new Scanner(System.in);
		while(true) {
			System.out.print(prompt);
			String tmp = in.nextLine();
			try {
				result = Integer.parseInt(tmp);
				return result;
			} catch(NumberFormatException nfe) {
				System.out.println("NumberFormatException caught (most likely non-number was given as an input): "+nfe.getLocalizedMessage());
			}
		}
	}
	
	public String getStringFromInput(String prompt) {
		int result;
		Scanner in = new Scanner(System.in);
		System.out.print(prompt);
		return in.nextLine();
	}
	
	public long getLongFromInput(String prompt) {
		long result;
		Scanner in = new Scanner(System.in);
		while(true) {
			System.out.print(prompt);
			String tmp = in.nextLine();
			try {
				result = Long.parseLong(tmp);
				return result;
			} catch(NumberFormatException nfe) {
				System.out.println("NumberFormatException caught (most likely non-number was given as an input): "+nfe.getLocalizedMessage());
			}
		}
	}
	
	public double getDoubleFromInput(String prompt) {
		double result;
		Scanner in = new Scanner(System.in);
		while(true) {
			System.out.print(prompt);
			String tmp = in.nextLine();
			try {
				result = Double.parseDouble(tmp);
				return result;
			} catch(NumberFormatException nfe) {
				System.out.println("NumberFormatException caught (most likely non-number was given as an input): "+nfe.getLocalizedMessage());
			}
		}
	}
	
	public abstract void perform();
	
	public abstract boolean isOperationPrivileged();
	
	public boolean run() {
		perform();
		int temp = 0;
		if(isOperationPrivileged()) {
			while((temp != 'y') || (temp != 'n')) {
				System.out.print("Commit changes? [y/n] ");
				try {
					temp = System.in.read();
					if(temp == '\n') System.out.println();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				if(temp == 'y') return commit();
				else if(temp == 'n') return rollback();
			}
		}
		return false;
	}
}
