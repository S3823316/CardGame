package cardgame.view;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PlaceBetPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JTextField betField = new JTextField(5);
	JTextField nameField = new JTextField(5);
	
	private Map<String, JTextField> fieldMap = new HashMap<String, JTextField>();

	public PlaceBetPanel(String player) {
		add(new JLabel("Enter Bet for " + player));
		betField.setPreferredSize(new Dimension(50,5));
		add(betField);
		fieldMap.put("betField",betField);
	}
	
	public String getFieldText(String fieldTitle) {
	      return fieldMap.get(fieldTitle).getText();
	   }

}
