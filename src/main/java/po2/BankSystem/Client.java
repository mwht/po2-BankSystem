package po2.BankSystem;

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
	private long pesel;
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
		 * Client ID
		 */
		ID,
		/**
		 * Client name
		 */
		NAME,
		/**
		 * Client surname
		 */
		SURNAME,
		/**
		 * Client PESEL
		 */
		PESEL,
		/**
		 * Client address
		 */
		ADDRESS,
		/**
		 * Client balance
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
		pesel = 0;
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
	public Client(int initialId, String initialName, String initialSurname, long initialPesel, String initialAddress, double initialBalance) {
		id = initialId;
		name = initialName;
		surname = initialSurname;
		pesel = initialPesel;
		address = initialAddress;
		balance = initialBalance;
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
	}
	
	
	public String getSurname() {
		return surname;
	}
	 
	/**
	 * Set new client's surname.
	 * @param newSurname new client's surname
	 */
	public void setSurname(String newSurname) {
		surname = newSurname;
	}
	
	public long getPesel() {
		return pesel;
	}
	 
	/**
	 * Set new client's PESEL.
	 * @param newPesel new client's PESEL
	 */
	public void setPesel(long newPesel) {
		pesel = newPesel;
	}
	
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
	
	public double getBalance() {
		return balance;
	}
	 
	/**
	 * Set new client's balance.
	 * @param newBalance new client's balance
	 */
	public void setBalance(double newBalance) throws InvalidBalanceException {
		if (newBalance < 0.0) {
			throw new InvalidBalanceException("Negative balance given.");
		}
		balance = newBalance;
	}
	
	public void addBalance(double bal) throws InvalidBalanceException {
		if (bal <= 0.0) {
			throw new InvalidBalanceException("Negative or zero balance given.");
		}
		balance += bal;
	}
	
	public void subBalance(double bal) throws InvalidBalanceException {
		if(bal <= 0.0) {
			throw new InvalidBalanceException("Negative or zero balance given.");
		}
		if(balance-bal < 0.0) {
			throw new InvalidBalanceException("Can't set balance less than 0.");
		}
		balance -= bal;
	}
}
