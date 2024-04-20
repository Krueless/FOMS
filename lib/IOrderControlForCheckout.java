public interface IOrderControlForCheckout {

	/**
	 * 
	 * @param order
	 */
	Order changeOrderStatus(Order order);

	/**
	 * 
	 * @param order
	 */
	Order chooseDineInOption(Order order);

}