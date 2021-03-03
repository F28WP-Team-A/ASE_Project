package f21as_coursework;

/*
 * Class written by Matthieu Pesci, but uploaded
 * by Elliot Whitehouse due to IT issues.
 */

public class Customer {
	
    private Name custName;
    private String custID;

    public Customer(Name custName, String custID) {

        this.custName=custName;
        this.custID=custID;
    }
    
    public String getCustID() {
    	return this.custID;
    }
    public String getCustName() {
    	return custName.getFullName();
    }
    public String getDetails(){
        return "Customer Name: " + custName.getFullName() + "\tCustomer ID: " + custID;
        
    }

}
