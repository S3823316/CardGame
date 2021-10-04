package cardgame.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cardgame.view.GameFrame;
import cardgame.view.GameMenuBar;

public class RemovePlayerController implements ActionListener {

	private GameMenuBar menuBar;
	private GameFrame frame;

	public RemovePlayerController(GameMenuBar menuBar, GameFrame frame) {
		this.menuBar = menuBar;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String player = menuBar.getSelectedPlayer();
		frame.removePlayer(player);
	}

}
