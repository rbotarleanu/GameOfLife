package oop.tema3.scene;

import oop.tema3.engine.Configuration;
import oop.tema3.engine.GameEngine;

/**
 * @author Botarleanu Robert-Mihai 321CB A Class that handles the control of the
 *         GameEngine.
 */
public class Game implements Runnable {

	private Configuration config = Configuration.getInstance();

	@Override
	public void run() {
		Thread updateThread = new Thread() {
			public void run() {
				updateGameBoard();
			}
		};

		updateThread.start();
		try {
			Thread.sleep(config.getSpeed().getFrameSpeed());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

	}

	/**
	 * Updates the state of the game by using the GameEngine class.
	 */
	private void updateGameBoard() {
		GameEngine gameEngine = new GameEngine();
		gameEngine.resolveCellSurvival(config.getConfig());
		config.increaseGenerations();
	}

}
