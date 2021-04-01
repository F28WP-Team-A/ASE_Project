package f21as_coursework;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

//import f21as_coursework.Items;

public class ItemMap {

		//create HashMap object called items 
		//where key = identifier (String) and value = menu item (String)
		HashMap<String, Items> items = new HashMap<String, Items>();
		
		//return Set view of mappings contained in map
		/*
		 * need to check
		 */
		public Set<Map.Entry<String, Items>> entrySet(){
			return items.entrySet();
			
		}
		
		
		//returns size of map - number of items in the map
		public int getMapSize() {
			return items.size();
		}
		
		//returns true if map contains no key/value mappings
		public boolean getEmpty() {
			return items.isEmpty();
		}
				
		//returns value for specified key
		public Items get(Object key) {
			return items.get(key);
		}
		
		public boolean contains(Object key) {
			return items.containsKey(key);
		}
		//remove mapping for specified key
		public Items remove(Object key) {
			return items.remove(key);
		}
		
		/*
		 * ADDING FOR TEST
		 * 
		 */
		
		public void addItem(String key, Items value) throws IncorrectItemException {
			// check if item cost is null
			if (value.getCost() == null) {
				throw new IncorrectItemException("Item price is NULL");
			}
			// check if ID is null
			if (value.getID().trim().length() == 0) {
				throw new IncorrectItemException ("Item ID is NULL");
			}
			// check if item identifier is correct 
			if (!value.getID().substring(0,4).equals("FOOD") &&
				!value.getID().substring(0,5).equals("DRINK") &&
				!value.getID().substring(0,5).equals("MERCH")) {
				throw new IncorrectItemException("Item ID identifier is incorrect" + value.getID());
			}
			// check item has ID number and not just identifier
			try {
				int i = Integer.parseInt(value.getID().substring(5,6));
			}
			catch (NumberFormatException e) {
				throw new IncorrectItemException("Incorrect item number identifier");
			}
			
			items.put(key, value);
		}
		
		/*
		 * getMenu method returns a String formatted as a table,
		 * containing all of the items in the HashMap ItemList that
		 * are on the menu.
		 * 
		 * This is used in generating the report when a user closes
		 * the application.
		 */
		public String getMenu() {

			String menu = String.format("%-20s", "Item Number")
							+ String.format("%-30s", "Description") 
							+ String.format("%-15s", "Type/Size")
							+ String.format("%-15s", "Drink Type")
							+ String.format("%-15s", "Flavour")
							+ String.format("%-19s", "Price") 
							+ "\n";

			for(Entry<String, Items> i : items.entrySet()) {
				menu 	+= String.format("%-20s", i.getKey()); 


				if(i.getValue() instanceof Drink) {
					Drink drink = (Drink) i.getValue();
					menu 	+= String.format("%-30s", drink.getDescription())
							+ String.format("%-15s", drink.getSize())
							+ String.format("%-15s", drink.getType())
							+ String.format("%-15s", drink.getFlavour())
							+ String.format("%-19s", drink.getCost());  
				}
				if(i.getValue() instanceof Merchandise) {
					Merchandise merch = (Merchandise) i.getValue();
					menu 	+= String.format("%-30s", merch.getDescription()) 
							+ String.format("%-45s", merch.getSize())
							+ String.format("%-19s", merch.getCost());
				}
				if(i.getValue() instanceof Food) {
					Food food = (Food) i.getValue();
					menu 	+= String.format("%-30s", food.getDescription())
							+ String.format("%-45s", food.getType())
							+ String.format("%-19s", food.getCost());
				}

				menu += "\n";
			}

			return menu;
		}
		
		
		/*
		 * Returns price of item entered in parameter as
		 * a BigDecimal.
		 */
		public BigDecimal getPrice(String itemChoice) {
			
			BigDecimal price = new BigDecimal(0);
			
			for(Entry<String, Items> i : items.entrySet()) {
				if(i.getValue().getID().equals(itemChoice)) {
					price =  price.add(i.getValue().getCost());
				}
			}
			
			return price;
		}
		
	}
