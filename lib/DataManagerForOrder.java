public class DataManagerForOrder implements IDataManager {

	private ArrayList<Order> orderList;
	private static DataManagerForOrder instance;

	private DataManagerForOrder() {
		// TODO - implement DataManagerForOrder.DataManagerForOrder
		throw new UnsupportedOperationException();
	}

	public static DataManagerForOrder getInstance() {
		return this.instance;
	}

	/**
	 * 
	 * @param o
	 */
	public void update(Object o) {
		// TODO - implement DataManagerForOrder.update
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param o
	 */
	public void add(Object o) {
		// TODO - implement DataManagerForOrder.add
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param o
	 */
	public void delete(Object o) {
		// TODO - implement DataManagerForOrder.delete
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param datum
	 */
	public Object find(Object datum) {
		// TODO - implement DataManagerForOrder.find
		throw new UnsupportedOperationException();
	}

}