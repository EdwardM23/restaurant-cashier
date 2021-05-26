import java.util.ArrayList;

public class Order {
	
	private String name;
	private int qty;
	private float price;

	public Order(String name, int qty, float price) {
		this.name = name;
		this.qty = qty;
		this.price = price;
	}
	
	public static void clearOrder(ArrayList<Order> orderList) {
		orderList.removeAll(orderList);
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected int getQty() {
		return qty;
	}

	protected void setQty(int qty) {
		this.qty = qty;
	}

	protected float getPrice() {
		return price;
	}

	protected void setPrice(float price) {
		this.price = price;
	}
	
}