/**
 * The {@code IOrderControlForCheckout} interface implements how to handle the checkout process in the ordering system.
 * This interface is responsible for managing the final part of an order, specifically altering dine-in
 * options and finalizing the checkout process.
 */
public interface IOrderControlForCheckout {

    /**
     * Changes the dine-in option for the specified order.
     * This method toggles the dine-in statuses, modifying how the order should be handled by the service staff.
     *
     * @param order The order for which the dine-in option needs to be changed.
     * @return The updated order after modifying the dine-in option.
     */
    Order changeDineInOption(Order order);

    /**
     * Processes the checkout for a given order, finalizing the transaction.
     * This method performs all actions needed to complete the order processing, including payment verification,
     * and may use a display formatter to show details or results of the checkout process to the user.
     *
     * @param order The order being checked out.
     * @param displayFormatter The display interface used to format and show checkout-related messages or information.
     * @return {@code Boolean} true if the checkout process is successful, false otherwise.
     */
    Boolean checkOut(Order order, IDisplay displayFormatter);
}