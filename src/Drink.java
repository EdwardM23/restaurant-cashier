public class Drink extends Menu{

	public Drink(String name, String type, float price) {
		super(name, type, price);
	}

	@Override
	public void printAddOrder() {
		System.out.println("Successfully Add New Drink.");
		System.out.println("Press ENTER to continue..");
		input.nextLine();
		System.out.println();
	}
}