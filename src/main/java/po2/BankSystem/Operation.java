package po2.BankSystem;

import java.io.IOException;

public abstract class Operation {
	public abstract boolean commit();
	public abstract boolean rollback();
	public static boolean prompt() {
		int temp = 0;
		while((temp != 'y') || (temp != 'n')) {
			System.out.print("Commit changes? [y/n] ");
			try {
				temp = System.in.read();
				if(temp == '\n') System.out.println();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(temp == 'y') return true;
			else if(temp == 'n') return false;
		}
		return false;
	}
	
	public abstract void perform();
	
	public boolean run(boolean confirm) {
		perform();
		int temp = 0;
		while((temp != 'y') || (temp != 'n')) {
			System.out.print("Commit changes? [y/n] ");
			try {
				temp = System.in.read();
				if(temp == '\n') System.out.println();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(temp == 'y') return commit();
			else if(temp == 'n') return rollback();
		}
		return false;
	}
}
