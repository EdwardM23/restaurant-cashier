import java.util.ArrayList;
import java.util.Scanner;

class newThread implements Runnable {
	private String cust_name;
	private int table_num;
	float total;
	
	public newThread(String cust_name, int table_num, float total) {
		this.cust_name = cust_name;
		this.table_num = table_num;
		this.total = total;
		Thread newThread = new Thread();
		newThread.start();
		try {
			newThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void run() {
		try {
			System.out.println("Generating total.\n");
			Thread.sleep(1000);
			System.out.println("Generating total..\n");
			Thread.sleep(1000);
			System.out.println("Generating total...\n");
			Thread.sleep(1000);
			System.out.printf("Total = Rp.%.0f\n", total);
			Transaction.writeTransactionHistory(cust_name, table_num, total);
		} catch (Exception e) {
			System.out.println("Error!");
		}
	}
}

public class Main {
	static Scanner input = new Scanner(System.in);
	ArrayList<Menu> foodList = new ArrayList<Menu>();
	ArrayList<Menu> drinkList = new ArrayList<Menu>();
	ArrayList<Order> orderList = new ArrayList<Order>();

	public Main() {
		int menu = 0;
		String cust_name = null;
		int table_num = 0;
		do {
			Customer.printMenu();
			menu = inputMenu(3);

			String adminPass;
			if (menu == 1) {
				System.out.print("Input your name: ");
				cust_name = input.nextLine();
				do {
					try {
						System.out.print("Input table number: ");
						table_num = input.nextInt();
					}catch (Exception e) {
						table_num = 0;
					}
				} while(table_num<1);
			}

			else if (menu == 2) {
				System.out.print("Input Admin Password: ");
				adminPass = input.nextLine();
				if (!adminPass.equals(Cashier.getPassword())) menu = 0;
			}
			System.out.println();
			FileController.readDrinkFile(drinkList);
			FileController.readFoodFile(foodList);

			switch(menu) {
			case 1:
				int custMenu = 0;
				do {
					System.out.println("Hello, " + cust_name);
					printOrderlist();
					System.out.println("[1] Choose Food");
					System.out.println("[2] Choose Drink");
					System.out.println("[3] Cancel Menu");
					System.out.println("[4] Finish");
					System.out.println("================");
					custMenu = inputMenu(4);
					System.out.println();
					
					Customer newCustomer = new Customer(cust_name, table_num);

					switch(custMenu) {
					case 1:
						printFoodList();
						newCustomer.addFoodOrder(foodList, orderList);
						break;

					case 2:
						printDrinkList();
						newCustomer.addDrinkOrder(drinkList, orderList);
						break;

					case 3:
						printOrderlist();
						newCustomer.cancelOrder(orderList);
						break;

					case 4:
						printOrderlist();
						System.out.println();
						newThread generateTotalThread = new newThread(newCustomer.getName(), 
								newCustomer.getTable_num(), Transaction.calculateTotal(orderList));
						generateTotalThread.run();
						System.out.println("Press ENTER to continue..");
						input.nextLine();
						System.out.println();
						break;
					}

				} while(custMenu != 4);
				break;

			case 2:
				int cashMenu = 0;
				do {
					Cashier.printMenu();
					cashMenu = inputMenu(4);
					
					Cashier cashier = new Cashier();

					switch(cashMenu) {
					case 1:
						cashier.addMenu();

						break;
					case 2:
						FileController.readFoodFile(foodList);
						FileController.readDrinkFile(drinkList);

						int choose = 0;
						System.out.println("Choose menu type to be deleted :");
						System.out.println("[1] Food");
						System.out.println("[2] Drink");
						choose = inputMenu(2);
						System.out.println();

						switch(choose){
						case 1:
							printFoodList();
							cashier.deleteFoodMenu(foodList);

							break;
						case 2:
							printDrinkList();
							cashier.deleteDrinkMenu(drinkList);

							break;
						}
						System.out.println();
						break;
					case 3:
						System.out.println();
						System.out.println("Transaction History");
						System.out.println("===================");
						FileController.readTransactionHistory();
						System.out.println("Press ENTER to continue..");
						input.nextLine();
						System.out.println();
						break;
					case 4:
						break;
					}
				} while(cashMenu != 3);
				break;

			case 3:
				System.out.println("Program Terminated");
				break;
			}
		} while(menu != 3);
	}

	public void printOrderlist() {
		System.out.println("ORDER LIST");
		System.out.println("==============================================");
		System.out.println("| No. | Menu               | Qty |   Price   |");
		System.out.println("==============================================");
		if (orderList.isEmpty()) 
			System.out.println("                    Empty");
		for (int i=0; i<orderList.size(); i++) {
			System.out.printf("| %2d. | %-18s | %3d | %9.0f |\n", i+1, orderList.get(i).getName(), 
					orderList.get(i).getQty(), orderList.get(i).getPrice());
		}
		System.out.println("==============================================");
	}
	
	public void printDrinkList() {
		System.out.println("DRINK LIST");
		System.out.println("========================================");
		System.out.println("| No. | Drink              |   Price   |");
		System.out.println("========================================");
		for (int i=0; i<drinkList.size(); i++) {
			System.out.printf("| %2d. | %-18s | %9.0f |\n", i+1, drinkList.get(i).getName(),
					drinkList.get(i).getPrice());
		}
		System.out.println("========================================");
	}
	
	public void printFoodList() {
		System.out.println("FOOD LIST");
		System.out.println("========================================");
		System.out.println("| No. | Food               |   Price   |");
		System.out.println("========================================");
		int i = 0;
		for (i=0; i<foodList.size(); i++) {
			System.out.printf("| %2d. | %-18s | %9.0f |\n", i+1, foodList.get(i).getName(),
					foodList.get(i).getPrice());
		}
		System.out.println("========================================");		
	}

	public static int inputMenu(int max) {
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

	public static void main(String[] args) {
		new Main();
	}

}
