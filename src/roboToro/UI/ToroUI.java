package roboToro.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.AWTException;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Choice;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;

import roboToro.Routine;
import roboToro.Step;
import roboToro.Toro;
import roboToro.game.Game;
import roboToro.game.Pitch;
import roboToro.game.tbs2020.FixedUtil;

import java.awt.Color;
import javax.swing.JSlider;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JLayeredPane;
import java.awt.Label;
import java.awt.Point;
import java.awt.TextField;
import javax.swing.AbstractListModel;
import javax.swing.JTextArea;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ItemEvent;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ToroUI {

	public static Toro toro;
	private static FixedUtil fixedUtil;
	private JFrame frame;
	private PhonePanel uxPhonePanel;
	private JTextField uxValidationTimeOutMS1;
	private JTextField textField_2;
	private JPanel uxValidateImagePanel;
	private JTextField textField_3;
	private PrintStream standardOut;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ToroUI window = new ToroUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws ParserConfigurationException
	 * @throws IOException 
	 */
	public ToroUI() throws ParserConfigurationException, IOException {
		ToroUI.toro = new Toro();
		fixedUtil = new FixedUtil();

		try {
			initialize();
			toro.phonePanelThread = new Thread(() -> fixedUtil.setLiveWindow(this.uxPhonePanel));
			toro.phonePanelThread.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * get frame
	 */
	public JFrame getFrame() {
		return this.frame;
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws AWTException
	 */
	private void initialize() throws AWTException {
		// this.toro = toro;
		frame = new JFrame();
		frame.setBackground(UIManager.getColor("Button.disabledToolBarBorderBackground"));
		frame.setBounds(100, 100, 1546, 1392);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1526, 1341);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 12, 792, 1290);
		panel.add(tabbedPane);

		JPanel uxMacroSelectionTab = new JPanel();
		tabbedPane.addTab("Macro Selection", null, uxMacroSelectionTab, null);
		uxMacroSelectionTab.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Set Routien Parameters to create a Macro");
		lblNewLabel_2.setBounds(52, 24, 198, 25);
		uxMacroSelectionTab.add(lblNewLabel_2);

		JPanel panel_7 = new JPanel();
		panel_7.setBounds(24, 59, 323, 376);
		uxMacroSelectionTab.add(panel_7);
		panel_7.setLayout(null);

		JLabel lblNewLabel_6 = new JLabel("Initialize Routien");
		lblNewLabel_6.setBounds(10, 46, 107, 13);
		panel_7.add(lblNewLabel_6);

		Choice cMacroInitializeRoutien = new Choice();
		cMacroInitializeRoutien.setBounds(147, 46, 129, 14);
		panel_7.add(cMacroInitializeRoutien);

		JLabel lblNewLabel_7 = new JLabel("Main Routien");
		lblNewLabel_7.setBounds(10, 115, 107, 13);
		panel_7.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("End Macro");
		lblNewLabel_8.setBounds(10, 215, 68, 13);
		panel_7.add(lblNewLabel_8);

		JLabel lblNewLabel_6_1 = new JLabel("Macro Name");
		lblNewLabel_6_1.setBounds(10, 10, 81, 13);
		panel_7.add(lblNewLabel_6_1);

		JList MacroName = new JList();
		MacroName.setBounds(147, 10, 129, 14);
		panel_7.add(MacroName);

		Choice cMainRoutien = new Choice();
		cMainRoutien.setBounds(147, 115, 129, 14);
		panel_7.add(cMainRoutien);

		Choice cEndRoutien = new Choice();
		cEndRoutien.setBounds(147, 215, 129, 14);
		panel_7.add(cEndRoutien);

		JLabel lblNewLabel_7_1 = new JLabel("# of Itterations");
		lblNewLabel_7_1.setBounds(10, 141, 107, 13);
		panel_7.add(lblNewLabel_7_1);

		textField_2 = new JTextField();
		textField_2.setBounds(147, 139, 129, 19);
		panel_7.add(textField_2);
		textField_2.setColumns(10);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Call Back");
		chckbxNewCheckBox.setBounds(10, 254, 93, 21);
		panel_7.add(chckbxNewCheckBox);

		JButton btnStartMacro = new JButton("Strart / Continue");
		btnStartMacro.setBounds(10, 311, 152, 21);
		panel_7.add(btnStartMacro);

		JCheckBox chckbxEveryIteration = new JCheckBox("Every Iteration");
		chckbxEveryIteration.setBounds(10, 76, 220, 21);
		panel_7.add(chckbxEveryIteration);

		JLabel lblNewLabel_7_2 = new JLabel("Allow Interupt");
		lblNewLabel_7_2.setBounds(10, 165, 107, 13);
		panel_7.add(lblNewLabel_7_2);

		JList list_5_2_2 = new JList();
		list_5_2_2.setBounds(147, 165, 129, 14);
		panel_7.add(list_5_2_2);

		JLabel lblNewLabel_7_2_1 = new JLabel("Error");
		lblNewLabel_7_2_1.setBounds(10, 190, 107, 13);
		panel_7.add(lblNewLabel_7_2_1);

		Choice cErrorRoutien = new Choice();
		cErrorRoutien.setBounds(147, 190, 129, 14);
		panel_7.add(cErrorRoutien);

		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Call Back");
		chckbxNewCheckBox_1.setBounds(10, 252, 93, 21);
		panel_7.add(chckbxNewCheckBox_1);

		JCheckBox chckbxRunAgainAfter = new JCheckBox("Run Again After Complete");
		chckbxRunAgainAfter.setBounds(10, 282, 189, 21);
		panel_7.add(chckbxRunAgainAfter);

		JButton btnTogleStartStop = new JButton("Togle Start Stop");

		btnTogleStartStop.setBounds(10, 338, 152, 26);
		panel_7.add(btnTogleStartStop);
		
		JButton btnLive = new JButton("Live ");
		btnLive.setBounds(172, 338, 104, 25);
		panel_7.add(btnLive);

		JLabel lblNewLabel_2_3 = new JLabel("Set Routien Parameters to create a Macro");
		lblNewLabel_2_3.setBounds(449, 59, 198, 25);
		uxMacroSelectionTab.add(lblNewLabel_2_3);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "Start", "Macro Name", "Running" }) {
			Class[] columnTypes = new Class[] { Object.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(111);
		table.getColumnModel().getColumn(0).setMinWidth(111);
		table.getColumnModel().getColumn(1).setPreferredWidth(434);
		table.getColumnModel().getColumn(1).setMinWidth(434);
		table.getColumnModel().getColumn(2).setMinWidth(75);
		table.setBounds(24, 464, 726, 59);
		uxMacroSelectionTab.add(table);

		JButton btnNewButton_2_1 = new JButton("Run");
		btnNewButton_2_1.setBounds(52, 490, 85, 21);
		uxMacroSelectionTab.add(btnNewButton_2_1);

		final JPanel uxRoutienTab = new JPanel();
		uxRoutienTab.setLayout(null);
		tabbedPane.addTab("Routiens", null, uxRoutienTab, null);

		JButton btnCreateNewRoutine = new JButton("Create New Routine");

		btnCreateNewRoutine.setBounds(6, 10, 234, 23);
		uxRoutienTab.add(btnCreateNewRoutine);

		JLabel lblNewLabel_2_1_2 = new JLabel("Select Routine to Load");
		lblNewLabel_2_1_2.setBounds(6, 45, 234, 14);
		uxRoutienTab.add(lblNewLabel_2_1_2);

		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("");
		lblNewLabel_2_1_1_1_1.setBounds(24, 464, 78, 14);
		uxRoutienTab.add(lblNewLabel_2_1_1_1_1);

		final Choice choiceRoutineSelection = new Choice();
		choiceRoutineSelection.setBounds(260, 40, 211, 22);

		uxRoutienTab.add(choiceRoutineSelection);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(10, 91, 765, 1126);
		uxRoutienTab.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_2_2 = new JLabel("Cusom Logic Class");
		lblNewLabel_2_2.setBounds(10, 1100, 151, 14);
		panel_2.add(lblNewLabel_2_2);

		Label label = new Label("Step Name");
		label.setBounds(10, 70, 82, 21);
		panel_2.add(label);

		final TextField tfStepName = new TextField();
		tfStepName.setBounds(256, 70, 205, 21);
		panel_2.add(tfStepName);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_3.setBounds(10, 97, 742, 428);
		panel_2.add(panel_3);
		panel_3.setLayout(null);

		Label label_1 = new Label("Validation");
		label_1.setBounds(10, 10, 90, 21);
		panel_3.add(label_1);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_5.setBounds(12, 47, 718, 369);
		panel_3.add(panel_5);
		panel_5.setLayout(null);

		JList list_2 = new JList();
		list_2.setBounds(295, 13, 0, 0);
		panel_5.add(list_2);

		JList list_3 = new JList();
		list_3.setBounds(300, 13, 0, 0);
		panel_5.add(list_3);

		uxValidateImagePanel = new JPanel();
		uxValidateImagePanel.setBounds(10, 85, 696, 272);
		uxValidateImagePanel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_5.add(uxValidateImagePanel);

		JLabel lblNewLabel_4 = new JLabel("type");
		lblNewLabel_4.setBounds(10, 10, 71, 20);
		panel_5.add(lblNewLabel_4);

		JButton btnEndScreenCapture = new JButton("End Capture");

		btnEndScreenCapture.setBounds(441, 11, 129, 23);
		panel_5.add(btnEndScreenCapture);

		JList list_4 = new JList();
		list_4.setModel(new AbstractListModel() {
			String[] values = new String[] { "Image", "None" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});

		list_4.setBounds(121, 34, -73, -20);
		panel_5.add(list_4);

		JLabel lblNewLabel_3_1 = new JLabel("Time Out (MS)");
		lblNewLabel_3_1.setBounds(10, 40, 95, 13);
		panel_5.add(lblNewLabel_3_1);

		JTextPane textPane = new JTextPane();
		textPane.setBounds(94, 58, 117, -28);
		panel_5.add(textPane);

		uxValidationTimeOutMS1 = new JTextField();
		uxValidationTimeOutMS1.setBounds(149, 39, 129, 19);
		panel_5.add(uxValidationTimeOutMS1);
		uxValidationTimeOutMS1.setColumns(10);

		Choice uxValidationType1 = new Choice();
		// uxValidationType1.add
		uxValidationType1.setBounds(149, 9, 129, 21);
		panel_5.add(uxValidationType1);

		JButton uxValidationTestValidation1 = new JButton("Test Validation");
		uxValidationTestValidation1.setBounds(577, 11, 129, 23);
		panel_5.add(uxValidationTestValidation1);

		JButton btnStartCaptureImage = new JButton("Start Capture");
		btnStartCaptureImage.setBounds(300, 9, 129, 23);
		panel_5.add(btnStartCaptureImage);
		uxValidationTestValidation1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("IMAGE COMPAIR: "
						+ toro.rmRoutineManager.currentRoutine.currentStep.Validate(uxPhonePanel.image));
				// double d =
				// FixedUtil.compaireImage(uxImagePanel_2.image.getSubimage((uxImagePanel_2.selection.x,
				// uxImagePanel_2.selection.y, uxImagePanel_2.selection.width,
				// uxImagePanel_2.selection.height), uxValidateImagePanel1.get)
			}
		});

		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_6.setBounds(12, 537, 740, 258);
		panel_2.add(panel_6);
		panel_6.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("Pass Action");
		lblNewLabel_5.setBounds(22, 3, 127, 24);
		panel_6.add(lblNewLabel_5);

		JLabel lblNewLabel_4_1_1 = new JLabel("type");
		lblNewLabel_4_1_1.setBounds(22, 39, 35, 20);
		panel_6.add(lblNewLabel_4_1_1);

		Choice uxValidationType1_1_1 = new Choice();
		uxValidationType1_1_1.setBounds(63, 37, 185, 22);
		panel_6.add(uxValidationType1_1_1);

		JButton createActionButton = new JButton("Start Record Action");

		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBounds(22, 86, 706, 90);
		panel_6.add(textArea);

		createActionButton.setBounds(254, 37, 150, 24);
		panel_6.add(createActionButton);

		JButton btnNewButton_1 = new JButton("Test Action");
		btnNewButton_1.setBounds(578, 37, 150, 24);
		panel_6.add(btnNewButton_1);

		JButton btnEndRecordActions = new JButton("End Record Actions");
		btnEndRecordActions.setBounds(416, 37, 150, 24);
		panel_6.add(btnEndRecordActions);
		
		final JCheckBox chckbxLookAtNextStep = new JCheckBox("Alwayes look at next step");
		chckbxLookAtNextStep.setBounds(22, 200, 191, 24);
		panel_6.add(chckbxLookAtNextStep);
		
		final JCheckBox chckbxNoValidation = new JCheckBox("Do not validate");
		chckbxNoValidation.setBounds(22, 230, 191, 24);
		panel_6.add(chckbxNoValidation);

		JButton btnNewButton_3 = new JButton("Save Step");
		btnNewButton_3.setBounds(256, 12, 205, 26);
		panel_2.add(btnNewButton_3);

		JButton btnCreateNewStep = new JButton("Create New Step");

		btnCreateNewStep.setBounds(12, 12, 215, 26);
		panel_2.add(btnCreateNewStep);

		JButton btnNewButton_5 = new JButton("Delete Step");
		btnNewButton_5.setBounds(484, 12, 215, 26);
		panel_2.add(btnNewButton_5);

		JPanel panel_6_1 = new JPanel();
		panel_6_1.setLayout(null);
		panel_6_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_6_1.setBounds(10, 877, 742, 211);
		panel_2.add(panel_6_1);

		JLabel lblNewLabel_5_1 = new JLabel("Fail Action");
		lblNewLabel_5_1.setBounds(22, 3, 127, 24);
		panel_6_1.add(lblNewLabel_5_1);

		JLabel lblNewLabel_4_1_1_1 = new JLabel("type");
		lblNewLabel_4_1_1_1.setBounds(22, 39, 37, 20);
		panel_6_1.add(lblNewLabel_4_1_1_1);

		Choice choiceFailedAction = new Choice();
		choiceFailedAction.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			}
		});
		choiceFailedAction.setBounds(68, 37, 182, 22);
		panel_6_1.add(choiceFailedAction);

		JButton btnStartRecordFailAction = new JButton("Start Record Action");
		btnStartRecordFailAction.setBounds(256, 37, 150, 24);
		panel_6_1.add(btnStartRecordFailAction);

		JButton btnEndRecordFailAction = new JButton("End Record Action");
		btnEndRecordFailAction.setBounds(418, 37, 150, 24);
		panel_6_1.add(btnEndRecordFailAction);

		JButton btnNewButton_1_1_1 = new JButton("Test Action");
		btnNewButton_1_1_1.setBounds(580, 37, 150, 24);
		panel_6_1.add(btnNewButton_1_1_1);

		final JTextArea taFailAction = new JTextArea();
		taFailAction.setLineWrap(true);
		taFailAction.setBounds(32, 89, 698, 105);
		panel_6_1.add(taFailAction);

		final Choice choiceStepSelection = new Choice();
		choiceStepSelection.setBounds(256, 44, 205, 22);
		// choiceStepSelection.setModel(toro.rmRoutineManager.currentRoutine);
		panel_2.add(choiceStepSelection);

		// choiceStepSelection.
		// for(int i = 0; i < toro.alRoutines.size(); i++) {
		// choiceStepSelection.addItem(toro.alRoutines.get(i).routineName);
		// }

		JLabel lblNewLabel_2_1_2_1 = new JLabel("Select Step ");
		lblNewLabel_2_1_2_1.setBounds(10, 50, 213, 14);
		panel_2.add(lblNewLabel_2_1_2_1);

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(260, 8, 211, 26);
		uxRoutienTab.add(btnSave);

		JButton deleteRoutine = new JButton("Delete Routine");
		deleteRoutine.setBounds(493, 8, 211, 26);
		uxRoutienTab.add(deleteRoutine);

		// new JTextField();
		final JTextField tfRoutinepName = new JTextField();
		tfRoutinepName.setBounds(260, 68, 211, 20);
		uxRoutienTab.add(tfRoutinepName);
		tfRoutinepName.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Routien Name");
		lblNewLabel_9.setBounds(6, 71, 224, 16);
		uxRoutienTab.add(lblNewLabel_9);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Auto Hit Learning", null, panel_1, null);
		panel_1.setLayout(null);

		JList list = new JList();
		list.setBounds(24, 595, 330, 18);
		panel_1.add(list);

		JButton btnSaveImage = new JButton("Save Image");
		btnSaveImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSaveImage.setBounds(225, 111, 129, 23);
		panel_1.add(btnSaveImage);

		JButton btnSnipImage = new JButton("Snip Image");
		btnSnipImage.setBounds(225, 145, 129, 23);
		panel_1.add(btnSnipImage);

		JButton btnSaveScreenRecord = new JButton("Save Screen Record");
		btnSaveScreenRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(e.getSource().toString());
			}
		});
		btnSaveScreenRecord.setBounds(225, 289, 129, 23);
		panel_1.add(btnSaveScreenRecord);

		JLabel lblNewLabel = new JLabel("Capture Type");
		lblNewLabel.setBounds(24, 364, 78, 14);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Pitch Type");
		lblNewLabel_1.setBounds(24, 474, 78, 14);
		panel_1.add(lblNewLabel_1);

		JLabel lblGameType = new JLabel("Game Type");
		lblGameType.setBounds(24, 395, 78, 14);
		panel_1.add(lblGameType);

		JLabel lblNewLabel_2_1 = new JLabel("Right or Left");
		lblNewLabel_2_1.setBounds(24, 420, 78, 14);
		panel_1.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("Ball Speed");
		lblNewLabel_2_1_1.setBounds(24, 449, 78, 14);
		panel_1.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_2_1_1_1 = new JLabel("");
		lblNewLabel_2_1_1_1.setBounds(24, 464, 78, 14);
		panel_1.add(lblNewLabel_2_1_1_1);

		Choice uxCaptureTypeInput = new Choice();
		uxCaptureTypeInput.setBounds(120, 364, 234, 20);
		panel_1.add(uxCaptureTypeInput);

		Choice uxGameTypeInput = new Choice();
		uxGameTypeInput.setBounds(120, 389, 234, 20);
		panel_1.add(uxGameTypeInput);
		uxGameTypeInput.add("");
		uxGameTypeInput.add(Game.BONUS);
		uxGameTypeInput.add(Game.PRIME);
		uxGameTypeInput.add(Game.TOURNMENT);
		uxGameTypeInput.add(Game.WOH);

		Choice uxRightOrLeftPitcherInput = new Choice();
		uxRightOrLeftPitcherInput.setBounds(120, 415, 234, 20);
		panel_1.add(uxRightOrLeftPitcherInput);
		uxRightOrLeftPitcherInput.add("");
		uxRightOrLeftPitcherInput.add(Pitch.LH_PITCH);
		uxRightOrLeftPitcherInput.add(Pitch.RH_PITCH);
		// uxRightOrLeftPitcherInput.add

		Choice uxPitchTypeInput = new Choice();
		uxPitchTypeInput.setBounds(120, 468, 234, 20);
		panel_1.add(uxPitchTypeInput);

		uxPitchTypeInput.add("");
		uxPitchTypeInput.add(Pitch.FAST_BALL);
		uxPitchTypeInput.add(Pitch.CURVE_BALL);

		Choice choice_1_1_1_1_1 = new Choice();
		choice_1_1_1_1_1.setBounds(120, 511, 234, 20);
		panel_1.add(choice_1_1_1_1_1);

		Choice choice_1_1_1_1_1_1 = new Choice();
		choice_1_1_1_1_1_1.setBounds(120, 546, 234, 20);
		panel_1.add(choice_1_1_1_1_1_1);

		JSlider uxBallSpeedInput = new JSlider();
		uxBallSpeedInput.setMinimum(60);
		uxBallSpeedInput.setMaximum(105);
		uxBallSpeedInput.setBounds(120, 441, 235, 26);
		panel_1.add(uxBallSpeedInput);

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("New tab", null, tabbedPane_1, null);

		uxPhonePanel = new PhonePanel();
		uxPhonePanel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		uxPhonePanel.setBounds(816, 12, 698, 1232);
		panel.add(uxPhonePanel);
		JScrollPane scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(816, 1256, 698, 73);
		panel.add(scroll);

		final JTextArea logTextArea = new JTextArea();
		scroll.setViewportView(logTextArea);
		PrintStream printStream = new PrintStream((java.io.OutputStream) new CustomOutputStream(logTextArea));

		// keeps reference of standard output stream
		 standardOut = System.out;

		// re-assigns standard output stream and error output stream
		// System.setOut(printStream);
		// System.set

		/* Listener and UX setup */

		// INITIALIZE Routine DropDown
		for (int i = 0; i < toro.rmRoutineManager.getSize(); i++) {
			choiceRoutineSelection.add(toro.rmRoutineManager.getElementAt(i));
			cMacroInitializeRoutien.add(toro.rmRoutineManager.getElementAt(i));
			cMainRoutien.add(toro.rmRoutineManager.getElementAt(i));
			cEndRoutien.add(toro.rmRoutineManager.getElementAt(i));
			cErrorRoutien.add(toro.rmRoutineManager.getElementAt(i));
		}

		/*MACRO TAB Section*/
		cMacroInitializeRoutien.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				toro.singleMacroTest.rStart = toro.rmRoutineManager.alRoutineList
						.get(cMacroInitializeRoutien.getSelectedIndex());
			}
		});
		
		cMainRoutien.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				toro.singleMacroTest.rMain = toro.rmRoutineManager.alRoutineList
						.get(cMainRoutien.getSelectedIndex());
			}
		});
		
		/*
		cEndRoutien.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				toro.singleMacroTest.rError = toro.rmRoutineManager.alRoutineList
						.get(cEndRoutien.getSelectedIndex());
			}
		});*/
		
		cErrorRoutien.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				toro.singleMacroTest.rError = toro.rmRoutineManager.alRoutineList
						.get(cErrorRoutien.getSelectedIndex());
			}
		});
		
		btnStartMacro.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	System.out.println("Strating Thread");
				toro.singleMacroTestThread = new Thread(toro.singleMacroTest);
				toro.singleMacroTestThread.start();
		    }
		});
		
		/*
		btnStartMacro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Strating Thread");
				toro.singleMacroTestThread = new Thread(toro.singleMacroTest);
				toro.singleMacroTestThread.start();
			}
		});*/
		/*END MACRO TAB SECTION*/

		toro.rmRoutineManager.currentRoutine = toro.rmRoutineManager.alRoutineList.get(0);
		// toro.rmRoutineManager.alRoutineList.get(choiceRoutineSelection.getSelectedIndex()).routineName
		// = toro.rmRoutineManager.currentRoutine.routineName;

		// Add Selection option
		choiceRoutineSelection.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("setting values now" + choiceRoutineSelection.getSelectedIndex());
				toro.rmRoutineManager.currentRoutine = toro.rmRoutineManager.alRoutineList
						.get(choiceRoutineSelection.getSelectedIndex());
				toro.rmRoutineManager.currentRoutine.currentStep = toro.rmRoutineManager.currentRoutine.alStepList
						.get(0);

				choiceStepSelection.removeAll();
				choiceStepSelection.invalidate();

				for (int i = 0; i < toro.rmRoutineManager.currentRoutine.alStepList.size(); i++) {
					choiceStepSelection.add(toro.rmRoutineManager.currentRoutine.alStepList.get(i).stepName);
				}
				// toro.rmRoutineManager.currentRoutine.currentStep =
				// toro.rmRoutineManager.currentRoutine.alStepList.get(0);
				choiceStepSelection.select(0);

				// Set Image
				JLabel screenLabel = new JLabel(new ImageIcon(toro.rmRoutineManager.currentRoutine.currentStep.image));
				uxValidateImagePanel.removeAll();
				uxValidateImagePanel.add(screenLabel);
				uxValidateImagePanel.revalidate();
				uxValidateImagePanel.repaint();

				// Set Text
				tfRoutinepName.setText(toro.rmRoutineManager.currentRoutine.routineName);

				textArea.setText(toro.rmRoutineManager.currentRoutine.currentStep.passAction.sXML);
				taFailAction.setText(toro.rmRoutineManager.currentRoutine.currentStep.failAction.sXML);

				taFailAction.revalidate();
				taFailAction.repaint();

				textArea.revalidate();
				textArea.repaint();
			}
		});

		/*
		 * 
		 * tfRoutinepName.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) {
		 * System.out.println("tfRoutinepName : actionPerformed : START");
		 * //toro.rmRoutineManager.currentRoutine.routineName =
		 * tfRoutinepName.getText(); int l = choiceRoutineSelection.getSelectedIndex();
		 * choiceRoutineSelection.removeAll(); choiceRoutineSelection.invalidate(); for
		 * (int i = 0; i < toro.rmRoutineManager.getSize(); i++) {
		 * choiceRoutineSelection.add(toro.rmRoutineManager.getElementAt(i)); } //
		 * String stemp = choiceRoutineSelection.getSelectedItem(); // stemp =
		 * tfRoutinepName.getText(); choiceRoutineSelection.select(l);
		 * choiceRoutineSelection.invalidate(); choiceRoutineSelection.repaint();
		 * System.out.println("tfRoutinepName : actionPerformed : END"); } });
		 */

		tfRoutinepName.setText(choiceRoutineSelection.getSelectedItem());

		tfRoutinepName.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("tfRoutinepName : keyTyped : START");
				toro.rmRoutineManager.currentRoutine.routineName = tfRoutinepName.getText();
				toro.rmRoutineManager.alRoutineList.get(choiceRoutineSelection
						.getSelectedIndex()).routineName = toro.rmRoutineManager.currentRoutine.routineName
								+ e.getKeyChar();
				int l = choiceRoutineSelection.getSelectedIndex();
				choiceRoutineSelection.removeAll();
				choiceRoutineSelection.invalidate();
				for (int i = 0; i < toro.rmRoutineManager.getSize(); i++) {
					choiceRoutineSelection.add(toro.rmRoutineManager.getElementAt(i));
				}
				// String stemp = choiceRoutineSelection.getSelectedItem();
				// stemp = tfRoutinepName.getText();
				choiceRoutineSelection.select(l);
				// choiceRoutineSelection.invalidate();
				choiceRoutineSelection.repaint();
				//System.out.println("tfRoutinepName : keyTyped : END");

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		// Save Button //
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Toro.doc.getFirstChild() != null)
					Toro.doc.removeChild(Toro.doc.getFirstChild());
				toro.rmRoutineManager.toXML();
			}
		});
		
		// Create Routine Button Listener //
		btnCreateNewRoutine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toro.rmRoutineManager.currentRoutine = new Routine();
				toro.rmRoutineManager.alRoutineList.add(toro.rmRoutineManager.currentRoutine);
				choiceRoutineSelection.add(toro.rmRoutineManager.currentRoutine.routineName);
				choiceRoutineSelection.select(choiceRoutineSelection.getItemCount() - 1);

				toro.rmRoutineManager.currentRoutine.currentStep = toro.rmRoutineManager.currentRoutine.alStepList
						.get(0);

				choiceStepSelection.removeAll();
				choiceStepSelection.invalidate();

				for (int i = 0; i < toro.rmRoutineManager.currentRoutine.alStepList.size(); i++) {
					choiceStepSelection.add(toro.rmRoutineManager.currentRoutine.alStepList.get(i).stepName);
				}
				// toro.rmRoutineManager.currentRoutine.currentStep =
				// toro.rmRoutineManager.currentRoutine.alStepList.get(0);
				choiceStepSelection.select(0);

				// Set Image
				JLabel screenLabel = new JLabel(new ImageIcon(toro.rmRoutineManager.currentRoutine.currentStep.image));
				uxValidateImagePanel.removeAll();
				uxValidateImagePanel.add(screenLabel);
				uxValidateImagePanel.revalidate();
				uxValidateImagePanel.repaint();

				// Set Text
				tfRoutinepName.setText(toro.rmRoutineManager.currentRoutine.routineName);

				textArea.setText(toro.rmRoutineManager.currentRoutine.currentStep.passAction.sXML);
				taFailAction.setText(toro.rmRoutineManager.currentRoutine.currentStep.failAction.sXML);

				taFailAction.revalidate();
				taFailAction.repaint();

				textArea.revalidate();
				textArea.repaint();
			}
		});

		// choiceStepSelection No Initialization of this
		for (int i = 0; i < toro.rmRoutineManager.currentRoutine.getSize(); i++) {
			choiceStepSelection.add(toro.rmRoutineManager.currentRoutine.alStepList.get(i).stepName);

		}

		//

		// Create New Step
		btnCreateNewStep.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// This should be method add step//
				toro.rmRoutineManager.currentRoutine.currentStep = new Step();
				toro.rmRoutineManager.currentRoutine.alStepList.add(toro.rmRoutineManager.currentRoutine.currentStep);
				choiceStepSelection.add(toro.rmRoutineManager.currentRoutine.currentStep.stepName);
				choiceStepSelection.select(toro.rmRoutineManager.currentRoutine.getSize() - 1);

				JLabel screenLabel = new JLabel(new ImageIcon(toro.rmRoutineManager.currentRoutine.currentStep.image));
				uxValidateImagePanel.removeAll();
				uxValidateImagePanel.add(screenLabel);
				uxValidateImagePanel.revalidate();
				uxValidateImagePanel.repaint();

				textArea.setText(toro.rmRoutineManager.currentRoutine.currentStep.passAction.sXML);
				taFailAction.setText(toro.rmRoutineManager.currentRoutine.currentStep.failAction.sXML);

				taFailAction.revalidate();
				taFailAction.repaint();

				textArea.revalidate();
				textArea.repaint();
			}
		});

		// step change

		choiceStepSelection.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("choiceStepSelection: itemStateChanged : " + choiceStepSelection.getSelectedIndex());

				toro.rmRoutineManager.currentRoutine.currentStep = toro.rmRoutineManager.currentRoutine.alStepList
						.get(choiceStepSelection.getSelectedIndex());

				JLabel screenLabel = new JLabel(new ImageIcon(toro.rmRoutineManager.currentRoutine.currentStep.image));
				uxValidateImagePanel.removeAll();
				uxValidateImagePanel.add(screenLabel);
				uxValidateImagePanel.revalidate();
				uxValidateImagePanel.repaint();

				tfStepName.setText(toro.rmRoutineManager.currentRoutine.currentStep.stepName);

				textArea.setText(toro.rmRoutineManager.currentRoutine.currentStep.passAction.sXML);
				taFailAction.setText(toro.rmRoutineManager.currentRoutine.currentStep.failAction.sXML);

				taFailAction.revalidate();
				taFailAction.repaint();

				textArea.revalidate();
				textArea.repaint();

			}
		});
		tfStepName.setText(choiceStepSelection.getSelectedItem());
		tfStepName.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("tfStepName : keyTyped : START");
				toro.rmRoutineManager.currentRoutine.currentStep.stepName = tfStepName.getText() + e.getKeyChar();

				int l = choiceStepSelection.getSelectedIndex();

				// toro.rmRoutineManager.alRoutineList.get(choiceRoutineSelection.getSelectedIndex()).routineName
				// = toro.rmRoutineManager.currentRoutine.routineName;
				// int l = choiceRoutineSelection.getSelectedIndex();
				choiceStepSelection.removeAll();
				choiceStepSelection.invalidate();
				for (int i = 0; i < toro.rmRoutineManager.currentRoutine.alStepList.size(); i++) {
					choiceStepSelection.add(toro.rmRoutineManager.currentRoutine.alStepList.get(i).stepName);
				}
				// String stemp = choiceRoutineSelection.getSelectedItem();
				// stemp = tfRoutinepName.getText();
				choiceStepSelection.select(l);
				// choiceRoutineSelection.invalidate();
				choiceStepSelection.repaint();
				//System.out.println("tfSteppName : keyTyped : END");

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		// VALIDATION//
		btnStartCaptureImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uxPhonePanel.bCreateAction = false;
				uxPhonePanel.bScreenCapture = true;
				// textArea.revalidate();
				// textArea.repaint();
			}
		});

		btnEndScreenCapture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// //System.out.println(e.getSource().toString());
				//System.out.println("Capturing Sub Image");
				uxPhonePanel.bCreateAction = false;
				uxPhonePanel.bScreenCapture = false;
				toro.rmRoutineManager.currentRoutine.currentStep.image = uxPhonePanel.getImageSnip();
				// toro.rmRoutineManager.currentRoutine.alStepList.get(0).image =
				// uxImagePanel_2.getImageSnip();
				toro.rmRoutineManager.currentRoutine.currentStep.subImageLocation = uxPhonePanel.selection;
				// toro.alRoutines.get(0).alStepList.get(0).passAction.
				JLabel screenLabel = new JLabel(new ImageIcon(toro.rmRoutineManager.currentRoutine.currentStep.image));
				uxValidateImagePanel.removeAll();
				uxValidateImagePanel.add(screenLabel);
				uxValidateImagePanel.revalidate();
				uxValidateImagePanel.repaint();
			}
		});
		
		btnTogleStartStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toro.singleMacroTest.paused.set(!toro.singleMacroTest.paused.get());
				if(toro.singleMacroTest.paused.get()) {
					toro.singleMacroTestThread.notify();
				}
			
			}
		});
		
		btnLive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uxPhonePanel.liveClick = !uxPhonePanel.liveClick;
			
			}
		});

		// PASS ACTION Commands

		chckbxLookAtNextStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toro.rmRoutineManager.currentRoutine.currentStep.passAction.bLookAtNextStep = chckbxLookAtNextStep.isSelected();
				
			}
		});
		
		chckbxNoValidation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toro.rmRoutineManager.currentRoutine.currentStep.passAction.noValidation = chckbxNoValidation.isSelected();
			}
		});

		
		createActionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uxPhonePanel.bCreateAction = true;
				uxPhonePanel.bScreenCapture = false;
				toro.rmRoutineManager.currentRoutine.currentStep.passAction.sXML = "";
				textArea.setText("");
				textArea.revalidate();
				textArea.repaint();
			}
		});

		btnEndRecordActions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// System.out.println(e.getSource().toString());
				// System.out.println("Turning on Action Switch");
				// textarea.revalidate();
				uxPhonePanel.bCreateAction = false;

				toro.rmRoutineManager.currentRoutine.currentStep.passAction.sXML = uxPhonePanel.sCreatedAction;
				toro.rmRoutineManager.currentRoutine.currentStep.passAction.alPointList = uxPhonePanel.alPoint;
				uxPhonePanel.alPoint = new ArrayList<Point>();
				// textArea.removeAll();
				textArea.setText(uxPhonePanel.sCreatedAction);
				textArea.revalidate();
				textArea.repaint();

				uxPhonePanel.sCreatedAction = "";
			}
		});

		// fail Action Command
		btnStartRecordFailAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uxPhonePanel.bCreateAction = true;
				taFailAction.setText("");
				taFailAction.revalidate();
				taFailAction.repaint();
			}
		});

		btnEndRecordFailAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// System.out.println(e.getSource().toString());
				// System.out.println("Turning on Action Switch");
				// textarea.revalidate();
				uxPhonePanel.bCreateAction = false;
				toro.rmRoutineManager.currentRoutine.currentStep.failAction.sXML = uxPhonePanel.sCreatedAction;
				toro.rmRoutineManager.currentRoutine.currentStep.failAction.alPointList = uxPhonePanel.alPoint;
				uxPhonePanel.alPoint = new ArrayList<Point>();
				// textArea.removeAll();
				taFailAction.setText(uxPhonePanel.sCreatedAction);
				taFailAction.revalidate();
				taFailAction.repaint();

				uxPhonePanel.sCreatedAction = "";
			}
		});
		// btnEndRecordFailAction

	}
}

