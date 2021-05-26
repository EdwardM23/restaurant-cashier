public class Food extends Menu{

	private String availability;

	public Food(String name, String type, float price, String availabality) {
		super(name, type, price);
		availability = availabality;
	}

	protected String getAvailability() {
		return availability;
	}

	protected void setAvailability(String availability) {
		this.availability = availability;
	}

	@Override
	public void printAddOrder() {
		System.out.println("Successfully Add New Food.");
		System.out.println("Press ENTER to continue..");
		input.nextLine();
		System.out.println();
	}
}
