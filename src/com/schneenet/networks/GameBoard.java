package com.schneenet.networks;

import java.awt.Image;

/**
 * GameBoard class - Contains the data and methods for manipulating the board. Accessed from the GamePanel.
 * @author Matt
 *
 */
public class GameBoard {

	public static final int GRID_SIZE = 32;
	
	private int board_h;
	private int board_w;
	private GameBlock[] boardData;
	
	protected GameBoard(int w, int h) {
		board_h = h;
		board_w = w;
		boardData = new GameBlock[w * h];
	}
	
	private void setBlockAt(int x, int y, GameBlock b) {
		boardData[y * board_w + x] = b;
	}
	
	public int getBoardHeight() {
		return board_h;
	}
	
	public int getBoardWidth() {
		return board_w;
	}
	
	public Image getTextureFor(int x, int y) {
		return boardData[y * board_w + x].getTextureState();
	}
	
	public void blockClicked(int x, int y) {
		boardData[y * board_w + x].blockClicked();
	}
	
	public static GameBoard createBoard(int w, int h, boolean routers) {
		GameBoard newBoard = new GameBoard(w, h);
		//TODO Generate a board with a valid solution
		
		
		return newBoard;
	}
}
