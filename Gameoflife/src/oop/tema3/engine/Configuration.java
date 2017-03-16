package oop.tema3.engine;

import oop.tema3.scene.Cell;

/**
 * @author Botarleanu Robert-Mihai A Configuration class for the game of life.
 *         Will contain the border mode: borderless or bordered, as well as the
 *         rendering speed and cell configuration.
 */
public class Configuration {
	private static final Configuration INSTANCE = new Configuration();

	private Cell[][] config;
	private Speed speed = Speed.MEDIUM;
	private BorderStrategyInterface strategy = new BorderedStrategy();
	private int generations;

	public enum Speed {
		SLOW(1000), MEDIUM(500), FAST(200), VERYFAST(50);

		private final long frameSpeed;

		/**
		 * @return the current speed one of three: Slow - One generation per
		 *         second Medium - 2 generations occur during one second Fast -
		 *         5 generations per seccond Very Fast - 20 generations per
		 *         second
		 */
		public long getFrameSpeed() {
			return frameSpeed;
		}

		/**
		 * @param frameSpeed
		 *            the time of pause between two generations/frame updates.
		 */
		Speed(long frameSpeed) {
			this.frameSpeed = frameSpeed;
		}
	};

	/**
	 * @return the instance of the current game configuration.
	 */
	public static Configuration getInstance() {
		return INSTANCE;
	}

	/**
	 * @return the cell array that describes the current state of the game.
	 */
	public Cell[][] getConfig() {
		return config;
	}

	/**
	 * @param config
	 *            cell array that describes the current state of the game.
	 */
	public void setConfig(Cell[][] config) {
		this.config = config;
	}

	/**
	 * @return the currrent game speed.
	 */
	public Speed getSpeed() {
		return speed;
	}

	/**
	 * @param speed
	 *            constant of the Speed enum that describes the current state of
	 *            the game.
	 */
	public void setSpeed(Speed speed) {
		this.speed = speed;
	}

	/**
	 * @return the current strategy that is used for the border cells.
	 */
	public BorderStrategyInterface getStrategy() {
		return strategy;
	}

	/**
	 * @param strategy
	 *            the strategy that is used for the border cells.
	 */
	public void setStrategy(BorderStrategyInterface strategy) {
		System.out.println("Setting strategy " + strategy.toString());
		this.strategy = strategy;
	}

	/**
	 * @return the number of generations that has passed since the beginning of
	 *         the game.
	 */
	public int getGenerations() {
		return generations;
	}

	/**
	 * Increases the current number of generations that have passed since the
	 * beginning of the game.
	 */
	public void increaseGenerations() {
		generations++;
	}

}
