package f21as_coursework;

import java.util.ArrayList;

/*
 * The CustomerList class is store a list of all Customer
 * objects who have placed an order.
 * 
 * Created as part of F21AS Advanced Software Engineering.
 * 
 * Author: Elliot Whitehouse (ew2000)
 */


public class CustomerList {
	
	private ArrayList<Customer> customers;
	
	
	/*
	 * When the constructor is called, a new ArrayList
	 * is instantiated.
	 */
	public CustomerList() {
		
		this.customers = new ArrayList<Customer>();
	}
	
	
	/*
	 * addCustomer method allows a user to add a Customer to the
	 * ArrayList provided a Customer object with the same ID
	 * does not already exist in the ArrayList.
	 * 
	 * Takes in as a parameter a Customer object to be added.
	 */
	public void addCustomer(Customer customer) {
		if(this.customers.size() == 0) {
			this.customers.add(customer);
		}
		else {
			if(!this.getCustomerIDs().contains(customer.getCustID())) {
				this.customers.add(customer);
			}
		}
	}
	
	
	/*
	 * Removes the Customer object form the ArrayList as
	 * specified by the int passed in as a parameter.
	 */
	public void removeCustomer(int i) {
		this.customers.remove(i);
	}
	
	
	/*
	 * getCustomer returns the Customer object with an ID
	 * matching that of the String passed in as a parameter.
	 */
	public Customer getCustomer(String id) {
		for(Customer c : this.customers) {
			if(c.getCustID().equals(id)) {
				return c;
			}
		}
		
		return null;
	}
	
	
	/*
	 * Returns the Customer object at the index specified
	 * by the int passed in as a parameter.
	 */
	public Customer getCustomerByIndex(int i) {
		return this.customers.get(i);
	}
	
	
	/*
	 * Returns an ArrayList of all customer IDs in the
	 * the CustomerList object. This is used to check if
	 * a particular ID already exists when adding a new
	 * Customer to the ArrayList.
	 */
	public ArrayList<String> getCustomerIDs() {
		
		ArrayList<String> customerIDs = new ArrayList<String>();
		
		for(Customer c : this.customers) {
			customerIDs.add(c.getCustID());
		}
		
		return customerIDs;
	}
	
	
	/*
	 * Returns the number of items in the ArrayList as an
	 * int.
	 */
	public int getCustListSize() {
		return this.customers.size();
	}

}
