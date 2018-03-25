package po2.BankSystem;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
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
				String wrk = line;
				String currentToken;
				
				int id;
				String name,surname,address;
				long pesel;
				double balance;
				String[] column = new String[6];
				int cur_delim = 0;
				int next_delim;
				for(int i=0;i<6;i++) {
					next_delim = line.indexOf(",", cur_delim);
					currentToken = line.substring(cur_delim, next_delim);
					if(currentToken.charAt(currentToken.length()-1) == '\\') {
						System.out.println("HIT");
						System.out.println();
					}
					System.out.println("column #"+i+" = " + currentToken);
					cur_delim = next_delim+1;
					wrk = wrk.substring(cur_delim);
				}
				
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
