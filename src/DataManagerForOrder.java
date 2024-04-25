
import java.util.ArrayList;
import java.io.*; 

/**
 * The {@code DataManagerForOrder} class manages a list of {@code Order} objects.
 * This class uses the Singleton design pattern to ensure that only one instance of this manager exists throughout the application.
 * It provides methods to add, update, delete, and find orders in the list based on their IDs.
 */
public class DataManagerForOrder implements IDataManager<Order, Integer> {

    private ArrayList<Order> orderList; 
    private static DataManagerForOrder instance;  
	private final Serializer<Order> serializer;

    /**
     * Private constructor to prevent instantiation outside of this class.
     */
    private DataManagerForOrder() {
		serializer = new Serializer<Order>("data/orderData.ser"); //TODO update file
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
		}catch (IOException | ClassNotFoundException e){
            System.out.println("Order: Serialized file not found or invalid, initialising new order list");
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
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderID().equals(newOrder.getOrderID())) {
                orderList.set(i, newOrder);
                serializer.serialize(orderList);
                System.out.println("Successfully updated order.");
                return;
            }
        }
    }

    /**
     * Adds a new order to the order list.
     * If the order with the same ID already exists, it outputs a message and does not add the order.
     *
     * @param order The new order to add to the list.
     */
    public void add(Order newOrder) {
        orderList.add(newOrder);
        serializer.serialize(orderList);
		System.out.println("Successfully added order!");
    }

    /**
     * Deletes an order from the order list based on its ID.
     * If the order exists, it is removed; otherwise, it prints a message indicating that the order was not found.
     *
     * @param order The order to be deleted.
     */
    public void delete(Order order) {
        if (orderList.remove(order)){
            System.out.println("Succesfully removed order.");
            serializer.serialize(orderList);
        }
        else{
            System.out.println("Failed to remove order.");
        }
	}

    /**
     * Finds and returns an order from the order list based on its ID.
     * If the order is found, it is returned; otherwise, it prints a message indicating that the order was not found.
     *
     * @param orderID The ID of the order to find.
     * @return The found {@code Order} object, or {@code null} if not found.
     */
    public Order find(Integer orderID) {
        for (Order currOrder: orderList) {
			if (currOrder.getOrderID().equals(orderID))
				return currOrder;
		}
		return null;
	}

    public ArrayList <Order> getAll(){
        return orderList;
    }

    public void updateCancelledOrders(){
        for (int i = 0; i < orderList.size(); i++){
            orderList.get(i).updateCancelled();
        }
    }
}
// DONE