import java.util.Scanner;


/**
 * Provides a user interface for customers to interact with the system, including options to create orders, check order status, and quit.
 * The interface ensures that customers can manage their orders effectively at a specific branch.
 */
public class CustomerUI {
	private String branchName;
	private IDataManager<Order,Integer> orderDB;

     /**
     * Constructor that initializes the customer interface with a specific branch and its corresponding order database.
     * It immediately shows customer options, starting the interaction process.
     *
     * @param branchName The name of the branch where the customer is placing orders.
     * @param orderDB The data manager responsible for managing orders at this branch.
     */
    public CustomerUI(String branchName, IDataManager<Order,Integer> orderDB) {
		this.branchName = branchName;
		this.orderDB = orderDB;

        showCustomerOptions();
	}

    /**
     * Handles the creation of a new order by the customer, providing options for dine-in or takeaway.
     * This method guides the customer through the order creation process and integrates with the order and food item databases.
     */
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
                System.out.println("(1) Dine-in");
                System.out.println("(2) Takeaway");
                String option = sc.nextLine();
                switch (option) {
                    case "1": //Dine-in
                        valid = true;
                        int orderID = orderDB.getAll().size() + 1;
                        Order newOrderDineIn = new Order(Integer.valueOf(orderID), false, branchName);
                        orderDB.add(newOrderDineIn);
                        System.out.println("Your order ID is: " + newOrderDineIn.getOrderID());
                        control = new OrderControl(orderDB, foodItemDB, displayformatter, newOrderDineIn, branchName);
                        control.chooseOptions();
                        break;
                    case "2": //Takeaway
                        valid = true;
                        Order newOrderTakeaway = new Order(orderDB.getAll().size() + 1, true, branchName);
                        orderDB.add(newOrderTakeaway);
                        System.out.println("Your order ID is: " + newOrderTakeaway.getOrderID());
                        control = new OrderControl(orderDB, foodItemDB, displayformatter, newOrderTakeaway,branchName);
                        control.chooseOptions();
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
     * Completes the order if ready for pickup, offering the customer the option to pick up their order.
     * The method updates the order status and persists the changes if the customer confirms pickup.
     *
     * @param order The order to complete.
     */
    private void completeOrder(Order order)
    {
        System.out.println("Would you like to pick up? Press 1 for yes and 0 for no");
        int choice = GetOption.getBinaryNumber();
        if (choice == 1){
            order.setOrderStatus(OrderStatus.COMPLETED);
            orderDB.update(order);
            System.out.println("Thank you for shopping at McOOP!");
        }
        else if (choice == 0){
            System.out.println("Remember to pick up your food before it cancels");
        }
        
    }

	/**
     * Checks the status of an order by its order ID.
     * This method retrieves an order using the provided order ID and checks if it belongs to the current branch.
     * It displays the order status and if the order is ready to pick up, prompts the customer to complete the order.
     * If the order does not belong to this branch or the order ID is not found, appropriate messages are displayed.
     *
     * @param orderID The ID of the order to be checked.
     */
	public void checkOrder(int orderID) {
        Order order = orderDB.find(orderID);
    
        if (order != null){
            if (order.getBranchName().equals(branchName)){
                System.out.println(order.viewOrderStatus());
                if (order.getOrderStatus() == OrderStatus.READY_TO_PICKUP)
                {
                    completeOrder(order);
                }
            }
            else{
                System.out.println("OrderID keyed in not for this branch!");
            }
		    System.out.println("Returning to customer page...");
        }
        else
            System.out.println("Invalid orderID! Returning to customer page...");
	}

    /**
     * Provides the primary user interface for customer interaction, allowing them to create orders, check order status, or quit.
     * The method loops until the user decides to quit, handling user input and responding accordingly.
     */
	public void showCustomerOptions() {
		Scanner sc = GlobalResource.SCANNER;
        boolean quit = false;

        while (!quit) {
            try {
                System.out.println("Welcome to " + branchName);
                System.out.println("Please select one of the following options.");
                System.out.println("(1) Create Order");
                System.out.println("(2) Check Order Status");
                System.out.println("(3) Quit");
                String option = sc.nextLine();

                switch (option) {
                    case "1":
                        createOrder();
                        break;
                    case "2":
                        System.out.println("What is your order ID? ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        checkOrder(id);
                        break;
                    case "3":
                        quit = true;
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
        System.out.println("Quit successfully.");
        
	}

}