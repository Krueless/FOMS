import java.util.Scanner;

public class CustomerUI {

	private String branchName;
	private IDataManager<Order,Integer> orderDB;

	public void createOrder() {
		DataManagerForOrder orderDB = DataManagerForOrder.getInstance();
		DataManagerForFoodItem foodItemDB = DataManagerForFoodItem.getInstance();
		DisplayFilteredByBranch displayformatter = new DisplayFilteredByBranch();
        Scanner sc = GlobalResource.SCANNER;
        boolean valid = false;
        OrderControl control;
        while (!valid) {
            try {
                System.out.println("Please select an option. ");
                System.out.println("1. Dine-in");
                System.out.println("2. Takeaway");
                String option = sc.nextLine();
                Order newOrder;
                switch (option) {
                    case "1": //Dine-in
                        valid = true;
                        newOrder = new Order(orderDB.getAll().size() + 1, false, branchName);
                        orderDB.add(newOrder);
                        control = new OrderControl(orderDB, foodItemDB, displayformatter, newOrder, branchName);
                        control.showOptions();
                        break;
                    case "2": //Takeaway
                        valid = true;
                        newOrder = new Order(orderDB.getAll().size() + 1, true, branchName);
                        orderDB.add(newOrder);
                        control = new OrderControl(orderDB, foodItemDB, displayformatter, newOrder, branchName);
                        control.showOptions();
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.\n");
                        break;
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }
	}

	/**
	 * 
	 * @param orderID
	 */
	public void checkOrder(int orderID) {
        Order order = orderDB.find(orderID);
        if (order != null){
		    System.out.println(order.viewOrderStatus());
            System.out.println("Returning to customer page...");
        }
        else
            System.out.println("Invalid orderID! Returning to customer page...");
	}

	public void showCustomerOptions() {
		Scanner sc = new Scanner(System.in);
        boolean valid = false;

        while (!valid) {
            try {
                System.out.println("Welcome to " + branchName);
                System.out.println("Please select one of the following options.");
                System.out.println("1. Create Order");
                System.out.println("2. Check Order Status");
                System.out.println("3. Quit"); //TODO implement quit suggest refer to admin to see how to continuously show this menu
                String option = sc.nextLine();

                switch (option) {
                    case "1":
                        valid = true;
                        createOrder();
                        break;
                    case "2":
                        System.out.println("What is your order ID? ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        checkOrder(id);;
                        valid = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.\n");
                        break;
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }

        sc.close();
        
	}

	/**
	 * 
	 * @param branchName
	 */
	public CustomerUI(String branchName, IDataManager<Order,Integer> orderDB) {
		this.branchName = branchName;
		this.orderDB = orderDB;

        showCustomerOptions();
	}

}