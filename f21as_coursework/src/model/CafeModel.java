package model;

import f21as_coursework.*;
import views.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map.Entry;

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
	
	public void addOrder(String id, String nameInput, String item, BigDecimal price) {
		try {
			String custName = "";
			System.out.println("Adding id:" + id);
			for(int i = 1; i< orders.getNumberOfOrders(); i++) {
				if(orders.getOrderItem(i).getId().equals(id)) {
					custName += customers.getCustomer(orders.getOrderItem(i).getId()).getCustName();
					break;
				}
				else {
					custName    = nameInput;
					String []  name = custName.split(" ");
					
					customers.addCustomer(new Customer( new Name(name[0], name[1]), String.valueOf(7)));
				}	
			}
			
			for(Entry<String, Items> i : items.entrySet()) {
				if(i.getValue().getID().equals(item)) {
					price =  price.add(i.getValue().getCost());
				}
			}
			
			int itemNum = items.getMapSize() + 1;
			
			System.out.println(itemNum + " "+ id + " "+ LocalDateTime.now() + " "+ item + " "+ price);
			
			orders.addDetails(new Order(itemNum, id, LocalDateTime.now(), item, price));
			
			
			
			for(ArrayList<String> s : Manager.indexOrders(orders, customers, items)) {
				System.out.println(s.toString());
			}
		}
		
		catch(NullPointerException n) {
			JOptionPane.showMessageDialog(null, "Invalid customer number");
			n.printStackTrace();
		}
	//	catch(IndexOutOfBoundsException i) {
	//		JOptionPane.showMessageDialog(null, "Invalid number of scores");
	//	}
		catch(NumberFormatException f) {
			JOptionPane.showMessageDialog(null, "Score must be made up of numbers only");
		}
		catch(IncorrectOrderException ioe) {
			ioe.printStackTrace();
			System.out.println("ioe exception");
		}
	}	

}
