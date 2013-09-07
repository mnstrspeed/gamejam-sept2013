package game.objects;

import java.awt.Dimension;

import nl.tomsanders.game.engine.util.Size;
import nl.tomsanders.game.engine.util.Vector;
import game.SpriteID;
import io.hid.MovementController;

public class Player extends GameObject {
	public enum MovementDirection {
		Left, Right, Jump;
	}
	
	public static final Dimension PLAYER_BOUNDS_SIZE = new Dimension(30, 50);
	public static final int PLAYER_SPEED = 80;
	
	private MovementController movementController;
	
	public Player(Vector position) {
		super(position, new Size(PLAYER_BOUNDS_SIZE.getWidth(), PLAYER_BOUNDS_SIZE.getHeight()), SpriteID.Player1);
		
		this.setSubjectToGravity(true);
		
		this.movementController = new MovementController(this);
	}
	
	public MovementController getMovementController() {
		return this.movementController;
	}
	
	public void move(MovementDirection direction) {
		switch(direction) {
		case Left:
			this.setVelocity(this.getVelocity().translate(new Vector(PLAYER_SPEED, 0)));
			break;
		case Right:
			this.setVelocity(this.getVelocity().translate(new Vector(-PLAYER_SPEED, 0)));
			break;
		case Jump:
			this.setVelocity(this.getVelocity().translate(new Vector(0, -PLAYER_SPEED)));
			break;
		}
	}
}
