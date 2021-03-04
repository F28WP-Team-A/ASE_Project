package f21as_coursework;

import javax.swing.table.AbstractTableModel;

public class CafeTableModel extends AbstractTableModel {
	
	private OrderList orders;
	private CustomerList customers;
	private String [] columnHeaders;
	private Object [] [] rowData;
	private ItemList items;
	
	/*
	 * Constructor creates an instance of the
	 * CompTableModel using a CompetitorList
	 * and String Array of column headers passed
	 * in as parameters.
	 * 
	 * The relevant data from the CompetitorList
	 * is then parsed into a 2 dimensional Object
	 * Array which becomes the formatted data used
	 * in the table.
	 */
	public CafeTableModel(OrderList orders , CustomerList customers,ItemList items, String [] headers) {
		this.orders = orders;
		this.customers = customers;
		this.items = items;
		rowData = new Object[orders.getNumberOfOrders()+25][4];
		System.out.println(orders.getNumberOfOrders());
		System.out.println(rowData.length);
		getRowData();
		System.out.println(rowData.length);
		columnHeaders = headers;
	}
	
	/*
	 * Sets the value of the 2 dimensional Object
	 * Array rowData to be the data from the 
	 * CompetitorList to be used in the JTable.
	 * 
	 * Iterates through the CompetitorList and
	 * populates each row of the 2D Object Array
	 * with data from the COmpetitors in the
	 * CompetitorList.
	 */
	private void getRowData() {
		for(int i = 0; i< orders.getNumberOfOrders(); i++) {
			String placeholder = orders.getOrderItem(i+1).getItemDetails().replaceAll("\\[", "").replaceAll("\\]",""); 
			rowData[i][0]	= orders.getOrderItem(i+1).getId();
			rowData[i][1]	= customers.getCustomer(orders.getOrderItem(i+1).getId()).getCustName();
			rowData[i][2]	= items.get(placeholder).getDescription();
			rowData[i][3]	= orders.getOrderItem(i+1).getPrice();
//			rowData[i][4]	= compList.getCompetitor(i).getScoreArray();
//			rowData[i][5]	= compList.getCompetitor(i).getOverallScore();
		}
		
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
	
	
//	@Override
	/*
	 * Returns the Class of the data in the
	 * column at the index specified by the 
	 * int passed in as a parameter.
	 * 
	 * Overrides the implementation of the
	 * getColumnClass method in the
	 * AbstractTableModel superclass which implements
	 * the method from the TableModel interface.
	 */
//	public Class<?> getColumnClass(int col) {
//		switch (col){
//			case 0:
//				return rowData[0][0].getClass();
//			case 1:
//				return rowData[0][1].getClass();
//			case 2:
//				return rowData[0][2].getClass();
//			case 3:
//				return rowData[0][3].getClass();
//			case 4:
//				return rowData[0][4].getClass();
//			case 5:
//				return rowData[0][5].getClass();
//			default:
//				return String.class;
//		}
//	}

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
