package factories;

import nl.tomsanders.game.engine.util.Size;
import nl.tomsanders.game.engine.util.Vector;
import game.objects.Player;
import game.objects.towers.AxeTower;
import game.objects.towers.ModernTower;

public class ModernTowerFactory extends TowerFactory{

	public ModernTower create(Vector position, Size size, Player owner) {
		return new ModernTower(new Vector(position), new Size(size), owner);
	}

}
