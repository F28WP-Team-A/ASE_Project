package f21as_coursework;
import model.SharedOrderObject;
public class ServerThread2 implements Runnable {
	
		SharedOrderObject SOO;
		
		public ServerThread2(SharedOrderObject s) {
			this.SOO = s;
		}
		
		public void run() {
			while(SOO.notEmpty()) {
				try {
					SOO.customerOrder();
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				System.out.println("get server 2 " + SOO.getCustomerOrder()+"\n");
				//SOO.staticNotify();
				try {
					SOO.serverWait();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				//SOO.staticNotify();
			
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// SOO.staticNotify();
			
		}
	}
}
