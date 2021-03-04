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
	JLabel 							compNumOutput, nameOuput, scoresOutput,
									overallScoreOutput, newOverallScore, showNewScore;
	JTextArea						menu;
	JButton 						southNumSearch, eastNumSearch, submitItemSelection,
									filterSubmit, close;
	ArrayList<JCheckBox> 			sportBoxes, levelBoxes;
	JCheckBox 						diving, surfing, beginner, intermediate, advanced;
	TableRowSorter<CafeTableModel> 	sorter;
	JComboBox<String> 				sortSelection, itemsList, sizeSelection;
	
	
	/*
	 * The CompetitionGUI constructor takes as a parameter
	 * a CompetitorList that will provide the data used
	 * by the GUI.
	 * 
	 * When the constructor is called, the CompetitorList
	 * is assigned to an instance variable, ArrayList<JCheckBoX>
	 * for the filter checkboxes are initiated, the 5 panels of
	 * the GUI are created and the GUI is launched and so can be
	 * seen by the user. 
	 */
	public CafeGUI(OrderList orders, CustomerList customers, ItemList items) {
		
		this.orders = orders;
		this.customers = customers;
		this.items = items;
		this.currentCustomerNum = orders.getNumberOfOrders();
		sportBoxes = new ArrayList<JCheckBox>();
		levelBoxes = new ArrayList<JCheckBox>();
//		getSportBoxes();
//		getLevelBoxes();
		// ****REMEMBER TO CHANGE****
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
	 * the eastPanel allows the user to search for a
	 * Competitor using their Competitor number and
	 * enter new scores for that Competitor. Upon
	 * submission of the new scores, a new overall score
	 * is calculated for that Competitor and this is
	 * then displayed to the user.
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
		
		TitledBorder 	border 			= new TitledBorder("Search for a competitor and alter their score");	// Creates a border with a title that informs the user of the functionality of the panel.
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
		
//		eastNumSearch 					= new JButton ("Search");
//		eastPanel.add(eastNumSearch);
//		eastNumSearch.addActionListener(this);
		
		JLabel 			compName 		= new JLabel("Name:");
		eastPanel.add(compName);
		
		nameOuput = new JLabel("");
		eastPanel.add(nameOuput);
		
		customerName 		= new JTextField(5);
		eastPanel.add(customerName);
		
		scoresOutput 					= new JLabel("");
		eastPanel.add(scoresOutput);
		
//		JLabel 			overallScore 	= new JLabel("Overall Score:");
//		eastPanel.add(overallScore);
		
		overallScoreOutput 				= new JLabel("");
		eastPanel.add(overallScoreOutput);
		
		JLabel 			newScoresPrompt	= new JLabel("Choose item:");
		eastPanel.add(newScoresPrompt);
		
		itemsList 						= new JComboBox<String>(getItems(items));
		eastPanel.add(itemsList);
		
		submitItemSelection 				= new JButton("Submit");
		eastPanel.add(submitItemSelection);
		submitItemSelection.addActionListener(this);
		
//		JLabel 			enterNewScores 	= new JLabel("New overall score:");
//		eastPanel.add(enterNewScores);
//		
//		newOverallScore 				= new JLabel("New overall score:");
//		eastPanel.add(newOverallScore);
//		
//		showNewScore 					= new JLabel("");
//		eastPanel.add(showNewScore);
		
		group.setHorizontalGroup(
				group.createSequentialGroup()
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(10)					// Padding added to create some space between each object.
						.addComponent(eastNumPrompt)
						.addComponent(compName)
						.addComponent(nameOuput)
						.addComponent(newScoresPrompt))
//						.addComponent(newOverallScore))
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(10)
						.addComponent(customerID)
						.addComponent(customerName)
						.addComponent(scoresOutput)
						.addComponent(itemsList))
				.addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(10)
