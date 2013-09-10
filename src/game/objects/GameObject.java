package game.objects;

import java.util.ArrayList;

import game.CollisionType;
import game.GameUpdateRequest;
import gui.sprites.SpriteID;
import nl.tomsanders.game.engine.util.Rectangle;
import nl.tomsanders.game.engine.util.Size;
import nl.tomsanders.game.engine.util.Vector;

public abstract class GameObject implements Comparable<GameObject> {
	private Vector position;
	private Vector velocity;
	private Size size;
	private SpriteID sid;
	
	private boolean subjectToGravity;
	private boolean subjectToFriction;
	private boolean isInAir;
	
	protected ArrayList<GameUpdateRequest> updateRequests;
	
	public GameObject(Vector position, Size size, SpriteID sid) {
		this(position, Vector.zero(), size, sid);
	}
	
	public GameObject(Vector position, Vector velocity, Size size, SpriteID sid) {
		this.updateRequests = new ArrayList<GameUpdateRequest>();
		this.sid = sid;
		this.position = position;
		this.velocity = velocity;
		this.size = size;
		
		this.subjectToGravity = false;
		this.subjectToFriction = false;
		this.isInAir = false;
	}
	
	public void setSubjectToGravity(boolean subjectToGravity) {
		this.subjectToGravity = subjectToGravity;
	}
	
	public void setSubjectToFriction(boolean subjectToFriction) {
		this.subjectToFriction = subjectToFriction;
	}
	
	public void setIsInAir(boolean isInAir) {
		this.isInAir = isInAir;
	}
	
	public boolean isSubjectToGravity() {
		return this.subjectToGravity;
	}
	
	public boolean isSubjectToFriction() {
		return this.subjectToFriction;
	}
	
	public boolean isInAir() {
		return this.isInAir;
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
	
	public void setSID(SpriteID sid) {
		this.sid = sid;
	}
	
	public abstract boolean ignoreCollisionWith(CollisionType ct);
	public abstract void hasCollidedWith(CollisionType ct, GameObject object);
	public abstract CollisionType getCollisionType();
	
	public boolean hasUpdateRequests() {
		return !updateRequests.isEmpty();
	}
	
	public ArrayList<GameUpdateRequest> getUpdateRequests() {
		return updateRequests;
	}
	
	@Override
	public int compareTo(GameObject b) {
		return b.hashCode() - this.hashCode();
	}
	
	public void emptyRequests() {
		updateRequests.clear();
	}
}
