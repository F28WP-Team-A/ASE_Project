package f21as_coursework;
import java.util.ArrayList;

//Order objects stored as an ArrayList
public class OrderList {
	// storage for details
    private ArrayList <Order> orderList;

    // initialise order list
    public OrderList() {
        orderList = new ArrayList<Order>();
    }

    // look up order id and return order details
    public Order findById(String id) {
        for (Order o : orderList) {
            if (o.getId().equals(id)) {
                return o;
            }
        }
        return null;
    }
    
    // add new details to list
    public void addDetails(Order details) {
    	orderList.add(details);
    }

    // remove order object by ID
    public void removeDetails(String id) {
        int index = findIndex(id);
        if (index != -1) {
            orderList.remove(index);
        }
    }

    // look up order ID and return index
    private int findIndex(String id) {
        int size = orderList.size();
        for (int i = 0; i < size; i++) {
            Order o = orderList.get(i);
            if (o.getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
    
    // return number of orders
    public int getNumberOfOrders() {
        return orderList.size();
    }

    // return all order details
    public String listDetails() {
        StringBuffer allOrders = new StringBuffer();
        for (Order details : orderList) {
            allOrders.append(details);
            allOrders.append('\n');
        }
        return allOrders.toString();
    }
}
