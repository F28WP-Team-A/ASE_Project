package f21as_coursework;

public class Drink extends Items {
	
	//instance variables
	private String size; 		//size of drink - small, regular, large
	private String type;		//type of drink - hot, iced, juice
	private String flavour;		//flavour of drink - vanilla, caramel
	
	//constructor
	public Drink (String cat, String id, String des, double cost, String size, String type, String flav) {
		super (id, des, cat, cost);
		this.size = size;
		this.type = type;
		flavour = flav;
	}
	
	//return size of drink
	public String getSize() {
		return size;
	}
	
	//return type of drink
	public String getType() {
		return type;
	}
	
	//return flavour of drink
	public String getFlavour() {
		return flavour;
	}
}