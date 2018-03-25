package po2.BankSystem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ClientStorageEngine {
	private List<Client> clients;
	private String path;
	
	public ClientStorageEngine(String initialPath) {
		clients = new ArrayList<Client>();
		path = initialPath;
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
			FileInputStream fis = new FileInputStream(path);
			
			fis.close();
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
			out.println(clients.size());
			for(int i=0;i<clients.size();i++) {
				Client c = clients.get(i);
				out.println(c.getId()+","+escape(c.getName())+","+escape(c.getSurname())+","+c.getPesel()+","+escape(c.getAddress())+","+c.getBalance());
			}
			out.close();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
}
