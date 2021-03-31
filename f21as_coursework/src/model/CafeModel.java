package model;

import f21as_coursework.*;
import views.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import controllers.*;

/*
 * The CafeModel class is designed to be the model
 * in the MVC design pattern that implements the cafe
 * GUI.
 * 
 * The methods contained within this class are intended
 * to retrieve and format the data needed by the cafe GUI
 * view and is called by the CafeGUIController when this
 * data is needed.
 * 
 * Created as part of F21AS Advanced Software Engineering.
 * 
 * Author: Elliot Whitehouse (ew2000)
 */

public class CafeModel {
	
	private OrderList 		orders;
	private ItemList 		items;
	private CustomerList 	customers;
	private int 			orderIndex;
	private int				processingSpeed;
	
	private final static Logger logger = Logger.getLogger(CafeModel.class.getName());
	private Log log = Log.getInstance();
	
	public CafeModel(OrderList orders, ItemList items, CustomerList customers) {
		
		this.orders 	= orders;
		this.items 		= items;
		this.customers 	= customers;
		orderIndex 		= 0;
		processingSpeed = 3;
		
	}
		
	/*
	 * getNextOrder method retrieves the order in the OrderList
	 * that is at the index of the current value of orderIndex.
	 * It then transposes the data from that order into a formatted
	 * string, iterating over the items in the order capture orders
	 * with a variable number of items.
	 * 
	 * The formatted string is then returned.
	 */
	public String getNextOrder() {
		
		ArrayList<ArrayList<String>> allOrders = Manager.indexOrders(orders, customers, items);
		
		if(orderIndex < allOrders.size()) {
				
			String nextOrder = "Order Number: " 	+ allOrders.get(orderIndex).get(0) + "\n"
								+ "Name: " 			+ allOrders.get(orderIndex).get(1) + "\n"
								+ "Total Price: " 	+ allOrders.get(orderIndex).get(2) + "\n"
								+ "Discount applied: " + Manager.getDiscount(orders, allOrders.get(orderIndex).get(0))
								+ "% \n";
			
			for(int i = 3; i < allOrders.get(orderIndex).size(); i++) {
				nextOrder += "Item " + (i-2) + ": " + allOrders.get(orderIndex).get(i) + "\n";
			}
				
			orderIndex ++;
			
			logger.log(Level.INFO, "Next order.");
			return nextOrder;
			
		}
		else {
			logger.log(Level.INFO, "No more orders");
			return "No more orders";
		}
		
	}
}
