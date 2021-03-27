package f21as_coursework;

import java.util.ArrayList;

import model.*;

public class QueueManager implements Runnable {

	private SharedObject so;
	private OrderList orders;
	private CustomerList customers;
	private ItemList items;
	private CafeModel model;
	private int count;
	
	public QueueManager(SharedObject so, OrderList orders, CafeModel model,
						CustomerList customers, ItemList items) {
		this.so = so;
		this.orders = orders;
		this.model = model;
		this.customers = customers;
		this.items = items;
		count = Manager.indexOrders(orders, customers, items).size();
	}

	@Override
	public void run() {
		
		while(count > 0) {
			System.out.println("i: " + count);
			System.out.println("Number of orders: " + Manager.indexOrders(orders, customers, items).size());
			try {
				System.out.println("Producer sleeping");
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {
				
			}
			so.put(model.getNextOrder());
			count = Manager.indexOrders(orders, customers, items).size();
		}
		so.finishedOrders();
		
	}
}
