package f21as_coursework;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JTable;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		ItemList items = new ItemList();

		OrderList orders = new OrderList();
		
		CustomerList customers = new CustomerList();

		GuiCreate gui = new GuiCreate();
		
		gui.GuiCreator();
		
		Manager.populateItemList(items, "items.csv");

		Manager.populateOrderList(orders, customers, "existingCustomerOrders.csv");

		BigDecimal price = new BigDecimal(0);
	
		Manager.indexOrders(orders, customers, items);
		
		price = Manager.getTotalPrice(orders, String.valueOf(5));

		System.out.println("Price: " + price);
		
		System.out.println(customers.getCustListSize());
		
		JTable placehold = new JTable();
		
		System.out.println(CurrentTime.getCurrentTime());
		
		for(int i = 1; i <= customers.getCustListSize(); i++) {
			System.out.println(customers.getCustomer(String.valueOf(i)).getDetails());
		}
		
		for(ArrayList<String> s : Manager.indexOrders(orders, customers, items)) {
			System.out.println(s.toString());
		}
		
		
//		for(Entry<String, Items> i : items.entrySet()) {
//			System.out.println(i.getKey() + " " + i.getValue().getDescription()); 
		}
		
	}		

