package f21as_coursework;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.MathContext;

class ManagerTest {

	@Test
	public void testDiscount() throws IncorrectItemException, IncorrectOrderException {
		ItemMap items = new ItemMap();
		OrderSet orders = new OrderSet();
		CustomerList customers = new CustomerList();
		Manager.populateItemMap(items, "items.csv");
		Manager.populateOrderSet(orders, customers, "existingCustomerOrders.csv");
		
		//test for the discount method (4 paths)
		//test for the discount with 1 merch (or more)
		BigDecimal price = new BigDecimal(0);
		BigDecimal expected1 = new BigDecimal(10.98);
		String error1 = "Fail for merch>=1";	
		price = Manager.getTotalPrice(orders, String.valueOf(1));//Order number 1 (John Smith)
		assertEquals(expected1.round(new MathContext(4)), price, error1);
		
		
		//test for the discount with 2 foods (or more) and 2 drinks (or more)
		BigDecimal expected2 = new BigDecimal(9.88);
		String error2 = "Fail for food >=2 and drink >=2";
		price = Manager.getTotalPrice(orders, String.valueOf(3));//Order number 3 (Evan Zigomalas)
		assertEquals(expected2.round(new MathContext(4)), price, error2);
		
		//test for no discount
		BigDecimal expected3 = new BigDecimal(4.400);
		String error3 = "Fail for no discount";
		price = Manager.getTotalPrice(orders, String.valueOf(4));//Order number 4 (France Andrade)
		assertEquals(expected3.round(new MathContext(2)), price, error3);
		
		//test for the discount with 3 foods(or more)
		BigDecimal expected4 = new BigDecimal(11.44);
		String error4 = "Fail for food >=3";
		price = Manager.getTotalPrice(orders, String.valueOf(5)); //Order number 5 (Ulysses McWalters)
		assertEquals(expected4.round(new MathContext(4)), price, error4);
	}
	
	@Test
	public void testException() {
		
	}

}
