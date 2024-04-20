public class DataManagerForPayment implements IDataManager {

	private ArrayList<IPayment> paymentList;
	private static DataManagerForPayment instance;

	private DataManagerForPayment() {
		// TODO - implement DataManagerForPayment.DataManagerForPayment
		throw new UnsupportedOperationException();
	}

	public static DataManagerForPayment getInstance() {
		return this.instance;
	}

	/**
	 * 
	 * @param o
	 */
	public void update(Object o) {
		// TODO - implement DataManagerForPayment.update
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param o
	 */
	public void add(Object o) {
		// TODO - implement DataManagerForPayment.add
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param o
	 */
	public void delete(Object o) {
		// TODO - implement DataManagerForPayment.delete
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param datum
	 */
	public Object find(Object datum) {
		// TODO - implement DataManagerForPayment.find
		throw new UnsupportedOperationException();
	}

}