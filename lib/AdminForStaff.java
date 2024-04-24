import java.util.Scanner;
import java.util.ArrayList;

public class AdminForStaff implements IAdminForStaff{
    private IDataManager<Account, String> accountDB;
    private IDataManager<Branch, String> branchDB;
    private IDisplayFilteredForAccount displayFormatter;


    public AdminForStaff(){
        this.accountDB=DataManagerForAccount.getInstance();
        this.branchDB=DataManagerForBranch.getInstance();
        this.displayFormatter=new DisplayFilteredForAccount();
	
    }
    public void editStaff(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the staffID");
        String staffID=sc.nextLine();
       
        Account account=accountDB.find(staffID);
        Boolean exit = false;
        if (account != null){
            String choice;
            do{
                System.out.println("Select the attributes to change");
                System.out.println("1. Name");
                System.out.println("2. Gender");
                System.out.println("3. Age");
                System.out.println("4. Password");
                System.out.println("5. Exit");
                choice=sc.nextLine();
                switch(choice){
                    case "1":
                        System.out.println("Enter new name:");
                        String name=sc.nextLine();
                        account.setName(name);
                        break;
                    case "2":
                        System.out.println("Enter new gender:");
                        String gender=sc.nextLine();
                        account.setGender(gender);
                        break;
                    case "3":
                        System.out.println("Enter new age:");
                        int age=sc.nextInt();
                        account.setAge(age);
                        break;
                    case "4":
                        System.out.println("Enter new password:");
                        String password=sc.nextLine();
                        account.setPassword(password);
                        break;
                    case "5":
                        System.out.println("Updating details...");
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option.");
                        break;
                }
            }while(!exit);
            accountDB.update(account);      
        }else{
            System.out.println("Account not found! Returning to user options...");
        }
        sc.close();
    }

    public void removeStaff(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the staffID");
        String staffID=sc.nextLine();
        Account staffAccount=accountDB.find(staffID);
        if (staffAccount != null){
            accountDB.delete(staffAccount);

        }
        ;
        sc.close;
    }
    public void addStaff(){
        Scanner sc=new Scanner(System.in);
	System.out.println("Enter branch to assign Staff");
        String branchName=sc.nextLine();
	if(quotaChecker.checkStaffQuota(branchName)){
		//add staff
		System.out.println("Enter name");
		String name = sc.nextLine();
	        System.out.println("Enter staffID");
	        String staffID=sc.nextLine();
	        String role="S";
	        System.out.println("Enter gender");
	        String gender=sc.nextLine();
	        System.out.println("Enter age");
	        int age=sc.nextInt();
			DataManagerForOrder orderDB = DataManagerForOrder.getInstance();
			DisplayFilteredByBranch displayFormatter = new DisplayFilteredByBranch();
	        Staff staffAccount=new Staff(name,staffID,role,gender,age,branchName, orderDB, displayFormatter);
	        accountDB.add(staffAccount);
	}else{
		System.out.println("Staff quota is reached. Cannot add staff");
	}
    }
    public void displayStaff(){
        Scanner sc=new Scanner(System.in);
		ArrayList<Account> accountList = accountDB.getAll();
        ArrayList<IGetBranchName> staffList = new ArrayList<>();
		for (Account item: accountList){
			if (item instanceof Staff){
				staffList.add((IGetBranchName)item);
			}
		}
        System.out.println("Choose filter by");
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
			System.out.println(branch.getBranchName());
			displayFormatter.displayFilteredByBranch(staffList,branch.getBranchName());
		}
		break;
            case 2:
		System.out.println("Admin");
		displayFormatter.displayFilteredByRole(accountList,"A");
		System.out.println("Manager");
		displayFormatter.displayFilteredByRole(accountList,"M");
		System.out.println("Staff");
		displayFormatter.displayFilteredByRole(accountList,"S");
            	break;
            case 3:
		System.out.println("Male");
		displayFormatter.displayFilteredByGender(accountList,"M");
		System.out.println("Female");
		displayFormatter.displayFilteredByGender(accountList,"F");
            	break;
            case 4:
		for(int age=0;age<=100;age++){
			displayFormatter.displayFilteredByAge(accountList,age);
		}
            	break;
	    default:break;
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
		String name = sc.nextLine();
	        System.out.println("Enter staffID");
	        String staffID=sc.nextLine();
	        String role="M";
	        System.out.println("Enter gender");
	        String gender=sc.nextLine();
	        System.out.println("Enter age");
	        int age=sc.nextInt();
			DataManagerForOrder orderDB = DataManagerForOrder.getInstance();
			DisplayFilteredByBranch displayFilteredByBranch = new DisplayFilteredByBranch();
			DataManagerForFoodItem foodItemDB = DataManagerForFoodItem.getInstance();
			DataManagerForAccount accountDB = DataManagerForAccount.getInstance();
	        Manager managerAccount=new Manager(name,staffID,role,gender,age,branchName, orderDB, displayFilteredByBranch, foodItemDB, accountDB);
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
		Manager managerAccount = (Manager) staffAccount;
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
