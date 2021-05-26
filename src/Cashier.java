import java.util.ArrayList;
import java.util.Scanner;

public class Cashier {

	Scanner input = new Scanner(System.in);
	private static String password = "admin123";
	
	public static void printMenu() {
		System.out.println("Cashier Menu");
		System.out.println("================");
		System.out.println("[1] Add Menu");
		System.out.println("[2] Remove Menu");
		System.out.println("[3] Transaction History");
		System.out.println("[4] Back");
		System.out.println("================");
	}

	public void addMenu() {
		String name, type;
		float price;
		// Input Menu Name
		do {
			System.out.print("Input menu name: ");
			name = input.nextLine();
		} while(name.length()<5 || name.length()>18);

		// Input Menu Type
		do {						
			System.out.print("Input menu type [food | drink]: ");
			type = input.nextLine();
		} while(!type.equals("food") && !type.equals("drink"));


		// Input Price
		System.out.print("Input price: ");
		price = input.nextInt();
		input.nextLine();

		// Save new menu to File System
		String data = null;
		if (type.equals("food")) {
			data = name + "#" + type + "#" + price + '#' + "available";
			FileController.writeFoodFile(data);
		}
		else {
			data = name + "#" + type + "#" + price;
			FileController.writeDrinkFile(data);
		}

		System.out.println();
		System.out.println("Successfully Add New Menu!");
		System.out.println("Press ENTER to continue..");
		input.nextLine();
	}

	public void deleteDrinkMenu(ArrayList<Menu> drinkList) {
		int rmv_index = 0;

		do {
			try {
				System.out.printf("Select the drink you want to delete [1-%d]: ", drinkList.size());
				rmv_index = input.nextInt();
			}
			catch (Exception e) {
				rmv_index = 0;
			} input.nextLine();
		} while(rmv_index<1 || rmv_index>drinkList.size());
		System.out.println();
		FileController.removeDrink(drinkList.get(rmv_index-1).getName());
		System.out.println("Successfully Delete A Drink Menu");
		System.out.println("Press ENTER to continue..");
		input.nextLine();
	}

	public void deleteFoodMenu(ArrayList<Menu> foodList) {
		int rmv_index = 0;

		do {
			try {
				System.out.printf("Select the food you want to delete [1-%d]: ", foodList.size());
				rmv_index = input.nextInt();
			}
			catch (Exception e) {
				rmv_index = 0;
			} input.nextLine();
		} while(rmv_index<1 || rmv_index>foodList.size());
		System.out.println();
		FileController.setUnavailableFood(foodList.get(rmv_index-1).getName());
		System.out.println("Successfully Delete A Food Menu");
		System.out.println("Press ENTER to continue..");
		input.nextLine();
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		Cashier.password = password;
	}
}
