package f21as_coursework;

import java.io.*;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


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
	public static void populateOrderList(OrderList orderList, String fileName) {
		
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
//						if(orderList.getNumberOfOrders() > 0 && orderList.existingID(newOrderInfo[0].trim())) {		// If order exists, item is added to order
//							orderList.findById(newOrderInfo[0]).addItem(newOrderInfo[10]);
//							orderList.findById(newOrderInfo[0]).addPrice(new BigDecimal(newOrderInfo[11]));
//							continue;
//						}
//						else {
//							orderList.addDetails(orderConstructor(newOrderInfo));
//						}
						orderList.addDetails(orderConstructor(newOrderInfo));
						customerConstructor(newOrderInfo);															// TODO Add customer list class
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
										+ " of input file lenght is: " + newOrderInfo.length);
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
	public static void populateItemList(ItemList itemList, String fileName) {
		
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
		
		int		orderItemNum    = Integer.parseInt(newOrderInfo[0]);
		String 	orderID 		= newOrderInfo[1];
		String  item			= newOrderInfo[11];
		BigDecimal  price 		= new BigDecimal(newOrderInfo[12]);
		String 	dateTime [] 	= {newOrderInfo[5], newOrderInfo[6], newOrderInfo[7], 
								   newOrderInfo[8], newOrderInfo[9], newOrderInfo[10]};
		int 	dTime 	 []		= new int [6];
		for(int i = 0 ; i<dateTime.length; i++) {
			dTime[i] = Integer.parseInt(dateTime[i]);
		}
		LocalDateTime timeStamp = LocalDateTime.of(dTime[0], dTime[1], dTime[2],
													dTime[3], dTime[4], dTime[5]);
		
		return new Order(orderItemNum, orderID, timeStamp, item, price);
		
	}
	
	
	/*
	 * Returns a newly instantiated Customer object, created with
	 * information from the String Array passed in as a parameter.
	 */
	private static Customer customerConstructor(String [] newOrderInfo) {
		
		String 	custID 			= newOrderInfo[0];
		String  firstName 		= newOrderInfo[1];
		String  secondName 		= newOrderInfo[2];
		Name	customerName 	= new Name(firstName, secondName);
		
		return new Customer(customerName, custID);
		
	}
	
	/*
	 * Food constructor
	 * 
	 * Food(String cat, String id, String des, double cost, String type, String side)
	 * 
	 */
	private static Food foodConstructor(String [] newItemInfo) {
		
		String cat  		= newItemInfo[0];
		String id 			= newItemInfo[1];
		String des			= newItemInfo[2];
		BigDecimal price	= new BigDecimal(newItemInfo[3]);
		String type 		= newItemInfo[4];
		String side 		= newItemInfo[5];
		
		return new Food(cat, id, des, price, type, side);
	}
	
	/*
	 * Drink constructor
	 * 
	 * (String cat,String id, String des, double cost, String size, String type, String flav)
	 * 
	 */
	private static Drink drinkConstructor(String [] newItemInfo) {
		
		String cat  		= newItemInfo[0];
		String id 			= newItemInfo[1];
		String des			= newItemInfo[2];
		BigDecimal price	= new BigDecimal(newItemInfo[3]);
		String size 		= newItemInfo[4];
		String type 		= newItemInfo[5];
		String flav 		= newItemInfo[6];
				
		return new Drink(cat, id, des, price, size, type, flav);
	}
	
	/*
	 * Merchandise constructor
	 * 
	 * Merchandise (String cat,String id, String des, double cost, String size)
	 * 
	 */
	private static Merchandise merchConstructor(String [] newItemInfo) {
		
		String cat  		= newItemInfo[0];
		String id 			= newItemInfo[1];
		String des			= newItemInfo[2];
		BigDecimal price	= new BigDecimal(newItemInfo[3]);
		String size 		= newItemInfo[4];
				
		return new Merchandise(cat, id, des, price, size);
	}
	
	
	/* 
	 * Method that checks if the order is eligible for a discount. Returns
	 * new price of order after discount if applicable.
	 */
	public static BigDecimal applyDiscount(OrderList orders, String customerID) {
			
		int food = 0;
		int drink = 0;
		int merch = 0;
		
		BigDecimal price = new BigDecimal(0);
		BigDecimal discount = new BigDecimal(0);
			
		for(int i = 1; i < orders.getNumberOfOrders(); i++) {
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
		
		
		if(food >= 2 && drink >= 2) {
			discount = price.multiply(new BigDecimal(0.20));
			System.out.println(discount);
			price = price.subtract(discount);
			System.out.println(price);
			return price.round(new MathContext(4));
			
			
		}
		
		if(merch >= 1) {
			discount = price.multiply(new BigDecimal(0.10));
			System.out.println(discount);
			price = price.subtract(discount);
			System.out.println(price);
			return price.round(new MathContext(4));
		}
		
		return discount;
	}
	
	

}
