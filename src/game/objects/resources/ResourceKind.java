package game.objects.resources;

import factories.ResourceFactory;
import factories.StoneFactory;
import factories.WoodFactory;

public enum ResourceKind {
	Life(null, false), Wood(WoodFactory.class, true), Stone(StoneFactory.class, true);
	
	private Class<? extends ResourceFactory> factoryClass;
	private boolean	isLaunchable;
	
	private ResourceKind(Class<? extends ResourceFactory> factoryClass, boolean isLaunchable) {
		this.factoryClass = factoryClass;
		this.isLaunchable = isLaunchable;
	}
	
	public ResourceFactory getFactory() {
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
}
