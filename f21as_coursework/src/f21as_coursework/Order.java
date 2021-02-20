package f21as_coursework;
import java.time.LocalDateTime;

public class Order {
    
    // instance variables
    public String        id;            // order ID
    public LocalDateTime timeStamp;     // order time stamp
    public String        itemDetails;   // details of items
    public double        price;         // price of items

// constructor
public Order (String id, LocalDateTime timeStamp, String itemDetails, double price) {
        
    // id must be provided
    if(id.trim().length() ==0) {
        throw new IllegalStateException("Must provide id");
    }
    this.id = id.trim();
    this.timeStamp = timeStamp;
    this.itemDetails = itemDetails;
    this.price = price;
}

// return order id
public String getId() {
    return "Order ID: " + id;
}

// return order time stamp
public LocalDateTime getTimeStamp() {
    return "Order time stamp: " + timeStamp;
}

// return item details
public String getItemDetails() {
    return itemDetails;
}

// return price of items
public double getPrice() {
    return price;
}
}
