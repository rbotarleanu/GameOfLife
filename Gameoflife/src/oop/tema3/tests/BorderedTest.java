package oop.tema3.tests;

import static org.junit.Assert.*;
import oop.tema3.engine.BorderedStrategy;
import oop.tema3.scene.Cell;

import org.junit.Test;

public class BorderedTest {

	Cell[][] cells;
	Cell[][] nextGen;
	private BorderedStrategy bs = new BorderedStrategy();
	
	/**
	 * Initializes cells and nextGen as 5x5 Cell arrays.
	 */
	private void initCells() {
		cells= new Cell[5][5];
		nextGen = new Cell[5][5];
		for(int i = 0; i < 5; i++)
			for(int j = 0; j < 5; j++) {
				cells[i][j] = new Cell();
				nextGen[i][j] = new Cell();
			}
		
	}
	
	/**
	 * Test survival with 3 neighbours on the first line without the first and last Cell.
	 */
	@Test
	public void firstLineTest() {
		initCells();
		cells[0][1].setAlive(true);
		cells[1][0].setAlive(true);
		cells[1][1].setAlive(true);
		cells[1][2].setAlive(true);
		bs.resolveCollision(cells, nextGen, 0, 1);
		assertTrue(nextGen[0][1].isAlive());
	}

	/**
	 * Test survival with 2 neighbours on the first line with the first Cell.
	 */
	@Test
	public void firstLineTestWithFirstColumn() {
		initCells();
		cells[0][0].setAlive(true);
		cells[1][0].setAlive(true);
		cells[1][1].setAlive(true);
		bs.resolveCollision(cells, nextGen, 0, 0);
		assertTrue(nextGen[0][0].isAlive());
	}
	
	/**
	 * Test survival with 2 neighbours on the first line with the last Cell.
	 */
	@Test
	public void firstLineTestWithLastColumn() {
		initCells();
		cells[0][4].setAlive(true);
		cells[1][3].setAlive(true);
		cells[1][4].setAlive(true);
		bs.resolveCollision(cells, nextGen, 0, 4);
		assertTrue(nextGen[0][4].isAlive());
	}
	
	/**
	 * Test survival with 3 neighbours on the last line without the first and last Cell.
	 */
	@Test
	public void lastLineTest() {
		initCells();
		cells[4][1].setAlive(true);
		cells[3][0].setAlive(true);
		cells[3][1].setAlive(true);
		cells[3][2].setAlive(true);
		bs.resolveCollision(cells, nextGen, 4, 1);
		assertTrue(nextGen[4][1].isAlive());
	}
	
	/**
	 * Test survival with 2 neighbours on the last line with the first Cell.
	 */
	@Test
	public void lastLineTestWithFirstColumn() {
		initCells();
		cells[4][0].setAlive(true);
		cells[3][0].setAlive(true);
		cells[3][1].setAlive(true);
		bs.resolveCollision(cells, nextGen, 4, 0);
		assertTrue(nextGen[4][0].isAlive());
	}
	
	/**
	 * Test survival with 2 neighbours on the last line with the last Cell.
	 */
	@Test
	public void lastLineTestWithLastColumn() {
		initCells();
		cells[4][4].setAlive(true);
		cells[3][4].setAlive(true);
		cells[3][3].setAlive(true);
		bs.resolveCollision(cells, nextGen, 4, 4);
		assertTrue(nextGen[4][4].isAlive());
	}

	/**
	 * Test survival with 3 neighbours on the first column without the first and last Cell.
	 */
	@Test
	public void firstColumnTest() {
		initCells();
		cells[1][0].setAlive(true);
		cells[0][0].setAlive(true);
		cells[2][0].setAlive(true);
		cells[1][1].setAlive(true);
		bs.resolveCollision(cells,nextGen,1,0);
		assertTrue(nextGen[1][0].isAlive());
	}
	

	/**
	 * Test death from overpopulation for cell on the first column.
	 */
	@Test
	public void firstColumnTestDeath() {
		initCells();
		cells[1][0].setAlive(true);
		cells[2][1].setAlive(true);
		cells[2][0].setAlive(true);
		cells[1][1].setAlive(true);
		cells[0][1].setAlive(true);
		bs.resolveCollision(cells,nextGen,1,0);
		assertFalse(nextGen[1][0].isAlive());
	}
	
	
	/**
	 * Test birth of Cell on the first column.
	 */
	@Test
	public void firstColumnTestRevive() {
		initCells();
		cells[1][0].setAlive(false);
		cells[2][1].setAlive(true);
		cells[2][0].setAlive(true);
		cells[0][1].setAlive(true);
		bs.resolveCollision(cells,nextGen,1,0);
		assertTrue(nextGen[1][0].isAlive());
	}
	
	/**
	 * Test survival with 3 neighbours on the last column without the first and last Cell.
	 */
	@Test
	public void lastColumnTest() {
		initCells();
		cells[1][4].setAlive(true);
		cells[0][4].setAlive(true);
		cells[2][4].setAlive(true);
		cells[0][3].setAlive(true);
		bs.resolveCollision(cells,nextGen,1,4);
		assertTrue(nextGen[1][4].isAlive());
	}
	
	/**
	 * Test birth of dead Cell on the last column.
	 */
	@Test
	public void lastColumnRevive() {
		initCells();
		cells[1][4].setAlive(false);
		cells[2][3].setAlive(true);
		cells[1][3].setAlive(true);
		cells[0][3].setAlive(true);
		bs.resolveCollision(cells,nextGen,1,4);
		assertTrue(nextGen[1][4].isAlive());
	}
}
