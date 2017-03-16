package oop.tema3.engine;

import oop.tema3.scene.Cell;

/**
 * @author Botarleanu Robert-Mihai 321CB
 * A Bordered strategy class for analyzing cells on the edges of the array.
 * This class will determine if a cell on a given location on the border survives
 * into the next generation.
 * Uses a bordered approach, any cell outside of the array is considered dead.
 */

public class BorderedStrategy implements BorderStrategyInterface {

	/**
	 * Public Constructor for a bordered strategy;
	 */
	public BorderedStrategy() {
		super();
	}

	@Override
	public void resolveCollision(Cell[][] cells, Cell[][] nextGeneration,
			int i, int j) {
		int nrNeighbours = 0;
		if (i == 0)
			nrNeighbours += nextLineNeighbours(cells, i, j);
		else if (i == cells.length - 1)
			nrNeighbours += previousLineNeighbours(cells, i, j);
		else if (j == 0)
			nrNeighbours += nextColumnNeighbours(cells, i, j);
		else
			nrNeighbours += previousColumnNeighbours(cells, i, j);
		checkSurvival(cells, nextGeneration, i, j, nrNeighbours);
	}

	/**
	 * Determines wether a Cell survives in the next generation.
	 * @param cells the array of Cells that exist in the game.
	 * @param nextGeneration the array of cells in the next generation
	 * @param i line position of a cell 
	 * @param j column position of a  cell
	 * @param nrNeighbours number of alive neighbours a cell has.
	 */
	private void checkSurvival(Cell[][] cells, Cell[][] nextGeneration, int i,
			int j, int nrNeighbours) {
		if (cells[i][j].isAlive()) {
			if (nrNeighbours >= 2 && nrNeighbours <= 3)
				nextGeneration[i][j].setAlive(true);
			else
				nextGeneration[i][j].setAlive(false);
		} else {
			if (nrNeighbours == 3)
				nextGeneration[i][j].setAlive(true);
			else
				nextGeneration[i][j].setAlive(false);
		}
	}

	/**
	 * Determines the number of alive neighbours a Cell has on the previous
	 * column. This is used for the last column.
	 * @param cells the array of Cells that exist in the game.
	 * @param i line position of a Cell in the array.
	 * @param j column position of a Cell in the array.
	 * @return the number of alive neighbours on the next column.
	 */
	private int previousColumnNeighbours(Cell[][] cells, int i, int j) {
		int no = 0;
		if (cells[i - 1][j].isAlive())
			no++;
		if (cells[i + 1][j].isAlive())
			no++;
		if (cells[i - 1][j - 1].isAlive())
			no++;
		if (cells[i][j - 1].isAlive())
			no++;
		if (cells[i + 1][j - 1].isAlive())
			no++;
		return no;
	}

	/**
	 * Determines the number of alive neighbours a cell has on the next
	 * column. This is used for the Cells on the first column.
	 * @param cells the array of Cells that exist in the game.
	 * @param i the line position of a cell
	 * @param j the j position of a cell
	 * @return number of alive neighbours a cell has on the next column.
	 */
	private int nextColumnNeighbours(Cell[][] cells, int i, int j) {
		int no = 0;
		if (cells[i - 1][j].isAlive())
			no++;
		if (cells[i + 1][j].isAlive())
			no++;
		if (cells[i - 1][j + 1].isAlive())
			no++;
		if (cells[i][j + 1].isAlive())
			no++;
		if (cells[i + 1][j + 1].isAlive())
			no++;
		return no;
	}

	/**
	 * Determines the number of  alive neighbours a Cell has on the previous
	 * line. This is used for the last column.
	 * @param cells the array of Cells that exist in the game.
	 * @param i the line position of a cell
	 * @param j the j position of a cell
	 * @return the number of alive neighbours this cell has on the previous
	 * line.
	 */
	private int previousLineNeighbours(Cell[][] cells, int i, int j) {
		int no = 0;
		if (j != 0) {
			if (cells[i][j - 1].isAlive())
				no++;
			if (cells[i - 1][j - 1].isAlive())
				no++;
		}
		if (j != cells.length - 1) {
			if (cells[i][j + 1].isAlive())
				no++;
			if (cells[i - 1][j + 1].isAlive())
				no++;
		}
		if (cells[i - 1][j].isAlive())
			no++;
		return no;
	}

	/**
	 * Determines the number of alive neighbours a Cell has on the next
	 * line. This is used for the first line.
	 * @param cells the array of Cells that exist in the game.
	 * @param i the line position of a cell
	 * @param j the j position of a cell
	 * @return the number of alive neighbours a cell has on the next line.
	 */
	private int nextLineNeighbours(Cell[][] cells, int i, int j) {
		int no = 0;
		if (j != 0) {
			if (cells[i][j - 1].isAlive())
				no++;
			if (cells[i + 1][j - 1].isAlive())
				no++;
		}
		if (j != cells.length - 1) {
			if (cells[i][j + 1].isAlive())
				no++;
			if (cells[i + 1][j + 1].isAlive())
				no++;
		}
		if (cells[i + 1][j].isAlive())
			no++;
		return no;
	}
}
