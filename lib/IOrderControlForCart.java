public interface IOrderControlForCart {

	/**
	 * 
	 * @param order
	 */
	Order addToCart(Order order);

	/**
	 * 
	 * @param order
	 */
	Order removeFromCart(Order order);

	/**
	 * 
	 * @param order
	 */
	Order editCart(Order order);

}