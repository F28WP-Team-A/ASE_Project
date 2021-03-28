package model;

import java.util.ArrayList;

import f21as_coursework.*;

public class SharedObject {
	
	private String nextOrder;
	private ArrayList<String> newOrder;
	private boolean nextOrderEempty;
	private boolean newOrderEmpty;
	private boolean done;
	
	public SharedObject() {
		nextOrderEempty = true;
		newOrderEmpty = true;
		done = false;
	}
	
	public synchronized String getNext(int i) {
		System.out.println("Consumer "+i+" getting...");
		while(nextOrderEempty) {
			try {
				System.out.println("Consumer "+i+" waiting...");
				wait();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Consumer "+i+ "Got order " + nextOrder);
		nextOrderEempty = true;
		notifyAll();
		return nextOrder;
	}
	
	
	public synchronized void putNext(String o) {
		System.out.println("Producer putting...");
		while(!nextOrderEempty) {
			try {
				System.out.println("Producer waiting...");
				wait();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Put order: " + o);
		nextOrderEempty = false;
		notifyAll();
		this.nextOrder = o;
	}
	
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
	
	public synchronized void putNew(ArrayList<String> o) {
		System.out.println("Controller putting...");
		System.out.println("Controller put order: " + o);
		newOrderEmpty = false;
		notifyAll();
		this.newOrder = o;
	}
	
	public void finishedOrders() {
		done = true;
	}
	
	public boolean getDone() {
		return done;
	}

}
