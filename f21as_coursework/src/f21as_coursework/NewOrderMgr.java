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
			
			ArrayList<String> newOrder = so.getNew();
			
			String inputString = newOrder.get(0);
			String newName = newOrder.get(2);
			String itemChoice = newOrder.get(1);
			BigDecimal price = items.getPrice(newOrder.get(1));
			boolean existingCustomer = customers.existingCustomer(inputString);
			
			try {
				String custName = "";
				for(int i = 1; i< orders.getNumberOfOrders(); i++) {
					if(orders.getOrderItem(i).getId().equals(inputString)) {
						custName += customers.getCustomer(orders.getOrderItem(i).getId()).getCustName();
						break;
					}
					else {
						custName    = newName;
						String []  name = custName.split(" ");
						
						customers.addCustomer(new Customer( new Name(name[0], name[1]), inputString));
					}	
				}
				
				int itemNum = orders.getNumberOfOrders()+1;
				
				Order order = new Order(itemNum, inputString, LocalDateTime.now(), itemChoice, price);
				
				orders.addDetails(order);
				
				System.out.println(Manager.indexOrders(orders, customers, items));
				System.out.println(customers.existingCustomer(inputString));
				
				if(existingCustomer) {
					gui.updateTable(Integer.parseInt(inputString));
				}
				else {
					gui.addTableRow();
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

}
