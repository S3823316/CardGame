package cardgame.view;

import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddPlayerInputPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JTextField nameField = new JTextField(5);
	JTextField pointsField = new JTextField(5);
	private Map<String, JTextField> fieldMap = new HashMap<String, JTextField>();

	public AddPlayerInputPanel() {
		add(new JLabel("Enter Player Name:"));
		add(nameField);
		add(Box.createHorizontalStrut(15));
		add(new JLabel("Enter Start Points:"));
		add(pointsField);
		fieldMap.put("name",nameField);
		fieldMap.put("points",pointsField);
	}
	
	public String getFieldText(String fieldTitle) {
	      return fieldMap.get(fieldTitle).getText();
	   }

}
