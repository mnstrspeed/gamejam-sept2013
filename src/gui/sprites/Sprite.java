package gui.sprites;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gui.Renderable;
import nl.tomsanders.game.engine.util.Size;
import nl.tomsanders.game.engine.util.Vector;

/**
 * Renders a graphic
 * @author tsanders
 *
 */
public abstract class Sprite implements Renderable {
	public static final int ANIMATION_TIME = 16;
	
	protected BufferedImage[] img;
	private int nextImg;
	private int counterImg;
	private Size size;
	
	private AffineTransform transform;
	
	public Sprite(String[] imgFiles) {
		this(imgFiles, false);
	}
	
	public Sprite(String[] imgFiles, boolean flip) {
		this.img = new BufferedImage[imgFiles.length];
		for(int i=0; i<imgFiles.length; i++) {
			img[i] = loadImage(imgFiles[i]);
			if (flip) {
				img[i] = flip(img[i]);
			}
		}
		this.nextImg = 0;
		this.counterImg = 0;
		this.transform = new AffineTransform();
	}
	
	private BufferedImage flip(BufferedImage image) {
		int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage dimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = dimg.createGraphics();

        g.drawImage(image, 0, 0, w, h, w, 0, 0, h, null);
        g.dispose();
        return dimg;
	}

	public void setTransform(AffineTransform transform) {
		this.transform = transform;
	}
	
	public AffineTransform getTransform() {
		return this.transform;
	}
	
	public Size getSize() {
		return this.size;
	}
	
	public BufferedImage loadImage(String imgFile) {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(imgFile));
		    this.size = new Size(img.getWidth(), img.getHeight());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	
	@Override
	public void render(Graphics2D graphics, Vector position, Size size) {
		AffineTransform previous = graphics.getTransform();
		AffineTransform adjustedTransform = graphics.getTransform();
		adjustedTransform.concatenate(this.transform);
		
		graphics.setTransform(adjustedTransform);
		graphics.drawImage(this.img[this.nextImg], (int) position.getX(), (int) position.getY(), (int) size.getWidth(), (int) size.getHeight(), null);
		counterImg++;
		if(counterImg == Sprite.ANIMATION_TIME / this.img.length) {
			this.nextImg = (this.nextImg + 1) % this.img.length;
			counterImg = 0;
		}
		
		graphics.setTransform(previous);
	}
}
