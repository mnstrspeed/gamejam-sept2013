package gui.sprites;

import gui.Renderable;


public class StoneSprite extends Sprite implements Renderable {
	private static final String[] IMG_FILES = new String[] {"res/img/stone.png"};
	
	public StoneSprite() {
		super(StoneSprite.IMG_FILES);
	}
}
