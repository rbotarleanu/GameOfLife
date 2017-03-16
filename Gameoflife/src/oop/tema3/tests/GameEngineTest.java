package oop.tema3.tests;

import static org.junit.Assert.*;
import oop.tema3.engine.GameEngine;
import oop.tema3.scene.Cell;

import org.junit.Test;

public class GameEngineTest {

	private GameEngine gameEngine = new GameEngine();
	Cell[][] cells;
	
	/**
	 * Creates the cells array as an empty 5x5 Cell matrix.
	 */
	private void initCells() {
		cells= new Cell[5][5];
		for(int i = 0; i < 5; i++)
			for(int j = 0; j < 5; j++)
				cells[i][j] = new Cell();
	}
	
	/**
	 * Tests the survival of a Cell when it has 0 alive neighbours.
	 */
	@Test
	public void testNoNeighbours() {
		initCells();
		cells[2][2].setAlive(true);
		gameEngine.resolveCellSurvival(cells);
		assertFalse(cells[2][2].isAlive());
	}

	/**
	 * Tests that a cell will survive with 2 alive neighbours.
	 */
	@Test
	public void willLive() {
		initCells();
		cells[2][2].setAlive(true);
		cells[2][1].setAlive(true);
		cells[2][3].setAlive(true);
		gameEngine.resolveCellSurvival(cells);
		assertTrue(cells[2][2].isAlive());
	}
	
	/**
	 * Tests that a Cell will die if it has less than 2 members. 
	 */
	@Test 
	public void tooFewNeighbours() {
		initCells();
		cells[2][2].setAlive(true);
		cells[1][1].setAlive(true);
		gameEngine.resolveCellSurvival(cells);
		assertFalse(cells[2][2].isAlive());
	}
	
	/**
	 * Tests that a Cell will die if it has more than 3 members. 
	 */
	@Test 
	public void tooManyNeighbours() {
		initCells();
		cells[2][2].setAlive(true);
		cells[1][1].setAlive(true);
		cells[1][2].setAlive(true);
		cells[1][3].setAlive(true);
		cells[2][1].setAlive(true);
		gameEngine.resolveCellSurvival(cells);
		assertFalse(cells[2][2].isAlive());
	}
	
}
//	@Test
//	public void blockTest() {
//		initCells();
//		cells[2][2].setAlive(true);
//		cells[1][1].setAlive(true);
//		cells[1][2].setAlive(true);
//		cells[2][1].setAlive(true);
//		gameEngine.resolveCellSurvival(cells);
//		assertTrue(cells[2][2].isAlive());
//		assertTrue(cells[1][1].isAlive());
//		assertTrue(cells[1][2].isAlive());
//		assertTrue(cells[2][1].isAlive());
//	}
//	
//	@Test
//	public void blinkerTest() {
//		initCells();
//		cells[2][1].setAlive(true);
//		cells[2][2].setAlive(true);
//		cells[2][3].setAlive(true);
//		
//		gameEngine.resolveCellSurvival(cells);
//		assertFalse(cells[2][1].isAlive());
//		assertFalse(cells[2][3].isAlive());
//		assertTrue(cells[2][2].isAlive());
//		assertTrue(cells[1][2].isAlive());
//		assertTrue(cells[3][2].isAlive());
//	}
