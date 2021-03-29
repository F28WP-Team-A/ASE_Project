package model;

import java.util.ArrayList;

import f21as_coursework.*;

/*
 * Shared object for next orders and new orders.
 * 
 */

public class SharedObject {
	
	private String nextOrder;
	private ArrayList<String> newOrder;
	private boolean nextOrderEmpty;
	private boolean newOrderEmpty;
	private boolean done;
	
	public SharedObject() {
		nextOrderEmpty = true;
		newOrderEmpty = true;
		done = false;
	}
	
	/*
	 * Server threads call getNext to see if there is
	 * an order ready to be served. If there is, this
	 * is returned and added to the server window of
	 * the gui.
	 * 
	 * If there is no next order, the server thread
	 * waits until it is notified.
	 */
	public synchronized String getNext(int i) {
		System.out.println("Consumer "+i+" getting...");
		while(nextOrderEmpty) {
			try {
				System.out.println("Consumer "+i+" waiting...");
				wait();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Consumer "+i+ " Got order ");
		nextOrderEmpty = true;
		notifyAll();
		return nextOrder;
	}
	
	/*
	 * The QueueManager class calls putNext when it
	 * puts the next order to be served into the
	 * the shared object.
	 * 
	 * If the previous order it put in the shared object
	 * has not yet been taken, it waits until it has
	 * and nextOrderEmpty becomes true.
	 */
	public synchronized void putNext(String o) {
		System.out.println("Producer putting...");
		while(!nextOrderEmpty) {
			try {
				System.out.println("Producer waiting...");
				wait();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Put order: ");
		nextOrderEmpty = false;
		notifyAll();
		this.nextOrder = o;
	}
	
	/*
	 * NewOrderMgr thread calls getNew to see if there is
	 * a new order ready to be processed. If there is, this
	 * is returned and added to the server window of
	 * the gui.
	 * 
	 * If there is no new order, the server thread
	 * waits until it is notified.
	 */
	public synchronized ArrayList<String> getNew() {
		System.out.println("New Order Manager getting...");
		while(newOrderEmpty) {
			try {
				System.out.println("New Order Manager waiting...");
				wait();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("New Order Manager got: " + newOrder);
		newOrderEmpty = true;
		notifyAll();
		return newOrder;
	}
	
	/*
	 * The CafeGUIController class calls this method to
	 * put a new order ready to be processed into the
	 * shared object in response to user input in the
	 * gui.
	 * 
	 * When a new order is put into the shared object,
	 * all threads are notified and the NewOrderMgr
	 * gets the new order.
	 */
	public synchronized void putNew(ArrayList<String> o) {
		System.out.println("Controller putting...");
		System.out.println("Controller put order: " + o);
		newOrderEmpty = false;
		notifyAll();
		this.newOrder = o;
	}
	
	/*
	 * When all order have been processed, done
	 * is made true, causing the active threads to
	 * stop execution.	
	 */
	public void finishedOrders() {
		done = true;
	}
	
	/*
	 * Returns the done instance variable as
	 * a boolean.
	 */
	public boolean getDone() {
		return done;
	}

}
