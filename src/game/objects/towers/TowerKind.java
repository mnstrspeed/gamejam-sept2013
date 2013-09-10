package game.objects.towers;

import java.util.ArrayList;

import nl.tomsanders.game.engine.util.Size;
import nl.tomsanders.game.engine.util.Vector;
import factories.AxeTowerFactory;
import factories.BananaTowerFactory;
import factories.ModernTowerFactory;
import factories.TowerFactory;
import game.objects.resources.ResourceKind;

public enum TowerKind {
	BananaTower(BananaTowerFactory.class, true),
	AxeTower(AxeTowerFactory.class, true),
	ModernTower(ModernTowerFactory.class, true);
	
	private final static int TOWER_HEIGHT = 64;
	private final static int TOWER_WIDTH = 32;
	
	private Class<? extends TowerFactory> factoryClass;
	private boolean	isLaunchable;
	private ArrayList<Vector> velocity;
	
	private TowerKind(Class<? extends TowerFactory> factoryClass, boolean isLaunchable) {
		this.factoryClass = factoryClass;
		this.isLaunchable = isLaunchable;
	}
	public TowerFactory getFactory() {
		try {
			return this.factoryClass.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean isLaunchable() {
		return this.isLaunchable;
	}
	
	public ArrayList<Vector> velocity() {
		return this.velocity;
	}
	
	
	public static int getCost(ResourceKind resource, TowerKind currentTowerType) {
		switch (resource) {
			case Life: return getLifeCost(currentTowerType);
			case Stone: return getStoneCost(currentTowerType);
			case Wood: return getWoodCost(currentTowerType);
			default: return 0;
		}
	}
	
	public static String getAgeString(TowerKind towerKind) {
		switch (towerKind) {
		case BananaTower: return "Apes";
		case AxeTower: return "Middle Ages";
		case ModernTower: return "Golden Age";
		default: return towerKind.toString();
		}
	}
	
	private static int getWoodCost(TowerKind currentTowerType) {
		switch (currentTowerType) {
		case ModernTower: return 2;
		case AxeTower: return 1;
		case BananaTower: return 1;
		default: return 0;
		}
	}
	
	private static int getStoneCost(TowerKind currentTowerType) {
		switch (currentTowerType) {
		case ModernTower: return 3;
		case AxeTower: return 2;
		default: return 0;
		}
	}
	
	private static int getLifeCost(TowerKind currentTowerType) {
		switch (currentTowerType) {
		default: return 0;
		}
	}
	
	public static Size getSize() {
		return new Size(TOWER_WIDTH, TOWER_HEIGHT);
	}
	
}
