package factories;

import nl.tomsanders.game.engine.util.Vector;
import game.objects.resources.Wood;

public class WoodFactory extends ResourceFactory{

	public Wood create() {
		return new Wood(new Vector((int) (Math.random() * this.getLevel().getSize().getWidth()), (int) (Math.random() * this.getLevel().getSize().getHeight())));
	}
}
