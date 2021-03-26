package f21as_coursework;

import model.*;

public class ThreadTestOne implements Runnable {
	
	private SharedObject so;
	
	public ThreadTestOne(SharedObject so) {
		this.so = so;
	}

	@Override
	public void run() {
		boolean done = false;
		
		while(!done) {
			try {
				System.out.println("Consumer 1 sleeping");
				Thread.sleep(100);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(so.getDone()) {
				System.out.println("Consumer 1 done");
				done = true;
				break;
			}
			
			String order = so.get();
			System.out.println("Thred 1 sees done as: " + done);
		}
		
	}
	
	

}
