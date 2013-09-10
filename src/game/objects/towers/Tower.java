package game.objects.towers;

import game.CollisionType;
import game.objects.GameObject;
import game.objects.Player;
import game.objects.resources.ResourceKind;
import gui.sprites.SpriteID;
import nl.tomsanders.game.engine.util.Size;
import nl.tomsanders.game.engine.util.Vector;

public abstract class Tower extends GameObject{
	private Player owner;
	
	public Tower(Vector position, Size size, Player owner, SpriteID sid) {
		super(position, size, sid);
		super.setSubjectToGravity(true);
		this.owner = owner;
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
		return CollisionType.Tower;
	}
	
	public Player getOwner() {
		return owner;
	}
}
