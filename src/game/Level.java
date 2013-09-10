package game;

import java.util.ArrayList;
import java.util.Arrays;

import nl.tomsanders.game.engine.util.Size;
import nl.tomsanders.game.engine.util.Vector;
import game.objects.GameObject;
import game.objects.resources.ResourceKind;
import io.file.LevelImporter;

public class Level {
	private Size size;
	private int[] startResourceAmounts;
	private ArrayList<Vector> playerStarts;
	private ArrayList<GameObject> objects;
	
	public static Level load(String filename) {
		return LevelImporter.importLevel(filename);
	}
	
	public Level(Size size) {
		this.size = size;
		this.startResourceAmounts = new int[ResourceKind.values().length];
		Arrays.fill(startResourceAmounts, 0);
		this.playerStarts = new ArrayList<Vector>();
		this.objects = new ArrayList<GameObject>();
	}
	
	public int[] getStartResourceAmounts() {
		return this.startResourceAmounts;
	}
	
	public void setStartResourceAmount(ResourceKind resource, int amount) {
		this.startResourceAmounts[resource.ordinal()] = amount;
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
	
	public Size getSize() {
		return size;
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
