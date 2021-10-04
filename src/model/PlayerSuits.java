package cardgame.model;

import java.util.ArrayList;
import java.util.Map;

import model.interfaces.PlayingCard;

public class PlayerSuits {

	private Map<String, ArrayList<PlayingCard>> playerSuits;
	
	public PlayerSuits() {
		
	}

	public Map<String, ArrayList<PlayingCard>> getPlayerSuits() {
		return playerSuits;
	}

	public void setPlayerSuits(Map<String, ArrayList<PlayingCard>> playerSuits) {
		this.playerSuits = playerSuits;
	}

}
