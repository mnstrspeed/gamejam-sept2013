package game;

import game.objects.Player;

import java.awt.Graphics2D;

import nl.tomsanders.game.engine.*;
import nl.tomsanders.game.engine.util.Vector;

public class Game extends nl.tomsanders.game.engine.Game {
	public static final int GAME_NUM_PLAYERS = 2;
	private Player[] players;
	private PhysicsController physx;
	private Level level;
	
	
	public Game(GameWindow window, Level level) {
		super(window);
		
		this.level = level;
		
		//Initialize players
		this.physx = new PhysicsController();
		players = new Player[GAME_NUM_PLAYERS];
		for(int i=0; i<GAME_NUM_PLAYERS; i++) {
			players[i] = new Player(new Vector(0,0));
			this.addKeyListener(players[i].getMovementController());
			this.level.addGameObject(players[i]);
		}
	}
	
	@Override
	public void update(GameTime time) {
		super.update(time);
		physx.update(time, level.getObjects());
	}
	
	
	@Override
	public void render(Graphics2D g) {
		super.render(g, level);
	}
	
	public Level getLevel() {
		return this.level;
	}
}
