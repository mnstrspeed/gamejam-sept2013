package gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import nl.tomsanders.game.engine.util.Size;
import nl.tomsanders.game.engine.util.Vector;

public class DummySprite implements Renderable {
	
	@Override
	public void render(Graphics2D graphics, Vector position, Size size) {
		Random random = new Random();
		
		graphics.setColor(new Color(
				random.nextFloat(), 
				random.nextFloat(), 
				random.nextFloat()));
		graphics.fill(new Rectangle2D.Double(
				position.getX(), position.getY(),
				size.getWidth(), size.getHeight()));
	}

}
