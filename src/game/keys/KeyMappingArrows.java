package game.keys;

import game.objects.Player;

import java.awt.event.KeyEvent;
import java.util.HashMap;

public class KeyMappingArrows implements KeySetting{
	
	public HashMap<Integer, Player.MovementDirection> getProfile() {
		HashMap<Integer, Player.MovementDirection> profile = new HashMap<Integer, Player.MovementDirection>();
		profile.put(KeyEvent.VK_UP, Player.MovementDirection.Jump);
		profile.put(KeyEvent.VK_RIGHT, Player.MovementDirection.Right);
		profile.put(KeyEvent.VK_LEFT, Player.MovementDirection.Left);
		profile.put(KeyEvent.VK_DOWN, Player.MovementDirection.PutTower);
		profile.put(KeyEvent.VK_ENTER, Player.MovementDirection.UpgradeTowers);
		return profile;
	}
}
