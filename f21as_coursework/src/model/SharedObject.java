package model;

import f21as_coursework.*;

public class SharedObject {
	
	private String order;
	private boolean empty;
	private boolean done;
	
	public SharedObject() {
		empty = true;
		done = false;
	}
	
	public synchronized String get() {
		System.out.println("Consumer getting...");
		while(empty) {
			try {
				System.out.println("Consumer waiting...");
				wait();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Got order ");
		empty = true;
		notifyAll();
		return order;
	}
	
	
	public synchronized void put(String o) {
		System.out.println("Producer putting...");
		while(!empty) {
			try {
				System.out.println("Producer waiting...");
				wait();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Put order: ");
		empty = false;
		notifyAll();
		this.order = o;
	}
	
	public void finishedOrders() {
		done = true;
	}
	
	public boolean getDone() {
		return done;
	}

}
