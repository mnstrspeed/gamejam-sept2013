package game;

import java.util.ArrayList;

import nl.tomsanders.game.engine.util.Vector;
import factories.AmmoFactory;
import game.objects.GameObject;
import game.objects.ammo.AmmoKind;
import game.objects.towers.AxeTower;
import game.objects.towers.BananaTower;
import game.objects.towers.ModernTower;
import game.objects.towers.Tower;

public class AmmoLauncher {
	private Level level;
	
	public AmmoLauncher(Level level) {
		this.level = level;
	}
	
	public void launch() {
		ArrayList<GameObject> toLaunch = new ArrayList<GameObject>();
		for (GameObject o : level.getObjects()) {
			if (o instanceof BananaTower) {
				
				toLaunch.addAll(AmmoKind.Banana.getFactory().create(o.getPosition().translate(new Vector(0,30)), AmmoKind.Banana.velocity()));
			}
			else if (o instanceof AxeTower) {
				toLaunch.addAll(AmmoKind.Axes.getFactory().create(o.getPosition().translate(new Vector(0,30)), AmmoKind.Axes.velocity()));
			}
			else if (o instanceof ModernTower) {
				toLaunch.addAll(AmmoKind.Modern.getFactory().create(o.getPosition().translate(new Vector(0,30)), AmmoKind.Modern.velocity()));
			}
		}
		for (GameObject o : toLaunch) {
			level.addGameObject(o);
		}			
	}

}
