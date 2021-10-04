package cardgame.app;

import javax.swing.SwingUtilities;

import cardgame.view.GameFrame;

public class CardGameTest {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GameFrame().populate();
			}
		});
	}
}
