package gui;

import game.objects.Player;
import game.objects.resources.ResourceKind;
import game.objects.towers.TowerKind;
import gui.sprites.LifeSprite;
import gui.sprites.StoneSprite;
import gui.sprites.WoodSprite;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.text.DecimalFormat;

import nl.tomsanders.game.engine.util.Size;
import nl.tomsanders.game.engine.util.Vector;

public class ScoreOverlay implements Renderable {
	private Player player;
	
	private StoneSprite stone;
	private WoodSprite wood;
	private LifeSprite life;
	
	private Image resourceDisplay;
	private boolean alignRight;
	
	public ScoreOverlay(Player player, boolean alignRight) {
		this.player = player;
		this.alignRight = alignRight;
		
		this.stone = new StoneSprite();
		this.wood = new WoodSprite();
		this.life = new LifeSprite();
		
		this.resourceDisplay = new BufferedImage(88, 10, BufferedImage.TYPE_INT_ARGB);
		Graphics2D resourceDisplayGraphics = (Graphics2D)this.resourceDisplay.getGraphics();
		AffineTransform transform = new AffineTransform();
		transform.translate(0, 10);
		transform.scale(1.0, -1.0);
		resourceDisplayGraphics.setTransform(transform);

		//resourceDisplayGraphics.setColor(Color.RED);
		//resourceDisplayGraphics.fillRect(0, 0, 88, 10);
		this.wood.render(resourceDisplayGraphics, new Vector(0, 1), new Size(10, 10));
		this.stone.render(resourceDisplayGraphics, new Vector(30, 1), new Size(10, 10));
		this.life.render(resourceDisplayGraphics, new Vector(60, 0), new Size(10, 10));
	}
	
	@Override
	public void render(Graphics2D graphics, Vector position, Size size) {
		Image image = new BufferedImage(100, 50, BufferedImage.TYPE_INT_ARGB);
		Graphics2D imageGraphics = (Graphics2D)image.getGraphics();
		imageGraphics.setFont(new Font(graphics.getFont().getFontName(), Font.BOLD, 10));
		
		// Draw player name
		this.drawString(imageGraphics, this.player.getName().toUpperCase(), 0, 0, image.getWidth(null));
		
		// Draw resources
		int resouceOffset = (this.alignRight ? image.getWidth(null) - this.resourceDisplay.getWidth(null) : 0);
		imageGraphics.drawImage(this.resourceDisplay, resouceOffset, 18, null);
		DecimalFormat numberFormat = new DecimalFormat("00");
		this.drawStaticString(imageGraphics, numberFormat.format(this.player.getResourceAmount(ResourceKind.Wood)), 12 + resouceOffset, 14);
		this.drawStaticString(imageGraphics, numberFormat.format(this.player.getResourceAmount(ResourceKind.Stone)), 42 + resouceOffset, 14);
		this.drawStaticString(imageGraphics, numberFormat.format(this.player.getResourceAmount(ResourceKind.Life)), 72 + resouceOffset, 14); // 88
		
		// Draw tower type
		this.drawString(imageGraphics, TowerKind.getAgeString(this.player.getCurentTowerType()).toUpperCase(), 0, 28, image.getWidth(null));
		
		// 
		
		AffineTransform previous = graphics.getTransform();
		graphics.setTransform(AffineTransform.getScaleInstance(2, 2));
		graphics.drawImage(image, (int)position.getX(), (int)position.getY(), null);
		graphics.setTransform(previous);
	}
	
	private void drawString(Graphics2D graphics, String text, int x, int y, int width) {
		int offsetX = 0;
		if (this.alignRight) {
			offsetX = width - graphics.getFontMetrics().stringWidth(text);
		}
		this.drawStaticString(graphics, text, offsetX, y);
	}
	
	private void drawStaticString(Graphics2D graphics, String text, int x, int y) {
		int offsetY = graphics.getFontMetrics().getHeight();
		
		graphics.setColor(Color.GRAY);
		graphics.drawString(text, x + 1, y + offsetY + 1);
		graphics.setColor(Color.WHITE);
		graphics.drawString(text, x, y + offsetY);
	}

}
