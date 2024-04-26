import java.io.Serializable;

/**
 * Represents a branch, storing details such as branch name,
 * location, and staff quota. This class implements the Serializable interface
 * to allow branch objects to be serialized for persistence.
 */
public class Branch implements Serializable {
    private static final long serialVersionUID = 1L;
	private int staffQuota;
	private String branchName;
	private String location;

	/**
     * Constructs a new Branch object with the specified name, location, and staff quota.
     * @param branchName The name of the branch.
     * @param location The location of the branch.
     * @param staffQuota The maximum number of staff members the branch can have.
     */
	public Branch(String branchName, String location, int staffQuota) {
		this.branchName = branchName;
		this.location = location;
		this.staffQuota = staffQuota;
	}
	
     /**
     * Retrieves the name of the branch.
     * @return The branch name.
     */
	public String getBranchName() {
		return this.branchName;
	}

	/**
     * Sets the name of the branch.
     * @param branchName The new name of the branch.
     */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

    /**
     * Retrieves the staff quota for the branch.
     * @return The staff quota.
     */
	public int getStaffQuota() {
		return this.staffQuota;
	}

	/**
     * Sets the staff quota for the branch.
     * @param staffQuota The new staff quota.
     */
	public void setStaffQuota(int staffQuota) {
		this.staffQuota = staffQuota;
	}

    /**
     * Retrieves the location of the branch.
     * @return The location.
     */
	public String getLocation() {
		return this.location;
	}

	/**
     * Sets the location of the branch.
     * @param location The new location of the branch.
     */
	public void setLocation(String location) {
		this.location = location;
	}

     /**
     * Provides a string representation of the branch, including its name and location.
     * @return A string representation of the branch.
     */
	public String toString(){
        String out;
        out = "Branch Name: " + branchName + ", Location: " + location;
        return out;
    }
	

}
