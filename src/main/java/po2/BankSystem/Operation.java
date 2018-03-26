package po2.BankSystem;

import java.io.IOException;
import java.util.Scanner;

/**
 * Operation is class which represents generic operation on system and provides basic input utilities.
 * @author Sebastian Madejski
 *
 */
public abstract class Operation {
	private static Scanner in;
	private String commitMessage;
	public enum InputType {
		STRING,
		INT,
		LONG,
		DOUBLE
	};
	
	/**
	 * Method called when the operation is confirmed.
	 * @return success or failure of commit operation
	 */
	public abstract boolean commit();
	
	/**
	 * Method called when the operation is cancelled.
	 * @return success or failure of rollback operation
	 */
	public abstract boolean rollback();
	
	/**
	 * Constructor for {@link Operation}, initializes input utils and sets empty commit message.
	 */
	public Operation() {
		if(in == null) in = new Scanner(System.in);
		commitMessage = "";
	}
	
	/**
	 * Prompts user with message and gets yes/no whether operation should be performed or not.
	 * 
	 * @param message Message prompt displayed to user asking whether to perform operation or not.
	 * @return user's answer (true/false)
	 */
	public static boolean prompt(String message) {
		String temp = "";
			while(!(temp.equals("y") || temp.equals("n"))) {
				temp = getStringFromInput(message).toLowerCase();
				if(temp.equals("y")) return true;
				else if(temp.equals("n")) return false;
			}
		return false;
	}
	
	/**
	 * Prompts user with message and gets integer value from user.
	 *
	 * User will be asked about integer until it is given.
	 * @see getStringFromInput
	 * @see getLongFromInput
	 * @see getDoubleFromInput
	 * @param prompt Message prompt displayed to user asking for integer
	 * @return integer given by user
	 */
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
	
	/**
	 * Prompts user with message and gets string from user.
	 * 
	 * @see getIntFromInput
	 * @see getLongFromInput
	 * @see getDoubleFromInput
	 * @param prompt Message prompt displayed to user asking for string
	 * @return string given by user
	 */
	public static String getStringFromInput(String prompt) {
		/* just in case method is called outside the class (i. e. Operation.getStringFromInput()) */
		if(in == null) in = new Scanner(System.in);
		System.out.print(prompt);
		return in.nextLine();
	}
	
	/**
	 * Prompts user with message and gets long value from user.
	 * 
	 * @see getIntFromInput
	 * @see getStringFromInput
	 * @see getDoubleFromInput
	 * @param prompt Message prompt displayed to user asking for long value
	 * @return long value given by user
	 */
	public static long getLongFromInput(String prompt) {
		/* just in case method is called outside the class (i. e. Operation.getLongFromInput()) */
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
	
	/**
	 * Prompts user with message and gets double-precision value from user.
	 * 
	 * @see getIntFromInput
	 * @see getStringFromInput
	 * @see getLongFromInput
	 * @param prompt Message prompt displayed to user asking for integer
	 * @return double-precision value given by user
	 */
	public static double getDoubleFromInput(String prompt) {
		/* just in case method is called outside the class (i. e. Operation.getDoubleFromInput()) */
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
	
	/**
	 * Perform actual operation.
	 * 
	 * Method should not commit data to database, since {@link #commit()} or {@link #rollback()} methods take care
	 * of these operations.
	 * @see run
	 * @see commit
	 * @see rollback
	 */
	public abstract void perform();
	
	/**
	 * Returns true if operation is privileged or not.
	 * 
	 * Difference between privileged operation and unprivileged operation is that privileged operation asks for confirmation and unprivileged operation
	 * does not, for instance adding client to database is privileged operation, displaying client information is not privileged operation.
	 * @return true if operation if privileged, false if operation is unprivileged
	 */
	public abstract boolean isOperationPrivileged();
	
	/**
	 * Gets prompt (or "commit") message set by {@link #setCommitMessage(String)}.
	 * @see setCommitMessage
	 * @return message
	 */
	protected String getCommitMessage() {
		return commitMessage;
	}
	
	/**
	 * Sets prompt (or "commit") message being displayed when operation is privileged and asks for confirmation.
	 * @see setCommitMessage
	 * @see isOperationPrivileged
	 * @param newCommitMessage new commit message
	 */
	protected void setCommitMessage(String newCommitMessage) {
		commitMessage = newCommitMessage;
	}
	
	/**
	 * Performs operation, and if operation is privileged asks for confirmation commits or rolls back the operation.
	 * @return Status of commit/rollback of operation.
	 */
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
