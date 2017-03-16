package oop.tema3.tests;

import static org.junit.Assert.*;
import oop.tema3.engine.BorderStrategyInterface;
import oop.tema3.engine.BorderedStrategy;
import oop.tema3.engine.BorderlessStrategy;

import org.junit.Test;

public class BorderStrategyInterfaceTest {

	/**
	 * Tests that Strategies can be created with this interface.
	 */
	@Test
	public void test() {
		BorderStrategyInterface b = new BorderlessStrategy();
		BorderStrategyInterface b2 = new BorderedStrategy();
		assertTrue(b.getClass().getInterfaces()[0].toString().equals(b2.getClass().getInterfaces()[0].toString()));
	}

}
