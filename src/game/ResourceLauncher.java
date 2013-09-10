package game;

import factories.ResourceFactory;
import game.objects.GameObject;
import game.objects.resources.ResourceKind;

public class ResourceLauncher {
	private Level level;
	
	public ResourceLauncher(Level level) {
		this.level = level;
	}
	
	public void launch() {
		//Generate random launchable resource
		ResourceKind resourceKind;
		do {
			resourceKind = ResourceKind.values()[(int)(Math.random() * ResourceKind.values().length)];
		} while(!resourceKind.isLaunchable());
		ResourceFactory resourceFactory = resourceKind.getFactory();
		resourceFactory.setLevel(this.level);
		GameObject resource = resourceFactory.create();
		level.addGameObject(resource);
	}

}
