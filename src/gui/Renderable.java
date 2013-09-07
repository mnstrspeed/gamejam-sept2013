package gui;

import java.awt.Graphics2D;

import nl.tomsanders.game.engine.util.Size;
import nl.tomsanders.game.engine.util.Vector;

public interface Renderable {
	public void render(Graphics2D graphics, Vector position, Size size);
}
