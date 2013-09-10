package factories;

import nl.tomsanders.game.engine.util.Vector;
import game.objects.resources.Stone;

public class StoneFactory extends ResourceFactory{

	public Stone create() {
		return new Stone(new Vector((int) (Math.random() * this.getLevel().getSize().getWidth()), (int) (Math.random() * this.getLevel().getSize().getHeight())));
	}
}
