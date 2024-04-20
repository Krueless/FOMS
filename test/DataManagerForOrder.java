import java.util.ArrayList;
import java.io.Serializable;
public class DataManagerForOrder implements IDataManager<Order, String>, Serializable {

	private ArrayList<Order> orderList;
	private static DataManagerForOrder instance;

	private DataManagerForOrder() {
		// TODO - implement DataManagerForOrder.DataManagerForOrder
		throw new UnsupportedOperationException();
	}

	public static DataManagerForOrder getInstance() {
		return instance;
	}


	@Override
	public void add(Order t1) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'add'");
	}

	@Override
	public void delete(Order t1) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'delete'");
	}

	@Override
	public Order find(String t2) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'find'");
	}

	@Override
	public ArrayList<Order> getAll() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getAll'");
	}

	@Override
	public void update(String t2, Order newT1) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'update'");
	}

}