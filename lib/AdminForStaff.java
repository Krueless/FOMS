import java.util.Scanner;
import java.util.ArrayList;

public class AdminForStaff implements IAdminForStaff{
    private IDataManagerWithCount accountDB;
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
            System.out.println("Account not found! Returning to user page...");
        }
        sc.close();
    }

    public void removeStaff(){
        Scanner sc=new Scanner(System.in);
	System.out.println("Enter branch to remove Staff:");
        String branchName=sc.nextLine();
        Branch branch = branchDB.find(branchName);
        if (branch != null){
            int numManager = accountDB.countManagerInBranch(branchName);
            int numStaff = accountDB.countStaffInBranch(branchName);
            if (QuotaChecker.checkQuota(numStaff-1, numManager)){
	        System.out.println("Enter the staffID");
	        String staffID=sc.nextLine();
	        Account staffAccount=accountDB.find(staffID);
	        if (staffAccount != null){
	            accountDB.delete(staffAccount);
		}else{
	            System.out.println("Account not found! Returning to user page...");
		}
            }else{
                System.out.println("Too many Managers in branch! ");
                System.out.println("Returning to user page..."); 
            }
        }else{
            System.out.println("Branch not found! Returning to user page...");
        }
        sc.close();
    }

    public void addStaff(){
        Scanner sc=new Scanner(System.in);
	    System.out.println("Enter branch to assign Staff:");
        String branchName=sc.nextLine();
        Branch branch = branchDB.find(branchName);
        if (branch != null){
            int numStaff = accountDB.countStaffInBranch(branchName);
            if (numStaff < branch.getStaffQuota()) {
                int numManager = accountDB.countManagerInBranch(branchName);
                if (QuotaChecker.checkQuota(numStaff+1, numManager)){
                    System.out.println("Enter name:");
                    String name = sc.nextLine();
                    System.out.println("Enter staffID:");
                    String staffID=sc.nextLine();
		    while(accountDB.find(staffID)!=null){
			    System.out.println("Staff ID already exists");
			    System.out.println("Enter staffID");
			    staffID=sc.nextLine();
		    }
                    String role="S";
                    System.out.println("Enter gender:");
                    String gender=sc.nextLine();
                    System.out.println("Enter age:");
                    int age=sc.nextInt();
                    DataManagerForOrder orderDB = DataManagerForOrder.getInstance();
                    DisplayFilteredByBranch displayFormatter = new DisplayFilteredByBranch();
                    Staff staffAccount=new Staff(name,staffID,role,gender,age,branchName, orderDB, displayFormatter);
                    accountDB.add(staffAccount);
                }else{
                    System.out.println("Not enough Managers in branch. Failed to add staff! ");
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
        sc.close();
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
                System.out.println("Enter branch to display");
		String branchName=sc.nextLine();
		//check if branch exists
		Branch branch=branchDB.find(branchName);
		if(branch!=null){
			displayFormatter.displayFilteredByBranch(staffList,branchName);
		}else{
			System.out.println("Branch not found! Returning to user page...");
		}
                break;
            case 2:
		System.out.println("Choose role to display");
                System.out.println("1. Admin");
                System.out.println("2. Manager");
                System.out.println("3. Staff");
		int role=sc.nextInt();
		switch(role){
			case 1:
				displayFormatter.displayFilteredByRole(accountList,"A");
				break;
			case 2:
				displayFormatter.displayFilteredByRole(accountList,"M");
				break;
			case 3:
				displayFormatter.displayFilteredByRole(accountList,"S");
				break;
			default: break;
		}
            	break;
            case 3:
		System.out.println("Choose gender to display");
                System.out.println("1. Male");
                System.out.println("2. Female");
		int gender=sc.nextInt();
		switch(gender){
			case 1:
				displayFormatter.displayFilteredByGender(accountList,"M");
				break;
			case 2:
				displayFormatter.displayFilteredByGender(accountList,"F");
				break;
			default: break;
		}
            	break;
            case 4:
		System.out.println("Choose age to display");
		int age=sc.nextInt();
                displayFormatter.displayFilteredByAge(accountList,age);
            	break;
	    default:
                System.out.println("Invalid Option. Returning to user page...");
                break;
        }
        sc.close();
    }

    public void assignManager(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the branch to assign Manager");
        String branchName=sc.nextLine();
	Branch branch=branchDB.find(branchName);
	if(branch!=null){
		int numStaff=accountDB.countStaffInBranch(branchName);
		int numManager=accountDB.countManagerInBranch(branchName);
		if(QuotaChecker.checkQuota(numStaff,numManager+1)){
			//add manager
		        System.out.println("Enter name");
		        String name = sc.nextLine();
	                System.out.println("Enter staffID");
	                String staffID=sc.nextLine();
			while(accountDB.find(staffID)!=null){
				System.out.println("Staff ID already exists");
				System.out.println("Enter staffID");
				staffID=sc.nextLine();
			}
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
			System.out.println("Too many Managers in branch! ");
                	System.out.println("Returning to user page..."); 
		}
	}else{
		System.out.println("Branch not found! Returning to user page...");
	}
	sc.close();
    }
        
    public void promoteStaff(){
        Scanner sc=new Scanner(System.in);
        //find the staff to be promoted
        System.out.println("Enter staffID");
        String staffID=sc.nextLine();
        Account account=accountDB.find(staffID);
	if(account!=null){
		if(account instanceof Staff){
			Staff staffAccount=(Staff)account;//downcast to Staff
			String branchName=staffAccount.getBranchName();
			Branch branch=branchDB.find(branchName);
			if(branch!=null){
				int numStaff=accountDB.countStaffInBranch(branchName);
				int numManager=accountDB.countManagerInBranch(branchName);
				if(QuotaChecker.checkQuota(numStaff-1,numManager+1)){
					//create a new Manager object and copy all attributes of staff
					DataManagerForOrder orderDB = DataManagerForOrder.getInstance();
			                DisplayFilteredByBranch displayFilteredByBranch = new DisplayFilteredByBranch();
			                DataManagerForFoodItem foodItemDB = DataManagerForFoodItem.getInstance();
			                DataManagerForAccount accountDB = DataManagerForAccount.getInstance();
					Manager managerAccount=new Manager(account.getName(),account.getStaffID(),"M",account.getGender(),account.getAge(),staffAccount.getBranchName(),orderDB,displayFilteredByBranch, foodItemDB, accountDB);
				        //delete the Staff object to the accountList in DataManagerForAccount
					accountDB.delete(staffAccount);
				        //add the Manager object to the accountList in Data ManagerForAccount
					accountDB.add(managerAccount);
				}else{
					System.out.println("Too many Managers in branch!");
					System.out.println("Returning to user page...");
				}
			}else{
				System.out.println("Branch not found! Returning to user page...");
			}
		}else{
			System.out.println("Account is not a staff. Cannot be promoted to manager");
			System.out.println("Returning to user page...");
		}
	}else{
		System.out.println("Account not found! Returning to user page...");
	}
	sc.close();
    }
	
    public void transferStaff(){
        Scanner sc=new Scanner(System.in);
	//take in 2 branches and check if they both exist
	System.out.println("Enter branch to transfer staff from");
	String branchName1=sc.nextLine();
	Branch branch1=branchDB.find(branchName1);
	System.out.println("Enter branch to transfer staff to");
	String branchName2=sc.nextLine();
	Branch branch2=branchDB.find(branchName2);
	if(branch1!=null && branch2!=null){
		//check quota for both branches
		//only if true, can transfer staff

		//count number of staff and manager in branch1
		int numStaff1=accountDB.countStaffInBranch(branchName1);
		int numManager1=accountDB.countManagerInBranch(branchName1);
		//count number of staff and manager in branch2
		int numStaff2=accountDB.countStaffInBranch(branchName2);
		int numManager2=accountDB.countManagerInBranch(branchName2);

		System.out.println("Choose option");
		System.out.println("1. Transfer staff");
		System.out.println("2. Transfer manager");
		int option=sc.nextInt();
		boolean validQuota=false;
		switch(option){
			case 1:
				validQuota=QuotaChecker.checkQuota(numStaff1-1,numManager1) && QuotaChecker.checkQuota(numStaff2+1,numManager2);
				break;
			case 2:
				validQuota=QuotaChecker.checkQuota(numStaff1,numManager1-1) && QuotaChecker.checkQuota(numStaff2,numManager2+1);
				break;
			default:
				System.out.println("Invalid option! Returning to user page...");
				break;
		}
		if(validQuota){
			//ask for details of staff to retrieve
			System.out.println("Enter staffID");
			String staffID=sc.nextLine();
			Account account=accountDB.find(staffID);
			if(account!=null){
				if(account instanceof Staff){
					Staff staffAccount=(Staff)account;
					//change branchName of staff object
					staffAccount.setBranchName(branchName2);
					//update in accountDB
					accountDB.update(staffAccount);
				}else{
					System.out.println("Account is not a Staff or Manager! Account cannot be transferred");
					System.out.println("Returning to user page");
				}
			}else{
				System.out.println("Account not found! Returning to user page...");
			}
		}else{
			System.out.println("Invalid Staff Manager ratio in branch! Returning to user page...");
		}
	}else{
		System.out.println("Branch not found! Returning to user page...");
	}
	sc.close();
    }
}
