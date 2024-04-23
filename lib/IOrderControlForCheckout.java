
public interface IOrderControlForCheckout {
    public Order changeOrderStatus(Order order, OrderStatus orderStatus);
    public Order changeDineInOption(Order order);
    public void checkOut(Order order, IDisplay displayFormatter);
}
