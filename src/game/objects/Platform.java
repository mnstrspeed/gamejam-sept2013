package game.objects;

import game.CollisionType;
import gui.sprites.SpriteID;
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

	@Override
	public boolean ignoreCollisionWith(CollisionType ct) {
		switch(ct) {
			case Player: return false; 
			case Tower: return false; 
			case Wall: return false;
			case Resource: return false;
			case Ammo: return false;
			default: return false; 
		}
	}

	@Override
	public void hasCollidedWith(CollisionType ct, GameObject object) {
		switch(ct) {
		case Player: break; 
		case Tower: break; 
		case Wall: break;
		case Resource: break;
		case Ammo: break;
		default: break; 
	}
	}

	@Override
	public CollisionType getCollisionType() {
		return CollisionType.Wall;
	}

}
