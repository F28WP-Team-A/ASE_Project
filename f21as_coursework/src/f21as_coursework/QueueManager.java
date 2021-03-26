package f21as_coursework;

import java.util.ArrayList;

import model.*;

public class QueueManager implements Runnable {

	private SharedObject so;
	private ArrayList<ArrayList<String>> orders;
	private CafeModel model;
	private int count;
	
	public QueueManager(SharedObject so, ArrayList<ArrayList<String>> orders, CafeModel model) {
		this.so = so;
		this.orders = orders;
		this.model = model;
		count = 0;
	}

	@Override
	public void run() {
		
		for(int i = 0; i < orders.size(); i++) {
			System.out.println("i: " + i);
			System.out.println("Number of orders: " + orders.size());
			try {
				System.out.println("Producer sleeping");
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {
				
			}
			so.put(model.getNextOrder());
		}
		so.finishedOrders();
		
	}
}
