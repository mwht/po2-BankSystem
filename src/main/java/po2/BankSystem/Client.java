package po2.BankSystem;

public class Client {
	private int id;
	private String name;
	private String surname;
	private long pesel;
	private String address;
	private double balance;
	public enum ClientCriteria {
		ID,
		NAME,
		SURNAME,
		PESEL,
		ADDRESS,
		BALANCE
	};
	
	public Client() {
		id = 0;
		name = "";
		surname = "";
		pesel = 0;
		address = "";
		balance = 0;
	}
	
	public Client(int initialId) {
		id = initialId;
		name = "";
		surname = "";
		pesel = 0;
		address = "";
		balance = 0;
	}
	
	public int getId() {
		return id;
	}
	 
	public void setId(int newId) {
		id = newId;
	}
	
	public String getName() {
		return name;
	}
	 
	public void setName(String newName) {
		name = newName;
	}
	
	public String getSurname() {
		return surname;
	}
	 
	public void setSurname(String newSurname) {
		surname = newSurname;
	}
	
	public long getPesel() {
		return pesel;
	}
	 
	public void setPesel(long newPesel) {
		pesel = newPesel;
	}
	
	public String getAddress() {
		return address;
	}
	 
	public void setAddress(String newAddress) {
		address = newAddress;
	}
	
	public double getBalance() {
		return balance;
	}
	 
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
