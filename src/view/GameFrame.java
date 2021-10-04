package cardgame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import cardgame.model.ConfirmOptions;
import cardgame.model.PlayerResults;
import cardgame.model.PlayerSuits;
import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	private GameMenuBar gameMenuBar;
	final GameEngine gameEngine = new GameEngineImpl();
	private CardPanel cardPanel;
	private SummaryPanel summaryPanel;
	PlayerSuits playerSuits = new PlayerSuits();
	PlayerResults playerResults = new PlayerResults();

	public GameFrame() {
		super("CardGameGUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(this));
		setBackground(Color.gray);
		setBounds(100, 100, 840, 640);
		setLayout(new BorderLayout());
	}

	public void populate() {
		gameMenuBar = new GameMenuBar(gameEngine, this);
		add(gameMenuBar, BorderLayout.NORTH);
		cardPanel = new CardPanel(); 
		add(cardPanel, BorderLayout.CENTER);
		summaryPanel = new SummaryPanel(playerResults);
		add(summaryPanel, BorderLayout.SOUTH);
		this.setVisible(true);
		refresh(null);
		revalidate();
		repaint();
	}

	public void showAddDialog() throws NumberFormatException {
		AddPlayerInputPanel playerInputPanel = new AddPlayerInputPanel();
		playerInputPanel.setLayout(new BoxLayout(playerInputPanel, BoxLayout.PAGE_AXIS));
		int result = JOptionPane.showConfirmDialog(this, playerInputPanel, "Add Player", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			try {
				Player newPlayer = new SimplePlayer(playerInputPanel.getFieldText("name"),
						playerInputPanel.getFieldText("name"),
						Integer.valueOf(playerInputPanel.getFieldText("points")).intValue());
				gameEngine.addPlayer(newPlayer);
				refresh(newPlayer.getPlayerId());
			} catch (NumberFormatException nfe) {
				JOptionPane.showOptionDialog(this,
						new JLabel("Invalid Start Points"), "Invalid Points",
						JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, ConfirmOptions.options, ConfirmOptions.options[0]);
			}
		}
	}

	public void showBetDialog(String player) {
		PlaceBetPanel betPanel = new PlaceBetPanel(player);
		betPanel.setLayout(new BoxLayout(betPanel, BoxLayout.PAGE_AXIS));
		betPanel.setPreferredSize(new Dimension(150,15));
		betPanel.repaint();

		int result = JOptionPane.showOptionDialog(this, betPanel, "Place Bet",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, ConfirmOptions.replayOptions, ConfirmOptions.replayOptions[0]);
		if (result == JOptionPane.OK_OPTION) {
			placeBet(player, betPanel.getFieldText("betField"));
		}
		
		refresh(player);
		
	}

	public void removePlayer(String player) {
		gameEngine.removePlayer(gameEngine.getPlayer(player));
		refresh(null);
	}

	public void refresh(String player) {
		gameMenuBar.refreshPlayerList(player);
	}

	public void placeBet(String player, String bet) throws NumberFormatException {
		Player gamePlayer = gameEngine.getPlayer(player);
		try {
			int betAmount = Integer.parseInt(bet);
			if (gamePlayer.getPoints() < betAmount) {
				JOptionPane.showOptionDialog(this,
						new JLabel("Not enough points: Bet: " + bet + " Points: " + gamePlayer.getPoints()), "Invalid Bet",
						JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, ConfirmOptions.options, ConfirmOptions.options[0]);
			}
			gameEngine.placeBet(gamePlayer, betAmount);
			refresh(player);
			gameMenuBar.validateButtons();
		} catch (NumberFormatException nfe) {
			JOptionPane.showOptionDialog(this,
					new JLabel("Invalid Bet Amount"), "Invalid Bet",
					JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, ConfirmOptions.options, ConfirmOptions.options[0]);
		}
	}

	public void dealPlayer(String player) {
		Player gamePlayer = gameEngine.getPlayer(player);
		if(playerSuits != null && playerSuits.getPlayerSuits() != null && playerSuits.getPlayerSuits().get(player) != null) {
			playerSuits.getPlayerSuits().get(player).clear();
		}
		gameEngine.dealPlayer(gamePlayer, 100);
		drawCard(player);
		validateHouseReady();
	}

	public void cancelBet(String player) {
		Player gamePlayer = gameEngine.getPlayer(player);
		gamePlayer.resetBet();
		gameMenuBar.validateButtons();
	}

	public void validatePlayer(String player) {
		gameMenuBar.validateButtons();
		drawCard(player);
	}

	public void validateHouseReady() {
		boolean houseReady = true;
		for (Iterator<Player> allPlayers = gameEngine.getAllPlayers().iterator(); allPlayers.hasNext();) {
			Player player = allPlayers.next();
			if (!(player.getPlayerId().equalsIgnoreCase("House")) && player.getResult() == 0) {
				houseReady = false;
			}
		}
		if (houseReady) {
			int result = JOptionPane.showOptionDialog(this, new JLabel("Press OK to Continue"), "House Ready",
					JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, ConfirmOptions.options, ConfirmOptions.options[0]);
		
			if (result == JOptionPane.OK_OPTION) {
				if(playerSuits != null && playerSuits.getPlayerSuits() != null && playerSuits.getPlayerSuits().get("House") != null) {
					playerSuits.getPlayerSuits().get("House").clear();
				}
				gameEngine.dealHouse(100);
			}
			
			int replay = JOptionPane.showOptionDialog(this, new JLabel("Play Again?"), "House Result",
					JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, ConfirmOptions.replayOptions, ConfirmOptions.replayOptions[0]);
			
			if (replay == JOptionPane.CANCEL_OPTION) {
				System.exit(0);
			}
			summaryPanel.populateSummary();
			for (Iterator<Player> allPlayers = gameEngine.getAllPlayers().iterator(); allPlayers.hasNext();) {
				Player player = allPlayers.next();
				player.resetBet();
				player.resetResutl();
			}
		}
		gameMenuBar.validateButtons();
	}
	
	public void drawCard(String player) {
		cardPanel.clearPanel();
		if(playerSuits != null && playerSuits.getPlayerSuits() != null && playerSuits.getPlayerSuits().get(player) != null) {
			cardPanel.setCardSuit(playerSuits.getPlayerSuits().get(player));
			cardPanel.setVisible(true);
			summaryPanel.setVisible(true);
		} else {
			cardPanel.clearPanel();
		}	
		revalidate();
		repaint();
	}

	public void setPlayerSuits(Map<String, ArrayList<PlayingCard>> suits) {
		playerSuits.setPlayerSuits(suits);
		revalidate();
		repaint();
	}
	
	public void setPlayerResults(Collection<Player> players) {
		playerResults.setPlayers(players);
	}

}
