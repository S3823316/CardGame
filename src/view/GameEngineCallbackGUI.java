package cardgame.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback
{
	   public static final Logger logger = Logger.getLogger(GameEngineCallbackGUI.class.getName());
	   final GameEngine gameEngine = new GameEngineImpl();
	   GameFrame frame;
	   Map<String, ArrayList<PlayingCard>> playerSuits = new HashMap<>();
       
	   // utility method to set output level of logging handlers
	   public static Logger setAllHandlers(Level level, Logger logger, boolean recursive)
	   {
	      // end recursion?
	      if (logger != null)
	      {
	         logger.setLevel(level);
	         for (Handler handler : logger.getHandlers())
	            handler.setLevel(level);
	         // recursion
	         setAllHandlers(level, logger.getParent(), recursive);
	      }
	      return logger;
	   }

	   public GameEngineCallbackGUI(GameFrame frame)
	   {
	      super();
	      this.frame = frame;
		  // NOTE can also set the console to FINE in %JRE_HOME%\lib\logging.properties
	      setAllHandlers(Level.INFO, logger, true);
	   }

	   @Override
	   public void nextCard(Player player, PlayingCard card, GameEngine engine)
	   {
	      String nextCard = String.format("Card Dealt to %1$s .. Suit: %2$s, Value: %3$s, Score: %4$s", player.getPlayerName(), card.getSuit().toString().substring(0, 1).toUpperCase() + card.getSuit().toString().substring(1).toLowerCase(),
	    		  card.getValue().toString().substring(0, 1).toUpperCase() + card.getValue().toString().substring(1).toLowerCase(), card.getScore());
	      logger.log(Level.INFO, nextCard);
	      addCards(player, card);
	   }

	   @Override
	   public void result(Player player, int result, GameEngine engine)
	   {
	      String resultInfo = String.format("%1$s, final result = %2$s", player.getPlayerName(), result);
	      logger.log(Level.INFO, resultInfo);
	      frame.setPlayerSuits(playerSuits);
	   }

		@Override
		public void bustCard(Player player, PlayingCard card, GameEngine engine) {
			String bustCard = String.format("Card Dealt to %1$s .. Suit: %2$s, Value: %3$s, Score: %4$s ... YOU BUSTED!", player.getPlayerName(), card.getSuit().toString().substring(0, 1).toUpperCase() + card.getSuit().toString().substring(1).toLowerCase(),
					card.getValue().toString().substring(0, 1).toUpperCase() + card.getValue().toString().substring(1).toLowerCase(), card.getScore());
			logger.log(Level.INFO, bustCard);
			addCards(player, card);
		}
		
		@Override
		public void nextHouseCard(PlayingCard card, GameEngine engine) {
			String nextHouseCard = String.format("Card Dealt to House .. Suit: %1$s, Value: %2$s, Score: %3$s", card.getSuit().toString().substring(0, 1).toUpperCase() + card.getSuit().toString().substring(1).toLowerCase(),
					card.getValue().toString().substring(0, 1).toUpperCase() + card.getValue().toString().substring(1).toLowerCase(), card.getScore());
		    logger.log(Level.INFO, nextHouseCard);
		    addCards(new SimplePlayer("House", "House", 0), card);
			
		}
		
		@Override
		public void houseBustCard(PlayingCard card, GameEngine engine) {
			String houseBustCard = String.format("Card Dealt to House .. Suit: %1$s, Value: %2$s, Score: %3$s ... HOUSE BUSTED!", card.getSuit().toString().substring(0, 1).toUpperCase() + card.getSuit().toString().substring(1).toLowerCase(),
					card.getValue().toString().substring(0, 1).toUpperCase() + card.getValue().toString().substring(1).toLowerCase(), card.getScore());
			logger.log(Level.INFO, houseBustCard);
			addCards(new SimplePlayer("House", "House", 0), card);
		}
		
		@Override
		public void houseResult(int result, GameEngine engine) {
			StringBuilder sb = new StringBuilder();
			String resultInfo = String.format("House, final result = %1$s", result);
		    logger.log(Level.INFO, resultInfo);
		    sb.append("Final Player Results \n");
		    for (Iterator<Player> allPlayers = engine.getAllPlayers().iterator(); allPlayers.hasNext();) {
		    	Player player = allPlayers.next();
		    	sb.append(player.toString());
		    	sb.append("\n");
		    }
		    logger.log(Level.INFO, sb.toString());
		    frame.setPlayerResults(engine.getAllPlayers());
		}
		
		public void addCards(Player player, PlayingCard card) {
			boolean hasKey = playerSuits.containsKey(player.getPlayerId());
		      if(hasKey) {
		    	  ArrayList<PlayingCard> cardList = playerSuits.get(player.getPlayerId());
		    	  cardList.add(card);
		      } else {
		    	  ArrayList<PlayingCard> cardList = new ArrayList<PlayingCard>();
		    	  cardList.add(card);
		    	  playerSuits.put(player.getPlayerId(), cardList);
		      }
		}
	}