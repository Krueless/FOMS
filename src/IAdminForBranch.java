import java.io.Serializable;

/**
 * The {@code IAdminForBranch} interface defines administrative operations for managing the opening and closing of branches within the FOMS.
 */
public interface IAdminForBranch extends Serializable {

    /**
     * Opens a new branch.
     */
    void openBranch();

    /**
     * Closes an existing branch.
     */
    void closeBranch();

}
