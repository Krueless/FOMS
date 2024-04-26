import java.util.ArrayList;

/**
 * Provides implementation for displaying accounts with various filters such as by branch, role, gender, and age.
 * This class is designed to handle the display operations for lists of accounts, allowing for different subsets
 * of accounts to be presented according to different criteria.
 */
public class DisplayFilteredForAccount implements IDisplayFilteredForAccount {

	/**
     * Displays all elements within a list.
     * If the list contains elements, each element's {@code toString()} method is called and the output is indexed and printed.
     *
     * @param data The list of elements to be displayed, expected to be an instance of {@code ArrayList}.
     */
	public void displayAll(Object data) {
		if (data instanceof ArrayList){
            @SuppressWarnings("unchecked")
            ArrayList<Object> array = (ArrayList<Object>) data;
            for (int i = 0; i < array.size(); i++){
                System.out.println(i+1 +") " + array.get(i).toString());
            }
            if (array.size() == 0){
                System.out.println("No records found!");
                
            }
        }
	}

	/**
     * Displays account data filtered by the specified branch name.
     * Only accounts belonging to the specified branch are displayed.
     *
     * @param data       The list of accounts implementing {@code IGetBranchName}.
     * @param branchName The name of the branch to filter accounts by.
     */
	public void displayFilteredByBranch(ArrayList<IGetBranchName> data, String branchName) {
		int j = 1;
		for (int i = 0; i < data.size(); i++){
			if (!(data.get(i) instanceof Admin)){
				if (data.get(i).getBranchName().compareTo(branchName) == 0){
					System.out.println(j +") " + data.get(i).toString());
					j++ ;
				}
			}
		}
        if (j == 1){
            System.out.println("No records found for this branch!");
        }
	}
	

	/**
     * Displays accounts filtered by the specified role.
     * Each account with a role matching the specified role is displayed.
     *
     * @param data The list of accounts.
     * @param role The role to filter accounts by.
     */
	public void displayFilteredByRole(ArrayList<Account> data, String role) {
		int j = 1 ;
        for (Account account: data){
            if (account.getRole().compareTo(role) == 0){
                System.out.println(j + ") " + account.toString());
                j++ ;
            }
        }
        if (j == 1){
            System.out.println("No account found for this role!");
        }
	}

	/**
     * Displays accounts filtered by the specified gender.
     * Each account with a gender matching the specified gender is displayed.
     *
     * @param data   The list of accounts.
     * @param gender The gender to filter accounts by.
     */
	public void displayFilteredByGender(ArrayList<Account> data, String gender) {
		int j = 1 ;
        for (Account account: data){
            if (account.getGender().compareTo(gender) == 0){
                System.out.println(j + ") " + account.toString());
                j++ ;
            }
        }
        if (j == 1){
            System.out.println("No account found for this gender!");
        }
	}

	/**
     * Displays accounts filtered by the specified age.
     * Each account with an age matching the specified age is displayed.
     *
     * @param data The list of accounts.
     * @param age  The age to filter accounts by.
     */
	public void displayFilteredByAge(ArrayList<Account> data, int age) {
		int j = 1 ;
        for (Account account: data){
            if (account.getAge() == age){
                System.out.println(j + ") " + account.toString());
                j++ ;
            }
        }
        if (j == 1){
            System.out.println("No account found for this age!");
        }
	}
}