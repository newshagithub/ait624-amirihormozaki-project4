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
public class VendingMachineItemTest {

	//declare item
	VendingMachineItem gummyBear;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		gummyBear = new VendingMachineItem("gummy bear", 2.00);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		
		gummyBear = null;
	}
	
	/** tests VendingMachineItem Constructor for a price < 0 */
	@Test(expected = VendingMachineException.class)
	public void testVendingMachineItemBelowZeroPrice() {
		
		VendingMachineItem belowZeroPrice = new VendingMachineItem("chips", -2.5);
	}
	
	/** tests VendingMachineItem Constructor for empty cases */
	@Test(expected = VendingMachineException.class)
	public void testVendingMachineItemNoName() {
			
		VendingMachineItem noName = new VendingMachineItem(null, 2.5);

	}
	/** tests String getName() */
	@Test
	public void getName() {
		
		assertEquals("gummy bear", gummyBear.getName());

	}
	
	/** tests getPrice() */
	@Test
	public void getPrice() {
		
		assertEquals(2.00, gummyBear.getPrice(), 0);
	}

}
