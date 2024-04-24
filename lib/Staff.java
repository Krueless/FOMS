import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a staff member in the system.
 * Inherits from the Account class and implements the IGetBranchName interface.
 */
public class Staff extends Account implements IGetBranchName {
    /**
     * The name of the branch associated with the staff member.
     */
	protected String branchName;

    /**
     * The database for managing orders.
     */
	protected IDataManager<Order,Integer> orderDB;

    /**
     * The display formatter for filtered orders.
     */
	protected IDisplayFilteredByBranch displayFormatter;
    
	public Staff(String name,String staffID,String role,String gender,int age,String branchName, IDataManager<Order, Integer> orderDB, IDisplayFilteredByBranch displayFormatter){
		super.name=name;
		super.staffID=staffID;
		super.role=role;
		super.gender=gender;
		super.age=age;
		this.branchName=branchName;
		this.orderDB=DataManagerForOrder.getInstance();
		this.displayFormatter=displayFormatter;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName){
		this.branchName=new String(branchName);
	}
	
	/**
	 * 
	 */
	public void displayNewOrders(){
		ArrayList<Order> orderList=orderDB.getAll();
		ArrayList<IGetBranchName> newOrders=new ArrayList<IGetBranchName>();
		//loop through orderList and add only the new orders to newOrders
		for(Order order: orderList){
			if(order.getOrderStatus()==OrderStatus.PREPARING)
				newOrders.add(order);
		}
		displayFormatter.displayFilteredByBranch(newOrders,branchName);
	}
	
	public void viewOrder(){
        Scanner sc = GlobalResource.SCANNER;
        System.out.println("Enter the Order ID:");
        try{
            int orderID = sc.nextInt();
            Order order=orderDB.find(orderID);
            if (order != null)
		        System.out.println(order); // use toString() method in Order class to get all attributes (encapsulation)
            else
                System.out.println("Order ID keyed in not found! Returning to user page...");
        }catch (Exception e){
            System.out.println("Order ID must be number! Returning to user page...");
        }
    }
	
	public void processOrder(){
		Scanner sc = GlobalResource.SCANNER;
        System.out.println("Enter the Order ID to be processed:");
        try{
            int orderID = sc.nextInt();
            Order order=orderDB.find(orderID);
            if (order != null){
                order.setOrderStatus(OrderStatus.READY_TO_PICKUP); 
                System.out.println("Order status updated to ready for collection. Returning to user page...");
            }
            else
                System.out.println("Order ID keyed in not found! Returning to user page...");
        }catch (Exception e){
            System.out.println("Order ID must be number! Returning to user page...");
        }
	}
	/**
     * Allows the staff member to select options from menu.
     */
	public void selectOptions(){
        Scanner sc = GlobalResource.SCANNER;
		boolean quit =false;
		while(!quit){
            showOptions();
			String option=sc.nextLine();
			switch(option){
			    case "1":
                    displayNewOrders();
                    break;
			    case "2":
                    viewOrder();
                    break;
			    case "3":
                    processOrder();
                    break;
                case "4":
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
     * Displays the menu options for staff.
     */
	public void showOptions(){
        System.out.println("'''''''''''''''''''''''''''''''''''''''''''''''''''''");
        System.out.println("Staff Page");
        System.out.println("Please select one of the following options");
        System.out.println("(1) Display new orders");
        System.out.println("(2) View order");
        System.out.println("(3) Process order");
        System.out.println("(4) Log out");
        System.out.println("'''''''''''''''''''''''''''''''''''''''''''''''''''''");
	}

}
