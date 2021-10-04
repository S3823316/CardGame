package cardgame.view;

import java.awt.Component;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.interfaces.PlayingCard;

@SuppressWarnings("serial")
public class CardPanel extends JPanel {

	List<JLabel> cards = new ArrayList<JLabel>();
	JLabel hLbl;
	JLabel sLbl;
	JLabel dLbl;
	JLabel cLbl;
	String imagePath;

	public CardPanel() {
		super();
	}

	public void setCardSuit(ArrayList<PlayingCard> cardList) {
		cards.clear();
		for (PlayingCard card : cardList) {
			switch (card.getSuit()) {
			case HEARTS:
				getHeartsValue(card);
				break;
			case SPADES:
				getSpadesValue(card);
				break;
			case CLUBS:
				getClubsValue(card);
				break;
			case DIAMONDS:
				getDiamondsValue(card);
				break;
			}
		}
		displayCards();
	}

	public void getHeartsValue(PlayingCard card) {
		switch (card.getValue()) {
		case ACE:
			imagePath = "img/cards/HEARTS_ACE.png";
			break;
		case TEN:
			imagePath = "img/cards/HEARTS_TEN.png";
			break;
		case JACK:
			imagePath = "img/cards/HEARTS_JACK.png";
			break;
		case QUEEN:
			imagePath = "img/cards/HEARTS_QUEEN.png";
			break;
		case KING:
			imagePath = "img/cards/HEARTS_KING.png";
			break;
		case NINE:
			imagePath = "img/cards/HEARTS_NINE.png";
			break;
		case EIGHT:
			imagePath = "img/cards/HEARTS_EIGHT.png";
			break;
		}
		hLbl = getCardIcon(imagePath);
		cards.add(hLbl);
	}

	public void getSpadesValue(PlayingCard card) {
		switch (card.getValue()) {
		case ACE:
			imagePath = "img/cards/SPADES_ACE.png";
			break;
		case TEN:
			imagePath = "img/cards/SPADES_TEN.png";
			break;
		case JACK:
			imagePath = "img/cards/SPADES_JACK.png";
			break;
		case QUEEN:
			imagePath = "img/cards/SPADES_QUEEN.png";
			break;
		case KING:
			imagePath = "img/cards/SPADES_KING.png";
			break;
		case NINE:
			imagePath = "img/cards/SPADES_NINE.png";
			break;
		case EIGHT:
			imagePath = "img/cards/SPADES_EIGHT.png";
			break;
		}
		sLbl = getCardIcon(imagePath);
		cards.add(sLbl);
	}

	public void getClubsValue(PlayingCard card) {
		switch (card.getValue()) {
		case ACE:
			imagePath = "img/cards/CLUBS_ACE.png";
			break;
		case TEN:
			imagePath = "img/cards/CLUBS_TEN.png";
			break;
		case JACK:
			imagePath = "img/cards/CLUBS_JACK.png";
			break;
		case QUEEN:
			imagePath = "img/cards/CLUBS_QUEEN.png";
			break;
		case KING:
			imagePath = "img/cards/CLUBS_KING.png";
			break;
		case NINE:
			imagePath = "img/cards/CLUBS_NINE.png";
			break;
		case EIGHT:
			imagePath = "img/cards/CLUBS_EIGHT.png";
			break;
		}
		cLbl = getCardIcon(imagePath);
		cards.add(cLbl);
	}

	public void getDiamondsValue(PlayingCard card) {
		switch (card.getValue()) {
		case ACE:
			imagePath = "img/cards/DIAMONDS_ACE.png";
			break;
		case TEN:
			imagePath = "img/cards/DIAMONDS_TEN.png";
			break;
		case JACK:
			imagePath = "img/cards/DIAMONDS_JACK.png";
			break;
		case QUEEN:
			imagePath = "img/cards/DIAMONDS_QUEEN.png";
			break;
		case KING:
			imagePath = "img/cards/DIAMONDS_KING.png";
			break;
		case NINE:
			imagePath = "img/cards/DIAMONDS_NINE.png";
			break;
		case EIGHT:
			imagePath = "img/cards/DIAMONDS_EIGHT.png";
			break;
		}
		dLbl = getCardIcon(imagePath);
		cards.add(dLbl);
	}
	
	public void displayCards() {
		clearPanel();
		for(JLabel lbl : cards) {
			add(lbl);
		}
	}
	
	public JLabel getCardIcon(String img) {
		JLabel lbl = new JLabel();
		lbl.setIcon(new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(120, 200, Image.SCALE_DEFAULT)));
		return lbl;
	}
	
	public void clearPanel() {
		for (Component c : this.getComponents()) {
			remove(c);
		}
		revalidate();
	}
}
