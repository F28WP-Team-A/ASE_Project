package f21as_coursework;

import java.util.HashMap;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.MathContext;

/*
 * The Manager class is designed to manage the workflow of
 * the application.
 * 
 * The Manager class is used to read in data from a text
 * file and populate an OrderList with existing Orders, as
 * well as populate an ItemList with the Items offered by
 * the cafe, also from a text file.
 * 
 * In addition to handling the file input and output of the
 * application, the Manager class contains the applyDiscount
 * method, which checks whether a customer's order is eligible
 * for a discount and if so, returns the discounted price.
 *
 * Created as part of F21AS Advanced Software Engineering.
 * 
 * Author: Elliot Whitehouse (ew2000)
 */


public class Manager {
	
	
	/*
	 * Manager class cannot be instantiated. 
	 * It is a static class only.
	 */
	private Manager() {}
	
	/*
	 * The populateOrderList method reads data from an external file specified by a String
	 * passed in as a parameter and constructs a Customer and Order object for each line
	 * in the file.
	 */
	public static void populateOrderList(OrderList orderList, CustomerList customers,
										String fileName) throws IncorrectOrderException {
		
		try {
			File 	orders 		= new File(fileName);									
			Scanner fileInput 	= new Scanner(orders);								
			int 	count 		= 0;
			
			if(fileInput.hasNextLine()==false) {
				System.out.println("Input file is empty. Stopping execution.");
				System.exit(0);
			}
			
			while(fileInput.hasNextLine()) {
				count 					+= 1;
				String line 			= fileInput.nextLine();
				String newOrderInfo [] 	= line.split(",");

				if(newOrderInfo.length == 13) {
					try {
						orderList.addDetails(orderConstructor(newOrderInfo));
						customers.addCustomer(customerConstructor(newOrderInfo));
					}
					catch(NumberFormatException nf) {
						System.out.println("Invalid data in row " + count
											+ ". Exiting program run.");
						System.exit(0);
					}
				}
				else {
					System.out.println("Incorrect number of data points at line "
										+ count 
										+ " of input file.");
					break;
				}
			}
			fileInput.close();
		}
		catch (FileNotFoundException fnf) {
			System.out.println("File not found, check file name");		
		}
	}
	
	/*
	 * The populateItemList method reads data from an external file specified by a String
	 * passed in as a parameter and constructs a Customer and Order object for each line
	 * in the file.
	 * 
	 */
	public static void populateItemList(ItemList itemList, String fileName) throws IncorrectItemException {
		
		try {
			File 	items 		= new File(fileName);									
			Scanner fileInput 	= new Scanner(items);								
			int 	count 		= 0;

			if(fileInput.hasNextLine()==false) {
				System.out.println("Input file is empty. Stopping execution.");
				System.exit(0);
			}
			
			while(fileInput.hasNextLine()) {
				count 					+= 1;
				String line 			= fileInput.nextLine();
				String newItemInfo [] 	= line.split(",");
				
				if(newItemInfo[0].equalsIgnoreCase("food") ) {
					if(newItemInfo.length == 6) {
						try {
							itemList.addItem(newItemInfo[1], foodConstructor(newItemInfo));
						}
						catch(NumberFormatException nf) {
							System.out.println("Invalid data in row " + count
												+ ". Exiting program run.");
							System.exit(0);
						}						
					}
					else {
						System.out.println("Incorrect number of data points at line "
								+ count 
								+ " of input file");
						break;
					}
				}
				
				if(newItemInfo[0].equalsIgnoreCase("drink")) {
					if(newItemInfo.length == 7) {
						try {
							itemList.addItem(newItemInfo[1], drinkConstructor(newItemInfo));
						}
						catch(NumberFormatException nf) {
							System.out.println("Invalid data in row " + count
												+ ". Exiting program run.");
							System.exit(0);
						}						
					}
					else {
						System.out.println("Incorrect number of data points at line "
								+ count 
								+ " of input file");
						break;
					}
				}
				
				if(newItemInfo[0].equalsIgnoreCase("merchandise")) {
					if(newItemInfo.length == 5) {
						try {
							itemList.addItem(newItemInfo[1], merchConstructor(newItemInfo));								
						}
						catch(NumberFormatException nf) {
							System.out.println("Invalid data in row " + count
												+ ". Exiting program run.");
							System.exit(0);
						}						
					}
					else {
						System.out.println("Incorrect number of data points at line "
								+ count 
								+ " of input file");
						break;
					}					
				}
			}
			fileInput.close();
		}
		catch (FileNotFoundException fnf) {
			System.out.println("File not found, check file name");		
		}		
	}
	
	
	/*
	 * Returns a newly instantiated Order object, created with
	 * information from the String Array passed in as a parameter.
	 * 
	 * orderID remains a String but the order timestamp is first
	 * split into another array, parsed into an array on type int
	 * which is then used to construct a LocalDateTime object.
	 * 
	 * These variables are then passed into the Order constructor
	 * as the return value.
	 */
	private static Order orderConstructor(String [] newOrderInfo) {
		
		int			orderItemNum    = Integer.parseInt(newOrderInfo[0]);
		String 		orderID 		= newOrderInfo[1];
		String  	item			= newOrderInfo[11];
		BigDecimal  price 			= new BigDecimal(newOrderInfo[12]);
		String 		dateTime [] 	= {newOrderInfo[5], newOrderInfo[6], newOrderInfo[7], 
								   		newOrderInfo[8], newOrderInfo[9], newOrderInfo[10]};
		int 		dTime 	 []		= new int [6];
		for(int i = 0 ; i<dateTime.length; i++) {
			dTime[i] = Integer.parseInt(dateTime[i]);
		}
		LocalDateTime timeStamp = LocalDateTime.of(dTime[0], dTime[1], dTime[2],
													dTime[3], dTime[4], dTime[5]);
		
		return new Order(orderItemNum, orderID, timeStamp, item, price);
		
	}
	
	
	/*
	 * The Customer constructor method returns a Customer
	 * object constructed using the data provided in the String 
	 * Array parameter. 
	 */
	private static Customer customerConstructor(String [] newOrderInfo) {
		
		String 	custID 			= newOrderInfo[1];
		String  firstName 		= newOrderInfo[2];
		String  secondName 		= newOrderInfo[3];
		Name	customerName 	= new Name(firstName, secondName);
		
		return new Customer(customerName, custID);
		
	}
	
