package model;

import java.util.ArrayList;

import f21as_coursework.*;

public class NewOrderSharedObj {
	
	private ArrayList<String> order;
	private boolean empty;
	private boolean done;
	
	public NewOrderSharedObj() {
		empty = true;
		done = false;
	}
	
	public synchronized ArrayList<String> get() {
		System.out.println("New Order Manager getting...");
		while(empty) {
			try {
				System.out.println("New Order Manager waiting...");
				wait();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("New Order Manager got: " + order);
		empty = true;
		notifyAll();
		return order;
	}
	
	
	public synchronized void put(ArrayList<String> o) {
		System.out.println("Controller putting...");
		System.out.println("Controller put order: " + o);
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
