package game.objects.resources;

import game.CollisionType;
import game.objects.GameObject;
import gui.sprites.SpriteID;
import nl.tomsanders.game.engine.util.Size;
import nl.tomsanders.game.engine.util.Vector;

public class Stone extends GameObject implements Resource {
		
	public Stone(Vector position) {
		super(position, new Size(25, 25), SpriteID.Stone);
		
		this.setSubjectToGravity(true);
	}
	
	public String toString() {
		return "Stone at " + this.getPosition();
	}
	
	@Override
	public boolean ignoreCollisionWith(CollisionType ct) {
		switch(ct) {
			case Player: return true; 
			case Tower: return true; 
			case Wall: return false;
			case Resource: return true;
			case Ammo: return true;
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
		return CollisionType.Resource;
	}
	
	public ResourceKind getResourceKind () {
		return ResourceKind.Stone;
	}
}
