package po2.BankSystem;

public class PrintClientInfoOperation extends Operation {
	
	private ClientStorageEngine cse;
	
	public PrintClientInfoOperation() {
		cse = ClientStorageEngine.getInstance();
	}
	
	@Override
	public boolean commit() {
		return true;
	}

	@Override
	public boolean rollback() {
		return true;
	}

	@Override
	public void perform() {
		String[] prompts = {"ID: ","Name: ","Surname: ","PESEL: ","Address: ","Balance: "};
		InputType[] inputTypes = {InputType.INT,InputType.STRING,InputType.STRING,InputType.LONG,InputType.STRING,InputType.DOUBLE};
		ClientCriteriaMenu ccm = new ClientCriteriaMenu();
		ccm.display();
		int selectedCriteria = ccm.getSelectedCriteria();
		Client[] clients;
		Object key = null;
		Client.ClientCriteria crit = Client.ClientCriteria.values()[selectedCriteria];
		switch(inputTypes[selectedCriteria]) {
			case INT:
				key = getIntFromInput(prompts[selectedCriteria]);
				break;
			case STRING:
				key = getStringFromInput(prompts[selectedCriteria]);
				break;
			case DOUBLE:
				key = getDoubleFromInput(prompts[selectedCriteria]);
				break;
			case LONG:
				key = getLongFromInput(prompts[selectedCriteria]);
				break;
		}
		clients = cse.findAllClientsMatchingCriteria(key, crit);
		System.out.println("ID	|	Name	|	Surname	|	PESEL	|	Address	|	Balance");
		System.out.println("---------------------------------------------------------------");
		for(int i=0;i<clients.length;i++) {
			System.out.println(clients[i].getTabulatedInfo());
		}
	}

	@Override
	public boolean isOperationPrivileged() {
		return false;
	}

}
