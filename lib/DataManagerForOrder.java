package OOP_Project_Classes;
public class DataManagerForOrder implements IDataManager {

	private ArrayList<Order> orderList;
	private static DataManagerForOrder instance;

	private DataManagerForOrder() {
	}

	public static DataManagerForOrder getInstance() {
		if (instance == null) {
			instance = new DataManagerForOrder();
		}
		return instance;
	}

	public void update(Order newOrder) 
	{
		for(int i = 0; i < orderlist.size(); i++)
		{
			if (orderList.get(i).getorderID() == newOrder.getOrderID())
			{
				orderList.set(i, newOrder);
				return;
			}
		}
	}

	public void add(Order order) 
	{
		for(int i = 0; i < orderlist.size(); i++)
		{
			if (orderList.get(i).getorderID() == order.getOrderID())
			{
				System.out.println("Order is already inside");
				return;
			}
		orderList.add(order);
	}

	public void delete(Order order) {
		for(int i = 0; i < orderlist.size(); i++)
		{
			if (orderList.get(i).getorderID() == order.getOrderID())
			{
				orderList.remove(i);
				return;
			}
		}
		System.out.println("Order not found in order list");
	}

	public Order find(int orderID) 
	{
		for(int i = 0; i < orderlist.size(); i++)
		{
			if (orderList.get(i).getorderID() == orderID())
			{
				return orderList.get(i);
			}
		}
		System.out.println("Order not found in order list");
	}

}

// DONE