package f21as_coursework;

public class Items {

	//instance variables
		private String category;		//category of item
		private String identifier;		//unique ID for item
		private String description;		//description of item
		private double cost;			//cost of item
		
		//constructor
		public Items (String cat, String id, String des, double cost) {
			identifier = id.trim();
			description = des;
			category = cat;
			this.cost = cost;
		}
		
		
		//////// hash code //////////
		
		public int compareTo(Items other) {
			return identifier.compareTo(other.identifier);
		}
		
		public boolean equals (Object other) {
			if (other instanceof Items) {
				Items otherItem = (Items) other;
				if (identifier.equals(otherItem.identifier))
					return true;
			}
			return false;
		}
		
		//HashSets
		public int hashCode() {
			return identifier.hashCode();
		}
		
		
		//return item ID
		public String getID() {
			return identifier;
		}
		
		//return description of item
		public String getDescription() {
			return description;
		}
		
		//return item category
		public String getCategory() {
			return category;
		}
		
		//return cost of item
		public double getCost() {
			return cost;
		}
		
	}