/*
 * JButton btnCaptureImage = new JButton("Capture Image");
 * btnCaptureImage.addActionListener(new ActionListener() { public void
 * actionPerformed(ActionEvent e) { //Toro t = ((ToroUI)e.getSource()).toro;
 * //Add call to screen capture //bind the UX element to the captured screen
 * //BufferedImage screen =
 * ToroUI.toro.robot.createScreenCapture(ToroUI.toro.toroUtils.rectangle);
 * fixedUtil.setScreenCapture(); //uxImagePanel.removeAll(); JLabel screenLabel
 * = new JLabel(new ImageIcon(fixedUtil.fScreateScreenCapture));
 * uxImagePanel.removeAll(); uxImagePanel.add(screenLabel);
 * uxImagePanel.revalidate();
 * 
 * uxImagePanel.repaint(); } }); btnCaptureImage.setBounds(225, 77, 129, 23);
 * panel_1.add(btnCaptureImage);
 * 
 * JButton btnStartScreenRecord = new JButton("Start Screen Record");
 * btnStartScreenRecord.setBounds(225, 221, 129, 23);
 * panel_1.add(btnStartScreenRecord);
 * 
 * btnStartScreenRecord.addActionListener(new ActionListener() { public void
 * actionPerformed(ActionEvent e) { //Toro t = ((ToroUI)e.getSource()).toro;
 * //Add call to screen capture //bind the UX element to the captured screen
 * //BufferedImage screen =
 * ToroUI.toro.robot.createScreenCapture(ToroUI.toro.toroUtils.rectangle);
 * fixedUtil.isRecord = true; new Thread(() ->
 * fixedUtil.setRecordWindow(uxImagePanel)).start();
 * 
 * } });
 * 
 * JButton btnEndScreenRecord = new JButton("End Screen Record");
 * btnEndScreenRecord.setBounds(225, 255, 129, 23);
 * panel_1.add(btnEndScreenRecord); btnEndScreenRecord.addActionListener(new
 * ActionListener() { public void actionPerformed(ActionEvent e) { //Toro t =
 * ((ToroUI)e.getSource()).toro; //Add call to screen capture //bind the UX
 * element to the captured screen //BufferedImage screen =
 * ToroUI.toro.robot.createScreenCapture(ToroUI.toro.toroUtils.rectangle);
 * fixedUtil.isRecord = false;
 * 
 * } btnEndScreenRecord.addActionListener(new ActionListener() { public void
 * actionPerformed(ActionEvent e) { } });
 * btnStartScreenRecord.addActionListener(new ActionListener() { public void
 * actionPerformed(ActionEvent e) { } }); });
 */