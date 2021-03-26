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
	
	public synchronized String get(int i) {
		System.out.println("Consumer "+i+" getting...");
		while(empty) {
			try {
				System.out.println("Consumer "+i+" waiting...");
				wait();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Consumer "+i+ "Got order " + order);
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
		System.out.println("Put order: " + o);
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
