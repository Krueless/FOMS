import IPasswordControl.*;

public class PasswordControl implements IPasswordControl {

	/**
	 * 
	 * @param staffID
	 * @param inputPassword
	 */
	public Account validate(String staffID, String inputPassword) {
		// TODO - implement PasswordControl.validate
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param account
	 * @param newPassword
	 */
	public void resetPassword(Account account, String newPassword) {
		// TODO - implement PasswordControl.operation
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param account
	 */
	public void checkFirstLogin(Account account) {
		// TODO - implement PasswordControl.checkFirstLogin
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param accountDB
	 */
	public PasswordControl(IDataManager accountDB) {
		// TODO - implement PasswordControl.PasswordControl
		throw new UnsupportedOperationException();
	}

	private IDataManager accountDB;

}