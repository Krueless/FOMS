import java.io.Serializable;

public class Branch implements Serializable {
    private static final long serialVersionUID = 1L;
	private int staffQuota;
	private String branchName;
	private String location;

		/**
	 * 
	 * @param branchName
	 * @param location
	 * @param staffQuota
	 */
	public Branch(String branchName, String location, int staffQuota) {
		this.branchName = branchName;
		this.location = location;
		this.staffQuota = staffQuota;
	}
	
	public String getBranchName() {
		return this.branchName;
	}
	/**
	 * 
	 * @param branchName
	 */

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public int getStaffQuota() {
		return this.staffQuota;
	}

	/**
	 * 
	 * @param staffQuota
	 */
	public void setStaffQuota(int staffQuota) {
		this.staffQuota = staffQuota;
	}

	public String getLocation() {
		return this.location;
	}

	/**
	 * 
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	public String toString(){
        String out;
        out = "Branch Name: " + branchName + ", Location: " + location;
        return out;
    }
	

}
