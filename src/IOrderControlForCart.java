public interface IOrderControlForCart {
    public Order addToCart(Order order, IDataManager<FoodItem, Integer> foodItemDB);
    public Order removeFromCart(Order order, IDisplay displayFormatter);
    public Order editCart(Order order, IDisplay displayFormatter); 
}
