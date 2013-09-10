package factories;

import java.util.ArrayList;

import nl.tomsanders.game.engine.util.Vector;
import game.objects.GameObject;
import game.objects.ammo.Banana;

public class BananaFactory extends AmmoFactory{

	public Banana create(Vector position, Vector velocity) {
		return new Banana(new Vector(position), new Vector(velocity));
	}

	@Override
	public ArrayList<GameObject> create(Vector position, ArrayList<Vector> velocities) {
		ArrayList<GameObject> result = new ArrayList<GameObject>();
		for (Vector v : velocities) {
			result.add(new Banana(new Vector(position), new Vector(v)));
		}
		return result;
	}
}
