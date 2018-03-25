package po2.BankSystem;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ClientStorageEngine {
	private List<Client> clients;
	private String path;
	private final String DELIMITER = ",";
	
	public ClientStorageEngine(String initialPath) {
		clients = new ArrayList<Client>();
		path = initialPath;
		load();
	}
	
	private String escape(String s) {
		return s.replace(",", "\\,");
	}
	
	private String unescape(String s) {
		return s.replace("\\,", ",");
	}
	
	public Client findClient(Object key, Client.ClientCriteria crit) {
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
		return null;
	}
	
	public void addClient(Client c) {
		clients.add(c);
	}
	
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
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
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
