package f21as_coursework;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableRowSorter;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CafeGUI extends JFrame implements ActionListener {
	
	private OrderList 				orders;
	private CustomerList			customers;
	private ItemList				items;
	private int 					currentCustomerNum;
	private CafeTableModel 			tableModel;
	JTable							table;
	JTextField 						customerID, eastNumInput, customerName;
	JTextArea						menu;
	JButton 						eastNumSearch, submitItemSelection,
									close;
	TableRowSorter<CafeTableModel> 	sorter;
	JComboBox<String> 				itemsList, sizeSelection;
	
	
	/*
	 * The CafeGUI constructor takes as a parameter
	 * an OrderList, CustomerList and ItemList that will provide
	 * the data used by the GUI.
	 */
	public CafeGUI(OrderList orders, CustomerList customers, ItemList items) {
		
		this.orders = orders;
		this.customers = customers;
		this.items = items;
		this.currentCustomerNum = orders.getNumberOfOrders();
		setDefaultCloseOperation(CafeGUI.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout(1,1));
		createEastPanel();
		createCentrePanel();
		createWestPanel();
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
		JPanel 			eastPanel 		= new JPanel();		
		
		TitledBorder 	border 			= new TitledBorder("Add a new order");									// Creates a border with a title that informs the user of the functionality of the panel.
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
		
		JLabel 			cName 		= new JLabel("Name:");
		eastPanel.add(cName);
		
		customerName 		= new JTextField(5);
		eastPanel.add(customerName);
		
		JLabel 			chooseItem	= new JLabel("Choose item:");
		eastPanel.add(chooseItem);
		
		itemsList 						= new JComboBox<String>(getItems(items));
		eastPanel.add(itemsList);
		
		submitItemSelection 				= new JButton("Submit");
		eastPanel.add(submitItemSelection);
		submitItemSelection.addActionListener(this);
		
		group.setHorizontalGroup(
				group.createSequentialGroup()
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(10)					// Padding added to create some space between each object.
						.addComponent(eastNumPrompt)
						.addComponent(cName)
						.addComponent(chooseItem))
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(10)
						.addComponent(customerID)
						.addComponent(customerName)
						.addComponent(itemsList))
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(10)
						.addComponent(submitItemSelection)
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
		);
				
		this.add(eastPanel, BorderLayout.EAST);
		
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
		JPanel 			centrePanel 	= new JPanel();
		centrePanel.setLayout(new BoxLayout(centrePanel,BoxLayout.Y_AXIS));
		
		TitledBorder 	border 			= new TitledBorder("Table of orders"); 					// Creates a border with a title that informs the user of the functionality of the panel.
															
		
		border.setTitleJustification(TitledBorder.CENTER);
		centrePanel.setBorder(BorderFactory.createCompoundBorder(new BevelBorder(BevelBorder.RAISED), border));	// Gives the JPanel a BevelBorder and puts the TitledBorder within the BevelBorder.
		
		
		String [] 		columnHeaders 	= {"ID", "Name", "Item", "Price"};										// String Array containing the column headers for the JTable.
		tableModel 						= new CafeTableModel(orders, customers, columnHeaders);
		sorter 							= new TableRowSorter<CafeTableModel>(tableModel);
		
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
		
	
	/*
	 * Creates a JPanel and adds it to the west section
	 * of the GUI instance.
	 * 
	 * The west JPanel is used to display the cafe's menu
	 * to be used as a reference when submitting new orders.
	 * 
	 * Access modifier set to private so this method
	 * can only be called from within the class.
	 */
	private void createWestPanel() {
		JPanel westPanel 	= new JPanel();
		
		westPanel.setBorder(BorderFactory.createCompoundBorder(new BevelBorder(BevelBorder.RAISED),				// Gives the JPanel a BevelBorder and an empty border to give the panel some padding
															   new EmptyBorder(10,10,10,10)));
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
		
		menu  = new JTextArea(items.getMenu());
		westPanel.add(menu);
		JScrollPane scroll = new JScrollPane (menu, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		westPanel.add(scroll);
		menu.setSize(1,1);
		
		scroll.setSize(1,1);
				
		this.add(westPanel, BorderLayout.WEST);
	}

	/*
	 * The actionPerformed method provides an implementation
	 * of the actionPerformed class from the ActionListener
	 * interface and proved the event handling capability
	 * of the CafeGUI class.
	 */
	public void actionPerformed(ActionEvent e) {
				
		/*
		 * If the "Submit" JButton is clicked in the 
		 * east panel, this condition is triggered.
		 * 
		 * Submits the the id, customer name (if entered)
		 * and item choice in order to create a new order.
		 */
		if(e.getSource() == submitItemSelection) {
			try {
				currentCustomerNum += 1;
				String 		inputString 	= customerID.getText().trim();
				String		custName    = "";
				String 		itemChoice 	= itemsList.getSelectedItem().toString();
				BigDecimal price = new BigDecimal(0);
				
				for(int i = 1; i< orders.getNumberOfOrders(); i++) {
					if(orders.getOrderItem(i).getId().equals(inputString)) {
						custName += customers.getCustomer(orders.getOrderItem(i).getId()).getCustName();
						break;
					}
					else {
						custName    = customerName.getText().trim();
						String []  name = custName.split(" ");
						
						customers.addCustomer(new Customer( new Name(name[0], name[1]), String.valueOf(7)));
					}	
				}
				
				for(Entry<String, Items> i : items.entrySet()) {
					if(i.getValue().getID().equals(itemChoice)) {
						price =  price.add(i.getValue().getCost());
					}
				}
				
				System.out.println(currentCustomerNum + " "+ inputString + " "+ LocalDateTime.now() + " "+ itemChoice + " "+ price);
				
				orders.addDetails(new Order(currentCustomerNum, inputString, LocalDateTime.now(), itemChoice, price));
				
				
				
				for(ArrayList<String> s : Manager.indexOrders(orders, customers, items)) {
					System.out.println(s.toString());
				}
				
				tableModel.addRow(orders);
				
				
				
				}
			catch(NullPointerException n) {
				JOptionPane.showMessageDialog(null, "Invalid customer number");
				n.printStackTrace();
			}
			catch(IndexOutOfBoundsException i) {
				JOptionPane.showMessageDialog(null, "Invalid number of scores");
			}
			catch(NumberFormatException f) {
				JOptionPane.showMessageDialog(null, "Score must be made up of numbers only");
			}
			catch(IncorrectOrderException ioe) {
				ioe.printStackTrace();
			}

		}
	}
}

