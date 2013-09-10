package game.objects.ammo;

import game.CollisionType;
import game.GameUpdateRequest;
import game.objects.GameObject;
import game.objects.Player;
import gui.sprites.SpriteID;
import nl.tomsanders.game.engine.util.Size;
import nl.tomsanders.game.engine.util.Vector;

public class Banana extends GameObject {
	
	public Banana(Vector position, Vector velocity) {
		super(position, new Size(18, 18), SpriteID.Banana);
		this.setVelocity(velocity);
		this.setSubjectToGravity(true);
	}
	
	public String toString() {
		return "Banana at " + this.getPosition();
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
		case Player: updateRequests.add(new GameUpdateRequest(this, GameUpdateRequest.UpdateRequestType.removeRequest)); 
					 ((Player)object).burnOneLife(); break;
		case Tower: break; 
		case Wall: updateRequests.add(new GameUpdateRequest(this, GameUpdateRequest.UpdateRequestType.removeRequest)); break;
		case Resource: break;
		case Ammo: break;
		default: break; 
	}
	}

	@Override
	public CollisionType getCollisionType() {
		return CollisionType.Ammo;
	}
}
