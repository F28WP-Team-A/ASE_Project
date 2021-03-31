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
import model.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		
		
		ItemList items = new ItemList();

		OrderList orders = new OrderList();
		
		CustomerList customers = new CustomerList();
	
		Manager.populateItemList(items, "items.csv");

		Manager.populateOrderList(orders, customers, "existingCustomerOrders.csv");
		
		CafeModel cafe = new CafeModel(orders, items, customers);
		
		ArrayList<ArrayList<String>> allOrders = Manager.indexOrders(orders, customers, items);
		
		System.out.println("Foo123 price: " + items.getPrice("DRINK206"));
		
		CafeGUIView gui = new CafeGUIView(orders, customers, items);
		
		SharedObject so = new SharedObject();
		
		CafeGUIController controller = new CafeGUIController(gui, cafe, so);
		
		QueueManager q = new QueueManager(so, orders, cafe, customers, items, controller);
		
		Thread producerThread = new Thread(q);
		producerThread.start();
		
		Server serverOne = new Server(so, gui, 1);
		Thread consumerThreadOne = new Thread(serverOne);
		consumerThreadOne.start();
		
		Server serverTwo = new Server(so, gui, 2);
		Thread consumerThreadTwo = new Thread(serverTwo);
		consumerThreadTwo.start();
		
		NewOrderMgr newOrderManager = new NewOrderMgr(so, orders, customers, gui, items);
		
		Thread newOrderThread = new Thread(newOrderManager);
		newOrderThread.start();		

		//Log.getInstance();
		//Log.Log();
		

	}	
}		

