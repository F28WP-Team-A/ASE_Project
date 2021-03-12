package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import f21as_coursework.*;
import views.*;
import java.util.Timer;
import java.util.TimerTask;

public class CafeGUIController {
	
	private CafeGUIView gui;
	
	
	public CafeGUIController(CafeGUIView gui) {
		this.gui = gui;
		
		gui.addSetListener(new UpdateListener());
		updateServer();
	}
	
	
	private void updateServer() {
		
		class DisplayCountdown extends TimerTask{
			int seconds = 10;
			
			public void run() {
				if(seconds > 0) {
					System.out.println(seconds + " seconds remaining");
//					gui.updateSeverOne("Update");
					seconds--;
				}
				else {
					System.out.println("Countdown finished");
					this.cancel();
				}
			}
		}
		
		Timer timer = new Timer();
		
		timer.schedule(new DisplayCountdown(), 0, 1000);
	}
	
	public class UpdateListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("Action listener working");
		}
	}
	

}
