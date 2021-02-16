package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import javax.swing.JFormattedTextField;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Label;

public class ClickSettingsView extends JPanel {
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_4;
	private JTextField textField_7;

	/**
	 * Create the panel.
	 */
	public ClickSettingsView(String labelText) {
		setLayout(null);
		
		Panel panel = new Panel();
		panel.setBounds(10, 24, 231, 116);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2_1 = new JLabel("Click Acceleration");
		lblNewLabel_2_1.setBounds(10, 39, 100, 14);
		panel.add(lblNewLabel_2_1);
		
		textField_5 = new JTextField();
		textField_5.setBounds(120, 36, 100, 20);
		panel.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Click Velocity");
		lblNewLabel_2_1_1.setBounds(10, 14, 100, 14);
		panel.add(lblNewLabel_2_1_1);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(120, 11, 100, 20);
		panel.add(textField_6);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Pause After");
		lblNewLabel_2_1_2.setBounds(10, 90, 100, 14);
		panel.add(lblNewLabel_2_1_2);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(120, 87, 100, 20);
		panel.add(textField_4);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Pause Before");
		lblNewLabel_2_1_1_1.setBounds(10, 65, 100, 14);
		panel.add(lblNewLabel_2_1_1_1);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(120, 62, 100, 20);
		panel.add(textField_7);
		
		Label label = new Label(labelText);
		label.setBounds(10, 2, 231, 16);
		label.setAlignment(Label.CENTER);
		add(label);

	}
}
