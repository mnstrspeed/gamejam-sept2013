package game.objects;

import game.SpriteID;
import nl.tomsanders.game.engine.util.Rectangle;
import nl.tomsanders.game.engine.util.Size;
import nl.tomsanders.game.engine.util.Vector;

public abstract class GameObject {
	private Vector position;
	private Vector velocity;
	private Size size;
	private SpriteID sid;
	
	private boolean subjectToGravity;
	
	public GameObject(Vector position, Size size, SpriteID sid) {
		this(position, Vector.zero(), size, sid);
	}
	
	public GameObject(Vector position, Vector velocity, Size size, SpriteID sid) {
		this.sid = sid;
		this.position = position;
		this.velocity = velocity;
		this.size = size;
		
		this.subjectToGravity = false;
	}
	
	public void setSubjectToGravity(boolean subjectToGravity) {
		this.subjectToGravity = subjectToGravity;
	}
	
	public boolean isSubjectToGravity() {
		return this.subjectToGravity;
	}
	
	public Vector getPosition() {
		return this.position;
	}
	
	public Vector getVelocity() {
		return this.velocity;
	}
	
	public Size getSize() {
		return this.size;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(this.position, this.size);
	}
	
	public void setPosition(Vector position) {
		this.position = position;
	}
	
	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}
	
	public void setSize(Size size) {
		this.size = size;
	}
	
	public SpriteID getSID() {
		return sid;
	}
}
