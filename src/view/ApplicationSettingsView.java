package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import javax.swing.JFormattedTextField;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;


public class ApplicationSettingsView extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the panel.
	 */
	public ApplicationSettingsView() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Check Screen Mirror Location");
		lblNewLabel.setBounds(10, 27, 139, 28);
		add(lblNewLabel);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBounds(160, 27, 30, 28);
		add(chckbxNewCheckBox);
		
		JLabel lblScreenMirrorWindow = new JLabel("Screen Mirror Window Name");
		lblScreenMirrorWindow.setBounds(10, 68, 139, 20);
		add(lblScreenMirrorWindow);
		
		textField = new JTextField();
		textField.setBounds(160, 68, 159, 20);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(160, 99, 159, 20);
		add(textField_1);
		
		JLabel lblConfigfilelocation = new JLabel("Config File Location");
		lblConfigfilelocation.setBounds(10, 99, 139, 20);
		add(lblConfigfilelocation);
		
		JLabel lblDirectoryRoot = new JLabel("Directory Root");
		lblDirectoryRoot.setBounds(10, 130, 139, 20);
		add(lblDirectoryRoot);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(160, 130, 159, 20);
		add(textField_2);

	}
}
