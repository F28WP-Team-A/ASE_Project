package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map.Entry;

import f21as_coursework.*;
import model.CafeModel;
import views.*;

import javax.swing.JOptionPane;
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
	private Timer		timer;
	
	
	public CafeGUIController(CafeGUIView gui, CafeModel cafe) {
		this.gui 	= gui;
		this.cafe 	= cafe;
//		updateServer();
		queueCountdown();
//		gui.addUpdateSpeedListener(new UpdateSpeed());
//		gui.addNewServerListener(new AddServer());
		
	}
	
	/*
	 * The updateServer method creates a new Swing
	 * Timer and triggers the OrderProcessor action
	 * listener at the given interval.
	 */
	private void updateServer() {
		int counter = 6;
		
//		timer = new Timer(cafe.getProcessingSpeed()*1000, new OrderProcessor());
		timer.start();
	}
	
	/*
	 * Stops the timer instance currently controlling
	 * the processing of orders.
	 */
	private void resetProcessingSpeed() {
		timer.stop();
	}
	
	/*
	 * Creates a new timer to control the updating of
	 * the JLabel that displays the time remaining to
	 * process the orders.
	 */
	private void queueCountdown() {

		Timer countdown = new Timer(1000, new QueueTimer());
		countdown.start();
	}
	
	
	/*
	 * The OrderProcessor class crates an action listener
	 * that when triggered, gives the server a new order
	 * to process on the CafeGUIView.
	 * 
	 * It calls the CafeModel method getNextOrder to
	 * provide the String for the updateServerOne method.
	 */
//	public class OrderProcessor implements ActionListener{
//		
//		public void actionPerformed(ActionEvent e) {
//			gui.updateSever(cafe.getNextOrder());
//		}
//	}
	
	/*
	 * The QueueTimer class crates an action listener
	 * that when triggered updates the time remaining
	 * JLabel on the GUI instance.
	 * 
	 * Is called based on a swing timer and is called
	 * once every second.
	 */
	public class QueueTimer implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			gui.updateGUITimer(cafe.queueTimer());
		}
	}
	
	/*
	 * UpdateSpeed class creates an action listener
	 * that when triggered stops the current timer
	 * that is controlling the processing of orders,
	 * updates the processing speed of the cafe model
	 * and initiates a new order processing timer with
	 * the speed submitted by the user on the GUI.
	 */
	public class UpdateSpeed implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			try {
				resetProcessingSpeed();
				cafe.setProcessingSpeed(gui.getSpeedInput());
				updateServer();
				System.out.println("Updated speed to: " + cafe.getProcessingSpeed());
			}
			catch(NumberFormatException n) {
				gui.errorMessage("Invalid number of seconds");
			}
		}
	}
	
	public class AddCustomer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String 		inputString 	= gui.getCustIDInput();
			String		custName    = gui.getCustName();
			String 		itemChoice 	= gui.getItemChoice();
			BigDecimal price = new BigDecimal(0);
			
			cafe.addOrder(inputString, custName, itemChoice, price);
		}
	}
	
	public class AddServer implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			// TODO Make add server number dynamic
			// Create new Server object
			gui.addServer(3);
			// Start new server object thread
		}
	}
	

}
