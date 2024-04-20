public interface IDataManagerWithCount extends IDataManager<Account,String> {

	/**
	 * 
	 * @param branchName
	 */
	int countStaffInBranch(String branchName);

	/**
	 * 
	 * @param branchName
	 */
	int countManagerInBranch(String branchName);

}