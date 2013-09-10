package gui.sprites;

import gui.Renderable;

public class LifeSprite extends Sprite implements Renderable {
	private static final String[] IMG_FILES = new String[] {"res/img/life.png"};
	
	public LifeSprite() {
		super(LifeSprite.IMG_FILES);
	}
}