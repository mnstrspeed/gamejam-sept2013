package game;

import game.keys.KeyMappingArrows;
import game.keys.KeyMappingWASD;
import game.keys.KeySetting;
import game.objects.GameObject;
import game.objects.Player;
import gui.ScoreOverlay;
import gui.sprites.AxeSprite;
import gui.sprites.AxeTowerSprite;
import gui.sprites.BananaSprite;
import gui.sprites.DummySprite;
import gui.sprites.LeftPlayer1Sprite;
import gui.sprites.LeftPlayer2Sprite;
import gui.sprites.ModernAmmoSprite;
import gui.sprites.ModernTowerSprite;
import gui.sprites.RightPlayer1Sprite;
import gui.sprites.RightPlayer2Sprite;
import gui.sprites.Sprite;
import gui.sprites.SpriteID;
import gui.sprites.StoneSprite;
import gui.sprites.BananaTowerSprite;
import gui.sprites.WoodSprite;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Random;

import nl.tomsanders.game.engine.GameTime;
import nl.tomsanders.game.engine.util.Size;
import nl.tomsanders.game.engine.util.Vector;

public class Game extends nl.tomsanders.game.engine.GameBase {
	public static final int GAME_NUM_PLAYERS = 2;
	public static final int GAME_RESOURCE_LAUNCHTIME_MIN = 10000;
	public static final int GAME_RESOURCE_LAUNCHTIME_MAX = 30000;

	public static final int GAME_TOWER_LAUNCHTIME = 3000;
	
	private Level level;
	private ArrayList<Player> players;
	private ArrayList<ScoreOverlay> scoreOverlays;
	private Hashtable<SpriteID, Sprite> resources;
	
	private PhysicsController physx;
	
	private ResourceLauncher resourceLauncher;
	private AmmoLauncher towerLauncher;
	private long lastResourceLaunchTime;
	private long nextResourceLaunchTime;
	private long lastAmmoLaunchTime;

	private boolean gameOver = false;
	
	public Game() {
		super();
		
		this.physx = new PhysicsController();
	}
	
	@Override
	public void loadContent() {
		super.loadContent();
		
		this.loadLevel();
		this.loadResources();
		this.addPlayers();
		this.startResourceLauncher();
		this.startTowerLauncher();
		
		System.out.println(this.level);
	}
	
	private void loadLevel() {
		this.level = Level.load("res/levels/test.xml");
	}
	
	private void startResourceLauncher() {
		this.lastResourceLaunchTime = 0;
		this.nextResourceLaunchTime = 0;
		this.resourceLauncher = new ResourceLauncher(this.level);
	}
	
	private void startTowerLauncher() {
		this.lastAmmoLaunchTime = 0;
		this.towerLauncher = new AmmoLauncher(this.level);
	}

	private void addPlayers() {
		this.players = new ArrayList<Player>();
		this.scoreOverlays = new ArrayList<ScoreOverlay>();
		ArrayList<Vector> playerStarts = this.level.getPlayerStarts();
		
		for(int i = 0; i < GAME_NUM_PLAYERS; i++) {
			KeySetting keys = null;
			SpriteID sidLeft = null;
			SpriteID sidRight = null;
			if (i == 1) {
				keys = new KeyMappingArrows();
				sidLeft = SpriteID.LeftPlayer1;
				sidRight = SpriteID.RightPlayer1;
			}
			else {
				keys = new KeyMappingWASD();
				sidLeft = SpriteID.LeftPlayer2;
				sidRight = SpriteID.RightPlayer2;
			}
			Player player = new Player("Player " + i, playerStarts.get(i), this.resources.get(SpriteID.RightPlayer1).getSize(), Arrays.copyOf(level.getStartResourceAmounts(), level.getStartResourceAmounts().length), keys, sidLeft, sidRight);
			this.players.add(player);
			this.scoreOverlays.add(new ScoreOverlay(player, (i != 0)));
			this.level.addGameObject(player);
			
			this.addKeyListener(player.getMovementController());
		}
	}
	
	private void loadResources() {
		this.resources = new Hashtable<SpriteID, Sprite>();
		this.resources.put(SpriteID.LeftPlayer1, new LeftPlayer1Sprite());
		this.resources.put(SpriteID.RightPlayer1, new RightPlayer1Sprite());
		this.resources.put(SpriteID.LeftPlayer2, new LeftPlayer2Sprite());
		this.resources.put(SpriteID.RightPlayer2, new RightPlayer2Sprite());
		this.resources.put(SpriteID.Wall, new DummySprite());
		this.resources.put(SpriteID.MovingPlatform, new DummySprite());
		this.resources.put(SpriteID.AxeTower, new AxeTowerSprite());
		this.resources.put(SpriteID.BananaTower, new BananaTowerSprite());
		this.resources.put(SpriteID.ModernTower, new ModernTowerSprite());
		this.resources.put(SpriteID.Wood, new WoodSprite());
		this.resources.put(SpriteID.Stone, new StoneSprite());
		this.resources.put(SpriteID.Banana, new BananaSprite());
		this.resources.put(SpriteID.Axe, new AxeSprite());
		this.resources.put(SpriteID.ModernAmmo, new ModernAmmoSprite());
	}
	
