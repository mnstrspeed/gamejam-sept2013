package gui.sprites;

import gui.Renderable;


public class WoodSprite extends Sprite implements Renderable {
	private static final String[] IMG_FILES = new String[] {"res/img/wood.png"};
	
	public WoodSprite() {
		super(WoodSprite.IMG_FILES);
	}
}
