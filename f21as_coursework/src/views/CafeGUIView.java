package views;

import f21as_coursework.*;
import model.CafeTableModel;
import controllers.*;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableRowSorter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CafeGUIView extends JFrame {
	
	private OrderList 				orders;
	private CustomerList			customers;
	private ItemList				items;
	private int 					executionSpeed;
	private CafeTableModel 			tableModel;
	JPanel							eastPanel, centrePanel, southPanel;
	JLabel							speed;
	JTable							table;
	JTextField 						customerID, eastNumInput, customerName, newSpeed;
	JTextArea						menu;
	JTextPane						serverOne, serverTwo;
	JButton 						eastNumSearch, submitItemSelection, submitNewSpeed,
									addServer, removeServer;
	TableRowSorter<CafeTableModel> 	sorter;
	JComboBox<String> 				itemsList, sizeSelection;
	ArrayList<JTextPane>			servers;
	
	
	/*
	 * The CafeGUI constructor takes as a parameter
	 * an OrderList, CustomerList and ItemList that will provide
	 * the data used by the GUI.
	 */
	public CafeGUIView(OrderList orders, CustomerList customers, ItemList items) {
		
		this.orders 		= orders;
		this.customers 		= customers;
		this.items 			= items;
		executionSpeed 		= 5;
		servers = new ArrayList<JTextPane>();
		setDefaultCloseOperation(CafeGUIView.DO_NOTHING_ON_CLOSE);
		this.setLayout(new BorderLayout(1,1));
		createEastPanel();
		createCentrePanel();
		createSouthPanel();
		pack();
		setLocation(450,150);
		setTitle("Cafe GUI");
		setVisible(true);
		this.setResizable(false);

		
	}
	
	
	/*
	 * Creates a JPanel and adds it to the east section
	 * of the GUI instance.
	 * 
	 * the eastPanel allows the user to enter a new order
	 * by entering a customer id (new or existing), a customer
	 * name (only for new customers) and selecting the item
	 * desired from a drop down menu. When the user clicks
	 * submit, a new Order is created with the information
	 * provided and added to the OrderList and JTable.
	 * 
	 * The east panel uses the GroupLayout layout manager
	 * in order to create a custom alignment of the buttons,
	 * labels and text fields.
	 * 
	 * Access modifier set to private so this method
	 * can only be called from within the class.
	 */
	private void createEastPanel() {
		eastPanel 						= new JPanel();		
		eastPanel.setMinimumSize(getMinimumSize());
		
		TitledBorder 	border 			= new TitledBorder("PLACEHOLDER FOR USER CONTROLS");					// Creates a border with a title that informs the user of the functionality of the panel.
		border.setTitleJustification(TitledBorder.CENTER);
		eastPanel.setBorder(BorderFactory.createCompoundBorder(new BevelBorder(BevelBorder.RAISED), border));	// Gives the JPanel a BevelBorder and puts the TitledBorder within the BevelBorder.
		
		GroupLayout 	group 			= new GroupLayout(eastPanel);											// Creates an new instance of a group layout in order to structure the objects in the JPanel.
		eastPanel.setLayout(group);
		group.setAutoCreateGaps(true);
		group.setAutoCreateContainerGaps(true);
		
		JLabel 			eastNumPrompt	= new JLabel("Enter customer number:");
		eastPanel.add(eastNumPrompt);
		
		customerID 						= new JTextField(5); 
		eastPanel.add(customerID);
		
		JLabel cName 					= new JLabel("Name:");
		eastPanel.add(cName);
		
		customerName 					= new JTextField(5);
		eastPanel.add(customerName);
		
		JLabel chooseItem				= new JLabel("Choose item:");
		eastPanel.add(chooseItem);
		
		itemsList 						= new JComboBox<String>(getItems(items));
		eastPanel.add(itemsList);
		
		submitItemSelection 			= new JButton("Submit");
		eastPanel.add(submitItemSelection);
		
		JLabel speedLabel				= new JLabel("Current time to process one order (seconds):");
		eastPanel.add(speedLabel);
		
		speed 							= new JLabel(String.valueOf(this.getExecutionSpeed()));
		eastPanel.add(speed);
		
		JLabel enterNewSpeed			= new JLabel("Enter new processing speed (seconds):");
		eastPanel.add(enterNewSpeed);
		
		newSpeed						= new JTextField(5);
		eastPanel.add(newSpeed);
		
		submitNewSpeed					= new JButton("Submit");
		eastPanel.add(submitNewSpeed);
		
		JLabel addServerLabel			= new JLabel("Click button to add a server:");
		eastPanel.add(addServerLabel);
		
		addServer						= new JButton("Add Server");
		eastPanel.add(addServer);
		
		JLabel removeServerLabel		= new JLabel("Click button to remove a server:");
		eastPanel.add(addServerLabel);
		
		removeServer						= new JButton("Remove Server");
		eastPanel.add(removeServer);
		
		group.setHorizontalGroup(
				group.createSequentialGroup()
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(10)					// Padding added to create some space between each object.
						.addComponent(eastNumPrompt)
						.addComponent(cName)
						.addComponent(chooseItem)
						.addComponent(speedLabel)
						.addComponent(enterNewSpeed)
						.addComponent(addServerLabel)
						.addComponent(removeServerLabel))
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(10)
						.addComponent(customerID)
						.addComponent(customerName)
						.addComponent(itemsList)
						.addComponent(speed)
						.addComponent(newSpeed))
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(10)
						.addComponent(submitItemSelection)
						.addComponent(submitNewSpeed)
						.addComponent(addServer)
						.addComponent(removeServer)
						)
					
		);
		group.setVerticalGroup(
				group.createSequentialGroup()
					.addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE).addGap(50)
							.addComponent(eastNumPrompt)
							.addComponent(customerID))
					.addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE).addGap(50)
							.addComponent(cName)
							.addComponent(customerName))
					.addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE).addGap(50)
							.addComponent(chooseItem)
							.addComponent(itemsList)
							.addComponent(submitItemSelection))
					.addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE).addGap(50)
							.addComponent(speedLabel)
							.addComponent(speed))
					.addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE).addGap(50)
							.addComponent(enterNewSpeed)
							.addComponent(newSpeed)
							.addComponent(submitNewSpeed))
					.addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE).addGap(50)
							.addComponent(addServerLabel)
							.addComponent(addServer))
					.addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE).addGap(50)
							.addComponent(removeServerLabel)
							.addComponent(removeServer))
		);
				
		this.add(eastPanel, BorderLayout.EAST);
