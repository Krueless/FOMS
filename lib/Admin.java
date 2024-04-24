import java.util.Scanner;
/**
 * Admin can manage staff, branches, and payment methods.
 * Inherits from the Account class and implements various administrative interfaces for specific tasks.
 */
public class Admin extends Account {
	private IAdminForStaff adminForStaff;
	private IAdminForBranch adminForBranch;
	private IAdminForPayment adminForPayment;

	/**
     * Constructs an Admin with specified details and initialises user interface.
     *
     * @param name    the name of the admin
     * @param staffID the unique identifier for the admin
     * @param role    the account role
     * @param gender  the gender of the admin
     * @param age     the age of the admin
     */
	public Admin(String name,String staffID,String role,String gender,int age){
		super.name=name;
		super.staffID=staffID;
		super.role=role;
		super.gender=gender;
		super.age=age;
		adminForStaff=new AdminForStaff();
		adminForBranch=new AdminForBranch();
		adminForPayment=new AdminForPayment();
	}
	
     /**
     * Displays a menu for admin actions and handles user input to perform various administrative tasks.
     */
	public void selectOptions() {
        Scanner sc = GlobalResource.SCANNER;
		boolean quit = false;
		while(!quit){
            showOptions();
			String option=sc.nextLine();
			switch(option){
			    case "1":
                    adminForStaff.addStaff();
                    break;
			    case "2":
                    adminForStaff.editStaff();
                    break;
			    case "3":
                    adminForStaff.removeStaff();
                    break;
                case "4":
                    adminForStaff.displayStaff();
                    break;
			    case "5":
                    adminForStaff.assignManager();
                    break;
			    case "6":
                    adminForStaff.transferStaff();
                    break;
                case "7":
                    adminForStaff.promoteStaff();
                    break;
			    case "8":
                    adminForPayment.addPaymentMethod();
                    break;
			    case "9":
                    adminForPayment.removePaymentMethod();
                    break;
                case "10":
                    adminForBranch.openBranch();
                    break;
			    case "11":
                    adminForBranch.closeBranch();
                    break;
			    case "12":
                    quit = true;
                    break;
			    default:
			        System.out.println("Invalid option. Please try again");
			    break;
			}
        }
        System.out.println("Log out successfully."); 
	}

    /**
     * Prints the available options that the admin can do
     */
	public void showOptions() {
        System.out.println("'''''''''''''''''''''''''''''''''''''''''''''''''''''");
        System.out.println("Admin Page");
        System.out.println("Please select one of the following options");
        System.out.println("(1) Add staff");
        System.out.println("(2) Edit staff");
        System.out.println("(3) Remove staff");
        System.out.println("(4) Display staff");
        System.out.println("(5) Assign manager");
        System.out.println("(6) Transfer staff/Manager");
        System.out.println("(7) Promote staff");
        System.out.println("(8) Add payment");
        System.out.println("(9) Remove payment");
        System.out.println("(10) Open branch");
        System.out.println("(11) Close branch");
        System.out.println("(12) Log out");
        System.out.println("'''''''''''''''''''''''''''''''''''''''''''''''''''''");

	}

}

	
