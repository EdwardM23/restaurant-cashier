import java.util.ArrayList;
import java.util.Scanner;

public class Customer implements CustomerInterface{
	Scanner input = new Scanner(System.in);
	
	private String name;
	private int table_num;

	public Customer(String name, int table_num) {
		this.name = name;
		this.table_num = table_num;
	}
	
	public static void printMenu() {
		System.out.println("================");
		System.out.println("Restaurant Menu");
		System.out.println("================");
		System.out.println("[1] Customer Menu");
		System.out.println("[2] Cashier Menu");
		System.out.println("[3] Exit");
		System.out.println("================");
	}
	
	public void cancelOrder(ArrayList<Order> orderList) {
		int delete;
		if (!orderList.isEmpty()) {
			do {
				try {
					System.out.print("Input the order you want to delete: ");
					delete = input.nextInt();
				}
				catch (Exception e) {
					delete = 0;
				} input.nextLine();
			} while(delete<1 || delete>orderList.size());

			orderList.remove(delete-1);
			System.out.println("Successfully Remove Order.");
		}
		System.out.println("Press ENTER to continue..");
		input.nextLine();
	}

	public void addDrinkOrder(ArrayList<Menu> drinkList, ArrayList<Order> orderList) {
		String name;
		int order, qty = 0;
		float price;

		order = inputMenu(drinkList.size());
		do {
			try {
				System.out.print("Input quantity: ");
				qty = input.nextInt();
			}
			catch (Exception e) {
			}input.nextLine();
		}while(qty<1);

		name = drinkList.get(order-1).getName();
		price = drinkList.get(order-1).getPrice();

		Order newOrder = new Order(name, qty, price);
		orderList.add(newOrder);
		
		Menu dummy = new Drink("d", "d", 1000);
		dummy.printAddOrder();
	}

	public void addFoodOrder(ArrayList<Menu> foodList, ArrayList<Order> orderList) {
		String name;
		int order, qty = 0;
		float price;

		order = inputMenu(foodList.size());
		do {
			try {
				System.out.print("Input quantity: ");
				qty = input.nextInt();
			}
			catch (Exception e) {
			}input.nextLine();
		}while(qty<1);

		name = foodList.get(order-1).getName();
		price = foodList.get(order-1).getPrice();

		Order newOrder = new Order(name, qty, price);
		orderList.add(newOrder);
		
		Menu dummy = new Food("d", "d", 1000, "d");
		dummy.printAddOrder();
	}

	private int inputMenu(int max) {
		int menu = 0;
		do {
			try {
				System.out.print("Input >> ");
				menu = input.nextInt();
			}
			catch (Exception e) {
				menu = 0;
			} input.nextLine();
		} while(menu<1 || menu>max);

		return menu;
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected int getTable_num() {
		return table_num;
	}

	protected void setTable_num(int table_num) {
		this.table_num = table_num;
	}
}