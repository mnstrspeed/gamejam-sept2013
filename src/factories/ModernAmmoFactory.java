package factories;

import game.objects.GameObject;
import game.objects.ammo.ModernAmmo;

import java.util.ArrayList;

import nl.tomsanders.game.engine.util.Vector;

public class ModernAmmoFactory extends AmmoFactory{

	public ModernAmmo create(Vector position, Vector velocity) {
		return new ModernAmmo(new Vector(position), new Vector(velocity));
	}

	@Override
	public ArrayList<GameObject> create(Vector position, ArrayList<Vector> velocities) {
		ArrayList<GameObject> result = new ArrayList<GameObject>();
		for (Vector v : velocities) {
			result.add(new ModernAmmo(new Vector(position), new Vector(v)));
		}
		return result;
	}
}
