
import java.util.ArrayList;
import java.io.*; 
import java.util.Scanner;

/**
 * The {@code DataManagerForOrder} class manages a list of {@code Order} objects.
 * This class uses the Singleton design pattern to ensure that only one instance of this manager exists throughout the application.
 * It provides methods to add, update, delete, and find orders in the list based on their IDs.
 */
public class DataManagerForOrder implements IDataManager<Order, Integer> {

    private ArrayList<Order> orderList; 
    private static DataManagerForOrder instance;  
	private final Serializer<Account> serializer;

    /**
     * Private constructor to prevent instantiation outside of this class.
     */
    private DataManagerForOrder() {
        orderList = new ArrayList<>();
		serializer = new Serializer<Order>("../src/..");
		loadData();
    }

    /**
     * Provides access to the singleton instance of the {@code DataManagerForOrder}.
     * If the instance does not exist, it is created.
     *
     * @return The singleton instance of {@code DataManagerForOrder}.
     */
    public static DataManagerForOrder getInstance() {
        if (instance == null) {
            instance = new DataManagerForOrder();
        }
        return instance;
    }

	private void loadData(){
		try{
			orderList = serializer.deserialize();
		}catch (IOException | ClassNotFoundException | FileNotFoundException e){
			orderList = new ArrayList<Order>();
		}
	}

    /**
     * Updates an existing order in the order list with new details.
     * If the order exists, it is replaced with the new order object.
     *
     * @param newOrder The new order object to replace the existing order.
     */



    public void update(Order newOrder) {
		if (order == null)
		{
			System.out.println("Invalid order placed, please try again");
		}
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderID() == newOrder.getOrderID()) {
                orderList.set(i, newOrder);
                return;
				System.out.println("Order successfully updated!");
            }
        }
		System.out.println("Order could not be found in the list, please try again.");
    }

    /**
     * Adds a new order to the order list.
     * If the order with the same ID already exists, it outputs a message and does not add the order.
     *
     * @param order The new order to add to the list.
     */
    public void add(Order order) {
		if (order == null)
		{
			System.out.println("Invalid order placed, please try again");
		}
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderID() == order.getOrderID()) {
                System.out.println("Order is already inside");
                return;
            }
        }
        orderList.add(order);
		System.out.println("Order is successfully added!");
    }

    /**
     * Deletes an order from the order list based on its ID.
     * If the order exists, it is removed; otherwise, it prints a message indicating that the order was not found.
     *
     * @param order The order to be deleted.
     */
    public void delete(Order order) {
		if (order == null)
		{
			System.out.println("Invalid order placed, please try again");
		}
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderID() == order.getOrderID()) {
                orderList.remove(i);
                return;
				System.out.println("Order is successfully removed!");
            }
        }
        System.out.println("Order not found in order list");
    }

    /**
     * Finds and returns an order from the order list based on its ID.
     * If the order is found, it is returned; otherwise, it prints a message indicating that the order was not found.
     *
     * @param orderID The ID of the order to find.
     * @return The found {@code Order} object, or {@code null} if not found.
     */
    public Order find(int orderID) {
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderID() == orderID) {
                return orderList.get(i);
            }
        }
        System.out.println("Order not found in order list");
        return null;
    }
}
// DONE