package game;

import java.util.ArrayList;
import java.util.List;

import game.objects.GameObject;
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
	public void update(GameTime time, List<GameObject> objects) {
		
		for (GameObject object : objects) {
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
							// Collision detected
							collisions.add(b);
						}
					}
				}
				
				// Update position if there were no collisions
				if (collisions.isEmpty()) {
					object.setPosition(newPosition);
				} else {
					// Just don't move?
					// TODO: possibly align object with conflicting objects
				}
				
			}
		}
	}
}
