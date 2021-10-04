package cardgame.view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTable;

import cardgame.model.PlayerResults;
import model.interfaces.Player;


@SuppressWarnings("serial")
public class SummaryPanel extends JPanel {
	
	private Object[][]data = new Object[10][];
	private Object[]columnNames = {"ID", "PLAYER", "POINTS", 
			"BET", "RESULT" };
	private Collection<Player> allPlayers;
	private JTable table;
	private PlayerResults results;
	
	public SummaryPanel(PlayerResults results) {
		super(new GridLayout(2,1));
		this.results = results;
	}
	
	public void populateSummary() {
		allPlayers = results.getPlayers();
		if(allPlayers != null) {
			clearPanel();
			data = new Object[allPlayers.size()][];
			int playerIdx = 0;
			for (Iterator<Player> players = allPlayers.iterator(); players.hasNext();) {
		    	Player player = players.next();
		    	data[playerIdx++] = new Object[] { player.getPlayerId(), player.getPlayerName(), player.getPoints(), player.getBet(), player.getResult()};
		    }
			
			table = new JTable(data,columnNames);
			table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			table.setFillsViewportHeight(true);
			table.getTableHeader().setPreferredSize(new Dimension(10,10));
			add(table.getTableHeader(), BorderLayout.PAGE_START);
			add(table, BorderLayout.CENTER);
		}
		revalidate();
		repaint();
	}
	
	public void clearPanel() {
		for (Component c : this.getComponents()) {
			remove(c);
		}
		revalidate();
	}
}