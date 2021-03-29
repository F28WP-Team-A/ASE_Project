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
	private CafeTableModel tableModel;
	private boolean newOrder;
	private SharedObject so;
	private int serverCount;
	private int				processingTime;
	private int				initTime;
	
	
	public CafeGUIController(CafeGUIView gui, CafeModel cafe, SharedObject so) {
		this.gui 	= gui;
		this.cafe 	= cafe;
		this.so = so;
		newOrder = false;
		serverCount = 2;
		this.tableModel = gui.getTableModel();
		processingTime = (tableModel.getNumRemaining() * gui.getExecutionSpeed()) / serverCount + gui.getExecutionSpeed();
		initTime = processingTime;
		System.out.println("Processing time: " + processingTime);
		queueCountdown();
		gui.addUpdateSpeedListener(new UpdateSpeed());
		gui.addNewServerListener(new AddServer());
		gui.addNewCustListener(new AddCustomer());
		gui.addRemoveServerListener(new RemoveServer());
		
	}
		
	public CafeTableModel getModel() {
		return tableModel;
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
	

	public void updateTimer() {
		System.out.println("Num remaning: " + tableModel.getNumRemaining());
		System.out.println("Execution speed: " + gui.getExecutionSpeed());
		System.out.println("Server count: " + serverCount);
		System.out.println("New count: " + ((tableModel.getNumRemaining() * gui.getExecutionSpeed()) / serverCount));
		processingTime += gui.getExecutionSpeed();
		System.out.println("Time updated to: " + processingTime);
	}
	
	public void reinitiateTimer() {
		int diff = initTime - processingTime;
//		int newTime = (tableModel.getNumRemaining() * gui.getExecutionSpeed()) / serverCount;
		processingTime = (tableModel.getNumRemaining() * gui.getExecutionSpeed()) / serverCount + diff - 1;
	}
	
	/*
	 * Returns the time remaining to process the queue in seconds as a
	 * String.
	 * 
	 * Is called by QueueTimer to display on the GUI instance every
	 * second. 	
	 */
	public String queueTimer() {
		
		if(processingTime <= 10) {
			return "0" + Integer.toString(processingTime-=1);
		}
		else {
			return Integer.toString(processingTime-=1);
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
				gui.updateExecutionSpeed();
				reinitiateTimer();
			}
			catch(NumberFormatException n) {
				gui.errorMessage("Invalid number of seconds");
			}
		}
	}
	
	 /* The QueueTimer class crates an action listener
	 * that when triggered updates the time remaining
	 * JLabel on the GUI instance.
	 * 
	 * Is called based on a swing timer and is called
	 * once every second.
	 */
	public class QueueTimer implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			gui.updateGUITimer(queueTimer());
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
