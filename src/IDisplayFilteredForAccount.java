import java.util.ArrayList;

/**
 * Extends {@code IDisplayFilteredByBranch} to provide additional filtering capabilities specific to accounts.
 * This interface defines methods to display account data filtered by role, gender, or age.
 */
public interface IDisplayFilteredForAccount extends IDisplayFilteredByBranch {

    /**
     * Displays a list of accounts filtered by the specified role.
     * This method should take the list of all accounts and output those that match the given role.
     *
     * @param data The list of accounts to be filtered.
     * @param role The role by which to filter the accounts.
     */
    void displayFilteredByRole(ArrayList<Account> data, String role);

    /**
     * Displays a list of accounts filtered by the specified gender.
     * This method allows for displaying accounts that match a particular gender.
     *
     * @param data The list of accounts to be filtered.
     * @param gender The gender by which to filter the accounts.
     */
    void displayFilteredByGender(ArrayList<Account> data, String gender);

    /**
     * Displays a list of accounts filtered by the specified age.
     * This method supports age-specific filtering.
     *
     * @param data The list of accounts to be filtered.
     * @param age The age by which to filter the accounts.
     */
    void displayFilteredByAge(ArrayList<Account> data, int age);

}