public class PasswordControl implements IPasswordControl {

	private IDataManagerWithCount accountDB;

	/**
	 * 
	 * @param accountDB
	 */
	public PasswordControl(IDataManagerWithCount accountDB) {
		this.accountDB = accountDB;
	}

	/**
	 * 
	 * @param staffID
	 * @param inputPassword
	 */
	public Account validate(String staffID, String inputPassword) {
		Account acc = accountDB.find(staffID);
		if (acc != null && acc.validatePassword(inputPassword)) 
			return acc;
		else 
			return null;
	}

	/**
	 * 
	 * @param account
	 * @param newPassword
	 */
	public Boolean resetPassword(Account account, String newPassword) {
		if (newPassword.length() >= 8 && newPassword != "password"){
			account.setPassword(newPassword);
			accountDB.update(account);
			return true;
		}
		else
			return false;
	}

	/**
	 * 
	 * @param account
	 */
	public Boolean checkFirstLogin(Account account) {
		return account.getPassword() == "password";
	}

}