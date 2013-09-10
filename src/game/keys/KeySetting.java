package game.keys;

import game.objects.Player;

import java.util.HashMap;

public interface KeySetting {
	public HashMap<Integer, Player.MovementDirection> getProfile();

}
