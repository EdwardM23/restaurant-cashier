import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileController {
	private static File file = null;
	private static FileWriter fileWriter = null;
	private static Scanner input = null;
	private static String foodPath = "data/FoodList.txt";
	private static String drinkPath = "data/DrinkList.txt";
	private static String historyPath = "data/TransactionHistory.txt";
	private static String[] data = new String[4];
	private static ArrayList<Food> tempFoodList = new ArrayList<Food>();
	private static ArrayList<Menu> tempDrinkList = new ArrayList<Menu>();

	public static void writeFoodFile(String data) {
		file = new File(foodPath);

		try {
			if(file.createNewFile()) {
				fileWriter = new FileWriter(file);
				fileWriter.write(data + '\n');
				fileWriter.close();
			}
			else {
				fileWriter = new FileWriter(file, true);
				fileWriter.write(data + '\n');
				fileWriter.close();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void readFoodFile(ArrayList<Menu> foodList) {
		file = new File(foodPath);

		if(file.exists()) {
			try {
				String availabality = null, name = null, type = null;
				float price = 0;
				Menu.clearList(foodList);
				input = new Scanner(file);

				while(input.hasNextLine()) {
					data = input.nextLine().split("#");
					name = data[0];
					type = data[1];
					price = Float.parseFloat(data[2]);
					availabality = data[3];

					if (availabality.equals("available")) {
						Food food = new Food(name, type, price, availabality);
						foodList.add(food);
					}

				}
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("File isn't exist, can't read file");
		}
	}

	public static void setUnavailableFood(String rmv_name) {
		file = new File(foodPath);

		if(file.exists()) {
			try {
				input = new Scanner(file);
				String availabality = null, name = null, type = null, new_data = null;
				float price = 0;

				while(input.hasNextLine()) {
					// store data from txt file to a variable
					data = input.nextLine().split("#");
					name = data[0];
					type = data[1];
					price = Float.parseFloat(data[2]);
					availabality = data[3];

					// change the availabality to be unavailable
					if (name.equals(rmv_name))
						availabality = "unavailable";

					// store the data in temporary food array
					Food foodTemp = new Food(name, type, price, availabality);
					tempFoodList.add(foodTemp);
				}

				for (int i=0; i<tempFoodList.size(); i++) {
					new_data = tempFoodList.get(i).getName() + "#food#" 
							+ tempFoodList.get(i).getPrice() + '#' + tempFoodList.get(i).getAvailability();
//					System.out.println(new_data);

					try {
						if(i == 0) {
							fileWriter = new FileWriter(file);
							fileWriter.write(new_data + '\n');
							fileWriter.close();
						}
						else {
							fileWriter = new FileWriter(file, true);
							fileWriter.write(new_data + '\n');
							fileWriter.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		} 

		else {
			System.out.println("File isn't exist, can't read file");
		}
	}

	public static void writeDrinkFile(String data) {
		file = new File(drinkPath);

		try {
			if(file.createNewFile()) {
				fileWriter = new FileWriter(file);
				fileWriter.write(data + '\n');
				fileWriter.close();
			}
			else {
				fileWriter = new FileWriter(file, true);
				fileWriter.write(data + '\n');
				fileWriter.close();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void readDrinkFile(ArrayList<Menu> drinkList) {
		file = new File(drinkPath);

		if(file.exists()) {
			try {
				Menu.clearList(drinkList);
				input = new Scanner(file);

				while(input.hasNextLine()) {
					data = input.nextLine().split("#");
					String name = data[0];
					String type = data[1];
					float price = Float.parseFloat(data[2]);

					Drink drink = new Drink(name, type, price);
					drinkList.add(drink);
				}
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("File isn't exist, can't read file");
		}
	}

	public static void removeDrink(String rmv_name) {
		file = new File(drinkPath);

		if(file.exists()) {
			try {
				input = new Scanner(file);
				String name = null, type = null, new_data = null;
				float price = 0;

				while(input.hasNextLine()) {
					// store data from txt file to a variable
					data = input.nextLine().split("#");
					name = data[0];
					type = data[1];
					price = Float.parseFloat(data[2]);
					// store the data in temporary food array
					if (!name.equals(rmv_name)) {
						Drink drinkTemp = new Drink(name, type, price);
						tempDrinkList.add(drinkTemp);
					}
				}

				for (int i=0; i<tempDrinkList.size(); i++) {
					new_data = tempDrinkList.get(i).getName() + "#drink#" 
							+ tempDrinkList.get(i).getPrice();
//					System.out.println(new_data);

					try {
						if(i == 0) {
							fileWriter = new FileWriter(file);
							fileWriter.write(new_data + '\n');
							fileWriter.close();
						}
						else {
							fileWriter = new FileWriter(file, true);
							fileWriter.write(new_data + '\n');
							fileWriter.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		} 

		else {
			System.out.println("File isn't exist, can't read file");
		}
	}

	public static void writeTransactionHistory(String data) {
		file = new File(historyPath);

		try {
			if(file.createNewFile()) {
				fileWriter = new FileWriter(file);
				fileWriter.write(data + '\n');
				fileWriter.close();
			}
			else {
				fileWriter = new FileWriter(file, true);
				fileWriter.write(data + '\n');
				fileWriter.close();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void readTransactionHistory() {
		file = new File(historyPath);

		if(file.exists()) {
			try {
				input = new Scanner(file);

				while(input.hasNextLine()) {
					data = input.nextLine().split("#");
					String name = data[0];
					String table_num = data[1];
					float total_price = Float.parseFloat(data[2]);

					System.out.println("Name : " + name + "(" + table_num + ")");
					System.out.println("Total = " + total_price);
					System.out.println("----------------------------");
				}
				System.out.println();
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("File isn't exist, can't read file");
		}
	}
}
