package gui.sprites;

import gui.Renderable;


public class DummySprite extends Sprite implements Renderable {
	private static final String[] IMG_FILES = new String[] {"res/img/topgrond.png"};
	
	public DummySprite() {
		super(DummySprite.IMG_FILES);
	}

}
