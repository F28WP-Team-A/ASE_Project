package f21as_coursework;
import java.math.BigDecimal;

public class Food extends Items{

	//instance variables
	private String type;	//type of food: main, snack
	private String side;	//side that comes with item
	
	//constructor
	public Food(String cat, String id, String des, BigDecimal cost, String type, String side) {
		super (id, des, cat, cost);
		this.type = type;
		this.side = side;
	}
	
	//return type of food
	public String getType() {
		return type;
	}
	
	//return side 
	public String getSide() {
		return side;
	}
}
