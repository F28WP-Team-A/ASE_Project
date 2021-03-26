package f21as_coursework;

import model.*;

public class ThreadTestTwo implements Runnable {

	private SharedObject so;
	
	public ThreadTestTwo(SharedObject so) {
		this.so = so;
	}
	
	@Override
	public void run() {
		boolean done = false;
		
		while(!done) {
			try {
				System.out.println("Consumer 2 sleeping");
				Thread.sleep(100);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(so.getDone()) {
				System.out.println("Consumer 2 done");
				done = true;
				break;
			}
			
			String order = so.get();
			System.out.println("Thred 2 sees done as: " + done);
		}
	}
}
