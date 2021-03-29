package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map.Entry;

import f21as_coursework.*;
import model.CafeModel;
import model.CafeTableModel;
import model.SharedObject;
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
	private boolean newOrder;
	private SharedObject so;
	private int serverCount;
	
	
	public CafeGUIController(CafeGUIView gui, CafeModel cafe, SharedObject so) {
		this.gui 	= gui;
		this.cafe 	= cafe;
		this.so = so;
		newOrder = false;
		serverCount = 2;
//		updateServer();
		queueCountdown();
//		gui.addUpdateSpeedListener(new UpdateSpeed());
		gui.addNewServerListener(new AddServer());
		gui.addNewCustListener(new AddCustomer());
		gui.addRemoveServerListener(new RemoveServer());
		
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
	
	/*
	 * When the submit button is clicked on the gui,
	 * the actionPerformed method of the AddCumstomer
	 * class calls the putNew method of the shared object
	 * and passes the new order info in as a parameter.
	 * 
	 * The new order is then added to the shared object
	 * and is retrieved by the NewOrderMgr thread to be
	 * processed.
	 */
	public class AddCustomer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			so.putNew(gui.getNewOrderInfo());
		}
	}
	
	public class AddServer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			serverCount++;
			gui.addServer();
			Server newServer = new Server(so, gui, serverCount);
			Thread newServerThread = new Thread(newServer);
			newServerThread.start();
		}
	}
	
	public class RemoveServer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			gui.removeServer();

		}
	}
	

}
