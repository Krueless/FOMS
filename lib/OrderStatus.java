/**
 * Represents the possible statuses of an order within the restaurant management system.
 */
public enum OrderStatus {
    /**
     * The order has been initiated but is still being modified or items are being added to it.
     */
    ORDERING,

    /**
     * The order has been placed and is now being prepared in the kitchen.
     */
    PREPARING,

    /**
     * The order has been prepared and is ready to be picked up by the customer.
     */
    READY_TO_PICKUP,

    /**
     * The order has been completed, meaning it has been picked up by the customer or delivered.
     */
    COMPLETED,

    /**
     * The order has been cancelled (due to time) and is no longer active.
     */
    CANCELLED
}