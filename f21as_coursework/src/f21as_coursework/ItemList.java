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
			if (value.getCost() == null) {
				throw new IncorrectItemException("Item price is NULL");
			}
			
			if (value.getID().trim().length() == 0) {
				throw new IncorrectItemException ("Item ID is NULL");
			}
			items.put(key, value);
		}
		
	}
