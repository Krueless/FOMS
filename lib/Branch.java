import java.io.Serializable;

public class Branch implements Serializable{

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
		// TODO - implement Branch.Branch
		throw new UnsupportedOperationException();
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

}