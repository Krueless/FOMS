import java.util.Scanner;
public class AdminForStaff implements IAdminForStaff{
    private IDataManager accountDB;
    private IDataManager branchDB;
    private IDisplay displayFormatter;
    private QuotaChecker quotaChecker;
    public AdminForStaff(IDataManager accountDB, IDataManager branchDB, IDisplay displayFormatter){
        this.accountDB=accountDB;
        this.branchDB=branchDB;
        if(!(displayFormatter instanceof DisplayWithFilterAndSort)){
            System.out.println("Error: Incorrect display formatter");
        }
        this.displayFormatter=displayFormatter;
	this.quotaChecker=new QuotaChecker();
    }
    public void editStaff(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the staffID");
        String staffID=sc.nextLine();
        accountDB.
    }
    public void removeStaff(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the staffID");
        String staffID=sc.nextLine();
        Account staffAccount=accountDB.find(staffID);
        accountDB.delete(staffAccount);
    }
    public void addStaff(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter staffID");
        String staffID=sc.nextLine();
        System.out.println("Enter password");
        String password=sc.nextLine();
        System.out.println("Enter role");
        String role=sc.nextLine();
        System.out.println("Enter gender");
        String gender=sc.nextLine();
        System.out.println("Enter age");
        int age=sc.nextInt();
        System.out.println("Enter branch");
        String branchName=sc.nextLine();
        DataManagerForOrder orderDB=//get DataManagerForOrder object
        Staff staffAccount=new Staff(staffID,password,role,gender,age,branchName,orderDB);
        accountDB.add(staffAccount);
    }
    public void displayStaff(){
        Scanner sc=new Scanner(System.in);
        ArrayList<Staff> staffList=accountDB.getAll();
        System.out.println("Choose filter by")
        System.out.println("1. branch");
        System.out.println("2. role");
        System.out.println("3. gender");
        System.out.println("4. age");
        int choice=sc.nextInt();
        switch(choice){
            case 1:
            displayFormatter.displaySortedByBranch(staffList);
            break;
            case 2:
            displayFormatter.displaySortedByRole(staffList);
            break;
            case 3:
            displayFormatter.displaySortedByGender(staffList);
            break;
            case 4:
            displayFormatter.displaySortedByAge(staffList);
            break;
        }
    }
    public void assignManager(){
        //checkquota
	//TODO
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter the branch to assign Manager");
	String branchName=sc.nextLine();
	if(quotaChecker.checkManagerQuota(branchName)){
		//add manager
		System.out.println("
	}else{
		System.out.printf("Manager quota is reached. Cannot add manager");
	}
    }
    public void promoteStaff(){
        Scanner sc=new Scanner(System.in);
        //find the staff to be promoted
        System.out.println("Enter staffID");
        String staffID=sc.nextLine();
        Account staffAccount=accountDB.find(staffID);
        //create a new Manager object and copy all attributes of staff
        Manager(String staffID,String password,String role,String gender,int age,String branchName,IDataManager orderDB,IDataManager foodItemDB,IDataManager accountDB);
        //delete the Staff object to the accountList in DataManagerForAccount
        //add the Manager object to the accountList in Data ManagerForAccount
    }
    public void transferStaff(){
        Scanner sc=new Scanner(System.in);
        //find the staff to be transferred
        System.out.println("Enter staffID");
        String staffID=sc.nextLine();
        Account staffAccount=accountDB.find(staffID);
        Staff staff=(Staff)staffAccount;
        //which branch to transfer to
        System.out.println("Enter branch to transfer staff to");
        String branchName=sc.nextLine();
        //remove from staffs list in previous branch and append to staffs list in new branch
        
        //subtract from numStaff in previous branch and add to numStaff in new branch
        Branch previousBranch=branchDB.find(staff.getBranchName());
        Branch newBranch=branchDB.find(branchName);
        previousBranch.changeNumStaff(-1);
        newBranch.changeNumStaff(1);
        //change branchName of Staff object
        staff.setBranchName(branchName);
    }
}
