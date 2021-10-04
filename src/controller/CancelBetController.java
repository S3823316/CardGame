package cardgame.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cardgame.view.GameFrame;
import cardgame.view.GameMenuBar;

public class CancelBetController implements ActionListener {

	private GameMenuBar menuBar;
	private GameFrame frame;

	public CancelBetController(GameMenuBar menuBar, GameFrame frame) {
		this.menuBar = menuBar;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String player = menuBar.getSelectedPlayer();
		frame.cancelBet(player);
	}

}
