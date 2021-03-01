package f21as_coursework;

import java.math.BigDecimal;

public class Merchandise extends Items {

	//instance variable
	private String size; //size of merch
	
	//constructor
	public Merchandise (String cat,String id, String des, BigDecimal cost, String size) {
		super (cat, id, des, cost);							// Moved cat to front of constructor to get correct variable order
		this.size = size;
	}
	
	
	//return size of merch
	public String getSize() {
		return size;
	}
}
