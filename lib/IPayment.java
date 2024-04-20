public interface IPayment {

	/**
	 * 
	 * @param order
	 */
	boolean processPayment(Order order);

	/**
	 * 
	 * @param order
	 */
	void printReceipt(Order order);

	String getName();

}