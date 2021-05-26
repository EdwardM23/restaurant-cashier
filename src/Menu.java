import java.util.ArrayList;
import java.util.Scanner;

public abstract class Menu {
	Scanner input = new Scanner(System.in);
	protected String name, type;
	protected float price;

	public Menu(String name, String type, float price) {
		this.name = name;
		this.price = price;
	}
	
	public static void clearList(ArrayList<Menu> list) {
		list.removeAll(list);
	}
	
	public abstract void printAddOrder();
		
	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected String getType() {
		return type;
	}

	protected void setType(String type) {
		this.type = type;
	}

	protected float getPrice() {
		return price;
	}

	protected void setPrice(int price) {
		this.price = price;
	}
}
