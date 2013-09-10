package factories;

import nl.tomsanders.game.engine.util.Size;
import nl.tomsanders.game.engine.util.Vector;
import game.objects.Player;
import game.objects.towers.BananaTower;

public class BananaTowerFactory extends TowerFactory{

	public BananaTower create(Vector position, Size size, Player owner) {
		return new BananaTower(new Vector(position), new Size(size), owner);
	}

}