//		this.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e) {
//				Manager.outputReport(orders, customers, items, "Cafe_Report.txt");
//			}
//		});
		
	}
	
	/*
	 * Returns a String array of all items on the
	 * menu at the cafe.
	 * 
	 * Used by the east panel to populate the drop
	 * down for item choices.
	 */
	private String [] getItems(ItemList items) {
		
		String [] itemList = new String [items.getMapSize()];
		int count = 0;
		for(Entry<String, Items> i : items.entrySet()) {
			itemList[count] = i.getValue().getID();
			count += 1;
		}
		
		return itemList;
	}
	
	/*
	 * Creates a JPanel and adds it to the centre section
	 * of the GUI instance.
	 * 
	 * The JPanel contains a table of Order information.
	 * 
	 * The JTable is instantiated using a custom table model
	 * CafeTableModel, that extends the AbstractTableModel
	 * class, rather than using the DefaultTableModel..
	 * 
	 * Access modifier set to private so this method
	 * can only be called from within the class.
	 * 
	 */
	private void createCentrePanel() {
		centrePanel 					= new JPanel();
		centrePanel.setLayout(new BoxLayout(centrePanel,BoxLayout.Y_AXIS));
		
		TitledBorder 	border 			= new TitledBorder("Table of orders"); 					// Creates a border with a title that informs the user of the functionality of the panel.
															
		
		border.setTitleJustification(TitledBorder.CENTER);
		centrePanel.setBorder(BorderFactory.createCompoundBorder(new BevelBorder(BevelBorder.RAISED), border));	// Gives the JPanel a BevelBorder and puts the TitledBorder within the BevelBorder.
		
		String [] 		columnHeaders 	= {"ID", "Name", "No. of Items", "Price"};										// String Array containing the column headers for the JTable.

		tableModel 						= new CafeTableModel(orders, customers, items, columnHeaders);			// Instantiates a new CompTableModel instance with a CompetitorList and column headers.
		sorter 							= new TableRowSorter<CafeTableModel>(tableModel);						// Instantiates a new TableRowSorter for the custom CompTableModel.

		
		table 							= new JTable(tableModel);
		table.setRowSorter(sorter);
		
		JScrollPane 	scrollPane 		= new JScrollPane(table);
		table.setFillsViewportHeight(true);
		centrePanel.add(scrollPane);
		
		this.add(centrePanel, BorderLayout.CENTER);
		
	}
	
	/*
	 * Returns CafeTableModel instance currently
	 * being used by the GUI.
	 * 
	 * Used by other classes to determine how
	 * many orders are left to process.
	 */
	public CafeTableModel getTableModel() {
		return tableModel;
	}
	
	/*
	 * Calls the CafeTableModel method addRow to
	 * inform the table model there is new data
	 * that needs to be inserted into the table.
	 */
	public void addTableRow() {
		tableModel.addRow();
	}
	
	/*
	 * Calls the CafeTableModel method updateTable
	 * to inform the table model the data has been
	 * updated and the table needs to be refreshed.
	 */
	public void updateTable(int i) {
		tableModel.updateOrder(i);
	}
	
	/*
	 * Calls the CafeTableModel method removeOrder
	 * to inform the table model the next order has
	 * gone to a server and can be removed from the
	 * table.
	 */
	public void removeOrder() {
		tableModel.removeRow();
	}
	
	/*
	 * Create South Panel for servers
	 */
	private void createSouthPanel() {
		southPanel 	= new JPanel();
		southPanel.setLayout(new BoxLayout(southPanel,BoxLayout.LINE_AXIS));
		southPanel.add(Box.createHorizontalGlue());
		
		TitledBorder 	border 			= new TitledBorder("Serving Counter"); 					// Creates a border with a title that informs the user of the functionality of the panel.
															
		
		border.setTitleJustification(TitledBorder.CENTER);
		southPanel.setBorder(BorderFactory.createCompoundBorder(new BevelBorder(BevelBorder.RAISED), border));
		
		TitledBorder 	sOneBorder 			= new TitledBorder("Server 1");
		sOneBorder.setTitleJustification(TitledBorder.CENTER);
		serverOne = new JTextPane();
		serverOne.setText("Waiting for order...");
		serverOne.setBorder(BorderFactory.createCompoundBorder(new BevelBorder(BevelBorder.RAISED), sOneBorder));
		StyledDocument sOneText = serverOne.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center,  StyleConstants.ALIGN_CENTER);
		sOneText.setParagraphAttributes(0, sOneText.getLength(), center, false);
		servers.add(serverOne);
		southPanel.add(serverOne);
		
		TitledBorder 	sTwoBorder 			= new TitledBorder("Server 2");
		sTwoBorder.setTitleJustification(TitledBorder.CENTER);
		serverTwo = new JTextPane();
		serverTwo.setText("Waiting for order...");
		serverTwo.setBorder(BorderFactory.createCompoundBorder(new BevelBorder(BevelBorder.RAISED), sTwoBorder));
		StyledDocument sTwoText = serverTwo.getStyledDocument();
		StyleConstants.setAlignment(center,  StyleConstants.ALIGN_CENTER);
		sTwoText.setParagraphAttributes(0, sTwoText.getLength(), center, false);
		servers.add(serverTwo);
		southPanel.add(serverTwo);
		
		this.add(southPanel, BorderLayout.SOUTH);	
		
	}
	
	/*
	 * Updates the JTextPane of the server specified
	 * by the int passed in as a parameter, to show
	 * the String also passed in as a parameter.
	 * 
	 * Searches through the ArrayList<JTextPane> of
	 * servers until it reaches the server at index i,
	 * where it then sets the text of that JTextPane to
	 * be the order String.
	 */
	public void updateSever(int i, String order) {
				
		for(int j = 0; j < servers.size(); j++) {
			if(j == i-1) {
				servers.get(j).setText(order);
			}
		}
		this.revalidate();
		
	}
	
	/*
	 * Creates a new JTextPane for a new server with
	 * the same styling as the other servers, adds
	 * it to the ArrayList<JTextPane> of servers and
	 * adds it to the southPanel.
	 */
	public void addServer() {
		TitledBorder 	newServerBorder 			= new TitledBorder("Server " + (servers.size()+1));
		newServerBorder.setTitleJustification(TitledBorder.CENTER);
		JTextPane newServer = new JTextPane();
		newServer.setText("Waiting for order...");
		newServer.setBorder(BorderFactory.createCompoundBorder(new BevelBorder(BevelBorder.RAISED), newServerBorder));
		StyledDocument sOneText = newServer.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center,  StyleConstants.ALIGN_CENTER);
		sOneText.setParagraphAttributes(0, sOneText.getLength(), center, false);
		servers.add(newServer);
		southPanel.add(newServer);
		this.revalidate();
	}
	
	/*
	 * Removes the server at the last index of the
	 * ArrayList<JTextPane> of servers from the
	 * southPanel.
	 */
	public void removeServer() {
		southPanel.remove(servers.get(servers.size()-1));
	}
	
	/*
	 * Returns an ArrayList<String> containing the
	 * new order information input by the user.
	 */
	public ArrayList<String> getNewOrderInfo(){
		ArrayList<String> newOrder = new ArrayList<String>();
		
		newOrder.add(customerID.getText().trim());
		System.out.println("New ID: " + customerID.getText().trim());
		newOrder.add(itemsList.getSelectedItem().toString());
		newOrder.add(customerName.getText().trim());
		
		return newOrder;
	}
	

	/*
	 * Returns the String input by the user as the
	 * new order processing speed as an int.
	 */
	public int getExecutionSpeed() {
		return executionSpeed;
	}
	
	/*
	 * Sets the variable executionSpeed to be the
	 * new speed input by the user and also updates
	 * the JLabel speed to reflect this.
	 */
	public void updateExecutionSpeed() {
		executionSpeed = Integer.parseInt(newSpeed.getText().trim());
		speed.setText(String.valueOf(executionSpeed));
	}
	
	/*
	 * Shows the user a JOptionPane containing
	 * an error message specified by the String
	 * passed in as a parameter.
	 */
	public void errorMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	

	
	/*
	 * Adds an ActionListener to the submitItemSelection
	 * JButton.
	 */
	public void addNewCustListener(ActionListener al) {
		submitItemSelection.addActionListener(al);
	}
	
	/*
	 * Adds an ActionListener to the addServer
	 * JButton.
	 */
	public void addNewServerListener(ActionListener al) {
		addServer.addActionListener(al);
	}
	
	/*
	 * Adds an ActionListener to the removeServer
	 * JButton.
	 */
	public void addRemoveServerListener(ActionListener al) {
		removeServer.addActionListener(al);
	}
	
	/*
	 * Adds an ActionListener to the submitNewSpeed
	 * JButton.
	 */
	public void addUpdateSpeedListener(ActionListener al) {
		submitNewSpeed.addActionListener(al);
	}
}