	/*
	 * The Food constructor method returns a Food
	 * object constructed using the data provided in the String 
	 * Array parameter. 
	 */
	private static Food foodConstructor(String [] newItemInfo) {
		
		String 		cat  	= newItemInfo[0];
		String 		id 		= newItemInfo[1];
		String 		des		= newItemInfo[2];
		BigDecimal 	price	= new BigDecimal(newItemInfo[3]);
		String 		type 	= newItemInfo[4];
		String 		side 	= newItemInfo[5];
		
		return new Food(cat, id, des, price, type, side);
	}
	
	/*
	 * The Drink constructor method returns a Drink
	 * object constructed using the data provided in the String 
	 * Array parameter. 
	 */
	private static Drink drinkConstructor(String [] newItemInfo) {
		
		String 		cat  	= newItemInfo[0];
		String 		id 		= newItemInfo[1];
		String 		des		= newItemInfo[2];
		BigDecimal 	price	= new BigDecimal(newItemInfo[3]);
		String 		size 	= newItemInfo[4];
		String 		type 	= newItemInfo[5];
		String 		flav 	= newItemInfo[6];
				
		return new Drink(cat, id, des, price, size, type, flav);
	}
	
	/*
	 * The Merchandise constructor method returns a Merchandise
	 * object constructed using the data provided in the String 
	 * Array parameter. 
	 */
	private static Merchandise merchConstructor(String [] newItemInfo) {
		
		String 		cat  	= newItemInfo[0];
		String 		id 		= newItemInfo[1];
		String 		des		= newItemInfo[2];
		BigDecimal 	price	= new BigDecimal(newItemInfo[3]);
		String 		size 	= newItemInfo[4];
				
		return new Merchandise(cat, id, des, price, size);
	};
	
	
	// creates an ArrayList of orders with customer names customer id and price 
	// need to work out some way of merging price orders id and name together. 
	// this maybe should also be in a different form / place
	// also this cannot be called in its current format within the GuiCreate 
	// need to work out some form of getter. 
	
	public static ArrayList<ArrayList<String>> indexOrders(OrderList orders, 
												  CustomerList customers,
												  ItemList items)	{
		
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
	
	
	/* 
	 * getTotalPrice sums the total price of an order and checks if the
	 * order is eligible for a discount. If the order is eligible, the
	 * discount is applied. Returns price of order as a BigDecimal.
	 * 
	 * Takes in as parameters the OrderList containing the Orders and the
	 * customerId of the customer who's order is being processed.
	 * 
	 * Returns the price of the customers order as a BigDecimal.
	 */
	public static BigDecimal getTotalPrice(OrderList orders, String customerID) {
			
		int food 	= 0;
		int drink 	= 0;
		int merch 	= 0;
		
		BigDecimal price = new BigDecimal(0);
			
		for(int i = 1; i < orders.getNumberOfOrders()+1; i++) {
			System.out.println("Manager 333 i: " + i);
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
	
	
	/*
	 * getReport method generates a report including the orders from the
	 * cafe, the total sales from orders and the full menu as a String.
	 * 
	 * This is called by outputReport when exiting the GUI.
	 */
	public static String getReport(OrderList orders, CustomerList customers,ItemList items) {
		
		String report 	= "Orders: \n";
		
		for(ArrayList<String> s : indexOrders(orders, customers, items)) {
			report 		+= s.toString() + "\n";
		}
		
		report          += "Total Sales: £" + orders.getTotalSales();
		
		report 			+= "\n\nMenu: \n"
						+  items.getMenu();
		
		return report;
	}
	
	/*
	 * Outputs a file containing a report including the orders from the
	 * cafe, the total sales from orders and the full menu.
	 * 
	 * The method is enclosed in a try/catch block in order to catch any
	 * IOExceptions that may arise when calling this method.
	 */
	public static void outputReport(OrderList orders, CustomerList customers,
									ItemList items, String fileName) {
		
		try {
			FileWriter reportOutput = new FileWriter(fileName);							
			reportOutput.write(getReport(orders, customers, items));									
			reportOutput.close();
		}
		catch (IOException e) {															
			e.getStackTrace();
		}
	}
}
