package model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Set;

import f21as_coursework.CustomerList;
import f21as_coursework.ItemList;
import f21as_coursework.OrderList;
import f21as_coursework.Server;

public class SharedOrderObject {
	private static OrderList orders;
	private static CustomerList customers;
	private static ItemList items;
	private static int Counter = 0;
	private static boolean empty = false;
	//this allows for calling the static wait and notifyAll methods in a static method
	// if i change things away from static the entire thing breaks
	private static Object obj = new Object();
	private static String OrderItem = "";
	// constructor
	public SharedOrderObject(OrderList orders, ItemList items, CustomerList customers){
		this.orders = orders;
		this.items = items;
		this.customers = customers;
	}
	
	private static ArrayList<ArrayList<String>> completeOrders(OrderList orders,
																	CustomerList customers,
																	ItemList items){
		
		ArrayList<ArrayList<String>> allOrders = new ArrayList<ArrayList<String>>();
		
		for(String s : customers.getCustomerIDs()) {
			ArrayList<String> orderDetails = new ArrayList<String>();
			String id = s;
			String name = customers.getCustomer(s).getCustName();
			String price = String.valueOf(getTotalPrice(orders, s));
			orderDetails.add(id);
			orderDetails.add(name);
			orderDetails.add(price);
			for(int i =1; i< orders.getNumberOfOrders()+1; i++) {
				if(orders.getOrderItem(i).getId().equals(s)) {
					String itemID = orders.getOrderItem(i).getItemDetails().replaceAll("\\[", "").replaceAll("\\]","");
					String menuItem = items.get(itemID).getDescription();
					orderDetails.add(menuItem);
					
				}
			}
			
			allOrders.add(orderDetails);
		}
		
		return allOrders;
		
	}
	
	
	public static void staticWait() throws InterruptedException  {
		synchronized(obj){
			try {obj.wait();}
			catch (InterruptedException e) {}
		}
	};
	
	public static void staticNotify() {
		synchronized (obj) {
			obj.notifyAll();
		}
	}
	
	public synchronized static void customerOrder() throws InterruptedException {
		
		while(empty) {System.out.println("aaa"); staticWait();
		};
		empty = true;
		staticNotify();
		
	}
	
	public synchronized static String getCustomerOrder() {
		
		OrderItem =(completeOrders(orders, customers, items).get(Counter).toString());
		Counter ++;
		return OrderItem;
	};
	
	
	public synchronized static boolean serverWait() throws InterruptedException {

		while(!empty) {staticWait();};
		empty = false;
		staticNotify();
		return empty;
		
	}
		
	// not implemented yet
	public String AddOrder() {
		return null;
	}
	// is used for the while loop to loop through the threads
	public static boolean notEmpty() {
		if(Counter < completeOrders(orders, customers, items).size()) {
		return true;
		}
		else 
		return false;
		
	}
	
	public static BigDecimal getTotalPrice(OrderList orders, String customerID) {
		
		int food 	= 0;
		int drink 	= 0;
		int merch 	= 0;
		
		BigDecimal price = new BigDecimal(0);
			
		for(int i = 1; i < orders.getNumberOfOrders()+1; i++) {
			
			if(orders.getOrderItem(i).getId().equals(customerID) 
				&& orders.getOrderItem(i).getItemDetails().substring(1,5).equalsIgnoreCase("food")) {
				food += 1;
				price = price.add(orders.getOrderItem(i).getPrice());
			}
			
			else if(orders.getOrderItem(i).getId().equals(customerID)
				&& orders.getOrderItem(i).getItemDetails().substring(1,6).equalsIgnoreCase("drink")) {
				drink += 1;
				price = price.add(orders.getOrderItem(i).getPrice());
			}
			
			else if(orders.getOrderItem(i).getId().equals(customerID)
				&& orders.getOrderItem(i).getItemDetails().substring(1,6).equalsIgnoreCase("merch")) {
				merch += 1;
				price = price.add(orders.getOrderItem(i).getPrice());
			}
		}
		
		// Check for discounts
		if(food >= 3)  {
			price = price.multiply(new BigDecimal(0.70));
			return price.round(new MathContext(4));
		}
		
		if(food >= 2 && drink >= 2) {
			price = price.multiply(new BigDecimal(0.80));
			return price.round(new MathContext(4));
		}
		
		if(merch >= 1) {
			price = price.multiply(new BigDecimal(0.90));
			return price.round(new MathContext(4));
		}
		
		return price;
	}
	
	
}
