import java.util.ArrayList;

/**
 * The {@code OrderControl} class manages the order process within a restaurant management system.
 * It handles user interactions for viewing menus, modifying the shopping cart, and checking out orders. This is done through composed classes for 
 * handling the control for the cart and the checkout respectively
 */
public class OrderControl {

    private IDataManager<Order, Integer> orderDB;         
    private IDataManager<FoodItem, Integer> foodItemDB;      
    private IDisplayFilteredByBranch displayFormatter;      
    private IOrderControlForCheckout checkout; 
    private IOrderControlForCart cart;       
    private Order order;                                

    /**
     * Constructs a new OrderControl with all necessary components and initial order settings.
     *
     * @param orderDB DataManager for order data.
     * @param foodItemDB DataManager for food item data.
     * @param displayFormatter Display formatter for showing data.
     * @param checkout Checkout manager.
     * @param cart Cart manager.
     * @param order The current order to be managed.
     * @param branchName The branch name where the order is being placed.
     */
    public OrderControl(IDataManager<Order, Integer> orderDB, IDataManager<FoodItem, Integer> foodItemDB, IDisplayFilteredByBranch displayFormatter, Order order) {
        this.orderDB = orderDB;
        this.foodItemDB = foodItemDB;
        this.displayFormatter = displayFormatter;
        this.checkout = new OrderControlForCheckout();
        this.cart = new OrderControlForCart();
        this.order = order;
    }

    /**
     * Displays the menu filtered by the branch using the display formatter.
     */
    public void viewMenu() {
        ArrayList<FoodItem> foodItemList = foodItemDB.getAll();
        ArrayList<IGetBranchName> branchNameList = new ArrayList<>(foodItemList.size());
        for (FoodItem item : foodItemList) {
            branchNameList.add(item);
        }
        System.out.println("'''''''''''''''''''''''''''''''''''''''''''''''''''''");
        System.out.println(order.getBranchName() + " Menu");
        displayFormatter.displayFilteredByBranch(branchNameList, order.getBranchName());
        System.out.println("'''''''''''''''''''''''''''''''''''''''''''''''''''''");
        
    }

    public void viewAvailableMenu() {
        ArrayList<FoodItem> foodItemList = foodItemDB.getAll();
        ArrayList<IGetBranchName> branchNameList = new ArrayList<>(foodItemList.size());
        for (FoodItem item : foodItemList) {
            if (item.getAvailability()) // only add when food item available
                branchNameList.add(item);
        }
        System.out.println("'''''''''''''''''''''''''''''''''''''''''''''''''''''");
        System.out.println(order.getBranchName() + " Menu");
        displayFormatter.displayFilteredByBranch(branchNameList, order.getBranchName());
        System.out.println("'''''''''''''''''''''''''''''''''''''''''''''''''''''");
        
    }

    /**
     * Shows the main options available for the user to interact with the cart and order process.
     */
    public void showOptions() {
        System.out.println("'''''''''''''''''''''''''''''''''''''''''''''''''''''");
        System.out.println("Welcome! Please choose one of the following options: ");
        System.out.println("(1) View Menu");
        System.out.println("(2) Add to cart");
        System.out.println("(3) Remove from cart");
        System.out.println("(4) Edit cart");
        System.out.println("(5) Change dine-in option");
        System.out.println("(6) Checkout");
        System.out.println("(7) Quit");
        System.out.println("'''''''''''''''''''''''''''''''''''''''''''''''''''''");
    }

    /**
     * Processes user input to choose an option and perform the corresponding action.
     */
    public void chooseOptions() {
		boolean quit = false;
		while (!quit)
		{   
            showOptions();
            int choice = GetOption.getValidNumber(7);
            Order newOrder;

			switch (choice) {
                case 1: 
                    viewMenu();
                    break;
				case 2:
					viewAvailableMenu();
					newOrder = cart.addToCart(order, foodItemDB);
					if(newOrder != null) 
					    orderDB.update(newOrder);
					break;
				case 3:
					newOrder = cart.removeFromCart(order, displayFormatter);
					if(newOrder != null)
					    orderDB.update(newOrder);
					break;
				case 4:
					newOrder = cart.editCart(order, displayFormatter);
					if(newOrder != null) 
					    orderDB.update(newOrder);
					break;
				case 5:
					newOrder = checkout.changeDineInOption(order);
					orderDB.update(newOrder);
					break;
				case 6:
					if (checkout.checkOut(order, displayFormatter)){
                        order.setOrderStatus(OrderStatus.PREPARING);
                        newOrder = order;
                        orderDB.update(newOrder);
                        quit = true;
                    }
					break;
				case 7:
                    System.out.println("Are you sure you want to quit? Your order will be removed. Press 1 if you're sure.");
                    int choiceToQuit = GetOption.getBinaryNumber();
                    if (choiceToQuit == 1)
                    {
                        System.out.println("Exiting...");
                        orderDB.delete(order);
                        quit = true;
                    }
                    break;
            }
	    }
    }
}

