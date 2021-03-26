package f21as_coursework;

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
				System.out.println("Consumer "+ threadNum +" sleeping");
				Thread.sleep(2000);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(so.getDone()) {
				System.out.println("Consumer "+ threadNum +" done");
				done = true;
				break;
			}
			
			class ServeOrders extends SwingWorker<String, String> {

				@Override
				protected String doInBackground() throws Exception {
					String order = so.get();
					return order;
				}
				
				protected void done() {
					try {
						gui.updateSever(threadNum, get());
					}
					catch(ExecutionException x) {
					
					}
					catch(InterruptedException e) {
						e.printStackTrace();
					}
					
				}
				
			}
			
			
			
			System.out.println("Thred 1 sees done as: " + done);
		}
		
	}
	

	
}
