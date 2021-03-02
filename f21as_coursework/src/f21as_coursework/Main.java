package f21as_coursework;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IncorrectItemException, IncorrectOrderException {
		// TODO Auto-generated method stub

		ItemList items = new ItemList();

		OrderList orders = new OrderList();

		GuiCreate.GuiCreator();
		
		Manager.populateItemList(items, "items.csv");

		Manager.populateOrderList(orders, "existingCustomerOrders.csv");

		BigDecimal price = new BigDecimal(0);

		for(int i = 1; i < orders.getNumberOfOrders(); i++) {
			if(orders.getOrderItem(i).getId().equals(String.valueOf(1))) {
				price = price.add(orders.getOrderItem(i).getPrice());
			}
		}

		System.out.println("Price: " + price);

		price = Manager.applyDiscount(orders, String.valueOf(1));

		System.out.println("New Price: " + price);

	}

}
