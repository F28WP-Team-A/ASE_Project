package f21as_coursework;

import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import model.*;
import views.*;


public class Server extends Thread{
	
	private SharedObject so;
	private CafeGUIView gui;
	private int threadNum;
	
	public Server(SharedObject so, CafeGUIView gui, int i) {
		this.so = so;
		this.gui = gui;
		threadNum =i;
	}
	
	@Override
	public void run() {
		
		boolean done = false;
		
		while(!done) {
			try {
//				System.out.println("Consumer "+ threadNum +" sleeping");
				Thread.sleep(10000);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(so.getDone()) {
//				System.out.println("Consumer "+ threadNum +" done");
				done = true;
				break;
			}
			
			SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {

				@Override
				protected String doInBackground() throws Exception {
					String order = so.getNext(threadNum);
					return order;
				}
				
				@Override
				protected void done() {
					try {
						String nextOrder = get();
						gui.updateSever(threadNum, nextOrder);
						gui.removeOrder();
					}
					catch(ExecutionException x) {
						x.printStackTrace();
					}
					catch(InterruptedException e) {
						e.printStackTrace();
					}
					
				}
				
			};
			
			worker.execute();
		}
		
	}
	

	
}
