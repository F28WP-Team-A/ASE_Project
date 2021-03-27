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
	private int 					currentCustomerNum;
	private CafeTableModel 			tableModel;
	JPanel							eastPanel, centrePanel, southPanel;
	JLabel							countdown;
	JTable							table;
	JTextField 						customerID, eastNumInput, customerName, newSpeed;
	JTextArea						menu;
	JTextPane						serverOne, serverTwo;
	JButton 						eastNumSearch, submitItemSelection, submitNewSpeed,
									addServer;
	TableRowSorter<CafeTableModel> 	sorter;
	JComboBox<String> 				itemsList, sizeSelection;
	
	
	/*
	 * The CafeGUI constructor takes as a parameter
	 * an OrderList, CustomerList and ItemList that will provide
	 * the data used by the GUI.
	 */
	public CafeGUIView(OrderList orders, CustomerList customers, ItemList items) {
		
		this.orders = orders;
		this.customers = customers;
		this.items = items;
		this.currentCustomerNum = orders.getNumberOfOrders();
		setDefaultCloseOperation(CafeGUIView.DO_NOTHING_ON_CLOSE);
		this.setLayout(new BorderLayout(1,1));
		createEastPanel();
		createCentrePanel();
		createSouthPanel();
		pack();
		setLocation(450,150);
		setTitle("Cafe GUI");
		setVisible(true);

		
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
		eastPanel 		= new JPanel();		
		
		TitledBorder 	border 			= new TitledBorder("PLACEHOLDER FOR USER CONTROLS");					// Creates a border with a title that informs the user of the functionality of the panel.
		border.setTitleJustification(TitledBorder.CENTER);
		eastPanel.setBorder(BorderFactory.createCompoundBorder(new BevelBorder(BevelBorder.RAISED), border));	// Gives the JPanel a BevelBorder and puts the TitledBorder within the BevelBorder.
		
		GroupLayout 	group 			= new GroupLayout(eastPanel);											// Creates an new instance of a group layout in order to structure the objects in the JPanel.
		eastPanel.setLayout(group);
		group.setAutoCreateGaps(true);
		group.setAutoCreateContainerGaps(true);
		
		JLabel 			eastNumPrompt	= new JLabel("Enter customer number:");
		eastPanel.add(eastNumPrompt);
		
		customerID 					= new JTextField(5); 
		eastPanel.add(customerID);
		
		JLabel cName 						= new JLabel("Name:");
		eastPanel.add(cName);
		
		customerName 		= new JTextField(5);
		eastPanel.add(customerName);
		
		JLabel 			chooseItem	= new JLabel("Choose item:");
		eastPanel.add(chooseItem);
		
		itemsList 						= new JComboBox<String>(getItems(items));
		eastPanel.add(itemsList);
		
		submitItemSelection 				= new JButton("Submit");
		eastPanel.add(submitItemSelection);
		
		JLabel timeRemaining				= new JLabel("Time Remaining:");
		eastPanel.add(timeRemaining);
		
		countdown							= new JLabel("00:00");
		eastPanel.add(countdown);
		
		JLabel enterNewSpeed				= new JLabel("Enter new processing speed (seconds):");
		eastPanel.add(enterNewSpeed);
		
		newSpeed							= new JTextField(5);
		eastPanel.add(newSpeed);
		
		submitNewSpeed						= new JButton("Submit");
		eastPanel.add(submitNewSpeed);
		
		JLabel addServerLabel				= new JLabel("Click button to add a server:");
		eastPanel.add(addServerLabel);
		
		addServer							= new JButton("Add Server");
		eastPanel.add(addServer);
		
		group.setHorizontalGroup(
				group.createSequentialGroup()
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(10)					// Padding added to create some space between each object.
						.addComponent(eastNumPrompt)
						.addComponent(cName)
						.addComponent(chooseItem)
						.addComponent(timeRemaining)
						.addComponent(enterNewSpeed)
						.addComponent(addServerLabel))
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(10)
						.addComponent(customerID)
						.addComponent(customerName)
						.addComponent(itemsList)
						.addComponent(countdown)
						.addComponent(newSpeed))
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(10)
						.addComponent(submitItemSelection)
						.addComponent(submitNewSpeed)
						.addComponent(addServer)
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
							.addComponent(timeRemaining)
							.addComponent(countdown))
					.addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE).addGap(50)
							.addComponent(enterNewSpeed)
							.addComponent(newSpeed)
							.addComponent(submitNewSpeed))
					.addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE).addGap(50)
							.addComponent(addServerLabel)
							.addComponent(addServer))
		);
				
		this.add(eastPanel, BorderLayout.EAST);
