package oop.tema3.engine;

import oop.tema3.scene.Cell;

/**
 * @author Botarleanu Robert-Mihai 321CB
 * A Borderless strategy class for analyzing cells on the edges of the array.
 * This class will determine if a cell on a given location on the border survives
 * into the next generation.
 * Uses a borderless approach, the array of cells is considered to be torroidal
 * and neighbours are determined as such.
 */
public class BorderlessStrategy implements BorderStrategyInterface {

	@Override
	public void resolveCollision(Cell[][] cells, Cell[][] nextGeneration,
			int i, int j) {
			int nrNeighbours = 0;
			if(getCell(cells,i-1,j-1).isAlive()) nrNeighbours++;
			if(getCell(cells,i-1,j).isAlive()) nrNeighbours++;
			if(getCell(cells,i-1,j+1).isAlive()) nrNeighbours++;
			if(getCell(cells,i,j-1).isAlive()) nrNeighbours++;
			if(getCell(cells,i,j+1).isAlive()) nrNeighbours++;
			if(getCell(cells,i+1,j-1).isAlive()) nrNeighbours++;
			if(getCell(cells,i+1,j).isAlive()) nrNeighbours++;
			if(getCell(cells,i+1,j+1).isAlive()) nrNeighbours++;
			
			checkSurvival(cells, nextGeneration, i, j, nrNeighbours);
	}

	/**
	 * Determines wether a cell will surive in the next generation.
	 * @param cells the array of Cells that describes the state of the game
	 * in the current generation.
	 * @param nextGeneration the array of Cells that describes the state of the
	 * game in the following generation.
	 * @param i the line position of a cell
	 * @param j the j position of a cell
	 * @param nrNeighbours number of alive neighbours this cells has.
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
	 * Determines the cell that is described by the coordinates in a 
	 * torroidal cells array.
	 * @param cells the array of Cells that exist in the game.
	 * @param i the line position of a cell outside of the array.
	 * @param j the j position of a cell outside of the array.
	 * @return a reference to the Cell described by [i,j] if the cells array
	 * is torroidal.
	 */
	public Cell getCell(Cell[][] cells, int i, int j) {
		int ti = i, tj = j;
		if(i >= cells.length) ti = i % (cells.length);
		if(i < 0) ti = cells.length - 1;
		if(j >= cells.length) tj = j % (cells.length);
		if(j < 0) tj = cells.length - 1;
		return cells[ti][tj];
	}
	
}
