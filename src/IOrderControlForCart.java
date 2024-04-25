public interface IOrderControlForCart {
    public Order addtoCart(Order order, String branchName, IDataManager<Order, Integer> menuDB);
    public Order removeFromCart(Order order, IDisplay displayFormatter);
    public Order editCart(Order order, IDisplay displayFormatter); 
}
