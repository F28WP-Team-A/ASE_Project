//package f21as_coursework;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.Test;
//
//class IncorrectItemExceptionTest {
//
///**
// * need to change put an invalid value (e.g change the price to a string) in item.csv file to trigger the exception
// */
//	@Test
//	void testIncorrectItemException() {
//		ItemMap items = new ItemMap();
//		assertThrows(IncorrectItemException.class, () -> {Manager.populateItemMap(items, "items.csv");});
//	}
//
//}
