package game;

import java.util.ArrayList;
import java.util.List;

import game.objects.GameObject;
import game.objects.Player;
import nl.tomsanders.game.engine.GameTime;
import nl.tomsanders.game.engine.util.Rectangle;
import nl.tomsanders.game.engine.util.Vector;

/**
 * Physics controller updates the position of GameObjects in the game world
 * and checks for possible collisions. Also handles gravity.
 * @author tsanders
 *
 */
public class PhysicsController {
	private static final double PLAYER_GRAVITY = 1500; // pixels per second
	private static final double PLAYER_FRICTION = 2000;
	private Level lvl; 
	
	public void update(GameTime time, Level lvl) {
		this.lvl = lvl;
		
		List<GameObject> objects = lvl.getObjects();
		for (GameObject object : objects) {	
			// Gravity
			if (object.isSubjectToGravity()) {
				Vector force = new Vector(0, -PLAYER_GRAVITY);
				if (object.isSubjectToFriction() &&
						Math.abs(object.getVelocity().getX()) > 5) {
					Vector friction = new Vector(object.getVelocity().getX() > 0 ? -PLAYER_FRICTION : PLAYER_FRICTION, 0);
					force = force.translate(friction);
				}
				object.setVelocity(object.getVelocity().translate(force.scale(time.getSecondsElapsed())));
				object.setIsInAir(true);
			}
			
			// Velocity
			if (!object.getVelocity().isZero()) {
				
				// Calculate new position and new bounds
				Vector newPosition = object.getPosition()
						.translate(object.getVelocity().scale(time.getSecondsElapsed()));
				Rectangle newBounds = new Rectangle(newPosition, object.getSize());
				
				// Check for collisions with other GameObjects
				ArrayList<GameObject> collisions = new ArrayList<GameObject>();
				for (GameObject b : objects) {
					if (object != b) {

							// TODO: naive collision check: object may
							// go through objects if it has a high enough velocity
							if (newBounds.intersects(b.getBounds())) {
								if (!object.ignoreCollisionWith(b.getCollisionType()) && 
										!b.ignoreCollisionWith(object.getCollisionType())) {
								// Collision detected
								collisions.add(b);
							}
							object.hasCollidedWith(b.getCollisionType(), b);
						}
					}
				}
				
				// Update position if there were no collisions
				if (collisions.isEmpty()) {
					if(!(object instanceof Player) || object instanceof Player && this.isInLevelBounds(newBounds)) {
						object.setPosition(newPosition);
					}
				} else {
					boolean horizontalCollision = false;
					boolean verticalCollision = false;
					for (GameObject b : collisions) {
						double overlapY = newBounds.getTop() - b.getBounds().getBottom();
						double overlapMinY = b.getBounds().getTop() - newBounds.getBottom();
						double overlapX = newBounds.getRight() - b.getBounds().getLeft();
						double overlapMinX = b.getBounds().getRight() - newBounds.getLeft();
						
						if (Math.min(Math.abs(overlapX), Math.abs(overlapMinX)) < 
								Math.min(Math.abs(overlapY), Math.abs(overlapMinY))) {
							// Horizontal
							horizontalCollision = true;
							if (overlapX < overlapMinX) {
								// From left
								newPosition = newPosition.translate(new Vector(-overlapX, 0));
							} else {
								// From right
								newPosition = newPosition.translate(new Vector(overlapMinX, 0));
							}
						} else {
							// Vertical
							verticalCollision = true;
							if (object.getVelocity().getY() < 0) {
								// From top
								if (object.isSubjectToGravity()) {
									object.setIsInAir(false);
								}
								newPosition = newPosition.translate(new Vector(0, -overlapY));
							} else {
								// From bottom
								newPosition = newPosition.translate(new Vector(0, overlapMinY));
							}
						}
						newBounds = new Rectangle(newPosition, object.getSize());

						if(!(object instanceof Player) || object instanceof Player && this.isInLevelBounds(newBounds)) {
							object.setPosition(newPosition);
						}
						object.setVelocity(new Vector(
								horizontalCollision ? 0 : object.getVelocity().getX(), 
								verticalCollision ? 0 : object.getVelocity().getY()));
					}
				}
				
			}
		}
	}
	
	public static Vector getOverlap(Rectangle a, Rectangle b) {
		Vector overlap = Vector.zero();
		if (a.getBottom() >= b.getTop()) {
			overlap.translate(new Vector(0,
					a.getBottom() - b.getTop()));
		}
		return overlap;
	}
	
	private boolean isInLevelBounds(Rectangle pos) {
		return (pos.getBottomLeftCorner().getX() >= 0 && pos.getBottomRightCorner().getX() < lvl.getSize().getWidth() && pos.getBottomLeftCorner().getY() >= 0 
				&& pos.getTopLeftCorner().getY() < lvl.getSize().getHeight());
	}
}
