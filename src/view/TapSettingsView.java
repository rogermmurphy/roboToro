package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import javax.swing.JFormattedTextField;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TapSettingsView extends JPanel {
	private JTextField tfDeviceWidth;
	private JTextField textField_DeviceHeight;
	private JTextField textField_ImageHeight;
	private JTextField textField_ImageWidth;
	private JTextField tfCalculatedPXHeight;
	private JTextField tfCalculatedPXWidth;
	private JTextField tfOffsetRadians;
	private JTextField tfOffsetY;
	private JTextField tfOffsetX;

	/**
	 * Create the panel.
	 */
	public TapSettingsView() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Device Width");
		lblNewLabel.setBounds(10, 30, 100, 14);
		add(lblNewLabel);
		
		tfDeviceWidth = new JTextField();
		tfDeviceWidth.setBounds(105, 27, 100, 20);
		add(tfDeviceWidth);
		tfDeviceWidth.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Device Height");
		lblNewLabel_1.setBounds(10, 58, 100, 14);
		add(lblNewLabel_1);
		
		textField_DeviceHeight = new JTextField();
		textField_DeviceHeight.setColumns(10);
		textField_DeviceHeight.setBounds(105, 55, 100, 20);
		add(textField_DeviceHeight);
		
		textField_ImageHeight = new JTextField();
		textField_ImageHeight.setColumns(10);
		textField_ImageHeight.setBounds(105, 111, 100, 20);
		add(textField_ImageHeight);
		
		JLabel lblNewLabel_1_1 = new JLabel("Img Height");
		lblNewLabel_1_1.setBounds(10, 114, 100, 14);
		add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Img Width");
		lblNewLabel_2.setBounds(10, 86, 100, 14);
		add(lblNewLabel_2);
		
		textField_ImageWidth = new JTextField();
		textField_ImageWidth.setColumns(10);
		textField_ImageWidth.setBounds(105, 83, 100, 20);
		add(textField_ImageWidth);
		
		tfCalculatedPXHeight = new JTextField();
		tfCalculatedPXHeight.setColumns(10);
		tfCalculatedPXHeight.setBounds(105, 170, 100, 20);
		add(tfCalculatedPXHeight);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Calc PX Height");
		lblNewLabel_1_1_1.setBounds(10, 173, 100, 14);
		add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Calc PX Width");
		lblNewLabel_2_1.setBounds(10, 145, 100, 14);
		add(lblNewLabel_2_1);
		
		tfCalculatedPXWidth = new JTextField();
		tfCalculatedPXWidth.setColumns(10);
		tfCalculatedPXWidth.setBounds(105, 142, 100, 20);
		add(tfCalculatedPXWidth);
		
		tfOffsetRadians = new JTextField();
		tfOffsetRadians.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		tfOffsetRadians.setColumns(10);
		tfOffsetRadians.setBounds(105, 257, 100, 20);
		add(tfOffsetRadians);
		
		JLabel lblNewLabel_2_2 = new JLabel("Offset Radians");
		lblNewLabel_2_2.setBounds(10, 260, 100, 14);
		add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_1_2 = new JLabel("Offset y");
		lblNewLabel_1_2.setBounds(10, 232, 100, 14);
		add(lblNewLabel_1_2);
		
		tfOffsetY = new JTextField();
		tfOffsetY.setColumns(10);
		tfOffsetY.setBounds(105, 229, 100, 20);
		add(tfOffsetY);
		
		tfOffsetX = new JTextField();
		tfOffsetX.setColumns(10);
		tfOffsetX.setBounds(105, 201, 100, 20);
		add(tfOffsetX);
		
		JLabel lblNewLabel_3 = new JLabel("Offset X");
		lblNewLabel_3.setBounds(10, 204, 100, 14);
		add(lblNewLabel_3);

	}
}
