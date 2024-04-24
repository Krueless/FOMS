import java.util.Scanner;
import java.util.ArrayList;

/**
 * The {@code OrderControl} class manages the order process within a restaurant management system.
 * It handles user interactions for viewing menus, modifying the shopping cart, and checking out orders. This is done through composed classes for 
 * handling the control for the cart and the checkout respectively
 */
public class OrderControl {

    private IDataManager<Order, Integer> orderDB;           // Interface to interact with order data
    private IDataManager<FoodItem, Integer> foodItemDB;        // Interface to interact with food item data
    private IDisplay displayFormatter;      // Interface for displaying data
    private OrderControlForCheckout checkout; // Controls the checkout process
    private OrderControlForCart cart;       // Controls the cart management process
    private Order order;                    // Current order being managed
    private String branchName;              // Branch name for context-specific operations

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
    public OrderControl(IDataManager<Order, Integer> orderDB, IDataManager<FoodItem, Integer> foodItemDB, IDisplay displayFormatter, Order order, String branchName) {
        this.orderDB = orderDB;
        this.foodItemDB = foodItemDB;
        this.displayFormatter = displayFormatter;
        this.checkout = new OrderControlForCheckout();
        this.cart = new OrderControlForCart();
        this.order = order;
        this.branchName = branchName;
    }

    /**
     * Displays the menu filtered by the branch using the display formatter.
     */
    public void viewMenu() {
        DisplayFilteredByBranch branchDisplayFormatter = (DisplayFilteredByBranch) displayFormatter;
        ArrayList<FoodItem> foodItemList = foodItemDB.getAll();
        ArrayList<IGetBranchName> branchNameList = new ArrayList<>(foodItemList.size());
        for (FoodItem item : foodItemList) {
            branchNameList.add(item); // This works because FoodItem implements IGetBranchName
        }
        branchDisplayFormatter.displayFilteredByBranch(branchNameList, branchName);
    }

    /**
     * Shows the main options available for the user to interact with the cart and order process.
     */
    public void showOptions() {
        System.out.println("'''''''''''''''''''''''''''''''''''''''''''''''''''''");
        System.out.println("Welcome!, please choose one of the following options: ");
        System.out.println("(1) Add to cart");
        System.out.println("(2) Remove from cart");
        System.out.println("(3) Edit cart");
        System.out.println("(4) Change dine-in option");
        System.out.println("(5) Checkout");
        System.out.println("(6) Quit");
        System.out.println("'''''''''''''''''''''''''''''''''''''''''''''''''''''");
        chooseOptions();
    }

    /**
     * Processes user input to choose an option and perform the corresponding action.
     */
    public void chooseOptions() {
        Scanner sc = new Scanner(System.in);
		boolean quit = false;
		while (!quit)
		{
            int choice = -1;
			try {
				choice = sc.nextInt();
			} catch (Exception e) {
				System.out.println("You did not enter a valid number");
				chooseOptions();
                return;
			}
			switch (choice) {
				case 1:
					viewMenu();
					Order newOrder = cart.addToCart(order, foodItemDB);
					if(newOrder == null) break;
					orderDB.update(newOrder);
					break;
				case 2:
					newOrder = cart.removeFromCart(order, displayFormatter);
					if(newOrder == null) break;
					orderDB.update(newOrder);
					break;
				case 3:
					newOrder = cart.editCart(order, displayFormatter);
					if(newOrder == null) break;
					orderDB.update(newOrder);
					break;
				case 4:
					newOrder = checkout.changeDineInOption(order);
					orderDB.update(newOrder);
					break;
				case 5:
					checkout.checkOut(order, displayFormatter);
					newOrder = checkout.changeOrderStatus(order, OrderStatus.PREPARING);
					orderDB.update(newOrder);
					quit = true;
					break;
				case 6:
					quit = true;
					break;
				default:
					System.out.println("Please choose a valid option");
            }
	    }
        sc.close();
    }
}

