package game;

import factories.TowerFactory;
import game.objects.GameObject;
import game.objects.Player;
import game.objects.towers.TowerKind;
import nl.tomsanders.game.engine.util.Vector;

public class GameUpdateRequest {
	public enum UpdateRequestType {addRequest, removeRequest};

	private UpdateRequestType type;
	private GameObject object;
	
	/** Removal request */
	public GameUpdateRequest(GameObject object, UpdateRequestType type) {
		this.object = object;
		this.type = type;
	}
	
	public GameUpdateRequest(TowerKind towerKind, Vector position, Player owner) {
		this.type = UpdateRequestType.addRequest;
		TowerFactory towerFactory = towerKind.getFactory();
		GameObject resource = towerFactory.create(position, TowerKind.getSize(), owner);
		this.object = resource;
	}

	public UpdateRequestType getType() {
		return type;
	}

	public GameObject getObject() {
		return object;
	}
}
