package com.schneenet.networks;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

/**
 * GamePanel class - A JPanel extension that controls the game.
 * @author Matt
 *
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel implements MouseListener {

	// Difficulty Variables
	private int grid_h;
	private int grid_w;
	private boolean use_routers;
	
	// Game Variables / Objects
	private GameBoard theBoard;
	
	/**
	 * Default Constructor
	 */
	public GamePanel() {
		// Defaults (Correspond to "Easy")
		grid_h = 5;
		grid_w = 5;
		use_routers = false;

		newGame();
		
		//TODO Set up the GamePanel
		
		
	}
	
	/**
	 * Change the difficulty of the game
	 * @param w Width of the grid
	 * @param h Height of the grid
	 * @param routers Complicate the network with routers
	 */
	public void setDifficulty(int w, int h, boolean routers) {
		grid_h = h;
		grid_w = w;
		use_routers = routers;
		this.newGame();
	}
	
	/**
	 * Start a new game
	 */
	public void newGame() {
		// Regenerate the board
		theBoard = GameBoard.createBoard(grid_w, grid_h, use_routers);
		
		//TODO New game action
		Dimension d = new Dimension(grid_w * GameBoard.GRID_SIZE, grid_h * GameBoard.GRID_SIZE);
		this.setPreferredSize(d);
		this.setSize(d);
		
		
	}
	
	/**
	 * The MainWindow is being closed, stop the game's threads and whatnot.
	 */
	public void closing() {
		//TODO Inform the GamePanel we are closing
	}
	
	/**
	 * Custom code for painting the game panel.
	 */
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		//TODO Draw the GamePanel
		for (int y = 0; y < grid_h; y++) {
			for (int x = 0; x < grid_w; x++) {
				theBoard.animateBlock(x, y);
				GameBlock theBlock = theBoard.getBlockAt(x, y);
				AffineTransform xform = AffineTransform.getRotateInstance(theBlock.getRotationRadians(), 16, 16);
				xform.translate(x * GameBoard.GRID_SIZE, y * GameBoard.GRID_SIZE);
				g2d.drawImage(theBoard.getTextureFor(x, y), xform, this);
			}
		}
	}

	/**
	 * What happens when the game board is clicked
	 */
	public void mouseClicked(MouseEvent e) {
		//TODO Mouse clicked event
		int x = e.getX() / GameBoard.GRID_SIZE; 
		int y = e.getY() / GameBoard.GRID_SIZE;
		theBoard.blockClicked(x, y);
		repaint();
	}

	// Don't care events:
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
}
