import java.util.ArrayList;

public class DisplayFilteredForAccount implements IDisplayFilteredForAccount {

	/**
	 * 
	 * @param data
	 */
	public void displayAll(Object data) {
		if (data instanceof ArrayList){
            @SuppressWarnings("unchecked")
            ArrayList<Object> array = (ArrayList<Object>) data;
            for (int i = 0; i < array.size(); i++){
                System.out.println(i+1 +") " + array.get(i).toString());
            }
        }
	}

	/**
	 * 
	 * @param data
	 * @param branchName
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
	}
	

	/**
	 * 
	 * @param data
	 * @param role
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
	 * 
	 * @param data
	 * @param gender
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
	 * 
	 * @param data
	 * @param age
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