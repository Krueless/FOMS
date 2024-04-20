
import java.util.ArrayList;
import java.io.Serializable;

public class DataManagerForBranch implements IDataManager<Branch,String> {

	private ArrayList<Branch> branchList;
	private static DataManagerForBranch instance;

	private DataManagerForBranch() {}

	public static DataManagerForBranch getInstance() {
		if (instance == null) {
			instance = new DataManagerForBranch();
		}
		return instance;
	}

	/**
	 * 
	 * @param branch
	 */
	public void add(Branch branch) {
		// TODO - implement DataManagerForBranch.add
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param branch
	 */
	public void delete(Branch branch) {
		// TODO - implement DataManagerForBranch.delete
		throw new UnsupportedOperationException();
	}
	/**
	 * 
	 * @param oldBranch
	 * @param newBranch
	 */
	public void update(Branch oldBranch, Branch newBranch) {
		// TODO - implement DataManagerForBranch.update
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param branchName
	 */
	public Branch find(String branchName) {
		// TODO - implement DataManagerForBranch.find
		throw new UnsupportedOperationException();
	}

	public ArrayList<Branch> getAll() {
		// TODO - implement DataManagerForBranch.getAll
		throw new UnsupportedOperationException();
	}

}





