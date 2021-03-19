package f21as_coursework;

public class Server extends Thread{
	
	private Order order;
	private boolean available = true;
	private boolean emptyQueue = false;
	
	
	public Server() {}
	
	public void run () {
		while(emptyQueue == false) {
			treatOrder();	
		}
	}
	
	public void treatOrder() {
		available = false;
		System.out.println(order.getPrice());
		try {
			wait(5000);
		}catch (InterruptedException e) {}	
		available = true;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public void setEmptyQueue(boolean emptyQueue) {
		this.emptyQueue = emptyQueue;
	}
	

	
}
