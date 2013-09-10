package game.objects.towers;

import game.objects.Player;
import game.objects.resources.ResourceKind;
import gui.sprites.SpriteID;
import nl.tomsanders.game.engine.util.Size;
import nl.tomsanders.game.engine.util.Vector;

public class ModernTower extends Tower{
	protected static int COST_WOOD = 1;
	protected static int COST_STONE = 2;
	protected static int COST_LIFE = 0;
	
	public ModernTower(Vector position, Size size, Player owner) {
		super(position, size, owner, SpriteID.ModernTower);
	}

	public String toString() {
		return "ModernTower at " + this.getPosition();
	}
}