	@Override
	public void update(GameTime time) {
		super.update(time);
		if (!this.gameOver) {
		
			for (Player player : this.players) {
				player.getMovementController().update();
			}
		
			this.physx.update(time, level);
			/** Handle update requests */
			ArrayList<GameUpdateRequest> requests = new ArrayList<GameUpdateRequest>();
			for (GameObject o : level.getObjects()) {
				if (o.hasUpdateRequests()) {
					requests.addAll(o.getUpdateRequests());
					o.emptyRequests();
				}
			}
			for (GameUpdateRequest gur : requests) {
				if (gur.getType() == GameUpdateRequest.UpdateRequestType.addRequest) {
					level.addGameObject(gur.getObject());
				}
				else {
					level.removeGameObject(gur.getObject());
				}	
			}
			for (GameObject o : level.getObjects()) {
				if (o.getCollisionType() == CollisionType.Player) {
					if (!((Player)o).isAlive()) {
						this.gameOver = true;
					}
				}
			}
		
			/** Launch random object */
			if (time.getSystemTime() >= this.lastResourceLaunchTime + this.nextResourceLaunchTime) {
				this.resourceLauncher.launch();			
				this.lastResourceLaunchTime = time.getSystemTime();
				this.nextResourceLaunchTime = Game.GAME_RESOURCE_LAUNCHTIME_MIN + (int)(Math.random() * ((Game.GAME_RESOURCE_LAUNCHTIME_MAX - Game.GAME_RESOURCE_LAUNCHTIME_MIN) + 1));
			}
		
			/** Launch towers */
			if (time.getSystemTime() >= this.lastAmmoLaunchTime + (Game.GAME_TOWER_LAUNCHTIME)) {
				this.towerLauncher.launch();			
				this.lastAmmoLaunchTime = time.getSystemTime();
			}
		}
	}
	
	@Override
	public void render(Graphics g) {		
		super.render(g);
		
		Graphics2D graphics = (Graphics2D)g;
		for (GameObject o : this.level.getObjects()) {
			if (this.resources.containsKey(o.getSID())) {
				this.resources.get(o.getSID()).render(graphics, o.getPosition(), o.getSize());
			}
		}
		
		this.renderOverlay(g);
		
		if (gameOver) {
			this.showGameOverScreen(g);
			return;
		}
	}
	
	@Override
	public void renderOverlay(Graphics g) {
		super.renderOverlay(g);
		
		//Show resources
		Graphics2D graphics = (Graphics2D) g;
		this.scoreOverlays.get(0).render(graphics, new Vector(10, 0), new Size(25, 25));
		this.scoreOverlays.get(1).render(graphics, new Vector(528, 0), new Size(25, 25));
		
		/*
		for(int i=0; i<Game.GAME_NUM_PLAYERS; i++) {
			this.scoreOverlays.get(i).render(graphics, new Vector(10+i*200, 0), new Size(25, 25));
		}*/
	}
		
	public void showGameOverScreen(Graphics g) {
		g.setColor(new Color(1.0f, 1.0f, 1.0f, 0.5f));
		g.fillRect(0, 0, (int)this.getBounds().getWidth(), (int)this.getBounds().getHeight());
		
		Player winner = null;
		for (Player p : this.players) {
			if (p.isAlive()) {
				winner = p;
			}
		}
		
		Image image = new BufferedImage(150, 20, BufferedImage.TYPE_INT_ARGB);
		Graphics2D imageGraphics = (Graphics2D)image.getGraphics();
		imageGraphics.setFont(new Font(g.getFont().getFontName(), Font.BOLD, 10));
		imageGraphics.setColor(Color.BLACK);
		imageGraphics.drawString(winner.getName().toUpperCase() + " WINS!", 1, 11);
		imageGraphics.setColor(Color.WHITE);
		imageGraphics.drawString(winner.getName().toUpperCase() + " WINS!", 0, 10);
		
		Graphics2D graphics = (Graphics2D)g;
		graphics.setTransform(AffineTransform.getScaleInstance(5, 5));
		graphics.drawImage(image, 80, 60, null);
	}

}
