package oop.tema3.engine;

import oop.tema3.scene.Cell;

/**
 * @author Botarleanu Robert-Mihai 321CB A GameEngine class which will determine
 *         the state of all cells in the next generation and update the current
 *         one.
 */
public class GameEngine {

	private Cell[][] nextGeneration;
	private Configuration config = Configuration.getInstance();

	/**
	 * Creates a cell array of the given size. Used for the cells array for the
	 * following generation.
	 * 
	 * @param n
	 *            size of cell array
	 * @return a cell array of the gien nxn size.
	 */
	private Cell[][] createCellArray(int n) {
		Cell[][] cells = new Cell[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				cells[i][j] = new Cell();
		return cells;
	}

	/**
	 * Determines the state of each cell in the following generation. For Cells
	 * that are on the border of the array, the strategy set in the
	 * configuration object is used.
	 * 
	 * @param cells
	 *            array of cells that describes the current state of the game.
	 */
	public void resolveCellSurvival(Cell[][] cells) {
		nextGeneration = createCellArray(cells.length);

		for (int i = 0; i < cells.length; i++)
			for (int j = 0; j < cells.length; j++)
				if (onBorder(i, j, cells))
					config.getStrategy().resolveCollision(cells,
							nextGeneration, i, j);
				else
					resolveSurvival(cells, nextGeneration, i, j);

		for (int i = 0; i < cells.length; i++)
			for (int j = 0; j < cells.length; j++) {
				if (cells[i][j].isAlive() != nextGeneration[i][j].isAlive()) {
					cells[i][j].setAlive(nextGeneration[i][j].isAlive());
					cells[i][j].repaint();
				}
			}
	}

	/**
	 * Determines if a cell is on the border of the array.
	 * 
	 * @param i
	 *            the line position of a Cell in the array.
	 * @param j
	 *            the column position of a Cell in the array.
	 * @param cells
	 *            the array of cells that describes the current state of the
	 *            game
	 * @return true if the cell is on the border of the array.
	 */
	private boolean onBorder(int i, int j, Cell[][] cells) {
		if (i == 0)
			return true;
		if (i == cells.length - 1)
			return true;
		if (j == 0)
			return true;
		if (j == cells.length - 1)
			return true;
		return false;
	}

	/**
	 * @param cells
	 *            an array that describes the current state of the game.
	 * @param nextGeneration
	 *            an array that describes the state of the game in the following
	 *            generation.
	 * @param i
	 *            line coordinate of a cell.
	 * @param j
	 *            column coordinate of a cell.
	 */
	private void resolveSurvival(Cell[][] cells, Cell[][] nextGeneration,
			int i, int j) {
		int nrNeighbours = countNeighbours(cells, i, j);
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
	 * Determines the number of alive neighbours a cell has. The cell will not
	 * be on the border of the array.
	 * 
	 * @param cells
	 *            the Cell array that describes the current state of the game.
	 * @param i
	 *            line coordinate of the cell.
	 * @param j
	 *            column coordinate of the cell.
	 * @return the number of alive neighbours of a cell.
	 */
	private int countNeighbours(Cell[][] cells, int i, int j) {
		int no = 0;
		if (cells[i - 1][j - 1].isAlive())
			no++;
		if (cells[i - 1][j].isAlive())
			no++;
		if (cells[i - 1][j + 1].isAlive())
			no++;
		if (cells[i][j - 1].isAlive())
			no++;
		if (cells[i][j + 1].isAlive())
			no++;
		if (cells[i + 1][j - 1].isAlive())
			no++;
		if (cells[i + 1][j].isAlive())
			no++;
		if (cells[i + 1][j + 1].isAlive())
			no++;
		return no;
	}
}
