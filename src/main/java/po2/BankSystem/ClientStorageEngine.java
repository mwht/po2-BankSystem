package po2.BankSystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * ClientStorageEngine is class responsible for retrieving and persisting
 * information about the clients.
 * 
 * Storage format is CSV-like file with name specified by user, where comma is
 * escaped by backslash, so it might be compatible with spreadsheet
 * applications. Class assumes it's operating as single instance across
 * entire application, meaning that another classes needing access to
 * storage should use {@link #getInstance()}.
 * 
 * @author Sebastian Madejski
 *
 */
public class ClientStorageEngine {
	private List<Client> clients;
	private String path;
	private final String DELIMITER = ",";
	private static ClientStorageEngine instance;
	
	/**
	 * Constructor for class ClientStorageEngine. Initializes actual memory
	 * storage and retrieves information from file given by user.
	 * 
	 * @param initialPath Path to file loaded by the engine.
	 */
	public ClientStorageEngine(String initialPath) {
		clients = new ArrayList<Client>();
		path = initialPath;
		load();
	}
	
	/**
	 * Get instance of {@link ClientStorageEngine} and if it does not exist,
	 * create one.
	 * @return Instance of {@link ClientStorageEngine}.
	 */
	public static ClientStorageEngine getInstance() {
		if(instance == null) instance = new ClientStorageEngine("clients.csv");
		return instance;
	}
	
	private String escape(String s) {
		return s.replace(",", "\\,");
	}
	
	private String unescape(String s) {
		return s.replace("\\,", ",");
	}
	
	/**
	 * Get the total client count stored in database.
	 * @return Clients stored in database.
	 */
	public int getClientCount() { return clients.size(); } 
	
	/**
	 * Find the client in database by key in criteria given by user.
	 * 
	 * Method returns the first found record in case when more than one
	 * record matches the key and criteria.
	 * 
	 * @param key Key which client is searched by.
	 * @param crit Criteria which client is searched by.
	 * @return {@link Client} object found by given criteria.
	 * @throws ClientNotFoundException if client is not found in database.
	 */
	public Client findClient(Object key, Client.ClientCriteria crit) throws ClientNotFoundException {
		for(int i=0;i<clients.size();i++) {
			Client c = clients.get(i);
			switch(crit) {
				case ADDRESS:
					if(c.getAddress().equals(key)) return c;
					break;
				case BALANCE:
					if(c.getBalance() == (double) key) return c;
					break;
				case ID:
					if(c.getId() == (int) key) return c;
					break;
				case NAME:
					if(c.getName().equals(key)) return c;
					break;
				case PESEL:
					if(c.getPesel() == (long) key) return c;
					break;
				case SURNAME:
					if(c.getSurname().equals(key)) return c;
					break;
			}
		}
		throw new ClientNotFoundException("client not found by "+crit.toString()+" ("+key.toString()+")");
	}
	
	/**
	 * Find all clients in database by key in criteria given by user.
	 * 
	 * @param key Key which clients are searched by.
	 * @param crit Criteria which clients are searched by.
	 * @return Array of {@link Client} objects matching the criteria.
	 */
	public Client[] findAllClientsMatchingCriteria(Object key, Client.ClientCriteria crit) {
		ArrayList<Client> match = new ArrayList<>();
		Client[] result;
		for(int i=0;i<clients.size();i++) {
			Client c = clients.get(i);
			switch(crit) {
				case ADDRESS:
					if(c.getAddress().equals(key)) match.add(c);
					break;
				case BALANCE:
					if(c.getBalance() == (double) key) match.add(c);
					break;
				case ID:
					if(c.getId() == (int) key) match.add(c);
					break;
				case NAME:
					if(c.getName().equals(key)) match.add(c);
					break;
				case PESEL:
					if(c.getPesel() == (long) key) match.add(c);
					break;
				case SURNAME:
					if(c.getSurname().equals(key)) match.add(c);
					break;
			}
		}
		
		result = new Client[match.size()];
		for(int i=0;i<match.size();i++) {
			result[i] = match.get(i);
		}
		return result;
	}
	
	/**
	 * Get all clients stored in database. 
	 * 
	 * @return Array of all {@link Client}s stored in database.
	 */
	public Client[] getAllClients() {
		Client[] result = new Client[clients.size()];
		for(int i=0;i<clients.size();i++) {
			result[i] = clients.get(i);
		}
		return result;
	}
	
	/**
	 * Add new client to database.
	 * 
	 * Client should have it's own unique ID.
	 * @param c {@link Client} object to be added to database.
	 */
	public void addClient(Client c) {
		clients.add(c);
	}
	
	/**
	 * 
	 * @param c {@link Client} object to be removed from database.
	 * @return status (true/false) whether client was removed from database.
	 */
	public boolean deleteClient(Client c) {
		for(int i=0;i<clients.size();i++) {
			if(clients.get(i) == c) {
				clients.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Load the database from file given in path.
	 * 
	 * File format is CSV-like, columns separated by commas, escaped by backslash
	 * and new rows delimited by new line.
	 * Method will print out error messages directly to "out" stream if
	 * an exception occurs.
	 * 
	 * @return status (true/false) of loading state
	 */
	public boolean load() {
		try {
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			String line;
			Client c;
			while((line = br.readLine()) != null) {
				String currentToken;
				
				int id;
				String name,surname,address;
				long pesel;
				double balance;
				String[] column = new String[6];
				int cur_delim = 0;
				int next_delim = 0;
				int temp_delim = 0;
				for(int i=0;i<6;i++) {
					if(i != 5) next_delim = line.indexOf(",", cur_delim);
					else next_delim = line.length();
					currentToken = line.substring(cur_delim, next_delim);
					while(currentToken.charAt(currentToken.length()-1) == '\\') {
						if(line.charAt(next_delim) == ',') {
							temp_delim = next_delim+1;
							next_delim = line.indexOf(",", temp_delim);
							currentToken = line.substring(cur_delim, next_delim);
						} else {
							break;
						}
					}
					column[i] = currentToken;
					cur_delim = next_delim+1;
				}
				id = Integer.parseInt(column[0]);
				name = unescape(column[1]);
				surname = unescape(column[2]);
				pesel = Long.parseLong(column[3]);
				address = unescape(column[4]);
				balance = Double.parseDouble(column[5]);
				c = new Client(id,name,surname,pesel,address,balance);
				addClient(c);
			}
			br.close();
			return true;
		} catch(FileNotFoundException fnf) {
			System.out.println("File \""+path+"\" not found - will be created at runtime");
			return false;			
		} catch(Exception e) {
			System.out.println(e.getClass().getName() + " caught: " + e.getLocalizedMessage());
			return false;
		}
		
	}
	
	/**
	 * Commit current state of database to file.
	 * 
	 * Like {@link #load()} method, it uses the same format, delimiting and
	 * escaping and separation columns.
	 * 
	 * @return status(true/false) whether operation was successful.
	 */
	public boolean commit() {
		try {
			FileOutputStream fos = new FileOutputStream(path);
			PrintStream out = new PrintStream(fos);
			for(int i=0;i<clients.size();i++) {
				Client c = clients.get(i);
				out.println(c.getId()+DELIMITER+escape(c.getName())+DELIMITER+escape(c.getSurname())+DELIMITER+c.getPesel()+DELIMITER+escape(c.getAddress())+DELIMITER+c.getBalance());
			}
			out.close();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
}
