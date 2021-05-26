import java.util.ArrayList;

public interface CustomerInterface {
	public static final String name = "";
	public static final int table_num = 0;

	void cancelOrder(ArrayList<Order> orderList);
	void addDrinkOrder(ArrayList<Menu> drinkList, ArrayList<Order> orderList);
	void addFoodOrder(ArrayList<Menu> foodList, ArrayList<Order> orderList);
}