package io.hid;

import game.objects.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MovementController implements KeyListener {
	private Player player;
	
	public MovementController(Player player) {
		this.player = player;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP:
			player.move(Player.MovementDirection.Jump);
			break;
		case KeyEvent.VK_LEFT:
			player.move(Player.MovementDirection.Left);
			break;
		case KeyEvent.VK_RIGHT:
			player.move(Player.MovementDirection.Right);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

}
