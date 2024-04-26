public interface IOrderControlForCheckout {
    public Order changeDineInOption(Order order);
    public Boolean checkOut(Order order, IDisplay displayFormatter);
}
