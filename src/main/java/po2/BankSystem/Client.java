package po2.BankSystem;

import java.util.regex.Pattern;

/**
 * Client is class which represents a bank client and contains methods
 * for handling bank account balance.
 * @author Sebastian Madejski
 *
 */
public class Client {
	private int id;
	private String name;
	private String surname;
	private String pesel;
	private String address;
	private double balance;
	/**
	 * ClientCriteria is enumeration which specifies the criteria which client is
	 * searched by for.
	 * @author Sebastian Madejski
	 *
	 */
	public enum ClientCriteria {
		/**
		 * Client is searched by ID
		 */
		ID,
		/**
		 * Client is searched by name
		 */
		NAME,
		/**
		 * Client is searched by surname
		 */
		SURNAME,
		/**
		 * Client is searched by PESEL
		 */
		PESEL,
		/**
		 * Client is searched by address
		 */
		ADDRESS,
		/**
		 * Client is searched by balance
		 */
		BALANCE
	};
	/**
	 * Client default constructor, initializes all client's values.
	 */
	public Client() {
		id = 0;
		name = "";
		surname = "";
		pesel = "";
		address = "";
		balance = 0;
	}
	/**
	 * Client constructor which initializes all values by given parameters.
	 * @param initialId client's initial ID
	 * @param initialName client's initial name
	 * @param initialSurname client's initial surname
	 * @param initialPesel client's initial PESEL
	 * @param initialAddress client's initial address
	 * @param initialBalance client's initial balance
	 */
	public Client(int initialId, String initialName, String initialSurname, String initialPesel, String initialAddress, double initialBalance) {
		setId(initialId);
		setName(initialName);
		setSurname(initialSurname);
		setPesel(initialPesel);
		setAddress(initialAddress);
		try {
			setBalance(initialBalance);
		} catch (InvalidBalanceException e) {
			balance = 0;
		}
	}
	
	/**
	 * Get client ID.
	 * @return client's ID
	 */
	public int getId() {
		return id;
	}
	 
	/**
	 * Set new client ID.
	 * @param newId client's ID
	 */
	public void setId(int newId) {
		id = newId;
	}
	
	/**
	 * Get client's name.
	 * @return client's name
	 */
	public String getName() {
		return name;
	}
	 
	/**
	 * Set new client's name.
	 * @param newName new client's name
	 */
	public void setName(String newName) {
		name = newName;
		if(Pattern.matches("[A-Z][a-z]+", newName)) {
			name = newName;
		} else {
			throw new IllegalArgumentException("Invalid name.");
		}
	}
	
	/**
	 * Get client's surname.
	 * @return client's surname
	 */
	public String getSurname() {
		return surname;
	}
	 
	/**
	 * Set new client's surname.
	 * @param newSurname new client's surname
	 */
	public void setSurname(String newSurname) {
		if(Pattern.matches("[A-Z][a-z]+", newSurname)) {
			surname = newSurname;
		} else {
			throw new IllegalArgumentException("Invalid surname.");
		}
	}

	/**
	 * Get client's PESEL.
	 * @return client's PESEL
	 */
	public String getPesel() {
		return pesel;
	}
	 
	/**
	 * Set new client's PESEL.
	 * @param newPesel new client's PESEL
	 */
	public void setPesel(String newPesel) {
		if(Pattern.matches("\\d{11}", newPesel)) {
			pesel = newPesel;
		} else {
			throw new IllegalArgumentException("Invalid PESEL.");
		}
	}
	
	/**
	 * Get client's address.
	 * @return client's address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Set new client's address.
	 * @param newAddress new client's address
	 */ 
	public void setAddress(String newAddress) {
		address = newAddress;
	}
	
	/**
	 * Get client's balance.
	 * @return client's balance
	 */
	public double getBalance() {
		return balance;
	}
	 
	/**
	 * Set new client's balance.
	 * @param newBalance new client's balance
	 * @throws InvalidBalanceException if negative balance is set
	 */
	public void setBalance(double newBalance) throws InvalidBalanceException {
		if (newBalance < 0.0) {
			throw new InvalidBalanceException("Negative balance given.");
		}
		balance = newBalance;
	}
	
	/**
	 * Transfer money to client's account.
	 * @param bal Money amount to be transferred
	 * @throws InvalidBalanceException if money amount to be transfered is zero or negative.
	 */
	public void addBalance(double bal) throws InvalidBalanceException {
		if (bal <= 0.0) {
			throw new InvalidBalanceException("Negative or zero balance given.");
		}
		balance += bal;
	}
	
	/**
	 * Cashout money from client's account.
	 * 
	 * If client attempts to cash out more money than he has on the account, a {@link InvalidBalanceException}
	 * @param bal Money amount to be cashed out
	 * @throws InvalidBalanceException if money amount to be transfered is zero or negative.
	 */
	public void subBalance(double bal) throws InvalidBalanceException {
		if(bal <= 0.0) {
			throw new InvalidBalanceException("Negative or zero balance given.");
		}
		if(balance-bal < 0.0) {
			throw new InvalidBalanceException("Can't set balance less than 0.");
		}
		balance -= bal;
	}
	
	/**
	 * Get the info about the client and return it in table-ready form
	 * 
	 * @return tab-formatted client info
	 */
	public String getTabulatedInfo() {
		return getId() + "\t|\t" + getName() + "\t|\t" + getSurname() + "\t|\t" + getPesel() + "\t|\t" + getAddress() + "\t|\t" + getBalance();
	}
	
	public String toString() {
		String temp = "";
		temp += "Client info:\n";
		temp += "------------\n";
		temp += "ID: " + getId() + "\n"; 
		temp += "Name: " + getName() + "\n"; 
		temp += "Surname: " + getSurname() + "\n"; 
		temp += "PESEL: " + getPesel() + "\n"; 
		temp += "Address: " + getAddress() + "\n"; 
		temp += "Balance: " + getBalance() + "\n"; 
		temp += "------------\n\n";
		return temp;
	}
}
