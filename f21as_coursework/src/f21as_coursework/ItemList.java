package f21as_coursework;

import java.util.HashMap;
import f21as_coursework.Items;

public class ItemList {

	public static void main(String[] args) {
		//create HashMap object called items 
		//where key = identifier (String) and value = menu item (String)
		HashMap<String, Items> items = new HashMap<String, Items>();
		
		//print items in map
		System.out.println(items);
		System.out.println();
		
		//print size of map
		System.out.println("Size of map is " + items.size());
		
		//loop through HashMap to print keys (identifiers)
		for (String iden : items.keySet()) {
			System.out.println(iden);
		}
		
		///print keys and  values
		for (String h : items.keySet()) {
			System.out.println("key: " + h + items.get(h));
		}


		/*
		 * finishing off HashMap 
		 */
		
	}
}
