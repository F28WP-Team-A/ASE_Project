package f21as_coursework;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.swing.JTable;

import views.*;
import controllers.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		ItemList items = new ItemList();

		OrderList orders = new OrderList();
		
		CustomerList customers = new CustomerList();
	
		Manager.populateItemList(items, "items.csv");

		Manager.populateOrderList(orders, customers, "existingCustomerOrders.csv");
		
		CafeGUIView gui = new CafeGUIView(orders, customers, items);
		
		CafeGUIController controller = new CafeGUIController(gui);
		
		}
		
	}		

