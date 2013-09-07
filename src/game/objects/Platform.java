package game.objects;

import game.SpriteID;
import nl.tomsanders.game.engine.util.Size;
import nl.tomsanders.game.engine.util.Vector;

public class Platform extends GameObject{
	public Platform(Vector position, Size size) {
		super(position, size, SpriteID.Wall);
	}
	
	@Override
	public String toString() {
		return "Platform at " + this.getPosition() + " with size " + this.getSize();
	}
}
