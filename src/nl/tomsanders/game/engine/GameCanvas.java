package nl.tomsanders.game.engine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

@SuppressWarnings("serial")
public class GameCanvas extends DoubleBufferedCanvas {
	private Game game;
	
	public GameCanvas(Game game) {
		this.game = game;
	}
	
	@Override
	public void paint(Graphics g) {
		System.out.println("GameCanvas.paint (" + this.getSize().getWidth() + ", " + this.getSize().getHeight() + ")");
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		this.game.render(g2);
	}
}
