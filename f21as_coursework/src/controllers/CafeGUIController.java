package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import f21as_coursework.*;
import model.CafeModel;
import views.*;
import javax.swing.Timer;

/*
 * The CafeGUIController class is intended to be the 
 * middle man between the CafeGUIView and the CafeModel.
 * 
 * It does not hold any cafe data within itself, instead
 * it calls methods within CafeModel to push data onto
 * the CafeGUIView in response to events.
 * 
 * Created as part of F21AS Advanced Software Engineering.
 * 
 * Author: Elliot Whitehouse (ew2000)
 */

public class CafeGUIController {
	
	private CafeGUIView gui;
	private CafeModel 	cafe;
	
	
	public CafeGUIController(CafeGUIView gui, CafeModel cafe) {
		this.gui 	= gui;
		this.cafe 	= cafe;
		updateServer();
		gui.addSetListener(new OrderProcessor());
		
	}
	
	/*
	 * The updateServer method creates a new Swing
	 * Timer and triggers the OrderProcessor action
	 * listener at the given interval.
	 */
	private void updateServer() {
		int counter = 6;
		
		Timer timer = new Timer(3000, new OrderProcessor());
		timer.start();
	}
	
	
	/*
	 * The OrderProcessor class crates an action listener
	 * that when triggered, gives the server a new order
	 * to process on the CafeGUIView.
	 * 
	 * It calls the CafeModel method getNextOrder to
	 * provide the String for the updateServerOne method.
	 */
	public class OrderProcessor implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			gui.updateSeverOne(cafe.getNextOrder());
		}
	}
	

}
