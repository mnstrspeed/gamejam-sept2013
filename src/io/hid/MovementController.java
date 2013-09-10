package io.hid;

import game.keys.KeySetting;
import game.objects.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class MovementController implements KeyListener {
	private Player player;
	private HashMap<Integer, Player.MovementDirection> profile;
	private HashMap<Integer, Boolean> keyboard;
	
	public void update() {
		for (Integer keyCode : this.keyboard.keySet()) {
			if (this.keyboard.get(keyCode)) {
				this.player.move(this.profile.get(keyCode));
			}
		}
	}
	
	public MovementController(Player player, KeySetting profile) {
		this.profile = profile.getProfile();
		this.player = player;
		
		this.keyboard = new HashMap<Integer, Boolean>();
		for (Integer keyCode : this.profile.keySet()) {
			this.keyboard.put(keyCode, false);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (this.keyboard.containsKey(e.getKeyCode())) {
			this.keyboard.put(e.getKeyCode(), true);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (this.keyboard.containsKey(e.getKeyCode())) {
			this.keyboard.put(e.getKeyCode(), false);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) { }

}
