package com.schneenet.networks;

import java.awt.Image;

public abstract class GameBlock {

	/**
	 * Boolean indicating if this block is connected to the server (via RouterBlocks, WireBlocks, or the ServerBlock)
	 */
	protected boolean lit;
	
	/**
	 * 4-element array telling us if the 4 cardinal directions have wires leading out of this block
	 */
	protected boolean[] connectedPoints;
	
	/**
	 * What happens when the block is clicked? (Should rotate the block)
	 */
	public abstract void blockClicked();
	
	/**
	 * Get the correctly orientated and lit texture into an Image object
	 * @return Image object with the texture
	 */
	public abstract Image getTextureState();
	
}
