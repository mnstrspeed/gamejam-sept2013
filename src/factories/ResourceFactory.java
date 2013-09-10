package factories;

import game.Level;
import game.objects.GameObject;

public abstract class ResourceFactory {
	protected Level level;
	
	public abstract GameObject create();
	public Level getLevel() {
		return this.level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
}