//						.addComponent(eastNumSearch)
//						.addComponent(overallScore)
						.addComponent(overallScoreOutput)
						.addComponent(submitItemSelection)
//						.addComponent(showNewScore)
						)
					
		);
		group.setVerticalGroup(
				group.createSequentialGroup()
					.addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE).addGap(50)
							.addComponent(eastNumPrompt)
							.addComponent(customerID))
//							.addComponent(eastNumSearch))
					.addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE).addGap(50)
							.addComponent(compName)
							.addComponent(customerName))
//							.addComponent(overallScore))
					.addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE).addGap(50)
							.addComponent(nameOuput)
							.addComponent(scoresOutput)
							.addComponent(overallScoreOutput))
					.addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE).addGap(50)
							.addComponent(newScoresPrompt)
							.addComponent(itemsList)
							.addComponent(submitItemSelection))
//					.addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE).addGap(50)
//							.addComponent(newOverallScore))
//							.addComponent(showNewScore).addGap(10))
		);
				
		this.add(eastPanel, BorderLayout.EAST);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("test12");
			}
		});
		
	}
	
	/*
	 * Creates a JPanel and adds it to the centre section
	 * of the GUI instance.
	 * 
	 * The JPanel contains a table of Competitor information
	 * including their sport, competitor number, name, level,
	 * scores and overall score.
	 * 
	 * The JTable is instantiated using a custom table model
	 * CompTableModel, that extends the AbstractTableModel
	 * class, rather than using the DefaultTableModel. This
	 * allows for more control over how the data can be sorted
	 * and filtered.
	 * 
	 * A separate instance of TableRowSorter is also instantiated
	 * to also allow more control over the sorting of the data
	 * using sort keys.
	 * 
	 * The user can select a sorting order from the JComboBox
	 * drop down placed above the table. Once the user has
	 * selected their preferred sorting order, the sort keys
	 * to implement that particular order are determined in the
	 * action performed method.
	 * 
	 * Access modifier set to private so this method
	 * can only be called from within the class.
	 * 
	 */
	private void createCentrePanel() {
		JPanel 			centrePanel 	= new JPanel();
		centrePanel.setLayout(new BoxLayout(centrePanel,BoxLayout.Y_AXIS));
		
		TitledBorder 	border 			= new TitledBorder("Table of competitors that can be" 					// Creates a border with a title that informs the user of the functionality of the panel.
															+ " sorted via the drop down or filtered "
															+ "using the check boxes");
		
		border.setTitleJustification(TitledBorder.CENTER);
		centrePanel.setBorder(BorderFactory.createCompoundBorder(new BevelBorder(BevelBorder.RAISED), border));	// Gives the JPanel a BevelBorder and puts the TitledBorder within the BevelBorder.
		
//		String [] 		choices 		= {"Competitor Number", "Alphabetically", "Overall Score"};				// String Array containing the sorting options available to the user.
//		sortSelection				 	= new JComboBox<String>(choices);
//		sortSelection.addActionListener(this);
//		centrePanel.add(sortSelection);
		
		String [] 		columnHeaders 	= {"ID", "Name", "Item", "Price"};										// String Array containing the column headers for the JTable.
		tableModel 						= new CafeTableModel(orders, customers, items,columnHeaders);							// Instantiates a new CompTableModel instance with a CompetitorList and column headers.
		sorter 							= new TableRowSorter<CafeTableModel>(tableModel);						// Instantiates a new TableRowSorter for the custom CompTableModel.
		
		table 			= new JTable(tableModel);												// Creates a new JTable that uses the custom table model CompTableModel just created.
		table.setRowSorter(sorter);																				// Sets the row sorter of the table to be the new TableRowSorter just created.
		
		JScrollPane 	scrollPane 		= new JScrollPane(table);												// JScrollPane allows more data to be added to the table without effecting the JFrame size.
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
	
	
	// ADD NEXT TIME
//	private String [] getSizes(ItemList items) {
//		
//		String [] sizesList = new String [items.getMapSize()];
//		int count = 0;
//		for(Entry<String, Items> i : items.entrySet()) {
//			if(i instanceof Drink) {
//				Drink temp = (Drink) i;
//				sizesList[count] = temp.getSize();
//				count += 1;
//			}
//			
//		}
//		
//		return sizesList;
//	}
	
	
	
	
	/*
	 * Creates a JPanel and adds it to the west section
	 * of the GUI instance.
	 * 
	 * The west JPanel is used to filter the table of
	 * Competitor information in the centre panel. The
	 * user is given a choice of JCheckboxes in order to
	 * filer the data by sport (Competitor subclass) or 
	 * by level.
	 * 
	 * Once filtered the data can then be sorted using
	 * options in the drop down above the table to allow 
	 * for even more custom view of the data. For example
	 * a user could filter the data by sport and level and
	 * then order by overall score to view in descending
	 * order the scores of all the Surfers in the "Standard"
	 * category.
	 * 
	 * To return to the original view of the data the user
	 * can click "View" while no checkboxes are checked and
	 * this will bring back all the data.
	 * 
	 * Access modifier set to private so this method
	 * can only be called from within the class.
	 */
	private void createWestPanel() {
		JPanel westPanel 	= new JPanel();
		
		westPanel.setBorder(BorderFactory.createCompoundBorder(new BevelBorder(BevelBorder.RAISED),				// Gives the JPanel a BevelBorder and an empty border to give the panel some padding
															   new EmptyBorder(10,10,10,10)));
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));										// Uses BoxLayout layout manager to create a vertical alignment of labels, checkboxes and buttons
		
		menu  = new JTextArea(items.getMenu());
		westPanel.add(menu);
		JScrollPane scroll = new JScrollPane (menu, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		westPanel.add(scroll);
		menu.setSize(1,1);
		menu.setEditable(false);
		scroll.setSize(1,1);
		
//		JLabel filterLabel 	= new JLabel("Filter by:");
//		filterLabel.setBorder(new EmptyBorder(20,0,10,0));														// Gives the JLabel and empty border to help with positioning
//		westPanel.add(filterLabel);
//		
//		JLabel sport 		= new JLabel("Sport:");
//		sport.setBorder(new EmptyBorder(0,0,5,5));
//		westPanel.add(sport);
//		
//		for(JCheckBox j : sportBoxes) {
//			j.addActionListener(this);
//			westPanel.add(j);
//		}
//		
//		JLabel level 		= new JLabel("Level:");
//		level.setBorder(new EmptyBorder(10,0,5,0));
//		westPanel.add(level);
//		
//		for(JCheckBox j : levelBoxes) {
//			j.addActionListener(this);
//			westPanel.add(j);
//		}
//		
//		filterSubmit 		= new JButton("View");
//		filterSubmit.addActionListener(this);
//		westPanel.add(filterSubmit);
		
		this.add(westPanel, BorderLayout.WEST);
	}
	
	
	/*
	 * The getSportBoxes method populates an ArrayList<JCheckBox>
	 * with checkboxes for each unique sport in the CompetitorList
	 * passed in as a parameter.
	 * 
	 * The method iterates through the CompetitorList and creates
	 * a new JCheckBox with the name of the current Competitor
	 * object's sport variable, if that sport have not been encountered
	 * before.
	 * 
	 * Each time a new sport is encountered, it is added to the
	 * ArrayList<String> "dupeCheck" and for each iteration "dupeCheck"
	 * is checked to see if it contains that sport already.
	 * 
	 * Access modifier set to private so this method
	 * can only be called from within the class.
	 */
