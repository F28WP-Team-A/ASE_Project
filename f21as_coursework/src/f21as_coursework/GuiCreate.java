package f21as_coursework;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
public class GuiCreate{

	public GuiCreate() throws IncorrectOrderException {

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
					new CreateOrders();

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
private class CustomerDataTable extends JPanel {

		String[] columns = new String [] {
				"Order" , "Order Number", "First Name","Last Name","Date","Item","Price £"
		};


		/*
		Object [][] data = new Object[][] {
			{1, 1 , "John" ,"Smith",1,2021,1,20,15,30,15,"FOOD101",2.95},
		};
		*/
		// defines the array of the table and types i.e String int
		// THIS NEEDS TO BE CHANGED DEPENDING UPON ARRAY SIZE otherwise it breaks in half with
		// no easily findable error in the console
		/*
		final Class[] columnCla = new Class[] {
				int.class, int.class, String.class ,String.class, int.class, int.class, int.class, int.class,
				int.class,int.class,int.class,String.class,BigDecimal.class
		};
		//changes the table so that only the table is not editable
		/*
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
					return false;
				};
			@Override
			public Class<?> getColumnClass(int columnIndex){
				return columnCla[columnIndex];
			};
			*/

		AbstractTableModel model = new AbstractTableModel() {

			OrderList orders = new OrderList();

			public int getColumnCount() {
				return columns.length;
			}

			public String getColumnName(int col) {
				return columns[col];
			}

			@Override
			public int getRowCount() {
				// TODO Auto-generated method stub
				return orders.getNumberOfOrders();
			}

			@Override
			public Object getValueAt(int arg0, int arg1) {
				// TODO Auto-generated method stub
				return null;
			}




		};

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
