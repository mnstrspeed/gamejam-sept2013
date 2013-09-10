package game.objects;

import game.CollisionType;
import game.GameUpdateRequest;
import game.keys.KeySetting;
import game.objects.resources.Resource;
import game.objects.resources.ResourceKind;
import game.objects.towers.TowerKind;
import gui.sprites.SpriteID;
import io.hid.MovementController;

import java.util.Arrays;

import nl.tomsanders.game.engine.util.Size;
import nl.tomsanders.game.engine.util.Vector;

public class Player extends GameObject {
	
	public enum MovementDirection {
		Left, Right, Jump, PutTower, UpgradeTowers;
	}
	
//	public static final Size PLAYER_BOUNDS_SIZE = new Size(30, 50);
	public static final int PLAYER_H_SPEED = 80;
	public static final int PLAYER_V_SPEED = 550;
	public static final int PLAYER_MAX_H_SPEED = 450;
	public static final int PLAYER_MAX_V_SPEED = 1400;
	
	private TowerKind currentTowerType;
	private String name;
	private MovementController movementController;
	private int[] resourceAmounts;
	private long lastTowerDrop = 0;
	private long lastTowerUpgrade = 0;
	
	private SpriteID sidLeft;
	private SpriteID sidRight;
	
	public Player(String name, Vector position, Size size, int[] resourceAmounts, KeySetting keysettings, SpriteID sidLeft, SpriteID sidRight) {
		super(position, size, sidRight);
		this.currentTowerType = TowerKind.BananaTower;
		this.setSubjectToGravity(true);
		this.setSubjectToFriction(true);
		this.name = name;
		this.movementController = new MovementController(this, keysettings);
		this.resourceAmounts = resourceAmounts;
		
		this.sidLeft = sidLeft;
		this.sidRight = sidRight;
	}
	
	public String getName() {
		
		return this.name;
	}
	
	public MovementController getMovementController() {
		return this.movementController;
	}
	
	public TowerKind getCurentTowerType() {
		return this.currentTowerType;
	}
	
	public void move(MovementDirection direction) {
		switch(direction) {
		case Left:
			if (this.getVelocity().getX() > 0)
				this.setVelocity(new Vector(0, this.getVelocity().y));
			this.setVelocity(this.getVelocity().translate(
					new Vector(-PLAYER_H_SPEED, 0)).boundedTranslate(PLAYER_MAX_H_SPEED, PLAYER_MAX_V_SPEED));
			this.setSID(this.sidLeft);
			break;
		case Right:
			if (this.getVelocity().getX() < 0)
				this.setVelocity(new Vector(0, this.getVelocity().y));
			this.setVelocity(this.getVelocity().translate(
					new Vector(PLAYER_H_SPEED, 0)).boundedTranslate(PLAYER_MAX_H_SPEED, PLAYER_MAX_V_SPEED));
			this.setSID(this.sidRight);
			break;
		case Jump:
			if (this.getVelocity().getY() == 0 && !this.isInAir())
				this.setVelocity(this.getVelocity().translate(
						new Vector(0, PLAYER_V_SPEED)).boundedTranslate(PLAYER_MAX_H_SPEED, PLAYER_MAX_V_SPEED));
		
			break;
		case PutTower:
			if((System.currentTimeMillis() >= this.lastTowerDrop + 1000) && 
					this.getResourceAmount(ResourceKind.Wood) >= TowerKind.getCost(ResourceKind.Wood, currentTowerType) && 
					this.getResourceAmount(ResourceKind.Life) >= TowerKind.getCost(ResourceKind.Life, currentTowerType) && 
					this.getResourceAmount(ResourceKind.Stone) >= TowerKind.getCost(ResourceKind.Stone, currentTowerType)) {
				this.spentResource(ResourceKind.Wood, TowerKind.getCost(ResourceKind.Wood, currentTowerType));
				this.spentResource(ResourceKind.Stone, TowerKind.getCost(ResourceKind.Stone, currentTowerType));
				this.spentResource(ResourceKind.Life, TowerKind.getCost(ResourceKind.Life, currentTowerType));
				this.lastTowerDrop = System.currentTimeMillis();
				updateRequests.add(new GameUpdateRequest(currentTowerType, this.getPosition(), this));
			}
		break;
		case UpgradeTowers:
			if(this.currentTowerType.ordinal() < TowerKind.values().length - 1 && System.currentTimeMillis() >= this.lastTowerUpgrade + 1000) {
				this.currentTowerType = TowerKind.values()[this.currentTowerType.ordinal() + 1];
				this.lastTowerUpgrade = System.currentTimeMillis();
				System.out.println("Current Tower type: " + this.currentTowerType);
			}
		}
	}
	
	public int getResourceAmount(ResourceKind resource) {
		return this.resourceAmounts[resource.ordinal()];
	}
	
	public void collectResource(ResourceKind resource, int amount) {
		this.resourceAmounts[resource.ordinal()] += amount;
	}
	
	public void spentResource(ResourceKind resource, int amount) {
		this.resourceAmounts[resource.ordinal()] -= amount;
	}
	
	@Override
	public String toString() {
		return "Player at " + this.getPosition() + " with resources: " + Arrays.toString(resourceAmounts);
	}
	
	@Override
	public boolean ignoreCollisionWith(CollisionType ct) {
		switch(ct) {
			case Player: return true; 
			case Tower: return true; 
			case Wall: return false;
			case Resource: return true;
			case Ammo: return true;
			default: return true; 
		}
	}

	@Override
	public void hasCollidedWith(CollisionType ct, GameObject o) {
		switch(ct) {
		case Player: break; 
		case Tower: break; 
		case Wall: break;
		case Resource:
			this.collectResource(((Resource)o).getResourceKind(), 1);
			updateRequests.add(new GameUpdateRequest(o, GameUpdateRequest.UpdateRequestType.removeRequest));
			break;
		case Ammo: 
			this.respawn();
			updateRequests.add(new GameUpdateRequest(o, GameUpdateRequest.UpdateRequestType.removeRequest)); 
			break;
		default: break; 
	}
	}

	private void respawn() {
		this.spentResource(ResourceKind.Life, 1);
	}
	
	@Override
	public CollisionType getCollisionType() {
		return CollisionType.Player;
	}
	
	public boolean isAlive() {
		return getResourceAmount(ResourceKind.Life) > 0;
	}
	
	public void burnOneLife() {
		this.spentResource(ResourceKind.Life, 1);
	}
}
