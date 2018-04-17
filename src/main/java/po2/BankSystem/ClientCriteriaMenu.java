package po2.BankSystem;

/**
 * ClientCriteriaMenu is {@link Menu} that gets criteria for searching the client from user.
 * 
 * @author Sebastian Madejski
 */
public class ClientCriteriaMenu extends Menu {
	
	private String[] criteriaStrings = {"ID","Name","Surname","PESEL","Address","Balance"};
	private int selectedCriteriaID;
	
	public ClientCriteriaMenu() {
		selectedCriteriaID = -1;
	}
	
	/**
	 * Get the menu title.
	 * 
	 * @return "Client Criteria" string.
	 */
	@Override
	public String getMenuTitle() {
		return "Client Criteria";
	}

	/**
	 * Get options count.
	 * 
	 * @return Criteria count.
	 */
	@Override
	public int getOptionsCount() {
		return 6;
	}
	
	/**
	 * Get the option string.
	 * 
	 * @return Currently queried option string and if string is not found returns "-";
	 */
	@Override
	public String getOptionString(int id) {
		try {
			return criteriaStrings[id-1];
		} catch(ArrayIndexOutOfBoundsException e) {
			return "-";
		}
	}

	/**
	 * Sets selected criteria and exits menu.
	 * 
	 * @return Nothing. (null)
	 */
	@Override
	public Object onOption(int id) {
		if(id > 0 && id <= getOptionsCount()) {
			selectedCriteriaID = id-1;
			exit();
		}
		return null;
	}
	
	public void onExit() {}
	
	/**
	 * Gets the selected criteria.
	 * 
	 * @return Selected criteria.
	 */
	public int getSelectedCriteria() {
		return selectedCriteriaID;
	}
	
}
