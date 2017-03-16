package oop.tema3.tests;

import static org.junit.Assert.*;
import oop.tema3.scene.World;

import org.junit.Test;

public class WorldTest {

	/**
	 * Test the initialization of a 5x5 cell world.
	 */
	@Test
	public void testWorld() {
		World w = new World(5);
		assertNotNull(w);
	}

}
