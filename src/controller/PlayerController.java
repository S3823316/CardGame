package cardgame.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import cardgame.view.GameFrame;
import cardgame.view.GameMenuBar;

public class PlayerController implements ItemListener {

	private GameFrame frame;

	public PlayerController(GameMenuBar menuBar, GameFrame frame) {
		this.frame = frame;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == 1) {
			frame.validatePlayer(e.getItem().toString());
		}
	}

}
