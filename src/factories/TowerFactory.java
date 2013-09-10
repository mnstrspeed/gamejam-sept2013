package factories;

import nl.tomsanders.game.engine.util.Size;
import nl.tomsanders.game.engine.util.Vector;
import game.Level;
import game.objects.GameObject;
import game.objects.Player;

public abstract class TowerFactory {
	protected Level level;
	
	public abstract GameObject create(Vector position, Size size, Player owner);
	
	public Level getLevel() {
		return this.level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
}
