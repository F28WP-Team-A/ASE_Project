package f21as_coursework;

import java.util.ArrayList;

import controllers.CafeGUIController;
import model.*;

/*
 * QueueManager acts as a producer for the
 * shared object.
 */

public class QueueManager implements Runnable {

	private SharedObject so;
	private OrderSet orders;
	private CustomerList customers;
	private ItemMap items;
	private CafeModel model;
	private CafeGUIController controller;
	private int count;
	
	public QueueManager(SharedObject so, OrderSet orders, CafeModel model,
						CustomerList customers, ItemMap items, CafeGUIController controller) {
		this.so = so;
		this.orders = orders;
		this.model = model;
		this.customers = customers;
		this.items = items;
		this.controller = controller;
		count = controller.getModel().getNumRemaining();
	}
	
	public int getCount(){
		return count;
	}

	/*
	 * Periodically the QueueManager calls the putNext
	 * method of the shared object, passing in the next
	 * order to be processed as an argument.
	 */
	@Override
	public void run() {
		
		while(count > 0) {
//			System.out.println("i: " + count);
//			System.out.println("Number of orders: " + Manager.indexOrders(orders, customers, items).size());
			try {
				System.out.println("Producer sleeping");
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {
				
			}
			so.putNext(model.getNextOrder());
			count = controller.getModel().getNumRemaining();
		}
		so.putNext(model.getNextOrder());
		so.finishedOrders();
		
	}
}
