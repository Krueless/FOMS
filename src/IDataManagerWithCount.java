/**
 * Extends the {@code IDataManager} interface to include methods that specifically count types of staff members within a branch.
 */
public interface IDataManagerWithCount extends IDataManager<Account, String> {

    /**
     * Counts the total number of staff members within a specified branch.
     *
     * @param branchName The name of the branch for which the staff count is requested.
     * @return The total number of staff members in the specified branch.
     */
    int countStaffInBranch(String branchName);

    /**
     * Counts the number of managers within a specified branch.
     *
     * @param branchName The name of the branch for which the manager count is requested.
     * @return The number of managers in the specified branch.
     */
    int countManagerInBranch(String branchName);

}