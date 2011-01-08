package com.schneenet.networks;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class RouterBlock extends WireBlock {

	@Override
	public Image getTextureState() {
		// Start with wires underneath
		BufferedImage tex = new BufferedImage(GameBoard.GRID_SIZE, GameBoard.GRID_SIZE, BufferedImage.TYPE_4BYTE_ABGR);
		Image wires = super.getTextureState();
		tex.getGraphics().drawImage(wires, 0, 0, null);
		//TODO RouterBlock Texture
		
		return tex;
	}
}
