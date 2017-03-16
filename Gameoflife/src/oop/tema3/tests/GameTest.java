package oop.tema3.tests;

import static org.junit.Assert.*;
import oop.tema3.scene.Game;

import org.junit.Test;

public class GameTest {

	/**
	 * Test the initialization of a Game object.
	 */
	@Test
	public void testGame() {
		Game g = new Game();
		assertNotNull(g);
	}
	
}
