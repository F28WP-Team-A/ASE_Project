package f21as_coursework;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Order implements Comparable<Order> {
    // instance variables
    public String id;       	    			// customer ID
    public LocalDateTime timeStamp; 			// order time stamp
    public ArrayList<String> itemDetails;      	// details of items
    public BigDecimal price;        			// price of items

	// constructor
	public Order (String id, LocalDateTime timeStamp, String itemDetails, BigDecimal price) {
	        
	    // id must be provided
	    if(id.trim().length() ==0) {
	        throw new IllegalStateException("Must provide id");
	    }
	    this.id = id.trim();
	    this.timeStamp = timeStamp;
	    this.itemDetails = new ArrayList<String> ();
	    this.itemDetails.add(itemDetails);
	    this.price = price;
	}
	
	// return customer id
	public String getId() {
	    return id;
	}
	
	// return order time stamp
	public LocalDateTime getTimeStamp() {
	    return timeStamp;
	}
	
	// add item to item details
	public void addItem(String item) {
		itemDetails.add(item);
	}
	
	// return item details
	public String getItemDetails() {
	    return itemDetails.toString();
	}
	
	// increment price of items
	public void addPrice(BigDecimal itemPrice) {
		price = price.add(itemPrice);
	}
	
	// return price of items
	public BigDecimal getPrice() {
	    return price;
	}
	
	// compare content equality between id and another object
	public boolean equals(Object other) {
	    if(other instanceof Order) {
	        Order otherOrder = (Order) other;
	        return id.equals(otherOrder.getId()); 
	    }
	            else {
	                return false;
	            }
	        
	}
	
	@Override
	public int compareTo(Order otherOrder) {
		
		if(this.timeStamp.isEqual( otherOrder.timeStamp)) {
			return 0;
		}
		else if(this.timeStamp.isAfter(otherOrder.timeStamp)) {
			return 1;
		}
		else {
			return -1;
		}
	}
	
	// return string with all details
	public String toString() {
	    return String.format("%5s", id);
	} 
}
