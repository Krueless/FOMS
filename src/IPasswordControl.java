/**
 * The {@code IPasswordControl} interface defines the standard operations for handling the password.
 * It is designed to carry out different methods to handle the password.
 * Extends the {@code Serializable} interface to allow objects to be serialized for storage or transmission.
 */
public interface IPasswordControl {

    /**
     * Validates a given password against the stored credentials for a specified staff ID.
     * This method checks if the input password corresponds to the existing password for the staff member identified by {@code staffID}.
     *
     * @param staffID The identifier for the staff member whose password is being validated.
     * @param inputPassword The password that needs to be validated against the stored password.
     * @return An {@code Account} object representing the staff member if the password is correct.
     */
    Account validate(String staffID, String inputPassword);

    /**
     * Resets the password for the given account to a new specified password.
     * This method should ensure the new password meets the required security standards before updating the account's password.
     *
     * @param account The {@code Account} object for which the password is to be reset.
     * @param newPassword The new password to be set for the account, which must comply with the system's password policies.
     * @return {@code Boolean} true if the password was successfully reset, false otherwise.
     */
    Boolean resetPassword(Account account, String newPassword);

    /**
     * Checks if the specified account is logging in for the first time.
     * This method returns true if the user is required to change their password upon first login.
     *
     * @param account The {@code Account} object to be checked for first login status.
     * @return {@code Boolean} true if it is the first login for the account, false otherwise.
     */
    Boolean checkFirstLogin(Account account);

}