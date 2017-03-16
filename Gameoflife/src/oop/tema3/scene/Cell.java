package oop.tema3.scene;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * @author Botarleanu Robert-Mihai 321CB Class that represents a Cell unit in
 *         the game of life.
 */
public class Cell extends JPanel {
	private static final long serialVersionUID = -1059550547927858573L;

	private boolean alive;

	/**
	 * Creates an instance of the Cell class and adds a MouseListener for it.
	 */
	public Cell() {
		setBorder(new LineBorder(Color.black, 1)); // cell's border
		addMouseListener(new MyMouseListener()); // Register listener
	}

	/** Paint the cell */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (alive)
			g.setColor(Color.yellow);
		else
			g.setColor(Color.gray);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	/**
	 * @author Botarleanu Robert-Mihai 321CB A class for the Cell mouseListener.
	 *         If a cell is clicked on the GUI it's state will be changed from
	 *         alive to dead and vice-versa.
	 */
	private class MyMouseListener extends MouseAdapter {
		// Handle mouse click on a cell
		public void mouseClicked(MouseEvent e) {
			setAlive(!alive);
			repaint();
		}
	}

	/**
	 * @return true if cell is alive.
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * @param alive
	 *            Sets the status of the cell.
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

}