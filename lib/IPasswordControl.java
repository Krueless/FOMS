public interface IPasswordControl {

	/**
	 * 
	 * @param staffID
	 * @param inputPassword
	 */
	Account validate(String staffID, String inputPassword);

	/**
	 * 
	 * @param account
	 * @param newPassword
	 */
	void resetPassword(Account account, String newPassword);

	IDataManager getAccountDB();

	/**
	 * 
	 * @param account
	 */
	void checkFirstLogin(Account account);

}