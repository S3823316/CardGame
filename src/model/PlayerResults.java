package cardgame.model;

import java.util.Collection;

import model.interfaces.Player;

public class PlayerResults {

	private Collection<Player> players;
	
	public PlayerResults() {
	
	}

	public Collection<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Collection<Player> players) {
		this.players = players;
	}

}
