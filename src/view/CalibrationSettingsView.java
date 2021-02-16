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

public class CalibrationSettingsView extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Create the panel.
	 */
	public CalibrationSettingsView() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Z Down");
		lblNewLabel.setBounds(10, 11, 100, 14);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(120, 11, 100, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Z Up");
		lblNewLabel_1.setBounds(10, 39, 100, 14);
		add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(120, 39, 100, 20);
		add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(120, 95, 100, 20);
		add(textField_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("Acceleration");
		lblNewLabel_1_1.setBounds(10, 95, 100, 14);
		add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Start Velocity");
		lblNewLabel_2.setBounds(10, 67, 100, 14);
		add(lblNewLabel_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(120, 67, 100, 20);
		add(textField_3);
		
		ClickSettingsView panel_2 = new ClickSettingsView("Click Up");
		panel_2.setBounds(0, 160, 251, 145);
		add(panel_2);
		
		ClickSettingsView panel_2_1 = new ClickSettingsView("Click Down");
		panel_2_1.setBounds(0, 316, 251, 145);
		add(panel_2_1);
		
		ClickSettingsView panel_2_1_1 = new ClickSettingsView("Click Up");
		panel_2_1_1.setBounds(0, 472, 251, 145);
		add(panel_2_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Pause");
		lblNewLabel_1_1_1.setBounds(10, 126, 100, 14);
		add(lblNewLabel_1_1_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(120, 126, 100, 20);
		add(textField_4);

	}
}
