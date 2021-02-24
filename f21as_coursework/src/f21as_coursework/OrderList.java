package f21as_coursework;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;

//Order objects stored as a TreeSet
public class OrderList {
	// storage for details
	Set<Order> orders = new TreeSet<Order>();

    public OrderList(String filename) throws FileNotFoundException {
    }

    // look up order id and return order details
    public Order findById(String id) {
        for (Order o : orders) {
            if (o.getId().equals(id)) {
                return o;
            }
        }
        return null;
    }
    
    // add new details to TreeSet
    public boolean addDetails(Order details) {
    	return orders.add(details);
    }
    
    // return number of orders
    public int getNumberOfOrders() {
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
    
}
