package factories;

import java.util.ArrayList;

import nl.tomsanders.game.engine.util.Vector;
import game.objects.GameObject;
import game.objects.ammo.Axe;

public class AxesFactory extends AmmoFactory{

	public Axe create(Vector position, Vector velocity) {
		return new Axe(new Vector(position), new Vector(velocity));
	}

	@Override
	public ArrayList<GameObject> create(Vector position, ArrayList<Vector> velocities) {
		ArrayList<GameObject> result = new ArrayList<GameObject>();
		for (Vector v : velocities) {
			result.add(new Axe(new Vector(position), new Vector(v)));
		}
		return result;
	}
}
