package f21as_coursework;

import java.io.*;
import java.util.Scanner;
import java.time.LocalDateTime;

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
				
				switch(newOrderInfo.length) {
				case 6:																// Change case depending on number of inputs
					try {
						orderList.addDetails(orderConstructor(newOrderInfo));
						customerConstructor(newOrderInfo);
					}
					catch(NumberFormatException nf) {
						System.out.println("Invalid data in row " + count
											+ ". Exiting program run.");
						System.exit(0);
					}
				}
			}
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
	 * TODO: Create constructors for each subclass category.
	 * TODO: Determine where the HashMap will be and how to populate it.
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
				String newOrderInfo [] 	= line.split(",");
				
				if(newOrderInfo[0].equalsIgnoreCase("food")) {
					switch(newOrderInfo.length) {
					case 6:																// Change case depending on number of inputs
						try {
							foodConstructor(newOrderInfo);
						}
						catch(NumberFormatException nf) {
							System.out.println("Invalid data in row " + count
												+ ". Exiting program run.");
							System.exit(0);
						}
					}
				}				
			}
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
		
		String 	orderID 		= newOrderInfo[2];
		String  item			= newOrderInfo[4];
		double  price 			= Double.parseDouble(newOrderInfo[5]);				// final structure of file TBD
		String 	dateTime [] 	= newOrderInfo[3].split(".");
		int 	dTime 	 [];
		for(int i; i<dateTime.length; i++) {
			dTime[i] = Integer.parseInt(dateTime[i]);
		}
		LocalDateTime timeStamp = LocalDateTime.of(dTime[0], dTime[1], dTime[2],
													dTime[3], dTime[4], dTime[5]);
		
		return Order(orderID, timeStamp, item, price);
		
	}
	
	
	/*
	 * Returns a newly instantiated Customer object, created with
	 * information from the String Array passed in as a parameter.
	 */
	private static Customer customerConstructor(String [] newOrderInfo) {
		
		String 	custID 		= newOrderInfo[0];
		String  name 		= newOrderInfo[1];
		
		return Customer(name, custID);
		
	}
	
		
		
		

}
