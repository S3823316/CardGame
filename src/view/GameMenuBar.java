package cardgame.view;


import java.util.Iterator;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToolBar;

import cardgame.controller.AddPlayerController;
import cardgame.controller.CancelBetController;
import cardgame.controller.DealPlayerController;
import cardgame.controller.PlaceBetController;
import cardgame.controller.PlayerController;
import cardgame.controller.RemovePlayerController;
import cardgame.model.PlayerComboBoxModel;
import model.interfaces.GameEngine;
import model.interfaces.Player;


@SuppressWarnings("serial")
public class GameMenuBar extends JToolBar {
	
	
	GameEngine gameEngine;
	JComboBox<Object> playerList = new JComboBox<>();
	//DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
	PlayerComboBoxModel comboModel = new PlayerComboBoxModel();
	AbstractButton addPlayerBtn;
	AbstractButton removePlayerBtn;
	AbstractButton betBtn;
	AbstractButton dealBtn;
	AbstractButton cancelBetBtn;
	
	public GameMenuBar(GameEngine gameEngine, GameFrame frame) {
		this.gameEngine = gameEngine;
		addPlayerBtn = new JButton("Add Player");
		addPlayerBtn.addActionListener(new AddPlayerController(frame));
		removePlayerBtn = new JButton("Remove Player");
		removePlayerBtn.addActionListener(new RemovePlayerController(this, frame));
		betBtn = new JButton("Bet");
		betBtn.addActionListener(new PlaceBetController(this, frame));
		dealBtn = new JButton("Deal");
		dealBtn.addActionListener(new DealPlayerController(this, frame));
		cancelBetBtn = new JButton("Cancel Bet");
		cancelBetBtn.addActionListener(new CancelBetController(this, frame));
		
		addPlayerBtn.setSize(20, 20);
		removePlayerBtn.setSize(20, 20);
		betBtn.setSize(20, 20);
		dealBtn.setSize(20, 20);
		cancelBetBtn.setSize(20, 20);
		
		add(addPlayerBtn);
		add(removePlayerBtn);
		add(betBtn);
		add(dealBtn);
		add(cancelBetBtn);
		
		comboModel.addElement("House");
		playerList.setModel(comboModel);
		playerList.addItemListener(new PlayerController(this, frame));
		add(playerList);	
		
		validateButtons();
	}
	
	public void refreshPlayerList(String player) {
		comboModel.removeAllElements();
		comboModel.addElement("House");
		for (Iterator<Player> allPlayers = gameEngine.getAllPlayers().iterator(); allPlayers.hasNext();) {   	
			comboModel.addElement(allPlayers.next().getPlayerName());
		}
		if(player != null) {
			comboModel.setSelectedItem(player);
		}
		validateButtons();
	}
	
	public String getSelectedPlayer() {
		return (String)comboModel.getElementAt(playerList.getSelectedIndex());
	}
	
	public void validateButtons() {
		if (comboModel.getSize() > 0 &&  !comboModel.getSelectedItem().equals("House")) {
			Player player = gameEngine.getPlayer(getSelectedPlayer());
			if (player != null) {
				removePlayerBtn.setEnabled(true);
				betBtn.setEnabled(true);
				if (player.getBet() > 0) {
					dealBtn.setEnabled(true);
					cancelBetBtn.setEnabled(true);
				} else {
					dealBtn.setEnabled(false);
					cancelBetBtn.setEnabled(false);
				}
				if (player.getPlayerId().equalsIgnoreCase("House")) {
					removePlayerBtn.setEnabled(false);
					betBtn.setEnabled(false);
					dealBtn.setEnabled(false);
					cancelBetBtn.setEnabled(false);
				}
			}
		} else {
			removePlayerBtn.setEnabled(false);
			betBtn.setEnabled(false);
			dealBtn.setEnabled(false);
			cancelBetBtn.setEnabled(false);
		}
		revalidate();
		repaint();
	}
}
