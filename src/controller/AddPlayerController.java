package cardgame.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cardgame.view.GameFrame;

public class AddPlayerController implements ActionListener {


	private GameFrame frame;

	public AddPlayerController(GameFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.frame.showAddDialog();
	}
}
