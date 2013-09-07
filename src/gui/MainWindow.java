package gui;

import game.Level;
import game.objects.GameObject;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import nl.tomsanders.game.engine.Game;
import nl.tomsanders.game.engine.GameCanvas;
import nl.tomsanders.game.engine.GameWindow;

public class MainWindow extends JFrame implements GameWindow{
	private static final long serialVersionUID = 1L;
	private final int XSIZE = 1280;
	private final int YSIZE = 720;
	
	private GameCanvas canvas;
	
	public MainWindow() {
		super("Main Game Window");
		this.setTitle("SUPER AWESOME GAME");
		this.setLocationRelativeTo(null);
		this.setSize(XSIZE, YSIZE);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void renderFrame() {
		this.canvas.repaint();
	}

	public Rectangle getRenderBounds() {
		return this.canvas.getBounds();
	}
	
	public void render(Graphics2D g, Level level)  {
		System.out.println("MainWindow.render");
		for (GameObject o : level.getObjects()) {
			switch(o.getSID()) {
			case Player1: new DummySprite().render(g, o.getPosition(), o.getSize());
				break;
			case Player2: new DummySprite().render(g, o.getPosition(), o.getSize());
				break;
			case Wall: new DummySprite().render(g, o.getPosition(), o.getSize());
				break;
			default:
				break;
				
			}
		}
	}

	public void setTarget(Game game) {
		this.canvas = new GameCanvas(game);
		this.getContentPane().add(canvas);
	}

	@Override
	public void addKeyListener(KeyListener l) {
		super.addKeyListener(l);
	}

	@Override
	public void removeKeyListener(KeyListener l) {
		super.removeKeyListener(l);
		
	}
}
