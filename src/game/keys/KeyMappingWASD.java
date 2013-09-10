package game.keys;

import game.objects.Player;

import java.awt.event.KeyEvent;
import java.util.HashMap;

public class KeyMappingWASD implements KeySetting{
	
	public HashMap<Integer, Player.MovementDirection> getProfile() {
		HashMap<Integer, Player.MovementDirection> profile = new HashMap<Integer, Player.MovementDirection>();
		profile.put(KeyEvent.VK_W, Player.MovementDirection.Jump);
		profile.put(KeyEvent.VK_D, Player.MovementDirection.Right);
		profile.put(KeyEvent.VK_A, Player.MovementDirection.Left);
		profile.put(KeyEvent.VK_S, Player.MovementDirection.PutTower);
		profile.put(KeyEvent.VK_CONTROL, Player.MovementDirection.UpgradeTowers);
		return profile;
	}
}
