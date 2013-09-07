import game.Game;
import game.Level;
import gui.MainWindow;
import nl.tomsanders.game.engine.GameWindow;

public class Main {
	public static final boolean DEBUG = true;
	
	public static void main(String[] args) {
		
		GameWindow window = new MainWindow();
		Level level = Level.load("res/levels/test.xml");
		Game game = new Game(window, level);
		window.setTarget(game);
		if(DEBUG) {
			System.out.println("Level:");
			System.out.println(game.getLevel());
		}
		game.start();
	}
}
