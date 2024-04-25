import java.util.ArrayList;

public interface IDisplayFilteredForAccount extends IDisplayFilteredByBranch{

	/**
	 * 
	 * @param data
	 * @param role
	 */
	void displayFilteredByRole(ArrayList<Account> data, String role);

	/**
	 * 
	 * @param data
	 * @param gender
	 */
	void displayFilteredByGender(ArrayList<Account> data, String gender);

	/**
	 * 
	 * @param data
	 * @param age
	 */
	void displayFilteredByAge(ArrayList<Account> data, int age);

}