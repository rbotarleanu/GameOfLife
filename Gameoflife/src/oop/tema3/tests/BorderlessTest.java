package oop.tema3.tests;

import static org.junit.Assert.*;
import oop.tema3.engine.BorderlessStrategy;
import oop.tema3.scene.Cell;

import org.junit.Test;

public class BorderlessTest {

	Cell[][] cells;
	Cell[][] nextGen;
	private BorderlessStrategy bs = new BorderlessStrategy();

	/**
	 * Initializes the cells and nextGen arrays as 5x5 Cell arrays.
	 */
	private void initCells() {
		cells = new Cell[5][5];
		nextGen = new Cell[5][5];
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++) {
				cells[i][j] = new Cell();
				nextGen[i][j] = new Cell();
			}
	}

	/**
	 * Test survival with 3 neighbours of Cell on the first line with 3 neighbours on the last line.
	 */
	@Test
	public void testAboveFirstLineLives() {
		initCells();
		cells[0][1].setAlive(true);
		cells[4][1].setAlive(true);
		cells[4][0].setAlive(true);
		cells[4][2].setAlive(true);

		bs.resolveCollision(cells, nextGen, 0, 1);
		assertTrue(nextGen[0][1].isAlive());
	}

	/**
	 *  Test death from overpopulation of Cell on the first line with 4 neighbours on the last line.
	 */
	@Test
	public void testAboveFirstLineDies() {
		initCells();
		cells[0][1].setAlive(true);
		cells[0][2].setAlive(true);
		cells[4][1].setAlive(true);
		cells[4][0].setAlive(true);
		cells[4][2].setAlive(true);

		bs.resolveCollision(cells, nextGen, 0, 1);
		assertFalse(nextGen[0][1].isAlive());
	}

	/**
	 * Test birth of Cell on the first line with 3 neighbours on the last line.
	 */
	@Test
	public void testAboveFirstLineRevives() {
		initCells();
		cells[4][1].setAlive(true);
		cells[4][0].setAlive(true);
		cells[4][2].setAlive(true);

		bs.resolveCollision(cells, nextGen, 0, 1);
		assertTrue(nextGen[0][1].isAlive());
	}
	
	/**
	 * Test survival of Cell on the last line with 3 neighbours on the first line.
	 */
	@Test
	public void testBelowLastLineLives() {
		initCells();
		cells[4][1].setAlive(true);
		cells[0][1].setAlive(true);
		cells[0][0].setAlive(true);
		cells[0][2].setAlive(true);

		bs.resolveCollision(cells, nextGen, 4, 1);
		assertTrue(nextGen[4][1].isAlive());
	}

	/**
	 * Test death from overpopulation of Cell on the last line with 4 neighbours on the first line.
	 */
	@Test
	public void testBelowLastLineDies() {
		initCells();
		cells[4][1].setAlive(true);
		cells[4][2].setAlive(true);
		cells[0][1].setAlive(true);
		cells[0][0].setAlive(true);
		cells[0][2].setAlive(true);

		bs.resolveCollision(cells, nextGen, 4, 1);
		assertFalse(nextGen[4][1].isAlive());
	}

	/**
	 * Test birth of cell on the last line with 3 neighbours on the first line.
	 */
	@Test
	public void testBelowLastLineRevives() {
		initCells();
		cells[0][1].setAlive(true);
		cells[0][0].setAlive(true);
		cells[0][2].setAlive(true);

		bs.resolveCollision(cells, nextGen, 4, 1);
		assertTrue(nextGen[4][1].isAlive());
	}

	/**
	 * Test survival of cell on the first column with 2 neighbours on the last column.
	 */
	@Test
	public void testLeftOfFirstColumnLives() {
		initCells();
		cells[1][0].setAlive(true);
		cells[1][1].setAlive(true);
		cells[1][4].setAlive(true);

		bs.resolveCollision(cells, nextGen, 1, 0);
		assertTrue(nextGen[1][0].isAlive());
	}

	/**
	 * Test survival of Cell on the last column with 2 neighbours on the first column.
	 */
	@Test
	public void testRightOfLastColumnLives() {
		initCells();
		cells[1][4].setAlive(true);
		cells[1][0].setAlive(true);
		cells[2][4].setAlive(true);

		bs.resolveCollision(cells, nextGen, 1, 4);
		assertTrue(nextGen[1][4].isAlive());
	}
}
