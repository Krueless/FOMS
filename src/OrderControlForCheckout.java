
/**
 * Manages the checkout processes for an order, including updating order status, handling payment,
 * and changing dining options. These will be done by the PaymentUI class, and the Order Class set methods
 */
public class OrderControlForCheckout {

    /**
     * Changes the status of the specified order to the new status provided.
     *
     * @param order The order whose status is to be changed.
     * @param status The new status to set for the order.
     * @return The order with its status updated.
     */
    public Order changeOrderStatus(Order order, OrderStatus status) {
        order.setOrderStatus(status);
        return order;
    }

    /**
     * Initiates the checkout process for the specified order. This method triggers the payment UI
     * to display payment options and process the payment.
     *
     * @param order The order to check out.
     * @param displayFormatter The display formatter used to format the display of payment options.
     */
    public Boolean checkOut(Order order, IDisplay displayFormatter) {
		if (order.getCartItems().size() == 0)
		{
			System.out.println("Cart is empty!");
			return false;
		}
        PaymentUI paymentUI = new PaymentUI(DataManagerForPayment.getInstance(), displayFormatter);
        return paymentUI.choosePaymentOption(order);
    }

    /**
     * Offers the user the option to toggle the dine-in status of the order.
     * If the user chooses to change it, this method updates the order's dine-in status accordingly.
     *
     * @param order The order whose dine-in option may be changed.
     * @return The order with its dine-in option updated, if changed.
     */
    public Order changeDineInOption(Order order) {
        String dineInOption = order.getTakeaway() ? "takeout" : "dine-in";
        System.out.println("Dine-in option is currently " + dineInOption);
        System.out.println("Would you like to change it? If so, enter 1 else enter 0");
		int choice = GetOption.getBinaryNumber();
		if (choice == 1)
		{
			order.setTakeaway(!order.getTakeaway());
		}
        return order;
    }

	
}