package po2.BankSystem;

public class ClientCriteriaMenu extends Menu {
	
	private String[] criteriaStrings = {"ID","Name","Surname","PESEL","Address","Balance"};
	private int selectedCriteriaID;

	@Override
	public String getMenuTitle() {
		return "Client Criteria";
	}

	@Override
	public int getOptionsCount() {
		return 6;
	}

	@Override
	public String getOptionString(int id) {
		try {
			return criteriaStrings[id-1];
		} catch(ArrayIndexOutOfBoundsException e) {
			return "-";
		}
	}

	@Override
	public Object onOption(int id) {
		if(id > 0 && id <= getOptionsCount()) {
			selectedCriteriaID = id-1;
			exit();
		}
		return null;
	}
	
	public int getSelectedCriteria() {
		return selectedCriteriaID;
	}
	
}
