import java.util.ArrayList;

public class Transaction {
	private int table_num, transaction_id;
	private float total_price;

	public static float calculateTotal(ArrayList<Order> orderList) {
		float total = 0;
		for (int i=0; i<orderList.size(); i++) {
			total += orderList.get(i).getPrice() * orderList.get(i).getQty();
		}
		Order.clearOrder(orderList);
		return total;
	}

	static void writeTransactionHistory(String cust_name, int table_num, float total) {
		String data = cust_name + "#" + table_num + "#" + total;
		FileController.writeTransactionHistory(data);
	}

	protected int getTable_num() {
		return table_num;
	}

	protected void setTable_num(int table_num) {
		this.table_num = table_num;
	}

	protected int getTransaction_id() {
		return transaction_id;
	}

	protected void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}

	protected float getTotal_price() {
		return total_price;
	}

	protected void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
}