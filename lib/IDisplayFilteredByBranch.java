public interface IDisplayFilteredByBranch extends IDisplay {

	/**
	 * 
	 * @param data
	 * @param branchName
	 */
	void displayFilteredByBranch(IGetBranchName data, String branchName);

}