package f21as_coursework;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import controllers.CafeGUIController;
import views.*;

import model.CafeModel;
import model.SharedObject;

/*
 * The NewOrderMgr class is designed to be
 * initiated as a thread that will wait until
 * a new order is placed into the shared object
 * by the controller.
 * 
 * When a new order is put into the shared object
 * the NewOrderMgr thread retrieves it, processes
 * it and then informing the table whether it
 * needs to add a new row or update the existing
 * data.
 */

public class NewOrderMgr implements Runnable {
	
	private SharedObject so;
	private OrderList orders;
	private CustomerList customers;
	private CafeGUIView gui;
	private ItemList items;
	
	public NewOrderMgr(SharedObject so, OrderList orders, CustomerList customers,
						CafeGUIView gui, ItemList items) {
		this.so = so;
		this.orders = orders;
		this.gui = gui;
		this.items = items;
		this.customers = customers;
	}

	/*
	 * While there are still orders in the queue,
	 * the thread sleeps for 500 milliseconds and
	 * then checks in the shared object for a new
	 * order. If there is no new order, the thread
	 * waits.
	 * 
	 * When it is notified there is a new order, it
	 * retrieves this new order information.
	 */
	@Override
	public void run() {
		
		boolean done = false;
		
		while(!done) {
			try {
//				System.out.println("New Order Manager Waiting...");
				Thread.sleep(500);

			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			if(so.getDone()) {
//				System.out.println("New Order Manager done...");
				done = true;
				break;
			}
			
//			System.out.println("New Order Manager Finished Waiting...");
			
			// Retrieve the new order from the shared object
			ArrayList<String> newOrder = so.getNew();
			
			try {
				// Extract the data needed to instantiate a new order
				String inputString = newOrder.get(0);
				int custNumCheck = Integer.parseInt(inputString);
				String newName = newOrder.get(2);
				String itemChoice = newOrder.get(1);
				BigDecimal price = items.getPrice(newOrder.get(1));
				boolean existingCustomer = customers.existingCustomer(inputString);
				String custName = "";
				
				if(inputString.length() > 0 && newName.length() > 0) {
					for(int i = 1; i< orders.getNumberOfOrders(); i++) {
						if(orders.getOrderItem(i).getId().equals(inputString)) {
							custName += customers.getCustomer(orders.getOrderItem(i).getId()).getCustName();				// If they are an existing customer, get their name.
							break;
						}
						else {
							custName    = newName;
							String []  name = custName.split(" ");
							if(name.length > 1) {
								customers.addCustomer(new Customer( new Name(name[0], name[1]), inputString));					// Create a new Customer object if they do not already exist.
							}
							else {
								customers.addCustomer(new Customer( new Name(name[0]), inputString));
							}
							
						}	
					}
					
					int itemNum = orders.getNumberOfOrders()+1;
					
					Order order = new Order(itemNum, inputString, LocalDateTime.now(), itemChoice, price);
					
					orders.addDetails(order);
					
					if(existingCustomer) {
						gui.updateTable(Integer.parseInt(inputString));
					}
					else {
						gui.addTableRow();
					}	
				}
				else if(inputString.length() == 0) {
					gui.errorMessage("Please enter a customer number");
				}
				else if(newName.length() == 0) {
					gui.errorMessage("Please enter a customer name");
				}
			}
			
			catch(NullPointerException n) {
				JOptionPane.showMessageDialog(null, "Invalid customer number");
				n.printStackTrace();
			}
			catch(NumberFormatException f) {
				gui.errorMessage("Customer number must be an integer");
			}
			catch(IncorrectOrderException ioe) {
				ioe.printStackTrace();
				System.out.println("ioe exception");
			}
		}

	}

}
