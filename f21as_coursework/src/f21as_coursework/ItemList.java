package f21as_coursework;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//import f21as_coursework.Items;

public class ItemList {

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
		
	}
