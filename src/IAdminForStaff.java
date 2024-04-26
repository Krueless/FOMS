import java.io.Serializable;

/**
 * The {@code IAdminForStaff} interface defines the administrative methods needed for managing staff within an organization.
 * 
 */
public interface IAdminForStaff extends Serializable {

    /**
     * Edits the details of an existing staff member.
     */
    void editStaff();

    /**
     * Removes a staff member from the organization.
     */
    void removeStaff();

    /**
     * Adds a new staff member to the organization.
     */
    void addStaff();

    /**
     * Displays information about staff members.
     */
    void displayStaff();

    /**
     * Assigns a staff member to a managerial position.
     */
    void assignManager();

    /**
     * Promotes a staff member within the organization.
     */
    void promoteStaff();

    /**
     * Transfers a staff member to a different department or location.
     */
    void transferStaff();

}
