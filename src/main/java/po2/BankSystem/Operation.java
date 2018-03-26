package po2.BankSystem;

import java.io.IOException;
import java.util.Scanner;

public abstract class Operation {
	private static Scanner in;
	private String commitMessage;
	
	public abstract boolean commit();
	public abstract boolean rollback();
	
	public Operation() {
		if(in == null) in = new Scanner(System.in);
		commitMessage = "";
	}
	
	public static boolean prompt(String message) {
		String temp = "";
			while(!(temp.equals("y") || temp.equals("n"))) {
				temp = getStringFromInput(message).toLowerCase();
				if(temp.equals("y")) return true;
				else if(temp.equals("n")) return false;
			}
		return false;
	}
	
	public static int getIntFromInput(String prompt) {
		int result;
		/* just in case method is called outside the class (i. e. Operation.getIntFromInput()) */
		if(in == null) in = new Scanner(System.in);
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
	
	public static String getStringFromInput(String prompt) {
		/* just in case method is called outside the class (i. e. Operation.getIntFromInput()) */
		if(in == null) in = new Scanner(System.in);
		System.out.print(prompt);
		return in.nextLine();
	}
	
	public static long getLongFromInput(String prompt) {
		/* just in case method is called outside the class (i. e. Operation.getIntFromInput()) */
		if(in == null) in = new Scanner(System.in);
		long result;
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
	
	public static double getDoubleFromInput(String prompt) {
		/* just in case method is called outside the class (i. e. Operation.getIntFromInput()) */
		if(in == null) in = new Scanner(System.in);
		double result;
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
	
	protected String getCommitMessage() {
		return commitMessage;
	}
	
	protected void setCommitMessage(String newCommitMessage) {
		commitMessage = newCommitMessage;
	}
	
	public boolean run() {
		perform();
		String temp = "";
		if(isOperationPrivileged()) {
			while(!(temp.equals("y") || temp.equals("n"))) {
				temp = getStringFromInput(commitMessage).toLowerCase();
				if(temp.equals("y")) return commit();
				else if(temp.equals("n")) return rollback();
			}
		}
		return false;
	}
}
