/**
 * Implements the password control mechanism for user authentication and password management.
 */
public class PasswordControl implements IPasswordControl {

	private IDataManagerWithCount accountDB;

	/**
     * Constructs a PasswordControl instance with the specified account database.
     * 
     * @param accountDB The data manager for accessing account information.
     */
	public PasswordControl(IDataManagerWithCount accountDB) {
		this.accountDB = accountDB;
	}

	/**
     * Validates the provided staff ID and password combination for authentication.
     * 
     * @param staffID The staff ID to validate.
     * @param inputPassword The password to validate.
     * @return The account associated with the provided staff ID if the authentication is successful, otherwise null.
     */
	public Account validate(String staffID, String inputPassword) {
		Account acc = accountDB.find(staffID);
        acc.getAge();
		if (acc != null && acc.validatePassword(inputPassword)) 
			return acc;
		else 
			return null;
	}

	/**
     * Resets the password for the specified account if the new password meets requirements.
     * 
     * @param account The account for which the password needs to be reset.
     * @param newPassword The new password to set.
     * @return True if the password was successfully reset, false otherwise.
     */
	public Boolean resetPassword(Account account, String newPassword) {
		if (newPassword.length() >= 8 && !newPassword.equals("password")){
			account.setPassword(newPassword);
			accountDB.update(account);
			return true;
		}
		else
			return false;
	}

	/**
     * Checks if it is the first login for the specified account, which requires a password change.
     * 
     * @param account The account to check.
     * @return True if it's the first login and password change is required, otherwise false.
     */
	public Boolean checkFirstLogin(Account account) {
		return account.getPassword().equals("password");
	}

}