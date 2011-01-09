package com.schneenet.networks;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class ComputerBlock extends WireBlock {
	
	/**
	 * ComputerBlock constructor
	 * <br /><br />
	 * ComputerBlock is limited to a single wire (type 0)
	 * @param x X-coordinate
	 * @param y Y-coordinate
	 */
	public ComputerBlock(int x, int y) {
		super(x, y, 0);
	}

	@Override
	public Image getTextureState() {
		// Start with wires underneath
		BufferedImage tex = new BufferedImage(GameBoard.GRID_SIZE, GameBoard.GRID_SIZE, BufferedImage.TYPE_4BYTE_ABGR);
		Image wires = super.getTextureState();
		tex.getGraphics().drawImage(wires, 0, 0, null);
		//TODO ComputerBlock Texture
		
		return tex;
	}
	
}
