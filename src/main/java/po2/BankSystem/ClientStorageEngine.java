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
	
	public boolean load() {
		try {
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			ois.close();
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
			out.close();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
}
