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
	Boolean resetPassword(Account account, String newPassword);

	/**
	 * 
	 * @param account
	 */
	Boolean checkFirstLogin(Account account);

}