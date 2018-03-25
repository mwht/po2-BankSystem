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
			while((line = br.readLine()) != null) {
				String wrk = line;
				// extract ID
				int cur_delim = wrk.indexOf(",");
				System.out.println("!!! LINE = " + line);
				System.out.println("cur_delim = " + cur_delim);
				System.out.println("substr() = " + line.substring(cur_delim+1));
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
			//out.println(clients.size());
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
