package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	private ArrayList<Server> servers;
	
	
	public CafeGUIController(CafeGUIView gui, CafeModel cafe, SharedObject so) {
		this.gui 	= gui;
		this.cafe 	= cafe;
		this.so = so;
		newOrder = false;
		serverCount = 2;
		servers = new ArrayList<Server>();
		this.tableModel = gui.getTableModel();
		gui.addUpdateSpeedListener(new UpdateSpeed());
		gui.addNewServerListener(new AddServer());
		gui.addNewCustListener(new AddCustomer());
		gui.addRemoveServerListener(new RemoveServer());
		
	}
		
	public CafeTableModel getModel() {
		return tableModel;
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
	
	private final static Logger logger = Logger.getLogger(CafeGUIController.class.getName());
	
	/*
	 * When the 'Add Server' button is clicked on the
	 * gui, the actionPerformed method of the AddServer
	 * class:
	 * 1. Increments the number of servers by 1.
	 * 2. Calls the gui to add a server window.
	 * 3. Instantiates a new server object.
	 * 4. Instantiates a new server Thread.
	 * 5. Adds the new server to the ArrayList<Server>.
	 * 6. Starts the new server Thread.
	 */
	public class AddServer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			serverCount++;
			gui.addServer();
			logger.log(Level.INFO, "New server added.");
			Server newServer = new Server(so, gui, serverCount);
			Thread newServerThread = new Thread(newServer);
			servers.add(newServer);
			newServerThread.start();
		}
	}
	
	/*
	 * When the 'Remove Server' button is clicked on the
	 * gui, the actionPerformed method of the RemoveServer
	 * class:
	 * 1. Calls the gui to remove the server JTextPane at the
	 * 	  last index of the ArrayList<JOptionPane>.
	 * 2. Terminates the server Thread at the last index of
	 *    the ArrayList<Server>.
	 */
	public class RemoveServer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			gui.removeServer();
			logger.log(Level.INFO, "Server removed.");
			servers.get(servers.size()-1).terminate();

		}
	}	

}
