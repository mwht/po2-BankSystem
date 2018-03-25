package po2.BankSystem;

public class Menu {
	
	public static String prettyHeader(String str) {
		StringBuffer buf = new StringBuffer();
		int length = str.length();
		buf.append("+");
		for(int i=0;i<length+2;i++) buf.append("-");
		buf.append("+\n");
		
		buf.append("| ");
		buf.append(str.toUpperCase());
		buf.append(" |\n");
		
		buf.append("+");
		for(int i=0;i<length+2;i++) buf.append("-");
		buf.append("+\n");
		return buf.toString();
	}
	
	public String getMenuTitle() {
		throw new UnsupportedOperationException("menu title not set");
	}
	
	public int getOptionsCount() {
		throw new UnsupportedOperationException("unknown number of options");
	}
	
	public String getOptionString(int id) {
		throw new UnsupportedOperationException("unknown strings");
	}
	
	public Object onOption(int id) {
		throw new UnsupportedOperationException("not implemented in menu");
	}
}
