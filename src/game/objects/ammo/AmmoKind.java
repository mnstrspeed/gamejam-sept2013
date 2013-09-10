package game.objects.ammo;

import java.util.ArrayList;

import nl.tomsanders.game.engine.util.Vector;
import factories.AmmoFactory;
import factories.AxesFactory;
import factories.BananaFactory;
import factories.ModernAmmoFactory;

public enum AmmoKind {
	Banana(BananaFactory.class, true, createBananaAmmo()),
	Axes(AxesFactory.class, true, createAxeAmmo()),
	Modern(ModernAmmoFactory.class, true, createModernAmmo());
	
	private Class<? extends AmmoFactory> factoryClass;
	private boolean	isLaunchable;
	private ArrayList<Vector> velocity;
	
	private AmmoKind(Class<? extends AmmoFactory> factoryClass, boolean isLaunchable, ArrayList<Vector> velocity) {
		this.factoryClass = factoryClass;
		this.isLaunchable = isLaunchable;
		this.velocity = velocity;
	}
	
	public AmmoFactory getFactory() {
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
	
	private static ArrayList<Vector> createBananaAmmo() {
		ArrayList<Vector> bananaAmmo = new ArrayList<Vector>();
		bananaAmmo.add(new Vector(500, 350));
		bananaAmmo.add(new Vector(-500, 350));
		return bananaAmmo;
	}
	
	private static ArrayList<Vector> createAxeAmmo() {
		ArrayList<Vector> axeAmmo = new ArrayList<Vector>();
		axeAmmo.add(new Vector(800, 350));
		axeAmmo.add(new Vector(-500, 550));
		axeAmmo.add(new Vector(500, 550));
		axeAmmo.add(new Vector(-800, 350));
		return axeAmmo;
	}
	
	private static ArrayList<Vector> createModernAmmo() {
		ArrayList<Vector> modernAmmo = new ArrayList<Vector>();
		modernAmmo.add(new Vector(800, 0));
		modernAmmo.add(new Vector(600, 0));
		modernAmmo.add(new Vector(400, 0));
		modernAmmo.add(new Vector(200, 0));
//		modernAmmo.add(new Vector(-500, 550));
//		modernAmmo.add(new Vector(500, 550));
		modernAmmo.add(new Vector(-800, 0));
		modernAmmo.add(new Vector(-600, 0));
		modernAmmo.add(new Vector(-400, 0));
		modernAmmo.add(new Vector(-200, 0));
		return modernAmmo;
	}
}