//	private void getSportBoxes(){
//		ArrayList<String> dupeCheck = new ArrayList<String>();
//		
//		for(int i = 0; i < compList.getLength(); i++) {
//			
//			if(!dupeCheck.contains(compList.getCompetitor(i).getSport())) {
//				sportBoxes.add(new JCheckBox(compList.getCompetitor(i).getSport()));
//				dupeCheck.add(compList.getCompetitor(i).getSport());
//			}
//		}	
//	}
	
	
	/*
	 * The getLevelBoxes method populates an ArrayList<JCheckBox>
	 * with checkboxes for each unique level in the CompetitorList
	 * passed in as a parameter.
	 * 
	 * The method iterates through the CompetitorList and creates
	 * a new JCheckBox with the name of the current Competitor
	 * object's level variable, if that level does not exist
	 * as a String in the ArrayList<String> "dupeCheck".
	 * 
	 * Each time a new level is encountered, it is added to the
	 * ArrayList<String> "dupeCheck" and for each iteration "dupeCheck"
	 * is checked to see if it contains that level already.
	 * 
	 * Access modifier set to private so this method
	 * can only be called from within the class.
	 */
//	private void getLevelBoxes(){
//		ArrayList<String> dupeCheck = new ArrayList<String>();
//		
//		for(int i = 0; i < compList.getLength(); i++) {
//			
//			if(!dupeCheck.contains(compList.getCompetitor(i).getCompetitorLevel())) {
//				levelBoxes.add(new JCheckBox(compList.getCompetitor(i).getCompetitorLevel()));
//				dupeCheck.add(compList.getCompetitor(i).getCompetitorLevel());
//			}
//		}	
//	}
	

	/*
	 * The actionPerformed method provides an implementation
	 * of the actionPerformed class from the ActionListener
	 * interface and proved the event handling capability
	 * of the CompetitionGUI class.
	 */
	public void actionPerformed(ActionEvent e) {
		
		/*
		 * If the "Search" JButton is clicked in the 
		 * south panel, this condition is triggered.
		 * 
		 * Outputs a JOptionPane with a Competitor's
		 * short or full details.
		 */
		
//		if(e.getSource() == southNumSearch) {
//			try {
//			String dChoice 			= detailsChoice.getSelectedItem().toString();								// The user's choice of short or full details is parsed to a String.
//			String compNumString 	= southNumInput.getText().trim();											// The competitor number the user enter is parsed to a String.
//			
//			if(compNumString.length() > 0) {
//				if(dChoice.equals("Short Details")) {
//					int    compNum      = Integer.parseInt(compNumString);
//					JOptionPane.showMessageDialog(null, compList.isValidCompetitor(compNum));					// Creates a JOptionPane with the relevant Competitor's short details.
//				}
//				else if(dChoice.equals("Full Details")) {
//					int    compNum      = Integer.parseInt(compNumString);
//					JOptionPane.showMessageDialog(null, compList.getCompetitorObj(compNum).getFullDetails());	// Creates a JOptionPane with the relevant Competitor's full details.
//				}
//				
//			}
//			else {
//				JOptionPane.showInternalMessageDialog(null, "Please enter a competitor number");				// If the user did not enter a Competitor number a prompt is output.
//			}
//			}
//			catch(NullPointerException n) {
//				JOptionPane.showMessageDialog(null, "Invalid competitor number");
//			}
//			catch(NumberFormatException f) {
//				JOptionPane.showMessageDialog(null, "Competitor number must be a number");						
//			}
//		}
		
		
		
		/*
		 * If the "Search" JButton is clicked in the 
		 * east panel, this condition is triggered.
		 * 
		 * Outputs the Competitor's name and scores to
		 * blank JLabels under the search.
		 */
//		if(e.getSource() == eastNumSearch) {
//			try {
//				String userInput = eastNumInput.getText().trim();												// The competitor number the user enter is parsed to a String.
//				
//				if(userInput.length() > 0) {
//					currentCompNum      = Integer.parseInt(userInput);											// Instance variable used to change the correct Competitors's score after.
//					nameOuput.setText(compList.getCompetitorObj(currentCompNum).getCompetitorName().getFullName());
//					scoresOutput.setText(compList.getCompetitorObj(currentCompNum).getScoreArray());
//					overallScoreOutput.setText(compList.getCompetitorObj(currentCompNum).getFormatScore());
//				}
//				else { 
//					JOptionPane.showInternalMessageDialog(null, "Please enter a competitor number");			// If the user did not enter a Competitor number a prompt is output.
//				}
//			}
//			catch(NullPointerException n) {
//				JOptionPane.showMessageDialog(null, "Invalid competitor number");								// Outputs a JOptionPane if the getCompetitorObj method returns null.
//			}
//			catch(NumberFormatException f) {
//				JOptionPane.showMessageDialog(null, "Competitor number must be a number");						// Outputs a JOptionPane if the user enters a non numeric value.
//			}
//		}
		
		
		/*
		 * If the "Submit" JButton is clicked in the 
		 * east panel, this condition is triggered.
		 * 
		 * Submits the new scores entered by the user,
		 * calculates the new overall score of the 
		 * correct competitor and outputs the result
		 * to a blank JLabel.
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
				JOptionPane.showMessageDialog(null, "Invalid competitor number");								// Outputs a JOptionPane if the getCompetitoObj method returns null.
				n.printStackTrace();
			}
			catch(IndexOutOfBoundsException i) {
				JOptionPane.showMessageDialog(null, "Invalid number of scores");								// Outputs a JOptionPane if the user enters an incorrect number of scores for that Competitor.
			}
			catch(NumberFormatException f) {
				JOptionPane.showMessageDialog(null, "Score must be made up of numbers only");					// Outputs a JOptionPane if the user enters 5 values that are not numbers.
			}
			catch(IncorrectOrderException ioe) {
				ioe.printStackTrace();
			}

		}
		//REFERENCE
//		if(e.getSource() == submitItemSelection) {
//			try {
//				String 		inputString 	= customerID.getText().trim();
//				String 	[] 	scoreString 	= inputString.split(",");											// Score Array parsed to String Array and subsequently copied to an int Array.
//				int 	[] 	scores 			= new int [0];
//				
//				if(compList.getCompetitor(currentCompNum).getSport().equals("Diving") ||						// Score Array will be of length 5 if the sport is Diving or Surfing.
//					compList.getCompetitor(currentCompNum).getSport().equals("Surfing")) {
//					scores 			= new int [5];
//					System.arraycopy(scoreString, 0, scoreString, 0, 5);
//					
//					for(int i = 0; i < scoreString.length; i++) {
//						scores[i] 	= Integer.parseInt(scoreString[i].trim());
//					}
//				}
//				else if (compList.getCompetitor(currentCompNum).getSport().equals("Kayaking")) {				// Score Array will be of length 3 if the sport is Kayaking.
//					scores 			= new int [3];
//					System.arraycopy(scoreString, 0, scoreString, 0, 3);
//					
//					for(int i = 0; i < scoreString.length; i++) {
//						scores[i]	= Integer.parseInt(scoreString[i].trim());
//					}
//				}
//				
//				
//				compList.getCompetitorObj(currentCompNum).setScoreArray(scores);								// Sets the Competitor's new score Array.
//				compList.getCompetitorObj(currentCompNum).setOverallScore();									// Calls the Competitor class method to calculate the Competitor's new overall score.
//				showNewScore.setText(compList.getCompetitorObj(currentCompNum).getFormatScore());	
//			}
//			catch(NullPointerException n) {
//				JOptionPane.showMessageDialog(null, "Invalid competitor number");								// Outputs a JOptionPane if the getCompetitoObj method returns null.
//			}
//			catch(IndexOutOfBoundsException i) {
//				JOptionPane.showMessageDialog(null, "Invalid number of scores");								// Outputs a JOptionPane if the user enters an incorrect number of scores for that Competitor.
//			}
//			catch(NumberFormatException f) {
//				JOptionPane.showMessageDialog(null, "Score must be made up of numbers only");					// Outputs a JOptionPane if the user enters 5 values that are not numbers.
//			}
//
//		}
		
		
		/*
		 * If the "View" JButton is clicked in the 
		 * west panel, this condition is triggered.
		 * 
		 * Applies the filters chosen by the user
		 * to the table of Competitor information.
		 * 
		 * Creates an ArrayList<RowFilter> of 
		 * RowFilters that is added to if a checkbox
		 * is checked. This list of filters is then
		 * applied when the user clicks "View".
		 */
//		if(e.getSource() == filterSubmit) {
//			ArrayList<RowFilter<CompTableModel, Object>> filters = new ArrayList<RowFilter<CompTableModel, Object>>();		// ArrayList containing multiple filters adapted from answer to StackOverflow question:
//																															// https://stackoverflow.com/questions/29106565/filtering-a-jtable-in-two-columns
//			try {
//				for(JCheckBox j : sportBoxes) {
//					if(j.isSelected()) {
//						RowFilter<CompTableModel, Object> filter = RowFilter.regexFilter(j.getText(),0);		// Creates a new RowFilter for the "Sport" checkbox the user checked.
//						filters.add(filter);																	// Adds that RowFilter to the list of RowFilters.
//					}
//				}
//				for(JCheckBox j : levelBoxes) {
//					if(j.isSelected()) {
//						RowFilter<CompTableModel, Object> filter = RowFilter.regexFilter(j.getText(),3);		// Creates a new RowFilter for the "Level" checkbox the user checked.
//						filters.add(filter);																	// Adds that RowFilter to the list of RowFilters.
//					}
//				}
//			}
//			catch(java.util.regex.PatternSyntaxException p) {
//				return;
//			}
//			RowFilter<CompTableModel, Object> appliedFilters = RowFilter.andFilter(filters);
//			sorter.setRowFilter(appliedFilters);
//		}
		
		
		/*
		 * If the an option from the JComboBox in the 
		 * centre panel is selected, this condition is
		 * triggered.
		 * 
		 * Sorts the table of Competitor information
		 * according to the option selected by the user.
		 * 
		 * Creates a new SortKey that corresponds to the 
		 * correct column to be sorted and specifies the
		 * order of the sorting. sort() is then called on
		 * the TableRowSorter to sort the table by the 
		 * specified sort key.
		 * 
		 */
//		if(e.getSource() == sortSelection) {
//			String 							sortChoice 	= sortSelection.getSelectedItem().toString();				// Parses the users choice of sort order to a String
//			ArrayList<RowSorter.SortKey> 	keys 	= new ArrayList<>();											// Creates an empty ArrayList to contain the SortKeys
//			
//			if(sortChoice.equals("Alphabetically")) {															
//				keys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
//				
//			}
//			
//			if(sortChoice.equals("Overall Score")) {
//				keys.add(new RowSorter.SortKey(5, SortOrder.DESCENDING));
//				
//			}
//			sorter.setSortKeys(keys);
//			sorter.sort();
//		}
		
		
		/*
		 * If the "Close" JButton is clicked in the 
		 * south panel, this condition is triggered.
		 * 
		 * Outputs the competition report to a file
		 * and closes the CompetitonGUI. Before the 
		 * GUI is closed, a JOptionPane is displayed 
		 * to the user notifying them of the name of
		 * the file the report has been saved to.
		 * 
		 * Calls the Manager class method outputReport
		 * that creates the file based on the CompetitorList
		 * passed in as a parameter and the file name
		 * String also passed in as a parameter.
		 */
//		if(e.getSource() == close) {
//			JOptionPane.showMessageDialog(null, "Competition report output to file named:\nCompetition_Report.txt");
//			Manager.outputReport(compList, "Competition_Report.txt");
//			this.dispose();
//		}
	}
}

