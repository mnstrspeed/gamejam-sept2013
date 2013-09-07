package nl.tomsanders.game.engine;

import game.Level;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyListener;


public interface GameWindow {
	public void renderFrame();
	public Rectangle getRenderBounds();
	public void setVisible(boolean visible);
	public void addKeyListener(KeyListener l);
	public void removeKeyListener(KeyListener l);
	public void setTitle(String title);
	public void render(Graphics2D g, Level level);
	public void setTarget(Game game);
}
