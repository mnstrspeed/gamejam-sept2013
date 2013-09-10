package factories;

import nl.tomsanders.game.engine.util.Size;
import nl.tomsanders.game.engine.util.Vector;
import game.objects.Player;
import game.objects.towers.AxeTower;

public class AxeTowerFactory extends TowerFactory{

	public AxeTower create(Vector position, Size size, Player owner) {
		return new AxeTower(new Vector(position), new Size(size), owner);
	}

}
