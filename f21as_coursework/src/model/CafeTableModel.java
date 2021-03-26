package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import f21as_coursework.CustomerList;
import f21as_coursework.ItemList;
import f21as_coursework.OrderList;

public class CafeTableModel extends AbstractTableModel {
	
	private OrderList orders;
	private CustomerList customers;
	private String [] columnHeaders;
	private Object [] [] rowData;
	private ItemList items;
	private ArrayList<ArrayList<String>> allOrders;
	
	/*
	 * Constructor creates an instance of the
	 * CafeTableModel using a OrderList
	 * and String Array of column headers passed
	 * in as parameters.
	 * 
	 * The relevant data from the OrderList
	 * is then parsed into a 2 dimensional Object
	 * Array which becomes the formatted data used
	 * in the table.
	 */
	public CafeTableModel(OrderList orders , CustomerList customers,ItemList items, 
							ArrayList<ArrayList<String>> allOrders, 
							String [] headers) {
		this.orders = orders;
		this.customers = customers;
		this.items = items;
		this.allOrders = allOrders;
		rowData = new Object[allOrders.size()][4];
		System.out.println(orders.getNumberOfOrders());
		System.out.println(rowData.length);
		getRowData();
		System.out.println(rowData.length);
		columnHeaders = headers;
	}
	
	/*
	 * Sets the value of the 2 dimensional Object
	 * Array rowData to be the data from the 
	 * OrderList to be used in the JTable.
	 * 
	 * Iterates through the OrderList and
	 * populates each row of the 2D Object Array
	 * with data from the Orders in the
	 * OrderList.
	 */
	private void getRowData() {
		int i = 0;
		for(ArrayList<String> order : allOrders) {
			rowData[i][0]	= order.get(0);
			rowData[i][1]	= order.get(1);
			rowData[i][2]	= getNumItems(order);
			rowData[i][3]	= order.get(2);
			i++;
		}
		
	}
	
	private int getNumItems(ArrayList<String> order) {
		int count = 0;
		for(int i = 3; i < order.size(); i++) {
			count += 1;
		}
		return count;
	}
	
	@Override
	/*
	 * Returns the name of the column specified by 
	 * the int passed in as a parameter, as a String.
	 * 
	 * Provides a custom implementation of
	 * the getColumnName method from the TableModel
	 * interface, overriding the default
	 * implementation provided by the
	 * AbstractTableModel superclass.
	 */
	public String getColumnName(int col) {
		return columnHeaders[col];
	}

	@Override
	/*
	 * Returns the number of rows
	 * in the table as an int.
	 * 
	 * Provides an implementation of
	 * the method from the TableModel
	 * interface.
	 */
	public int getRowCount() {
		return rowData.length;
	}

	@Override
	/*
	 * Returns the number of columns
	 * in the table as an int.
	 * 
	 * Provides an implementation of
	 * the method from the TableModel
	 * interface.
	 */
	public int getColumnCount() {
		return columnHeaders.length;
	}

	@Override
	/*
	 * Returns the value in the cell index
	 * specified by the rowIndex int and 
	 * columnIndex int passed in as parameters,
	 * as an Object.
	 * 
	 * Provides an implementation of
	 * the method from the TableModel
	 * interface.
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		return rowData[rowIndex][columnIndex];
	}
	
	public void addRow(OrderList orders) {
		rowData[orders.getNumberOfOrders()-1][0]	= orders.getOrderItem(orders.getNumberOfOrders()).getId();
		System.out.println("No. of orders: " + orders.getOrderItem(orders.getNumberOfOrders()).getId());
		System.out.println("Stack trace: " + customers.getCustomer(orders.getOrderItem(orders.getNumberOfOrders()).getId()));
		rowData[orders.getNumberOfOrders()-1][1]	= customers.getCustomer(orders.getOrderItem(orders.getNumberOfOrders()).getId()).getCustName();
		rowData[orders.getNumberOfOrders()-1][2]	= orders.getOrderItem(orders.getNumberOfOrders()).getItemDetails();
		rowData[orders.getNumberOfOrders()-1][3]	= orders.getOrderItem(orders.getNumberOfOrders()).getPrice();
		
	    this.fireTableRowsInserted(orders.getNumberOfOrders() - 1, orders.getNumberOfOrders() - 1);
	}


}
