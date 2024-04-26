import java.util.ArrayList;

/**
 * Extends the {@code IDisplay} interface to provide functionality for displaying data filtered by branch name.
 */
public interface IDisplayFilteredByBranch extends IDisplay {

    /**
     * Displays a list of items, filtered to include only those associated with the specified branch name.
     * This method is intended for use in displaying data according to branch.
     *
     * @param data The list of items implementing the {@code IGetBranchName} interface to be filtered.
     * @param branchName The name of the branch by which to filter the items.
     */
    void displayFilteredByBranch(ArrayList<IGetBranchName> data, String branchName);

}