//		this.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e) {
//				Manager.outputReport(orders, customers, items, "Cafe_Report.txt");
//			}
//		});
		
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
		centrePanel 	= new JPanel();
		centrePanel.setLayout(new BoxLayout(centrePanel,BoxLayout.Y_AXIS));
		
		TitledBorder 	border 			= new TitledBorder("Table of orders"); 					// Creates a border with a title that informs the user of the functionality of the panel.
															
		
		border.setTitleJustification(TitledBorder.CENTER);
		centrePanel.setBorder(BorderFactory.createCompoundBorder(new BevelBorder(BevelBorder.RAISED), border));	// Gives the JPanel a BevelBorder and puts the TitledBorder within the BevelBorder.
		
		String [] 		columnHeaders 	= {"ID", "Name", "No. of Items", "Price"};										// String Array containing the column headers for the JTable.

		tableModel 						= new CafeTableModel(orders, customers, items, columnHeaders);			// Instantiates a new CompTableModel instance with a CompetitorList and column headers.
		sorter 							= new TableRowSorter<CafeTableModel>(tableModel);						// Instantiates a new TableRowSorter for the custom CompTableModel.

		
		table 			= new JTable(tableModel);
		table.setRowSorter(sorter);
		
		JScrollPane 	scrollPane 		= new JScrollPane(table);
		table.setFillsViewportHeight(true);
		centrePanel.add(scrollPane);
		
		this.add(centrePanel, BorderLayout.CENTER);
		
	}
	
	private String [] getItems(ItemList items) {
		
		String [] itemList = new String [items.getMapSize()];
		int count = 0;
		for(Entry<String, Items> i : items.entrySet()) {
			itemList[count] = i.getValue().getID();
			count += 1;
		}
		
		return itemList;
	}
	
	public CafeTableModel getTableModel() {
		return tableModel;
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
		serverOne.setText("lorem ipsum \nlorem ipsum \nlorem ipsum \n");
		serverOne.setBorder(BorderFactory.createCompoundBorder(new BevelBorder(BevelBorder.RAISED), sOneBorder));
		StyledDocument sOneText = serverOne.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center,  StyleConstants.ALIGN_CENTER);
		sOneText.setParagraphAttributes(0, sOneText.getLength(), center, false);
		southPanel.add(serverOne);
		
		TitledBorder 	sTwoBorder 			= new TitledBorder("Server 2");
		sTwoBorder.setTitleJustification(TitledBorder.CENTER);
		serverTwo = new JTextPane();
		serverTwo.setText("lorem ipsum \nlorem ipsum \nlorem ipsum \n");
		serverTwo.setBorder(BorderFactory.createCompoundBorder(new BevelBorder(BevelBorder.RAISED), sTwoBorder));
		StyledDocument sTwoText = serverTwo.getStyledDocument();
		StyleConstants.setAlignment(center,  StyleConstants.ALIGN_CENTER);
		sTwoText.setParagraphAttributes(0, sTwoText.getLength(), center, false);
		southPanel.add(serverTwo);
		
		this.add(southPanel, BorderLayout.SOUTH);	
		
	}
	
	public String getCustIDInput() {
		return customerID.getText().trim();
	}
	
	public String getItemChoice() {
		return itemsList.getSelectedItem().toString();
	}
	
	public String getCustName() {
		return customerName.getText().trim();
	}
	
	public ArrayList<String> getNewOrderInfo(){
		ArrayList<String> newOrder = new ArrayList<String>();
		
		newOrder.add(customerID.getText().trim());
		newOrder.add(itemsList.getSelectedItem().toString());
		newOrder.add(customerName.getText().trim());
		
		return newOrder;
	}
	
	/*
	 * Updates the text in the JTextPane of server1
	 * with the String input as a parameter.
	 */
	public void updateSever(int i, String order) {
		
		switch(i) {
		
		case 1:
			serverOne.setText(order);
			break;
			
		case 2:
			serverTwo.setText(order);
			break;
		}
		
		
	}
	
	/*
	 * Updates the text of the JLabel showing the
	 * time remaining.
	 */
	public void updateGUITimer(String time) {
		countdown.setText("00:" + time);
	}
	
	/*
	 * Returns the String input by the user as the
	 * new order processing speed as an int.
	 */
	public int getSpeedInput() {
		return Integer.parseInt(newSpeed.getText());
	}
	
	public void errorMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	public void addServer(int num) {
		TitledBorder 	newServerBorder 			= new TitledBorder("Server " + num);
		newServerBorder.setTitleJustification(TitledBorder.CENTER);
		JTextPane newServer = new JTextPane();
		newServer.setText("lorem ipsum \nlorem ipsum \nlorem ipsum \n");
		newServer.setBorder(BorderFactory.createCompoundBorder(new BevelBorder(BevelBorder.RAISED), newServerBorder));
		StyledDocument sOneText = newServer.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center,  StyleConstants.ALIGN_CENTER);
		sOneText.setParagraphAttributes(0, sOneText.getLength(), center, false);
		southPanel.add(newServer);
	}
	
	/*
	 * Add ActionListeners
	 */
	public void addNewCustListener(ActionListener al) {
		submitItemSelection.addActionListener(al);
		System.out.println("Action Listener added");
	}
	
	public void addNewServerListener(ActionListener al) {
		addServer.addActionListener(al);
		System.out.println("Add server action Listener added");
	}
	
	public void addUpdateSpeedListener(ActionListener al) {
		submitNewSpeed.addActionListener(al);
		System.out.println("Action Listener added");
	}
}
