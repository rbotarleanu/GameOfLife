package oop.tema3.engine;

import oop.tema3.scene.Cell;

/**
 * @author RobertPC Interface used to describe the strategy used for the cells
 *         on the border of the array.
 */
public interface BorderStrategyInterface {
	/**
	 * @param cells cells the array of Cells that exist in the game.
	 * @param nextGeneration array off Cell units that describes the layout in the next generation
	 * @param i the line in the array of the cell to be analyzed
	 * @param j the column in the array of the cell to be analyzed
	 */
	public void resolveCollision(Cell[][] cells, Cell[][] nextGeneration,
			int i, int j);
}
