package po2.BankSystem;

public class DeleteClientOperation extends Operation {

	@Override
	public boolean commit() {
		return false;
	}

	@Override
	public boolean rollback() {
		return false;
	}

	@Override
	public void perform() {
		
	}

	@Override
	public boolean isOperationPrivileged() {
		return false;
	}

}
