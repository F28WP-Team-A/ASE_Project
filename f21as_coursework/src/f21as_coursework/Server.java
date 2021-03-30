package f21as_coursework;

import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import model.*;
import views.*;

/*
 * Acts as a consumer of the shared object.
 */

public class Server extends Thread{
	
	private SharedObject so;
	private CafeGUIView gui;
	private int threadNum;
	boolean done;
	boolean terminated;
	
	public Server(SharedObject so, CafeGUIView gui, int i) {
		this.so = so;
		this.gui = gui;
		threadNum =i;
		terminated = false;
	}
	
	/*
	 * Periodically checks to see if there is a
	 * next order to be processed in the shared object.
	 * If there is not, the thread waits.
	 * 
	 * If there is, it retrieves the order and inserts
	 * it into the server's JTextPane component on the
	 * gui.
	 * 
	 * This is done inside a SwingWorker class so that
	 * the background threads do not disrupt the gui and
	 * any interaction with the gui is managed properly
	 * on the Event Dispatch Thread.
	 */
	@Override
	public void run() {
		
		done = false;
		
		while(!done) {
			try {
				System.out.println("Execution speed: " + gui.getExecutionSpeed());
				Thread.sleep(gui.getExecutionSpeed()*1000);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(so.getDone()) {
				System.out.println("Consumer "+ threadNum +" done");
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
						if(gui.getTableModel().getNumRemaining() > 0) {
							gui.removeOrder();
						}
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
		
		/*
		 * If done becomes true and the while loop is exited,
		 * the server checks if it was because it was terminated
		 * (i.e. the user clicked 'Remove Server' on the gui).
		 * 
		 * If terminated is false, it means done was made true
		 * because all orders have been processed, so the server
		 * creates a new SwingWordker class to show in its
		 * corresponding JTextPane on the gui that there are no
		 * more orders.
		 * 
		 * If the thread was forcefully terminated, nothing happens.
		 */
		if(!terminated) {
			SwingWorker<String, Void> endWorker = new SwingWorker<String, Void>() {

				@Override
				protected String doInBackground() throws Exception {
					String order = "No more orders";
					return order;
				}
				
				@Override
				protected void done() {
					try {
						String nextOrder = get();
						gui.updateSever(threadNum, nextOrder);
					}
					catch(ExecutionException x) {
						x.printStackTrace();
					}
					catch(InterruptedException e) {
						e.printStackTrace();
					}
					
				}
				
			};
			endWorker.execute();
		}
		
	}
	
	/*
	 * terminate method allows the server thread
	 * to be terminated externally in response to
	 * a user removing a server from the gui.
	 */
	public void terminate() {
		done = true;
		terminated = true;
	}
	

	
}
