package f21as_coursework;

public class Merchandise extends Items {

	//instance variable
	private String size; //size of merch
	
	//constructor
	public Merchandise (String cat, String id, String des, double cost, String size) {
		super (id, des, cat, cost);
		this.size = size;
	}
	
	
	//return size of merch
	public String getSize() {
		return size;
	}
}
