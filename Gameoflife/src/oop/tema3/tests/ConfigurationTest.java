package oop.tema3.tests;

import static org.junit.Assert.*;
import oop.tema3.engine.BorderStrategyInterface;
import oop.tema3.engine.BorderlessStrategy;
import oop.tema3.engine.Configuration;
import oop.tema3.engine.Configuration.Speed;
import oop.tema3.scene.Cell;

import org.junit.Test;

public class ConfigurationTest {

	/**
	 * Tests the Singleton pattern, that only one Instance of the Configuration class exists.
	 */
	@Test
	public void testGetInstance() {
		Configuration c = Configuration.getInstance();
		Configuration c2 = Configuration.getInstance();
		assertNotNull(c);
		assertTrue(c.equals(c2));
	}

	/**
	 * Tests the setConfig method. 
	 */
	@Test
	public void testSetConfig() {
		Configuration c = Configuration.getInstance();
		Cell[][] cells = new Cell[5][5];
		c.setConfig(cells);
		assertTrue(cells.equals(c.getConfig()));
	}

	/**
	 * Tests the setSpeed method.
	 */
	@Test
	public void testSetSpeed() {
		Configuration c = Configuration.getInstance();
		c.setSpeed(Speed.FAST);
		assertEquals(Speed.FAST, c.getSpeed());
	}

	/**
	 * Tests the setStrategy method. 
	 */
	@Test
	public void testSetStrategy() {
		BorderStrategyInterface b = new BorderlessStrategy();
		Configuration c = Configuration.getInstance();
		c.setStrategy(b);
		assertTrue(b.equals(c.getStrategy()));
	}

	/**
	 * Tests the increase Generations method.
	 */
	@Test
	public void testIncreaseGenerations() {
		Configuration c = Configuration.getInstance();
		c.increaseGenerations();
		c.increaseGenerations();
		assertEquals(2,c.getGenerations());
	}

}
