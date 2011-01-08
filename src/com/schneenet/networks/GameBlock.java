package com.schneenet.networks;

import java.awt.Image;

public abstract class GameBlock {
	
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
	 * Check if the block is should be animating a rotating to a new state
	 * @return True if the block is animating, false otherwise
	 */
	public abstract boolean isBlockAnimating();
	
	/**
	 * Find out if this block is lit (or possibly a light source?)
	 * @return True if the block should be a source of light
	 */
	public abstract boolean isLit();
	
}
