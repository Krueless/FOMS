import java.util.ArrayList;
import java.util.Scanner;
public class AdminForBranch implements IAdminForBranch{
	private IDataManager<Branch, String> branchDB;
	public AdminForBranch(){
		this.branchDB=DataManagerForBranch.getInstance();
	}
	
    /**
     * Allows admin to open a new branch
     */
	public void openBranch(){
		//obtain the attributes for the new branch
		Scanner sc = GlobalResource.SCANNER;
        int staffQuota = -1;
        while (staffQuota < 1 || staffQuota > 15) {
            System.out.println("Enter the staff quota (Between 1-15):");
            if (sc.hasNextInt()) {  // Check if the next token is an integer
                staffQuota = sc.nextInt();  // Read the integer
                if (staffQuota < 1 || staffQuota > 15) {
                    System.out.println("Invalid input: Quota must be between 1 and 15.");
                }
            } else {
                System.out.println("Invalid input: Please enter a number.");
                sc.nextLine();  
            }
        }
        sc.nextLine();
        System.out.println("Enter the branch name:");
        String branchName = sc.nextLine();
        while(branchDB.find(branchName)!=null){
            System.out.println("A branch with this name already exists");
            System.out.println("Enter a new branch name:");
            branchName = sc.nextLine();
        }
        System.out.println("Enter the location:");
        String location = sc.nextLine();
        //create a new branch object
        Branch branch = new Branch(branchName,location,staffQuota);
        //add the new Branch object to branchList in DataManagerForBranch
        branchDB.add(branch);
	}
	
    /**
     * Allows admin to close an existing branch
     */
	public void closeBranch(){
		Scanner sc = GlobalResource.SCANNER;
        IDisplay displayFormatter = new Display(); 
        ArrayList<Branch> branchList = branchDB.getAll();
        if (branchList.size() > 0){
            displayFormatter.displayAll(branchDB.getAll());;
            System.out.println("Enter the name of the branch to be closed (case-semsitive):");
            String branchName = sc.nextLine();
            Branch branch = branchDB.find(branchName);//find the branch
            if(branch != null){
                branchDB.delete(branch);//delete the branch
            }else{
                System.out.println("Branch not found! Returning to user page...");
            }
	    }else{
            System.out.println("No branch to be closed! Returning to user page...");
        }
    }
}
