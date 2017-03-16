package oop.tema3.scene;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import oop.tema3.engine.BorderedStrategy;
import oop.tema3.engine.BorderlessStrategy;
import oop.tema3.engine.Configuration;
import oop.tema3.engine.Configuration.Speed;

/**
 * @author Botarleanu Robert-Mihai 321CB
 * A Frame that shows the current state of the game and controls the interaction
 * of the user with the application.
 * Holds all graphical elements.
 */
public class World extends JFrame {
	private static final long serialVersionUID = -4504721552849870942L;

	private Configuration config = Configuration.getInstance();
	private Game game = new Game();
	private int no;
	private Cell[][] cells;

	private String[] speeds = { "Slow", "Medium", "Fast", "Very Fast" };
	private String[] strategies = { "Bordered", "Borderless" };

	private JComboBox<String> speedSelector = new JComboBox<String>(speeds);
	private JButton startButton = new JButton("Start");
	private JButton stopButton = new JButton("Stop");
	private JLabel generationCounter = new JLabel("Generations: " + 0);
	private JComboBox<String> borderStrategySelector = new JComboBox<String>(
			strategies);

	private GameThread gameThread;
	private JPanel gamePanel;
	private JPanel buttonPanel;

	/**
	 * Creates an instance of the World.
	 * @param numberOfCells the number of cells in the world.
	 */
	public World(int numberOfCells) {
		this.no = numberOfCells;
		cells = new Cell[numberOfCells][numberOfCells];
		config.setConfig(cells);
		gamePanel = new JPanel(new GridLayout(numberOfCells, numberOfCells, 0, 0));
		buttonPanel = new JPanel();
		initUI();
	}

	/**
	 * Initializes the user interface, adds panels, buttons and actionListeners.
	 */
	private void initUI() {
		// add cells
		for (int i = 0; i < no; i++)
			for (int j = 0; j < no; j++)
				gamePanel.add(cells[i][j] = new Cell());

		// add buttons, combo boxes, generation label
		buttonPanel.add(startButton);
		buttonPanel.add(stopButton);
		buttonPanel.add(speedSelector);
		buttonPanel.add(generationCounter);
		buttonPanel.add(borderStrategySelector);

		// deactivate stopButton
		stopButton.setEnabled(false);

		manageSpeedComboBox();
		manageBorderStrategyComboBox();

		addButtonListeners();

		// Set line borders on the cells panel and the start button
		gamePanel.setBorder(new LineBorder(Color.BLUE));

		// Place the panel and the button to the applet
		add(gamePanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	/**
	 * Manages the Strategy ComboBox.
	 * Sets the strategy in the Configuration ass Bordered or Borderless.
	 */
	private void manageBorderStrategyComboBox() {
		// set default strategy to borderless and add listener
		borderStrategySelector.setSelectedIndex(0);
		borderStrategySelector.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				switch (borderStrategySelector.getSelectedIndex()) {
				case 0:
					config.setStrategy(new BorderedStrategy());
					break;
				case 1:
					config.setStrategy(new BorderlessStrategy());
					break;
				}
			}
		});

	}

	/**
	 * Adds the button listeners for the Start and Stop buttons.
	 */
	private void addButtonListeners() {
		// add listeners for the buttons
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameThread = new GameThread();
				gameThread.start();
				stopButton.setEnabled(true);
				startButton.setText("Resume");
			}
		});

		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameThread.stopThread();
			}
		});

	}

	/**
	 * Manages the Speed ComboBox.
	 * Sets the speed of the game in the configuration as Slow, Medium,
	 * Fast and Very Fast.
	 */
	private void manageSpeedComboBox() {
		// set Default speed to medium and add listener
		speedSelector.setSelectedIndex(1);
		
		speedSelector.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				switch (speedSelector.getSelectedIndex()) {
				case 0:
					config.setSpeed(Speed.SLOW);
					break;
				case 1:
					config.setSpeed(Speed.MEDIUM);
					break;
				case 2:
					config.setSpeed(Speed.FAST);
					break;
				case 3:
					config.setSpeed(Speed.VERYFAST);
					break;
				}
			}
		});
	}

	/**
	 * @author Botarleanu Robert-Mihai 321CB An internal class for the
	 *         gameThread. Will continue to run an update cell generations until
	 *         the stopThread method is called.
	 */
	private class GameThread extends Thread {
		private boolean running = true;

		@Override
		public void run() {
			while (running) {
				game.run();
				generationCounter.setText("Generations: "
						+ config.getGenerations());
			}
		}

		/**
		 * Stops the simulation.
		 */
		public void stopThread() {
			running = false;
		}

	}

	public static void main(String[] args) {
		// width and height of frame
		int width = 900;
		int height = 900;

		// get number of cells, default is 5x5
		int cells;
		String input = JOptionPane
				.showInputDialog("Number of cells on a row/column. Default is 30:");
		cells = 5;
		try {
			cells = new Integer(input.trim());
		} catch (NumberFormatException e2) {
			System.out.println("Invalid number of cells. Using default.");
		}

		// Create the world frame
		World frame = new World(cells);

		// set frame size and center it
		frame.setSize(width, height);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(screenSize.width / 2 - width / 2, screenSize.height
				/ 2 - height / 2);

		// Display frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
