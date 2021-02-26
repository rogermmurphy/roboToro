package view;




import javax.swing.JPanel;
import javax.swing.JTextField;

public class ActionView extends CommandSettingsView {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public ActionView() {
		super("Action");
		
		textField = new JTextField();
		textField.setBounds(130, 141, 96, 20);
		add(textField);
		textField.setColumns(10);

	}

}
