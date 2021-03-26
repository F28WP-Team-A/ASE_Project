package f21as_coursework;

import model.SharedOrderObject;
/*creates a thread which acts as a server

*/ 

public class ServerThread1 implements Runnable{
	
	SharedOrderObject SOO;
	
	
	
	public void run() {
		while(SOO.notEmpty()) {
		try {
			SOO.customerOrder();
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		System.out.print("Server 1 gets " + SOO.getCustomerOrder()+ "\n");
		//SOO.staticNotify();
		try {
			SOO.serverWait();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//SOO.staticNotify();
		 
		try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 //SOO.staticNotify();
			
		}
	}
}

