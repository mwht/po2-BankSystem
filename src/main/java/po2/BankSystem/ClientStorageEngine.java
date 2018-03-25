package po2.BankSystem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ClientStorageEngine {
	private List<Object> objs;
	private String path;
	
	public ClientStorageEngine(String initialPath) {
		objs = new ArrayList<Object>();
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
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			for(int i=0;i<objs.size();i++) {
				oos.writeObject(objs.get(i));
			}
			
			oos.flush();
			oos.close();
			fos.close();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
}
