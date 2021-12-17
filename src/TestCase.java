import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class TestCase {
	double price = 7.45;
	Item it1 = new Item("132145", "Apple", "a type of fruit", "./image/apple", 7.45, 20);
	Item it2 = new Item("234253", "Pepsi", "a type of druik", "./image/Pepsi", 16.45, 3);
	Item it3 = new Item("234asf", "Banana", "a type of yellow color fruit", "./image/Banana", 7.45, 2);
	Item it4 = new Item("53453z", "Water", "water", "./image/water", 1, 0);
	Address invalidAdrAddress = new Address("XXX", "34578", "123");
	Address validAdrAddress = new Address("New York", "34578", "123");
	
	DiscountCode dsCode1 = new DiscountCode(LocalDateTime.of(2022, 03, 28, 14, 33, 48, 123456789), 0.8, it1);
	DiscountCode dsCode2 = new DiscountCode(LocalDateTime.of(2022, 03, 28, 14, 33, 48, 123456789), 0.8, it2);
	Cart cart1 = new Cart();
	@Test
	void discountRateChangetest() {
		assertEquals(price,it1.getPrice());
		it1.updateDiscountRate(0.1);
		assertEquals(price*0.1,it1.getPrice());
	}
	
	@Test
	void AddItemGetContenttest() {
		cart1.addItem(it1);
		CartContentWrapper cWrapper = cart1.getContent(invalidAdrAddress);
		assertEquals(null, cWrapper);
		cWrapper = cart1.getContent(validAdrAddress);
		assertEquals(price*(1+0.4), cWrapper.totalCost);
		assertEquals(it1, cWrapper.items.get(0));
	}
	
	@Test
	void ApplyDiscounttest() {
		cart1.addItem(it1);
		boolean invalidDC = cart1.applyDiscount(dsCode2);
		assertEquals(false, invalidDC);
		boolean validDC = cart1.applyDiscount(dsCode1);
		assertEquals(true, validDC);
	}
	
	@Test
	void UpdateQuantitytest() {
		cart1.addItem(it1);
		cart1.addItem(it2);
		cart1.addItem(it3);
		ArrayList<Item> itemList = new ArrayList<Item>();
		ArrayList<Integer> quantityList = new ArrayList<Integer>();
		itemList.add(it1);
		itemList.add(it2);
		quantityList.add(0);
		quantityList.add(3);
		cart1.updateQuantity(itemList, quantityList);
		assertEquals(79.52, cart1.getContent(validAdrAddress).totalCost);
		
	}
	
	
}

