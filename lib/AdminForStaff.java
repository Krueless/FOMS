import java.util.Scanner;
public class AdminForStaff implements IAdminForStaff{
    private IDataManager<Account, String> accountDB;
    private IDataManager<Branch, String> branchDB;
    private IDisplayFilteredForAccount displayFormatter;
    private QuotaChecker quotaChecker;
    public AdminForStaff(){
        this.accountDB=DataManagerForAccount.getInstance();
        this.branchDB=DataManagerForBranch.getInstance();
        this.displayFormatter=new DisplayWithFilterAndSort();
	this.quotaChecker=new QuotaChecker();
    }
    public void editStaff(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the staffID");
        String staffID=sc.nextLine();
        Account account=accountDB.find(staffID);
	int choice;
	do{
		System.out.println("Select attribute to change");
		System.out.println("1. Name");
		System.out.println("2. Gender");
		System.out.println("3. Age");
		System.out.println("4. Password");
		System.out.println("5. Exit loop");
		choice=sc.nextInt();
		switch(choice){
			case 1:
				System.out.println("Enter new name");
				String name=sc.nextLine();
				account.setName(name);
				break;
			case 2:
				System.out.println("Enter new gender");
				String gender=sc.nextLine();
				account.setGender(gender);
				break;
			case 3:
				System.out.println("Enter new age");
				int age=sc.nextInt();
				account.setAge(age);
				break;
			case 4:
				System.out.println("Enter new password");
				String password=s.nextLine();
				account.setPassword(password);
				break;
			default:
				break;
		}
	}while(choice>=1 && choice<=4);
	accountDB.update(account);
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
	System.out.println("Enter branch to assign Staff");
        String branchName=sc.nextLine();
	if(quotaChecker.checkStaffQuota(branchName)){
		//add staff
		System.out.println("Enter name");
		String name-sc.nextLine();
	        System.out.println("Enter staffID");
	        String staffID=sc.nextLine();
	        System.out.println("Enter password");
	        String password=sc.nextLine();
	        String role="S";
	        System.out.println("Enter gender");
	        String gender=sc.nextLine();
	        System.out.println("Enter age");
	        int age=sc.nextInt();
	        Staff staffAccount=new Staff(name,staffID,role,gender,age,password,branchName);
	        accountDB.add(staffAccount);
	}else{
		System.out.println("Staff quota is reached. Cannot add staff");
	}
    }
    public void displayStaff(){
        Scanner sc=new Scanner(System.in);
        ArrayList<Staff> staffList=accountDB.getAll();
        System.out.println("Choose filter by")
        System.out.println("1. Branch");
        System.out.println("2. Role");
        System.out.println("3. Gender");
        System.out.println("4. Age");
        int choice=sc.nextInt();
        switch(choice){
            case 1:
		ArrayList<Branch> branchList=branchDB.getAll();
		for(int i=0;i<branchList.size();i++){
			Branch branch=branchList.get(i);
			displayFormatter.displayFilteredByBranch(staffList,branch.getBranchName());
		}
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
		System.out.println("Enter name");
		String name-sc.nextLine();
	        System.out.println("Enter staffID");
	        String staffID=sc.nextLine();
	        System.out.println("Enter password");
	        String password=sc.nextLine();
	        String role="M";
	        System.out.println("Enter gender");
	        String gender=sc.nextLine();
	        System.out.println("Enter age");
	        int age=sc.nextInt();
	        Manager managerAccount=new Manager(name,staffID,role,gender,age,password,branchName);
	        accountDB.add(managerAccount);
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
	String name=staffAccount.name;
	String staffID=staffAccount.staffID;
	String role=staffAccount.role;
	String gender=staffAccount.gender;
	int age=staffAccount.age;
	int password=staffAccount.password;
	Staff staffAccount=(Staff)staffAccount;
	String branchName=staffAccount.getBranchName();
	Manager managerAccount=new Manager(name,staffID,role,gender,age,password,branchName);
        //delete the Staff object to the accountList in DataManagerForAccount
	accountDB.delete(staffAccount);
        //add the Manager object to the accountList in Data ManagerForAccount
	accountDB.add(managerAccount);
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
        //change branchName of Staff object
        staff.setBranchName(branchName);
	//update in database
	accountDB.update(staff);
    }
}
