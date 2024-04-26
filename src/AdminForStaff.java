import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class AdminForStaff implements IAdminForStaff{
    private IDataManagerWithCount accountDB;
    private IDataManager<Branch, String> branchDB;
    private IDisplayFilteredForAccount displayFormatter;

    public AdminForStaff(){
        this.displayFormatter=new DisplayFilteredForAccount();
        this.branchDB=DataManagerForBranch.getInstance();
    }
    /**
     * Helper function to find the staff account based on user input for Staff ID
     */
    private Account getStaffFromUser(){
        Scanner sc = GlobalResource.SCANNER;
        System.out.println("Enter the staffID:");
        String staffID = sc.nextLine();
        Account account = accountDB.find(staffID);
        return account;
    }

    /**
     * Allows admin to edit the attributes in a staff account
     */
    public void editStaff(){
        Scanner sc = GlobalResource.SCANNER;
        accountDB=DataManagerForAccount.getInstance();
        Account account = getStaffFromUser();
        Boolean exit = false;
        if (account != null){
            String choice;
            do{
                System.out.println("'''''''''''''''''''''''''''''''''''''''''''''''''''''");
                System.out.println("Select the attributes to change");
                System.out.println("(1) Name");
                System.out.println("(2) Gender");
                System.out.println("(3) Age");
                System.out.println("(4) Password");
                System.out.println("(5) Exit");
                System.out.println("'''''''''''''''''''''''''''''''''''''''''''''''''''''");
                choice = sc.nextLine();
                switch (choice){
                    case "1":
                        String name;
                        do {
                            System.out.println("Enter new name:");
                            name = sc.nextLine();
                            if (name.trim().isEmpty())
                                System.out.println("Name field cannot be empty.");
                        } while (name.trim().isEmpty());
                        account.setName(name);
                        break;

                    case "2":
                        String gender;
                        do {
                            System.out.println("Enter new gender (M/F):");
                            gender = sc.nextLine();
                            if (!(gender.equals("M") || gender.equals("F")))
                                System.out.println("Invalid gender.");
                        } while (!(gender.equals("M") || gender.equals("F")));
                        account.setGender(gender);
                        break;

                    case "3":
                        int age = -1;
                        do{
                            try {
                                System.out.println("Enter age (18 to 65):");
                                age = sc.nextInt();
                                sc.nextLine();
                                if (!(age >= 18 || age <= 65)) {
                                    System.out.println("Please enter an age number between 18 to 65.");
                                } 
                            } catch (InputMismatchException e) {
                                System.out.println("Please enter a valid number.");
                                sc.nextLine();
                            }
                        } while (!(age >= 18 || age <= 65) || (age == -1));
                        account.setAge(age);
                        break;

                    case "4":
                        System.out.println("Enter new password:");
                        String password = sc.nextLine();
                        account.setPassword(password);
                        break;

                    case "5":
                        exit = true;
                        break;

                    default:
                        System.out.println("Invalid option.");
                        break;
                }
            }while (!exit);
            accountDB.update(account);
        }
        else {
            System.out.println("Account not found! Returning to user page...");
        }
    }
	
    /**
     * Allows admin to remove an existing staff account from the staff list
     */
    public void removeStaff(){
        accountDB=DataManagerForAccount.getInstance();
        Account account = getStaffFromUser();

        if (account instanceof Staff){
            Staff staffAccount = (Staff) account;
            String branchName = staffAccount.getBranchName(); 
            int numManager = accountDB.countManagerInBranch(branchName);
            int numStaff = accountDB.countStaffInBranch(branchName);
            Boolean valid = false;

            if (account instanceof Manager){
                valid = QuotaChecker.checkQuotaForRemoveManager(numStaff, numManager - 1);
            }
            else{
                valid = QuotaChecker.checkQuotaForRemoveStaff(numStaff - 1, numManager);
            }

            if (valid)
                accountDB.delete(staffAccount);
            else{
                System.out.println("Account cannot be removed. Invalid Manager to Staff Ratio!");
                System.out.println("Returning to user page...");
            }
        }
        else {
            System.out.println("Account not found/Admin account cannot be removed! Returning to user page...");
        }
    }

    private Staff createStaff(String branchName, String role){
        Scanner sc = GlobalResource.SCANNER;
        System.out.println("Enter Staff ID:");
        String staffID = sc.nextLine();

        while (accountDB.find(staffID) != null){
            System.out.println("Staff ID already exists!");
            System.out.println("Enter Staff ID:");
            staffID = sc.nextLine();
        }

        String name, gender;
        int age = -1;
        do {
            System.out.println("Enter name:");
            name = sc.nextLine();
            if (name.trim().isEmpty())
                System.out.println("Name field cannot be empty.");
        } while (name.trim().isEmpty());
        
        do {
            System.out.println("Enter gender (M/F):");
            gender = sc.nextLine();
            if (!(gender.equals("M") || gender.equals("F")))
                System.out.println("Invalid gender.");
        } while (!(gender.equals("M") || gender.equals("F")));
        
        do{
            try {
                System.out.println("Enter age (18 to 65):");
                age = sc.nextInt();
                sc.nextLine();
                if (!(age >= 18 || age <= 65)) {
                    System.out.println("Please enter an age number between 18 to 65.");
                } 
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                sc.nextLine();
            }
        } while (!(age >= 18 || age <= 65) || (age == -1));
        
        DisplayFilteredByBranch displayFormatter = new DisplayFilteredByBranch();

        if (role.equals("S")){
            return new Staff(name, staffID, role, gender, age, branchName, displayFormatter);
        } 
        // Manager to be added
        else{
            IDataManager<FoodItem,Integer> foodItemDB = DataManagerForFoodItem.getInstance();
            return new Manager(name, staffID, role, gender, age, branchName, displayFormatter, foodItemDB, accountDB);
        }
    }
     /**
     * Allows admin to add a new staff account to the staff list
     */
    public void addStaff(){
        Scanner sc = GlobalResource.SCANNER;
        accountDB = DataManagerForAccount.getInstance();
        displayFormatter.displayAll(branchDB.getAll());
	    System.out.println("Enter the name of branch to assign Staff (case-sensitive):");
    
        String branchName = sc.nextLine();
        Branch branch = branchDB.find(branchName);

        if (branch != null){
            int numStaff = accountDB.countStaffInBranch(branchName);
            int numManager = accountDB.countManagerInBranch(branchName);

            if (numStaff < branch.getStaffQuota()) {


                if (QuotaChecker.checkQuotaForAddStaff(numStaff + 1, numManager)){
                    accountDB.add(createStaff(branchName, "S"));
                } 
                else{
                    System.out.println("Not enough managers in branch. Failed to add staff! ");
                    System.out.println("Returning to user page..."); 
                }
            }
            else{
                System.out.println("Branch has reached staff quota limit. Cannot add staff.");
                System.out.println("Returning to user page..."); 
            }  
        }else{
            System.out.println("Branch not found! Returning to user page...");
        }
    }

    /**
     * Allows admin to display the staff list with filters
     */
    public void displayStaff(){
        Scanner sc = GlobalResource.SCANNER;
        accountDB = DataManagerForAccount.getInstance();
		ArrayList<Account> accountList = accountDB.getAll();
        ArrayList<IGetBranchName> staffList = new ArrayList<IGetBranchName>();

		for (Account item: accountList){
			if (item instanceof Staff){
				staffList.add((IGetBranchName)item);
			}
        }

        Boolean exit=false;
        String choice;

        do{
            System.out.println("'''''''''''''''''''''''''''''''''''''''''''''''''''''");
	        System.out.println("Choose option to filter by");
	        System.out.println("(1) Branch");
	        System.out.println("(2) Role");
	        System.out.println("(3) Gender");
	        System.out.println("(4) Age");
            System.out.println("(5) Display all");
            System.out.println("(6) Exit");
            System.out.println("'''''''''''''''''''''''''''''''''''''''''''''''''''''");
            choice = sc.nextLine();

            switch (choice){
                case "1":
                    displayFormatter.displayAll(branchDB.getAll());
                    System.out.println("Enter the name of branch to filter by:");
                    String branchName=sc.nextLine();
                    Branch branch=branchDB.find(branchName);
                    //check if branch exists
                    if (branch != null){
                        displayFormatter.displayFilteredByBranch(staffList, branchName);
                    } else{
                        System.out.println("Branch not found.");
                    }
                    break;
            
                case "2":
                    System.out.println("Choose role to display");
                    System.out.println("(1) Admin");
                    System.out.println("(2) Manager");
                    System.out.println("(3) Staff");
                    String role = sc.nextLine();

                    switch (role){
                        case "1":
                            displayFormatter.displayFilteredByRole(accountList,"A");
                            break;
                        case "2":
                            displayFormatter.displayFilteredByRole(accountList,"M");
                            break;
                        case "3":
                            displayFormatter.displayFilteredByRole(accountList,"S");
                            break;
                        default:
                            System.out.println("Invalid option.");
                            break;
                    }
                    break;

                case "3":
                    System.out.println("Choose gender to display");
                    System.out.println("(1) Male");
                    System.out.println("(2) Female");
                    String gender = sc.nextLine();

                    switch (gender){
                        case "1":
                            displayFormatter.displayFilteredByGender(accountList,"M");
                            break;
                        case "2":
                            displayFormatter.displayFilteredByGender(accountList,"F");
                            break;
                        default:
                            System.out.println("Invalid option.");
                            break;
                    }
                    break;

                case "4":
                    System.out.println("Enter age to filter by:");
                    try{
                        int age = sc.nextInt();
                        sc.nextLine();
                        displayFormatter.displayFilteredByAge(accountList,age);
                    } catch (InputMismatchException e){
                        System.out.println("Age must be a number.");
                        sc.nextLine();
                    }
                    break;
                
                case "5":
                    displayFormatter.displayAll(accountList);
                    break;

                case "6":
                    System.out.println("Returning to user page...");
                    exit=true;
                    break;

                default:
                    System.out.println("Invalid Option.");
                    break;
                }
		}while (!exit);
    }

    /**
     * Allows admin to assign Managers to a branch within the quota constraint
     */
    public void assignManager(){
        Scanner sc = GlobalResource.SCANNER;
        accountDB = DataManagerForAccount.getInstance();
        displayFormatter.displayAll(branchDB.getAll());
        System.out.println("Enter the name of branch to assign Manager:");
        String branchName = sc.nextLine();
        Branch branch = branchDB.find(branchName);

        if (branch != null){
            int numStaff = accountDB.countStaffInBranch(branchName);
            int numManager = accountDB.countManagerInBranch(branchName);

            if (QuotaChecker.checkQuotaForAddManager(numStaff, numManager+1)){
                //add manager
                accountDB.add(createStaff(branchName, "M"));
            }
            else{
                System.out.println("Too many Managers in branch! ");
                System.out.println("Returning to user page..."); 
            }
        }
        else{
            System.out.println("Branch not found! Returning to user page...");
        }
    }

     /**
     * Allows admin to promote a staff to branch manager
     */
    public void promoteStaff(){
        accountDB = DataManagerForAccount.getInstance();
        Account account = getStaffFromUser();
        if(account != null){
            if (!(account instanceof Manager || account instanceof Admin)){
                Staff staffAccount = (Staff) account;//downcast to Staff
                String branchName = staffAccount.getBranchName();
                Branch branch = branchDB.find(branchName);
                
                if(branch != null){
                    int numStaff = accountDB.countStaffInBranch(branchName);
                    int numManager = accountDB.countManagerInBranch(branchName);

                    if (QuotaChecker.checkQuotaForRemoveStaff(numStaff - 1,numManager) && QuotaChecker.checkQuotaForAddManager(numStaff,numManager + 1)){
                        //create a new Manager object and copy all attributes of staff
                        DisplayFilteredByBranch displayFilteredByBranch = new DisplayFilteredByBranch();
                        DataManagerForFoodItem foodItemDB = DataManagerForFoodItem.getInstance();
                        Manager managerAccount=new Manager(account.getName(),account.getStaffID(),"M", account.getGender(), account.getAge(), staffAccount.getBranchName(), displayFilteredByBranch, foodItemDB, accountDB);
                        managerAccount.setPassword(staffAccount.getPassword());
                            //delete the Staff object to the accountList in DataManagerForAccount
                        accountDB.delete(staffAccount);
                            //add the Manager object to the accountList in Data ManagerForAccount
                        accountDB.add(managerAccount);
                    }
                    else{
                        System.out.println("Too many Managers in branch!");
                        System.out.println("Returning to user page...");
                    }
                }
                else {
                    System.out.println("Branch not found! Returning to user page...");
                }
            }
            else {
                System.out.println("Account is not a staff. Cannot be promoted to manager");
                System.out.println("Returning to user page...");
            }
        }
        else {
            System.out.println("Account not found! Returning to user page...");
        }
    }

    /**
     * Allows admin to transfer a Staff/Manager between branches
     */
    public void transferStaff(){
        Scanner sc = GlobalResource.SCANNER;
        accountDB = DataManagerForAccount.getInstance();
        //take in 2 branches and check if they both exist
        Account account = getStaffFromUser();
        if (account instanceof Staff){
            Staff staffAcc = (Staff) account;
            String branchNameFrom = staffAcc.getBranchName();
            Branch branchFrom = branchDB.find(branchNameFrom);

            System.out.println("Enter branch to transfer staff to:");
            String branchNameTo = sc.nextLine();
            Branch branchTo = branchDB.find(branchNameTo);

            if(branchFrom != null && branchTo != null){
                int numStaffFrom = accountDB.countStaffInBranch(branchNameFrom);
                int numManagerFrom = accountDB.countManagerInBranch(branchNameFrom);
 
                int numStaffTo = accountDB.countStaffInBranch(branchNameTo);
                int numManagerTo = accountDB.countManagerInBranch(branchNameTo);
                boolean validQuota = false;

                if (staffAcc instanceof Manager){
                    validQuota = QuotaChecker.checkQuotaForRemoveManager(numStaffFrom, numManagerFrom - 1) && QuotaChecker.checkQuotaForAddManager(numStaffTo, numManagerTo + 1);
                }

                else{
                    validQuota = QuotaChecker.checkQuotaForRemoveStaff(numStaffFrom - 1, numManagerFrom) && QuotaChecker.checkQuotaForAddStaff(numStaffTo + 1,numManagerTo);
                }

                if (validQuota){
                    staffAcc.setBranchName(branchNameTo);
                    accountDB.update(staffAcc);
                }

                else{
                    System.out.println("Transfer failed: Invalid Staff/Manager ratio! Returning to user page...");
                }
            }
            else{
                System.out.println("Branch not found! Returning to user page...");
            }
        }
        else{
            System.out.println("Account not found/Account is not a Staff or Manager!");
            System.out.println("Returning to user page");
        }
    }
}
