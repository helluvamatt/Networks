package com.schneenet.networks;

import java.awt.Image;

public abstract class GameBlock {
	
	protected int _x;
	protected int _y;
	
	/**
	 * Create a GameBlock
	 * @param x X-coordinate of block
	 * @param y Y-coordinate of block
	 */
	public GameBlock(int x, int y) {
		this._x = x;
		this._y = y;
	}
	
	/**
	 * What happens when the block is clicked? (Should rotate the block)
	 */
	public abstract void blockClicked();
	
	/**
	 * Get the correctly oriented and lit texture into an Image object
	 * @return Image object with the texture
	 */
	public abstract Image getTextureState();
	
	/**
	 * Check if the block is should be animating a rotation to a new state
	 * @return True if the block is animating, false otherwise
	 */
	public abstract boolean animateBlock(GameBoard parent);
	
	/**
	 * Find out if this block is lit (or possibly a light source?)
	 * @return True if the block should be a source of light
	 */
	public abstract boolean isLit(GameBoard parent);
	
	/**
	 * Get the rotation of the texture in radians during an animation
	 * @return double rotation in radians
	 */
	public double getRotationRadians() {
		return 0;
	}
	
}
