package f21as_coursework;

import java.util.ArrayList;

public class CustomerList {
	
	private ArrayList<Customer> customers;
	
	public CustomerList() {
		
		this.customers = new ArrayList<Customer>();
	}
	
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
	
	public Customer getCustomer(String id) {
		for(Customer c : this.customers) {
			if(c.getCustID().equals(id)) {
				return c;
			}
		}
		
		return null;
	}
	
	public Customer getCust(int i) {
		return this.customers.get(i);
	}
	
	public ArrayList<String> getCustomerIDs() {
		
		ArrayList<String> customerIDs = new ArrayList<String>();
		
		for(Customer c : this.customers) {
			customerIDs.add(c.getCustID());
		}
		
		return customerIDs;
	}
	
	public int getCustListSize() {
		return this.customers.size();
	}

}
