package com.schneenet.networks;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class WireBlock extends GameBlock {

	public static final boolean[] CPOINTS_ONE =          {true, false, false, false};
	public static final boolean[] CPOINTS_TWO_ELBOW =    {true, true,  false, false};
	public static final boolean[] CPOINTS_TWO_STRAIGHT = {true, false, true,  false};
	public static final boolean[] CPOINTS_THREE =        {true, true,  true,  false};
	public static final boolean[] CPOINTS_FOUR =         {true, true,  true,  true};
	
	/**
	 * Rotation of the block = wire_rotation * 90 degrees
	 */
	private int wire_rotation;
	
	/**
	 * Boolean indicating if this block is connected to the server (via RouterBlocks, WireBlocks, or the ServerBlock)
	 */
	protected boolean lit;
	
	/**
	 * 4-element array telling us if the 4 cardinal directions have wires leading out of this block
	 * <br /><br />
	 * The order of the directions is E,N,W,S corresponding to rotations in degrees of 0, 90, 180, and 270 on a standard Cartesian plane. 
	 */
	protected boolean[] connectedPoints;
	
	@Override
	public void blockClicked() {
		//TODO Clicking on a wire block rotates the wires
		
	}

	@Override
	public Image getTextureState() {
		// Generate wire texture from state (rotation + lit + connected points)
		BufferedImage tex = new BufferedImage(GameBoard.GRID_SIZE, GameBoard.GRID_SIZE, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = (Graphics2D) tex.getGraphics();
		//TODO Draw the wires into the texture buffered image
		return tex;
	}

	@Override
	public boolean isBlockAnimating() {
		//TODO Wire rotation animation
		return false;
	}

	@Override
	public boolean isLit() {
		//TODO WireBlock isLit: check blocks that we are connected to for lit and relight accordingly
		return false;
	}

}
