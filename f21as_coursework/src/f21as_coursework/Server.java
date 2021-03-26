package f21as_coursework;

import model.*;

public class Server extends Thread{
	
	private SharedObject so;
	private int threadNum;
	
	public Server(SharedObject so, int i) {
		this.so = so;
		threadNum =i;
	}
	
	@Override
	public void run() {
		boolean done = false;
		
		while(!done) {
			try {
				System.out.println("Consumer "+ threadNum +" sleeping");
				Thread.sleep(100);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(so.getDone()) {
				System.out.println("Consumer "+ threadNum +" done");
				done = true;
				break;
			}
			
			String order = so.get();
			System.out.println("Thred 1 sees done as: " + done);
		}
		
	}
	

	
}
