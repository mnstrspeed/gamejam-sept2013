package gui.sprites;

import java.awt.geom.AffineTransform;



public class LeftPlayer1Sprite extends Sprite {
	private static final String[] IMG_FILES = new String[] {"res/img/player1.png", "res/img/player1b.png"};
	
	public LeftPlayer1Sprite() {
		super(LeftPlayer1Sprite.IMG_FILES, true);
	}
}
