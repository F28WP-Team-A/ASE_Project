package f21as_coursework;

import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;

//Order objects stored as a TreeSet
public class OrderList {
	// storage for details
	static Set<Order> orders = new TreeSet<Order>();

    public OrderList() {
    }

    // look up orderItemNum and return order details
    public Order getOrderItem(int orderItemNum) {
    	for(Order o : orders) {
    		if(o.getOrderItemNum() == orderItemNum) {
    			return o;
    		}
    	}
    	
    	return null;
    }
    
    // look up order id and return order details
    public static Order findById(String id) {
        for (Order o : orders) {
            if (o.getId().equals(id)) {
                return o;
            }
        }
        return null;
    }
    
 // add new details to TreeSet
    public void addDetails(Order details) throws IncorrectOrderException {
    	// check order ID is not null
    	if (details.getId() == null) {
    		throw new IncorrectOrderException("Order ID is NULL");
    	}
    	// check order time stamp is not null
    	if (details.getTimeStamp() == null) {
    		throw new IncorrectOrderException("Order timestamp is NULL");
    	}
    	orders.add(details);
    	}
	
    
    // return number of orders
    public static int getNumberOfOrders() {
        return orders.size();
    }

    // return all order details
    public String listDetails() {
        StringBuffer allOrders = new StringBuffer();
        for (Order details : orders) {
            allOrders.append(details);
            allOrders.append('\n');
        }
        return allOrders.toString();
    }
    
    // return report
    public String getReport() {
    	return null;
    }
    
    // return total order sales
    public BigDecimal getTotalSales() {
    	BigDecimal totalSales = new BigDecimal("0");
    	for (Order o : orders) {
    		totalSales = totalSales.add(o.getPrice());
    	}
    	return totalSales;
    }
    
    // Boolean match for id
    public static boolean existingID(String inputId) {
    	for(Order o: orders) {
    		if(o.id.equals(inputId)) {
    			return true;
    		}
    	}
    	
    	return false;
    }
    
}
