package model;

import f21as_coursework.*;
import views.*;

import java.util.ArrayList;

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
	private int				processingTime;
	private int				processingSpeed;
	
	public CafeModel(OrderList orders, ItemList items, CustomerList customers) {
		
		this.orders 	= orders;
		this.items 		= items;
		this.customers 	= customers;
		orderIndex 		= 0;
		processingSpeed = 3;
		queueTimerInit(Manager.indexOrders(orders, customers, items));
		
	}
	
	public int getProcessingSpeed() {
		return processingSpeed;
	}


	public void setProcessingSpeed(int processingSpeed) {
		this.processingSpeed = processingSpeed;
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
								+ "Total Price: " 	+ allOrders.get(orderIndex).get(2) + "\n";
			
			for(int i = 3; i < allOrders.get(orderIndex).size(); i++) {
				nextOrder += "Item " + (i-2) + ": " + allOrders.get(orderIndex).get(i) + "\n";
			}
				
			orderIndex ++;
			
			return nextOrder;
			
		}
		else {
			return "No more orders";
		}
		
	}
	
	/*
	 * This method initialises the time remaining to process all
	 * orders. Updates instance int variable processingTime.
	 * 
	 * Is updated if user alters number of orders or processing
	 * speed.	
	 */
	public void queueTimerInit(ArrayList<ArrayList<String>> orders) {
		
		processingTime = orders.size() * processingSpeed + processingSpeed;		
	}
	
	/*
	 * Returns the time remaining to process the queue in seconds as a
	 * String.
	 * 
	 * Is called by QueueTimer to display on the GUI instance every
	 * second. 	
	 */
	public String queueTimer() {
		
		if(processingTime <= 10) {
			return "0" + Integer.toString(processingTime-=1);
		}
		else {
			return Integer.toString(processingTime-=1);
		}
	}

}