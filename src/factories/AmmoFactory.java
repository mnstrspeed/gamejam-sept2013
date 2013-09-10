package factories;

import java.util.ArrayList;

import nl.tomsanders.game.engine.util.Vector;
import game.Level;
import game.objects.GameObject;

public abstract class AmmoFactory {
	protected Level level;
	
	public abstract ArrayList<GameObject> create(Vector position, ArrayList<Vector> arrayList);
	public Level getLevel() {
		return this.level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
}
