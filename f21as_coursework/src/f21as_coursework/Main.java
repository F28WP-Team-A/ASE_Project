package f21as_coursework;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IncorrectItemException, IncorrectOrderException {
		// TODO Auto-generated method stub

		ItemList items = new ItemList();

		OrderList orders = new OrderList();
		
		CustomerList customers = new CustomerList();

		GuiCreate gui = new GuiCreate();
		
		gui.GuiCreator();
		
		Manager.populateItemList(items, "items.csv");

		Manager.populateOrderList(orders, customers, "existingCustomerOrders.csv");

		BigDecimal price = new BigDecimal(0);
	
		Manager.indexOrders(orders, customers,items);
		
		price = Manager.getTotalPrice(orders, String.valueOf(5));

		System.out.println("Price: " + price);
		
		System.out.println(customers.getCustListSize());
		
		
		for(int i = 1; i <= customers.getCustListSize(); i++) {
			System.out.println(customers.getCustomer(String.valueOf(i)).getDetails());
		}
	}		

}
