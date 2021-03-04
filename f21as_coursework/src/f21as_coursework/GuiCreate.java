package f21as_coursework;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
public class GuiCreate{
	
	private OrderList orders;
	private ItemList items;
	private CustomerList customers;
	
	public GuiCreate(OrderList orders, ItemList items, CustomerList customers) throws IncorrectOrderException {
		
		this.orders = orders;
		this.customers = customers;
		this.items = items;

	}
	public void GuiCreator(){
	EventQueue.invokeLater(new Runnable() {
		@Override

		public void run() {
			 try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
			// creates a panel where the buttons are stored
			// still need to add event listeners to change stuff but for the moment this is fine
			JPanel panel = new JPanel();
			JButton order = new JButton("Add new order");
			// adds an action listener for a button press have not yet implemented the proper interaction
			order.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new CreateOrders(orders, items, customers);

				}
			});
			// adds event listener to button press still havent added the correct interaction
			JButton change = new JButton("Change existing order");
			change.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "test 123");

				}
			});
			panel.add(order);
			panel.add(change);
			// creates the JFrame using the CustomerDataTable and the panel
			JFrame frame = new JFrame("Customer data");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(new CustomerDataTable());
			frame.getContentPane().add(BorderLayout.SOUTH,panel);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		};
	});
};


public class CustomerDataTable extends JPanel {
				
		String[] columns = new String [] {
				"Order" , "Order Number", "Name" ,"Price £","Item"
		};
		
		
	//	ArrayList<ArrayList<String>> data = Manager.indexOrder();
		
		
		
		DefaultTableModel model = new DefaultTableModel(columns,25);
		
		
		public CustomerDataTable() {

		JTable table = new JTable(model);
		// uses a gridbag layout to place the table in the middle of the frame
		setBorder(new EmptyBorder(50,50,50,50));
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(new JScrollPane(table),gbc);
		};
	};
};
