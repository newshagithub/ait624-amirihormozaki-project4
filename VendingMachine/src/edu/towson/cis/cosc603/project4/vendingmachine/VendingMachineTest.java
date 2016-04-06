/**
 * 
 */
package edu.towson.cis.cosc603.project4.vendingmachine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author newsha
 *
 */
public class VendingMachineTest {
	
	//declare items
	VendingMachine myVendingMachine;
	VendingMachineItem gummyBear, chips, water, chitoz;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		myVendingMachine = new VendingMachine();
		gummyBear = new VendingMachineItem("gummy bear", 2.00);
		chips = new VendingMachineItem("chips", 1.25);
		water = new VendingMachineItem("water", 0.75);
		chitoz = new VendingMachineItem("chitoz", 1.50);
		
		//add items to vending machine
		myVendingMachine.addItem(gummyBear, "A");
		myVendingMachine.addItem(chips, "B");
		myVendingMachine.addItem(water, "C");
		myVendingMachine.addItem(chitoz, "D");		
	}

	/**
	 * Set all items to null
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		
		myVendingMachine = null;
		gummyBear = null;
		chips = null;
		water = null;
		chitoz = null;
	}
	
	/**
	 * Tests add item
	 */
	@Test
	public void testAddItem() {
		
		assertEquals(chips, myVendingMachine.getItem("B"));
	}
	
	/**
	 * Tests to see if item is added with wrong code 
	 */
	@Test(expected = VendingMachineException.class)
	public void testWrongCodeAddItem() {
		
		myVendingMachine.addItem(chips, "N");
		assertEquals(chips, myVendingMachine.getItem("N"));
	}
	
	/**
	 * Tests if the slot is already filled
	 */
	@Test(expected = VendingMachineException.class)
	public void testAlreadyFilledSlotAddItem() {
		
		myVendingMachine.addItem(chips, "B");
		assertEquals(chips, myVendingMachine.getItem("B"));
	}
	
	/**
	 * Tests remove item
	 */
	@Test
	public void testRemoveItem() {
		
		myVendingMachine.removeItem("A");
		myVendingMachine.removeItem("B");
		
		assertNull(myVendingMachine.getItem("A"));
		assertNull(myVendingMachine.getItem("B"));
	}
	
	/**
	 * Tests if the slot code is wrong
	 */
	@Test(expected = VendingMachineException.class)
	public void testWrongSlotSlotRemoveItem() {
		
		myVendingMachine.removeItem("O");
	}
	
	/**
	 * Tests if the slot is empty
	 */
	@Test(expected = VendingMachineException.class)
	public void testEmptySlotRemoveItem() {
		
		myVendingMachine.removeItem("B");
		assertNotSame(chips, myVendingMachine.removeItem("B")); 
	}
	
	/**
	 * Tests insert money
	 */
	@Test
	public void testInsertMoney() {
		
		myVendingMachine.insertMoney(8.00);
		assertEquals(8.00, myVendingMachine.getBalance(), 0); 
	}
	
	/**
	 * Tests if the balance is previous balance + amount
	 */
	@Test
	public void testAddBalanceInsertMoney() {
		
		myVendingMachine.balance = 0.5;
		myVendingMachine.insertMoney(8.00);
		assertEquals(8.50, myVendingMachine.getBalance(), 0); 
	}
	
	/**
	 * Tests exception for amount < 0
	 */
	@Test(expected = VendingMachineException.class)
	public void testBelowZeroBalanceInsertMoney() {
		
		myVendingMachine.insertMoney(-5.00);
	}

	/**
	 * Tests to see if the initial balance is 0
	 */
	@Test
	public void testInitialBalanceGetBalance() {
		
		assertEquals(0, myVendingMachine.getBalance(), 0);
	}
	
	/**
	 * Tests if wrong code can be purchased
	 */
	@Test(expected = VendingMachineException.class)
	public void testWrongCodeMakePurchase() {
		
		assertFalse(myVendingMachine.makePurchase("Z"));
		
	}
	
	/**
	 * Tests to see if purchase cannot make when enough money is not put
	 */
	@Test
	public void testLessBalanceMakePurchase() {
		
		myVendingMachine.balance = 1.00;
		assertFalse(myVendingMachine.makePurchase("A")); // A price is > 1.00
		
	}
	
	/**
	 * Tests to see if purchase can make when enough money is put
	 */
	@Test
	public void testEnoughBalanceMakePurchase() {
		
		myVendingMachine.balance = 1.00;
		assertTrue(myVendingMachine.makePurchase("C")); // C price is < 1.00
		
	}
	
	/**
	 * Tests to see if the amount of the item is subtracted from the balance
	 */
	@Test
	public void testAmountSubstractedMakePurchase() {
		
		myVendingMachine.insertMoney(4.5);
		myVendingMachine.makePurchase("A");
		myVendingMachine.makePurchase("B");
		myVendingMachine.makePurchase("C");
		assertEquals(0.50, myVendingMachine.balance, 0);
		
	}
	
	/**
	 * Tests to see if purchase cannot make when its for empty slot
	 */
	@Test
	public void testEmpltySlotMakePurchase() {
		
		myVendingMachine.removeItem("B");
		assertFalse(myVendingMachine.makePurchase("B"));
		
	}
	
	/**
	 * Tests to see if return change sets the balance to 0
	 */
	@Test
	public void testReturnChange() {
		
		myVendingMachine.balance = 1.00;
		myVendingMachine.returnChange();
		assertEquals(0.00, myVendingMachine.balance, 0);
		
	}
	
}
