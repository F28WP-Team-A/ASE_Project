//package f21as_coursework;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.Test;
//
//class IncorrectOrderExceptionTest {
//
//	/**
//	 * need to change put an invalid value (e.g change the year to a string) in item.csv file to trigger the exception
//	 */
//	@Test
//	void testIncorrectOrderException() {
//		OrderSet orders = new OrderSet();
//		CustomerList customers = new CustomerList();
//		assertThrows(IncorrectItemException.class, () -> {Manager.populateOrderSet(orders, customers, "existingCustomerOrders.csv");});
//	}
//
//}
