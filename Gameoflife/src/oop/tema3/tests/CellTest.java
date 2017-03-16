package oop.tema3.tests;

import static org.junit.Assert.*;
import oop.tema3.scene.Cell;

import org.junit.Test;

public class CellTest {

	/**
	 * Test that Cell can be initialized.
	 */
	@Test
	public void testInit() {
		Cell c = new Cell();
		assertNotNull(c);
	}
	
	/**
	 * Test the setAlive method of a Cell.
	 */
	@Test
	public void testChangeStatus() {
		Cell c = new Cell();
		c.setAlive(true);
		assertTrue(c.isAlive());
	}

}
