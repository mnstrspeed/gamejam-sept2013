package game;

import java.util.ArrayList;

import nl.tomsanders.game.engine.util.Size;
import nl.tomsanders.game.engine.util.Vector;
import game.objects.GameObject;
import io.file.LevelImporter;

public class Level {
	private Size size;
	private ArrayList<Vector> playerStarts;
	private ArrayList<GameObject> objects;
	
	public static Level load(String filename) {
		return LevelImporter.importLevel(filename);
	}
	
	public Level(Size size) {
		this.size = size;
		this.playerStarts = new ArrayList<Vector>();
		this.objects = new ArrayList<GameObject>();
	}
	
	public void addPlayerStart(Vector playerStart) {
		this.playerStarts.add(playerStart);
	}
	
	public ArrayList<Vector> getPlayerStarts() {
		return this.playerStarts;
	}
	
	public void addGameObject(GameObject object) {
		objects.add(object);
	}
	
	public void removeGameObject(GameObject object) {
		objects.remove(object);
	}
	
	public ArrayList<GameObject> getObjects() {
		return objects;
	}
	
	/**
	 * Displays the elements within this level as String. This function is for debug purposes only.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(GameObject object : this.objects) {
			sb.append(object.toString() + "\n");
		}
		return sb.toString();
	}
	
}
