package com.schneenet.networks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

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
	
	private int animStep;

	public WireBlock(int x, int y, int type) {
		super(x, y);
		Random r = new Random(System.currentTimeMillis());
		switch (type) {
		case 0:
			connectedPoints = CPOINTS_ONE;
			break;
		case 1:
			connectedPoints = CPOINTS_TWO_ELBOW;
			break;
		case 2:
			connectedPoints = CPOINTS_TWO_STRAIGHT;
			break;
		case 3:
			connectedPoints = CPOINTS_THREE;
			break;
		case 4:
			connectedPoints = CPOINTS_FOUR;
			break;
		}
		wire_rotation = r.nextInt(4);
	}
	
	@Override
	public void blockClicked() {
		// Initiate wire rotation animation
		animStep = 0;
	}

	@Override
	public Image getTextureState() {
		// Generate wire texture from state (rotation + lit + connected points)
		//TODO TEST WireBlock::getTextureState()
		BufferedImage tex = new BufferedImage(GameBoard.GRID_SIZE, GameBoard.GRID_SIZE, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = (Graphics2D) tex.getGraphics();
		g.setColor(Color.BLACK);
		g.drawRect(13, 13, 6, 6);
		Color hlColor = new Color(0, 0, 0x33);
		if (lit) hlColor = new Color(0, 0, 0xFF);
		g.setColor(hlColor);
		g.drawRect(14, 14, 4, 4);
		for (int i = 0; i < 4; i++) {
			int r = i + this.wire_rotation;
			if (r > 3) r -= 4;
			switch (r) {
			case 0: // East
				g.setColor(Color.BLACK);
				g.drawRect(18, 13, 14, 6);
				g.setColor(hlColor);
				g.drawRect(18, 14, 14, 4);
				break;
			case 1: // North
				g.setColor(Color.BLACK);
				g.drawRect(13, 0, 6, 14);
				g.setColor(hlColor);
				g.drawRect(14, 0, 4, 14);
				break;
			case 2: // West
				g.setColor(Color.BLACK);
				g.drawRect(0, 13, 14, 6);
				g.setColor(hlColor);
				g.drawRect(0, 14, 14, 4);
				break;
			case 3: // South
				g.setColor(Color.BLACK);
				g.drawRect(13, 18, 6, 14);
				g.setColor(hlColor);
				g.drawRect(14, 18, 4, 14);
				break;
			}
		}
		
		
		
		//TODO Draw the wires into the texture buffered image
		return tex;
	}

	@Override
	public boolean animateBlock(GameBoard parent) {
		//TODO Wire rotation animation
		if (animStep < 0) return false;
		animStep++;
		if (animStep > 29) {
			this.isLit(parent);
			animStep = -1;
		}
		return true;
	}
	
	public double getRotationRadians() {
		return ((double) animStep / 30.0) * (Math.PI / 2.0);
	}

	@Override
	public boolean isLit(GameBoard parent) {
		// Check blocks that we are connected to for lit and relight accordingly
		//TODO TEST WireBlock::isLit
		if (lit) return true;
		for (int i = 0; i < 4; i++) {
			int d_x = this._x;
			int d_y = this._y;
			int r = i + this.wire_rotation;
			if (r > 3) r -= 4;
			//TODO LOW PRIORITY Wrapping (Blocks connect to other side of board)
			switch (r) {
			case 0:
				d_x++;
				if (d_x > GameBoard.getBoardWidth()) return false;
				break;
			case 1:
				d_y--;
				if (d_y < 0) return false;
				break;
			case 2:
				d_x--;
				if (d_x < 0) return false;
				break;
			case 3:
				d_y++;
				if (d_y > GameBoard.getBoardHeight()) return false;
				break;
			}
			if (parent.getBlockAt(d_x, d_y).isLit(parent)) {
				lit = true;
				return true;
			}
		}
		lit = false;
		return false;
	}

}
