package org.seb.dso.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.seb.dso.CharacterSnapshot;
import org.seb.dso.Inventory;
import org.seb.dso.model.CharacterPower;
import org.seb.dso.model.EnumTypes;
import org.seb.dso.model.Item;
import org.seb.dso.model.Modifier;
import org.seb.dso.model.OptimizerModel;
import org.seb.dso.model.SetConfig;
import org.seb.dso.model.e.ModelChangeEvent;
import org.seb.dso.model.e.ModelChangeListener;
import org.seb.dso.util.ItemUtils;
import org.seb.dso.util.PropertyManager;

import net.miginfocom.swing.MigLayout;

/**
 * TODO Fix all 1000+ checkstyle warnings TODO Constants TODO L10N/I18N
 * 
 * @author Sebastian
 *
 */
public class OptimizerApp extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1238659605741906256L;
	private static final Logger fLogger = Logger.getLogger(OptimizerApp.class.getPackage().getName());

	private OptimizerModel om = new OptimizerModel();

	public synchronized OptimizerModel getOM() {
		return om;
	}

	public synchronized void setOM(OptimizerModel om) {
		this.om = om;
	}

	private JFrame frame;
	private JFileChooser fileChooser;
	private JButton openButton;
	private JPanel panelNorth;
	private JPanel panelSouth;
	private JPanel panelWest;
	private JLabel progressLabel;
	private JPanel panelEast;
	private JPanel panelCenter;
	private JLabel labelAmulet;
	private JLabel labelBelt;
	private JLabel labelCloak;
	private JLabel labelRing1;
	private JLabel labelRing2;
	private JLabel labelAdornment;
	private JLabel labelWeapon;
	private JLabel labelHelmet;
	private JLabel labelBoots;
	private JLabel labelPauldrons;
	private JLabel labelGloves;
	private JLabel labelTorso;
	private JLabel labelAmuletValue;
	private JLabel labelBeltValue;
	private JLabel labelCloakValue;
	private JLabel labelRing1Value;
	private JLabel labelRing2Value;
	private JLabel labelAdornmentValue;
	private JLabel labelWeaponValue;
	private JLabel labelHelmetValue;
	private JLabel labelBootsValue;
	private JLabel labelPauldronsValue;
	private JLabel labelGlovesValue;
	private JLabel labelTorsoValue;
	private JProgressBar progressBar;

	private JLabel labelOffenseIndex;
	private JLabel labelMinDamage;
	private JLabel labelMaxDamage;
	private JLabel labelMedianDamage;
	private JLabel labelAttackSpeed;
	private JLabel labelCriticalHit;
	private JLabel labelCriticalDamage;
	private JLabel labelHP;
	private JLabel labelArmor;
	private JLabel labelResistance;
	private JLabel labelTravelSpeed;
	private JButton buttonGenerateSnapshots;
	private JLabel labelAttack;
	private JComboBox<String> dropdownCharacterClass;
	private JCheckBox checkboxTwohanded;
	private JCheckBox checkboxWeaponDamage;
	private JRadioButton radioGreen;
	private JRadioButton radioBlue;
	private JRadioButton radioPurple;
	private JRadioButton radioRed;
	private JRadioButton radioNo;
	private JSlider sliderAttack;
	private JPanel panelTopLevelMenu;
	private JPanel panelSecondLevelMenu;
	private JPanel panelThirdLevelMenu;
	private JCheckBox checkboxRage;
	private JLabel lblAgility;
	private JSlider sliderAgility;
	private JLabel lblDefensiveGems;
	private JTextField textFieldDefGems;
	private JLabel lblOffensiveGems;
	private JTextField textFieldOffGems;
	private JLabel labelPetAndBuff;
	private JTextField textFieldPetAndBuffs;
	private JLabel lblFunctions;
	private Component rigidArea;
	private Component rigidArea_1;
	private Component rigidArea_2;
	private Component rigidArea_3;
	private JLabel lblSnapshotsLoaded;
	private Component rigidArea_4;
	private JButton processButton;

	private List<CharacterSnapshot> snapshots;
	private File itemsFile;

	/**
	 * Launch the application.el
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OptimizerApp window = new OptimizerApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OptimizerApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		try {
			UIManager.setLookAndFeel("net.sourceforge.napkinlaf.NapkinLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 540);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initNorth();
		initCenter();
		initWest();
		initSouth();

		// TODO PanelEast will contain last 10 compiled snapshots
		panelEast = new JPanel();
		frame.getContentPane().add(panelEast, BorderLayout.EAST);

		om.addModelChangeListener(new ModelChangeListener() {

			@Override
			public void modelChanged(ModelChangeEvent e) {
				switch (e.getEventType()) {
				case CHARCLASS:
					break;
				case INVETORY:
					break;
				case MODIFIER:
					break;
				case STATE: {
					progressLabel.setForeground(Color.BLACK);
					switch (e.getCommand()) {
					case CALCULATED:
						progressLabel.setText("Found the best build");
						progressBar.setVisible(false);
						break;
					case CALCULATING:
						progressLabel.setText("Looking for the best build");
						progressBar.setValue(0);
						progressBar.setVisible(true);
						break;
					case CLEAN:
						progressLabel.setText("Start by loading items ");
						progressBar.setVisible(false);
						break;
					case ERROR:
						progressLabel.setText("Error:  " + e.getMessage());
						progressBar.setVisible(false);
						progressLabel.setForeground(Color.RED);
						setComponentEnable(processButton, false);
						break;
					case GENERATED_SNAPSHOTS:
						progressLabel.setText("Generated " + e.getMessage() + " snapshots");
						progressBar.setVisible(false);
						break;
					case GENERATING_SNAPSHOTS:
						progressLabel.setText("Generating " + e.getMessage() + " snapshots");
						progressBar.setVisible(false);
						break;
					case LOADED:
						progressLabel.setText("Preprocessing required.");
						progressBar.setVisible(false);
						setComponentEnable(buttonGenerateSnapshots, true);
						setComponentEnable(checkboxTwohanded, true);
						setComponentEnable(dropdownCharacterClass, true);

						setComponentEnable(processButton, false);
						setComponentEnable(textFieldDefGems, false);
						setComponentEnable(textFieldPetAndBuffs, false);
						setComponentEnable(textFieldOffGems, false);
						setComponentEnable(sliderAttack, false);
						setComponentEnable(sliderAgility, false);
						setComponentEnable(checkboxRage, false);
						setComponentEnable(checkboxWeaponDamage, false);
						setComponentEnable(radioBlue, false);
						setComponentEnable(radioGreen, false);
						setComponentEnable(radioNo, false);
						setComponentEnable(radioPurple, false);
						setComponentEnable(radioRed, false);

						break;
					case LOADING:
						progressLabel.setText("Loading items");
						progressBar.setVisible(false);
						setComponentEnable(checkboxTwohanded, false);
						setComponentEnable(dropdownCharacterClass, false);
						setComponentEnable(buttonGenerateSnapshots, false);
						setComponentEnable(processButton, false);
						setComponentEnable(textFieldDefGems, false);
						setComponentEnable(textFieldPetAndBuffs, false);
						setComponentEnable(textFieldOffGems, false);
						setComponentEnable(sliderAttack, false);
						setComponentEnable(sliderAgility, false);
						setComponentEnable(checkboxRage, false);
						setComponentEnable(checkboxWeaponDamage, false);
						setComponentEnable(radioBlue, false);
						setComponentEnable(radioGreen, false);
						setComponentEnable(radioNo, false);
						setComponentEnable(radioPurple, false);
						setComponentEnable(radioRed, false);

						break;
					case PREPROCESSED:
						progressLabel.setText("Ready for calculation");
						progressBar.setVisible(false);
						setComponentEnable(processButton, true);
						setComponentEnable(textFieldDefGems, true);
						setComponentEnable(textFieldPetAndBuffs, true);
						setComponentEnable(textFieldOffGems, true);
						setComponentEnable(sliderAttack, true);
						setComponentEnable(sliderAgility, true);
						setComponentEnable(checkboxRage, true);
						setComponentEnable(checkboxWeaponDamage, true);
						setComponentEnable(radioBlue, true);
						setComponentEnable(radioGreen, true);
						setComponentEnable(radioNo, true);
						setComponentEnable(radioPurple, true);
						setComponentEnable(radioRed, true);

						break;
					case PREPROCESSING:
						progressLabel.setText("Preprocessing for a class: " + e.getMessage());
						progressBar.setValue(0);
						progressBar.setVisible(true);
						break;
					default:
						break;
					}
					break;
				}
				default:
					break;

				}
				refresh();
			}
		});
	}

	private void initSouth() {
		panelSouth = new JPanel();
		frame.getContentPane().add(panelSouth, BorderLayout.SOUTH);

		progressLabel = new JLabel("Start by loading items");
		panelSouth.add(progressLabel);
		progressLabel.setVisible(true);

		progressBar = new JProgressBar();
		panelSouth.add(progressBar);
		progressBar.setVisible(false);

	}

	private void initWest() {
		panelWest = new JPanel();
		frame.getContentPane().add(panelWest, BorderLayout.WEST);
		panelWest.setLayout(new MigLayout("", "[140px,grow]", "[23px][23px][23px][23px][23px][23px]"));

		// Defensive gems and the value change listener to update the model

		lblDefensiveGems = new JLabel("Defensive Gems");
		panelWest.add(lblDefensiveGems, "cell 0 0");

		textFieldDefGems = new JTextField();
		panelWest.add(textFieldDefGems, "cell 0 1,growx");
		textFieldDefGems.setColumns(10);
		textFieldDefGems.getDocument().addDocumentListener(new ModifierFieldDocumentListener());

		// Offensive gems and the value change listener to update the model

		lblOffensiveGems = new JLabel("Offensive Gems");
		panelWest.add(lblOffensiveGems, "cell 0 2");

		textFieldOffGems = new JTextField();
		panelWest.add(textFieldOffGems, "cell 0 3,growx");
		textFieldOffGems.setColumns(10);
		textFieldOffGems.getDocument().addDocumentListener(new ModifierFieldDocumentListener());

		// Pet and Buffs editfield and label, and the value change listener to
		// update the model

		labelPetAndBuff = new JLabel("Pet and Buffs");
		panelWest.add(labelPetAndBuff, "cell 0 4");

		textFieldPetAndBuffs = new JTextField();
		panelWest.add(textFieldPetAndBuffs, "cell 0 5,growx");
		textFieldPetAndBuffs.setColumns(10);
		textFieldPetAndBuffs.getDocument().addDocumentListener(new ModifierFieldDocumentListener());

		textFieldDefGems.setEnabled(false);
		textFieldPetAndBuffs.setEnabled(false);
		textFieldOffGems.setEnabled(false);

	}

	private void initCenter() {
		panelCenter = new JPanel();
		frame.getContentPane().add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new MigLayout("",
				"[100px:100px:100px][100px:100px,grow,fill][100px:100px:100px][100px:100px]",
				"[15px,baseline][15px,baseline][15px,baseline][15px,baseline][15px,baseline][15px,baseline][15px,baseline][15px,baseline][15px,baseline][15px,baseline][15px,baseline][15px,baseline]"));

		labelAmulet = new JLabel("Amulet");
		panelCenter.add(labelAmulet, "cell 0 0");

		labelBelt = new JLabel("Belt");
		panelCenter.add(labelBelt, "cell 0 1");

		labelCloak = new JLabel("Cloak");
		panelCenter.add(labelCloak, "cell 0 2");

		labelRing1 = new JLabel("Ring");
		panelCenter.add(labelRing1, "cell 0 3");

		labelRing2 = new JLabel("Ring");
		panelCenter.add(labelRing2, "cell 0 4");

		labelAdornment = new JLabel("Adornment");
		panelCenter.add(labelAdornment, "cell 0 5");

		labelWeapon = new JLabel("Weapon");
		panelCenter.add(labelWeapon, "cell 0 6");

		labelHelmet = new JLabel("Helmet");
		panelCenter.add(labelHelmet, "cell 0 7");

		labelPauldrons = new JLabel("Pauldrons");
		panelCenter.add(labelPauldrons, "cell 0 8");

		labelTorso = new JLabel("Torso");
		panelCenter.add(labelTorso, "cell 0 9");

		labelGloves = new JLabel("Gloves");
		panelCenter.add(labelGloves, "cell 0 10");

		labelBoots = new JLabel("Boots");
		panelCenter.add(labelBoots, "cell 0 11");

		labelAmuletValue = new JLabel("");
		panelCenter.add(labelAmuletValue, "cell 1 0");

		labelBeltValue = new JLabel("");
		panelCenter.add(labelBeltValue, "cell 1 1");

		labelCloakValue = new JLabel("");
		panelCenter.add(labelCloakValue, "cell 1 2");

		labelRing1Value = new JLabel("");
		panelCenter.add(labelRing1Value, "cell 1 3");

		labelRing2Value = new JLabel("");
		panelCenter.add(labelRing2Value, "cell 1 4");

		labelAdornmentValue = new JLabel("");
		panelCenter.add(labelAdornmentValue, "cell 1 5");

		labelWeaponValue = new JLabel("");
		panelCenter.add(labelWeaponValue, "cell 1 6");

		labelHelmetValue = new JLabel("");
		panelCenter.add(labelHelmetValue, "cell 1 7");

		labelPauldronsValue = new JLabel("");
		panelCenter.add(labelPauldronsValue, "cell 1 8");

		labelTorsoValue = new JLabel("");
		panelCenter.add(labelTorsoValue, "cell 1 9");

		labelGlovesValue = new JLabel("");
		panelCenter.add(labelGlovesValue, "cell 1 10");

		labelBootsValue = new JLabel("");
		panelCenter.add(labelBootsValue, "cell 1 11");

		JLabel tmp = new JLabel("Offense Index");
		panelCenter.add(tmp, "cell 2 0");
		labelOffenseIndex = new JLabel();
		panelCenter.add(labelOffenseIndex, "cell 3 0");

		tmp = new JLabel("Minimal Damage");
		panelCenter.add(tmp, "cell 2 1");
		labelMinDamage = new JLabel();
		panelCenter.add(labelMinDamage, "cell 3 1");

		tmp = new JLabel("Maximum Damage");
		panelCenter.add(tmp, "cell 2 2");
		labelMaxDamage = new JLabel();
		panelCenter.add(labelMaxDamage, "cell 3 2");

		tmp = new JLabel("Median Damage");
		panelCenter.add(tmp, "cell 2 3");
		labelMedianDamage = new JLabel();
		panelCenter.add(labelMedianDamage, "cell 3 3");

		tmp = new JLabel("Attack Speed");
		panelCenter.add(tmp, "cell 2 4");
		labelAttackSpeed = new JLabel();
		panelCenter.add(labelAttackSpeed, "cell 3 4");

		tmp = new JLabel("Critical Hit");
		panelCenter.add(tmp, "cell 2 5");
		labelCriticalHit = new JLabel();
		panelCenter.add(labelCriticalHit, "cell 3 5");

		tmp = new JLabel("Critical Damage");
		panelCenter.add(tmp, "cell 2 6");
		labelCriticalDamage = new JLabel();
		panelCenter.add(labelCriticalDamage, "cell 3 6");

		tmp = new JLabel("Health Points");
		panelCenter.add(tmp, "cell 2 7");
		labelHP = new JLabel();
		panelCenter.add(labelHP, "cell 3 7");

		tmp = new JLabel("Armor");
		panelCenter.add(tmp, "cell 2 8");
		labelArmor = new JLabel();
		panelCenter.add(labelArmor, "cell 3 8");

		tmp = new JLabel("Resistance");
		panelCenter.add(tmp, "cell 2 9");
		labelResistance = new JLabel();
		panelCenter.add(labelResistance, "cell 3 9");

		tmp = new JLabel("Travel Speed");
		panelCenter.add(tmp, "cell 2 10");
		labelTravelSpeed = new JLabel();
		panelCenter.add(labelTravelSpeed, "cell 3 10");

	}

	/**
	 * 
	 */
	private void initNorth() {
		panelNorth = new JPanel();
		frame.getContentPane().add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new BorderLayout(0, 0));

		panelTopLevelMenu = new JPanel();
		panelTopLevelMenu.setBorder(new MatteBorder(1, 1, 1, 1, (Color) SystemColor.windowBorder));
		FlowLayout flowLayout_1 = (FlowLayout) panelTopLevelMenu.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);

		panelNorth.add(panelTopLevelMenu, BorderLayout.NORTH);
		openButton = new JButton("Load Items");
		panelTopLevelMenu.add(openButton);
		openButton.addActionListener(this);
		fileChooser = new JFileChooser(".");

		rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		panelTopLevelMenu.add(rigidArea_1);

		JLabel lblSelectClass = new JLabel("Select Class");
		panelTopLevelMenu.add(lblSelectClass);

		// Character class dropdown and the action listener to update the model
		dropdownCharacterClass = new JComboBox<String>();
		panelTopLevelMenu.add(dropdownCharacterClass);
		dropdownCharacterClass.addItem("Mage");
		dropdownCharacterClass.addItem("Dragonknight");
		// dropdownCharacterClass.addItem("Ranger");
		// dropdownCharacterClass.addItem("Dwarf");
		dropdownCharacterClass.addActionListener(new FieldActionListener());

		rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		panelTopLevelMenu.add(rigidArea_2);

		checkboxTwohanded = new JCheckBox("Twohanded");
		panelTopLevelMenu.add(checkboxTwohanded);
		checkboxTwohanded.addActionListener(new FieldActionListener());

		rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		panelTopLevelMenu.add(rigidArea_3);

		buttonGenerateSnapshots = new JButton("Generate Snapshots");
		panelTopLevelMenu.add(buttonGenerateSnapshots);
		buttonGenerateSnapshots.addActionListener(this);
		buttonGenerateSnapshots.setEnabled(false);

		rigidArea = Box.createRigidArea(new Dimension(40, 20));
		panelTopLevelMenu.add(rigidArea);

		lblFunctions = new JLabel("Functions");
		panelTopLevelMenu.add(lblFunctions);

		processButton = new JButton("Process");
		processButton.setEnabled(false);
		panelTopLevelMenu.add(processButton);
		processButton.setVerticalAlignment(SwingConstants.TOP);
		processButton.addActionListener(this);
		processButton.setOpaque(true);

		// TODO Implement other functions
		JButton defenseButton = new JButton("Most Defense");
		panelTopLevelMenu.add(defenseButton);
		defenseButton.setVerticalAlignment(SwingConstants.TOP);
		defenseButton.setEnabled(false);
		defenseButton.setVisible(true);
		defenseButton.setToolTipText("Not implemented yet");

		panelSecondLevelMenu = new JPanel();
		panelSecondLevelMenu.setBorder(new MatteBorder(1, 1, 1, 1, (Color) SystemColor.windowBorder));
		FlowLayout flowLayout = (FlowLayout) panelSecondLevelMenu.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelNorth.add(panelSecondLevelMenu, BorderLayout.CENTER);

		lblSnapshotsLoaded = new JLabel("Snapshots loaded: 0");
		panelSecondLevelMenu.add(lblSnapshotsLoaded);

		rigidArea_4 = Box.createRigidArea(new Dimension(20, 20));
		panelSecondLevelMenu.add(rigidArea_4);

		labelAttack = new JLabel("Attack");
		panelSecondLevelMenu.add(labelAttack);

		sliderAttack = new JSlider();
		sliderAttack.setValue(25);
		sliderAttack.setMaximum(25);
		sliderAttack.setMinorTickSpacing(1);
		sliderAttack.setMajorTickSpacing(5);
		sliderAttack.setPaintTicks(true);
		sliderAttack.setPaintLabels(true);
		panelSecondLevelMenu.add(sliderAttack);
		sliderAttack.addChangeListener(new ModifierFieldChangeListener());

		lblAgility = new JLabel("Agility");
		panelSecondLevelMenu.add(lblAgility);

		sliderAgility = new JSlider();
		sliderAgility.setValue(0);
		sliderAgility.setMaximum(25);
		sliderAgility.setMinorTickSpacing(1);
		sliderAgility.setMajorTickSpacing(5);
		sliderAgility.setPaintTicks(true);
		sliderAgility.setPaintLabels(true);
		panelSecondLevelMenu.add(sliderAgility);
		sliderAgility.addChangeListener(new ModifierFieldChangeListener());

		checkboxWeaponDamage = new JCheckBox("50% Weapon Damage");
		panelSecondLevelMenu.add(checkboxWeaponDamage);
		checkboxWeaponDamage.addActionListener(new ModifierFieldActionListener());

		checkboxRage = new JCheckBox("25% Rage/Mana");
		panelSecondLevelMenu.add(checkboxRage);
		checkboxRage.addActionListener(new ModifierFieldActionListener());

		panelThirdLevelMenu = new JPanel();
		panelThirdLevelMenu.setBorder(new MatteBorder(1, 1, 1, 1, (Color) SystemColor.windowBorder));
		FlowLayout flowLayout_2 = (FlowLayout) panelThirdLevelMenu.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panelNorth.add(panelThirdLevelMenu, BorderLayout.SOUTH);

		radioNo = new JRadioButton("No Essence");
		panelThirdLevelMenu.add(radioNo);
		radioNo.setSelected(true);

		radioGreen = new JRadioButton("Green");
		panelThirdLevelMenu.add(radioGreen);

		radioBlue = new JRadioButton("Blue");
		panelThirdLevelMenu.add(radioBlue);

		radioPurple = new JRadioButton("Purple");
		panelThirdLevelMenu.add(radioPurple);

		radioRed = new JRadioButton("Red");
		panelThirdLevelMenu.add(radioRed);

		ButtonGroup bp = new ButtonGroup();
		bp.add(radioBlue);
		bp.add(radioGreen);
		bp.add(radioNo);
		bp.add(radioPurple);
		bp.add(radioRed);

		radioBlue.addActionListener(new ModifierFieldActionListener());
		radioGreen.addActionListener(new ModifierFieldActionListener());
		radioNo.addActionListener(new ModifierFieldActionListener());
		radioRed.addActionListener(new ModifierFieldActionListener());
		radioPurple.addActionListener(new ModifierFieldActionListener());

		sliderAttack.setEnabled(false);
		sliderAgility.setEnabled(false);
		checkboxRage.setEnabled(false);
		checkboxTwohanded.setEnabled(false);
		checkboxWeaponDamage.setEnabled(false);
		radioBlue.setEnabled(false);
		radioGreen.setEnabled(false);
		radioNo.setEnabled(false);
		radioPurple.setEnabled(false);
		radioRed.setEnabled(false);
		dropdownCharacterClass.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == openButton) {
			int ret = fileChooser.showOpenDialog(OptimizerApp.this);
			if (ret == JFileChooser.APPROVE_OPTION) {
				om.setState(EnumTypes.State.LOADING);
				itemsFile = fileChooser.getSelectedFile();
				try {
					Collection<Item> items = ItemUtils.getItems(itemsFile);
					om.setItems(items);
					Inventory inv = ItemUtils.parseInventoryFromItems(items);
					om.setInventory(inv);
				} catch (Exception ex) {
					om.setState(EnumTypes.State.ERROR, ex.getMessage());
					// this.progressLabel.setText(ex.getMessage());
					this.progressLabel.setVisible(true);
					// TODO make it nice
					return;
				}
				om.setState(EnumTypes.State.LOADED);

				// progressLabel.setText("Items Loaded Successfully");
				progressLabel.setVisible(true);
			}
		} else if (e.getSource() == buttonGenerateSnapshots) {
			Thread qthread = new Thread() {
				public void run() {
					try {
						generateSnapshots();
					} catch (Exception e) {
						om.setState(EnumTypes.State.ERROR, e.getMessage());
						// progressLabel.setText(e.getMessage());
						e.printStackTrace();
					}
				}
			};
			qthread.start();
		} else if (e.getSource() == processButton) {
			Thread qthread = new Thread() {

				public void run() {
					try {
						processItems();
					} catch (Exception e) {
						om.setState(EnumTypes.State.ERROR, e.getLocalizedMessage());
						// progressLabel.setText(e.getMessage());
						e.printStackTrace();
					}
				}

			};
			qthread.start();
		}
	}

	/**
	 * Generates all possible snapshots given the list of the items
	 * 
	 * @throws Exception
	 */
	private void generateSnapshots() throws Exception {

		String currentCharClass = (String) dropdownCharacterClass.getSelectedItem();
		om.setCharClass(OptimizerModel.CharClass.valueOf(currentCharClass.toUpperCase()));
		PropertyManager.getPropertyManager().setCurrentClass(currentCharClass);

		SetConfig.getSetConfig().reinitialize();
		fLogger.log(Level.INFO, "Character Class: " + (String) dropdownCharacterClass.getSelectedItem());

		boolean b = checkboxTwohanded.isSelected();
		om.setTwoHanded(b);

		// Number of snapshots to be generated
		// TODO optimize the array
		int size;
		if (b) {
			size = om.getInventory().getAmulets().size() * om.getInventory().getBelts().size()
					* om.getInventory().getCloaks().size() * om.getInventory().getCrystals().size()
					* om.getInventory().getTwohands().size() * om.getInventory().getHelmets().size()
					* om.getInventory().getPauldrons().size() * om.getInventory().getTorsos().size()
					* om.getInventory().getGloves().size() * om.getInventory().getBoots().size()
					* (om.getInventory().getRings().size()) * (om.getInventory().getRings().size() - 1) / 2;
		} else {
			size = om.getInventory().getAmulets().size() * om.getInventory().getBelts().size()
					* om.getInventory().getCloaks().size() * om.getInventory().getCrystals().size()
					* om.getInventory().getMainhands().size() * om.getInventory().getOffhands().size()
					* om.getInventory().getHelmets().size() * om.getInventory().getPauldrons().size()
					* om.getInventory().getTorsos().size() * om.getInventory().getGloves().size()
					* om.getInventory().getBoots().size() * (om.getInventory().getRings().size())
					* (om.getInventory().getRings().size() - 1) / 2;
		}
		if (size == 0) {
			throw new Exception("There should be at least one item of each kind.");
		}

		om.setState(EnumTypes.State.GENERATING_SNAPSHOTS, String.valueOf(size));

		snapshots = ItemUtils.getAllSnapshots(om.getInventory(), b);
		om.setState(EnumTypes.State.GENERATED_SNAPSHOTS, String.valueOf(size));

		lblSnapshotsLoaded.setText("Snapshots loaded: " + size);

		om.setState(EnumTypes.State.PREPROCESSING, currentCharClass);
		startProgress(size, "Snapshots generated. Pre-processing for a class: " + currentCharClass);
		int i = 0;
		for (Iterator<CharacterSnapshot> iterator = snapshots.iterator(); iterator.hasNext();) {
			CharacterSnapshot cs = iterator.next();
			cs.clean();
			// Process all the items
			cs.processModifiers();
			// process sets if any
			cs.processSets();
			++i;
			updateProgress(i, cs);
		}
		om.setState(EnumTypes.State.PREPROCESSED);
	}

	private void processItems() throws Exception {
		System.out.println(om);
		om.setState(EnumTypes.State.CALCULATING);
		populateModel();

		startProgress(snapshots.size(), "Processing...");

		// off gem mods, def gem mods, attack mods, agility mods, essence mods,
		// wp mod, rage mod

		Modifier[] additionalMods = { om.getAttack(), om.getAgility(), om.getEssence() };

		double max = 0;
		CharacterSnapshot bestSnapshot = null;
		CharacterPower bestPower = null;
		int i = 0;
		for (Iterator<CharacterSnapshot> iterator = snapshots.iterator(); iterator.hasNext();) {
			CharacterSnapshot cs = iterator.next();
			CharacterPower power = cs.getCharacterPowerCopy();
			if (null != om.getOffGems())
				cs.processModifiers(Arrays.asList(om.getOffGems()));
			if (null != om.getDefGems())
				cs.processModifiers(Arrays.asList(om.getDefGems()));
			if (null != om.getWeaponDmg())
				cs.processModifiers(Arrays.asList(om.getWeaponDmg()));
			if (null != om.getRage())
				cs.processModifiers(Arrays.asList(om.getRage()));
			if (null != om.getPetAndBuffs())
				cs.processModifiers(Arrays.asList(om.getPetAndBuffs()));

			cs.processModifiers(Arrays.asList(additionalMods));
			double cmd = cs.getCp().calculateEffectiveDamage();
			if (cmd > max) {
				max = cmd;
				bestSnapshot = cs;
				bestPower = cs.getCp();
			}
			++i;
			cs.setCp(power);
			updateProgress(i, bestSnapshot);
		}
		//bestSnapshot.setCp(bestPower);
		om.setState(EnumTypes.State.CALCULATED);

		updateGUI(bestSnapshot, bestPower);
		fLogger.log(Level.INFO, "Best snapshot: " + bestSnapshot.toString());

	}

	/**
	 * @throws Exception
	 * 
	 */
	private void populateModel() throws Exception {
		String cc = (String) dropdownCharacterClass.getSelectedItem();
		PropertyManager.getPropertyManager().setCurrentClass(cc);
		om.setCharClass(OptimizerModel.CharClass.valueOf(cc.toUpperCase()));

		SetConfig.getSetConfig().reinitialize();
		fLogger.log(Level.INFO, "Character Class: " + (String) dropdownCharacterClass.getSelectedItem());

		boolean b = checkboxTwohanded.isSelected();
		om.setTwoHanded(b);

		String str = textFieldOffGems.getText();
		Modifier[] offGemMods = ItemUtils.parseModifiersFromString(str);
		om.setOffGems(offGemMods);
		fLogger.log(Level.INFO, "OffGems: " + str);

		str = textFieldDefGems.getText();
		Modifier[] defGemMods = ItemUtils.parseModifiersFromString(str);

		om.setDefGems(defGemMods);
		fLogger.log(Level.INFO, "DefGems: " + str);

		str = textFieldPetAndBuffs.getText();
		Modifier[] petAndBuffs = ItemUtils.parseModifiersFromString(str);

		om.setPetAndBuffs(petAndBuffs);
		fLogger.log(Level.INFO, "PetAndBuffs: " + str);

		Modifier attack = new Modifier();
		attack.setType(Modifier.Type.PDAMAGE);
		attack.setValue(sliderAttack.getValue() * 2.0);
		attack.setAbsolute(false);
		om.setAttack(attack);

		Modifier agility = new Modifier();
		agility.setType(Modifier.Type.PATTACK_SPEED);
		agility.setValue(sliderAgility.getValue() * 1.6);
		agility.setAbsolute(false);
		om.setAgility(agility);

		Modifier[] weaponDmg = null;
		if (checkboxWeaponDamage.isSelected()) {
			fLogger.log(Level.INFO, "CheckBox WeaponDmg selected.");
			weaponDmg = new Modifier[2];
			weaponDmg[0] = new Modifier();
			weaponDmg[1] = new Modifier();
			weaponDmg[0].setType(Modifier.Type.PEXTRA_WEAPON_DMG);
			weaponDmg[0].setValue(50.0);
			weaponDmg[0].setAbsolute(false);
			weaponDmg[1].setType(Modifier.Type.PATTACK_SPEED);
			weaponDmg[1].setValue(-10.0);
			weaponDmg[1].setAbsolute(false);
		}
		om.setWeaponDmg(weaponDmg);

		Modifier[] rage = null;
		if (checkboxRage.isSelected()) {
			fLogger.log(Level.INFO, "CheckBox Rage selected.");
			rage = new Modifier[2];
			rage[0] = new Modifier();
			rage[1] = new Modifier();
			rage[0].setType(Modifier.Type.PMANA);
			rage[0].setValue(25.0);
			rage[0].setAbsolute(false);

			rage[1].setType(Modifier.Type.PTRAVEL_SPEED);
			rage[1].setValue(-5.0);
			rage[1].setAbsolute(false);
		}
		om.setRage(rage);

		Modifier essence = new Modifier();
		essence.setType(Modifier.Type.PDAMAGE);
		essence.setAbsolute(false);
		Double v = 0.0;
		if (radioGreen.isSelected()) {
			v = 50.0;
			essence.setAbsolute(true);
			essence.setType(Modifier.Type.DAMAGE);
		} else if (radioBlue.isSelected()) {
			v = 100.0;
		} else if (radioPurple.isSelected()) {
			v = 200.0;
		} else if (radioRed.isSelected()) {
			v = 300.0;
		}
		essence.setValue(v);
		om.setEssence(essence);

		fLogger.log(Level.INFO, "Essence in use:" + v);

	}

	private void startProgress(int size, String message) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				progressBar.setMaximum(size);
			}
		});

	}

	private void setComponentEnable(JComponent c, boolean b) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				c.setEnabled(b);
			};
		});
	}

	private void updateProgress(int i, CharacterSnapshot cs) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				progressBar.setValue(i);
			}
		});
	}

	private void refresh() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				frame.invalidate();
				frame.revalidate();
				frame.repaint();
			}
		});
	}

	private void updateGUI(final CharacterSnapshot cs, final CharacterPower cp) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				if (labelAmuletValue.getText().equals(cs.getAmulet().toString())) {
					labelAmuletValue.setForeground(Color.DARK_GRAY);
				} else {
					labelAmuletValue.setForeground(Color.red);
				}
				labelAmuletValue.setText(cs.getAmulet().toString());

				if (labelBeltValue.getText().equals(cs.getBelt().toString())) {
					labelBeltValue.setForeground(Color.DARK_GRAY);
				} else {
					labelBeltValue.setForeground(Color.red);
				}
				labelBeltValue.setText(cs.getBelt().toString());

				if (labelCloakValue.getText().equals(cs.getCloak().toString())) {
					labelCloakValue.setForeground(Color.DARK_GRAY);
				} else {
					labelCloakValue.setForeground(Color.red);
				}
				labelCloakValue.setText(cs.getCloak().toString());

				if (labelRing1Value.getText().equals(cs.getRing1().toString())) {
					labelRing1Value.setForeground(Color.DARK_GRAY);
				} else {
					labelRing1Value.setForeground(Color.red);
				}
				labelRing1Value.setText(cs.getRing1().toString());

				if (labelRing2Value.getText().equals(cs.getRing2().toString())) {
					labelRing2Value.setForeground(Color.DARK_GRAY);
				} else {
					labelRing2Value.setForeground(Color.red);
				}
				labelRing2Value.setText(cs.getRing2().toString());

				if (labelAdornmentValue.getText().equals(cs.getCrystal().toString())) {
					labelAdornmentValue.setForeground(Color.DARK_GRAY);
				} else {
					labelAdornmentValue.setForeground(Color.red);
				}
				labelAdornmentValue.setText(cs.getCrystal().toString());

				if (labelWeaponValue.getText().equals(cs.getTwohand().toString())) {
					labelWeaponValue.setForeground(Color.DARK_GRAY);
				} else {
					labelWeaponValue.setForeground(Color.red);
				}
				labelWeaponValue.setText(cs.getTwohand().toString());

				if (labelHelmetValue.getText().equals(cs.getHelmet().toString())) {
					labelHelmetValue.setForeground(Color.DARK_GRAY);
				} else {
					labelHelmetValue.setForeground(Color.red);
				}
				labelHelmetValue.setText(cs.getHelmet().toString());

				if (labelPauldronsValue.getText().equals(cs.getPauldrons().toString())) {
					labelPauldronsValue.setForeground(Color.DARK_GRAY);
				} else {
					labelPauldronsValue.setForeground(Color.red);
				}
				labelPauldronsValue.setText(cs.getPauldrons().toString());

				if (labelTorsoValue.getText().equals(cs.getTorso().toString())) {
					labelTorsoValue.setForeground(Color.DARK_GRAY);
				} else {
					labelTorsoValue.setForeground(Color.red);
				}
				labelTorsoValue.setText(cs.getTorso().toString());

				if (labelGlovesValue.getText().equals(cs.getGloves().toString())) {
					labelGlovesValue.setForeground(Color.DARK_GRAY);
				} else {
					labelGlovesValue.setForeground(Color.red);
				}
				labelGlovesValue.setText(cs.getGloves().toString());

				if (labelBootsValue.getText().equals(cs.getBoots().toString())) {
					labelBootsValue.setForeground(Color.DARK_GRAY);
				} else {
					labelBootsValue.setForeground(Color.red);
				}
				labelBootsValue.setText(cs.getBoots().toString());

				double minDmg = Math.round(cp.calculateMinDamage() * 100.0) / 100.0;
				double maxDmg = Math.round(cp.calculateMaxDamage() * 100.0) / 100.0;

				labelMinDamage.setText(String.valueOf(minDmg));
				labelMaxDamage.setText(String.valueOf(maxDmg));
				labelMedianDamage.setText(String.valueOf((maxDmg + minDmg) / 2.00));
				labelHP.setText(String.valueOf(Math.round(cp.calculateHP())));
				labelArmor.setText(String.valueOf(Math.round(cp.calculateArmor())));
				labelCriticalHit.setText(String.valueOf(Math.round(cp.calculateCrit() * 100.0) / 100.0) + "%");
				labelOffenseIndex.setText(String.valueOf(Math.round(cp.calculateEffectiveDamage() * 100.0) / 100.0));
				labelResistance.setText(String.valueOf(Math.round(cp.calculateResist())));
				labelCriticalDamage.setText(String.valueOf(Math.round((cp.getCd() + 200) * 100.0) / 100.0) + "%");
				double attackSpeed = 0.83333333 + cp.getAspeed() / 100.0 * 0.83333333;
				System.out.println(attackSpeed);
				labelAttackSpeed.setText(
						String.valueOf(Math.round((0.83333333 + cp.getAspeed() / 100.0 * 0.83333333) * 100.0) / 100.0)
								+ " ps");
				labelTravelSpeed.setText(String.valueOf(Math.round(cp.getTspeed() * 100.0) / 100.0) + "%");

				// progressLabel.setText("Calculation completed.");
				om.setState(EnumTypes.State.CALCULATED);

			}

		});

	}

	class ModifierFieldDocumentListener implements DocumentListener {

		@Override
		public void insertUpdate(DocumentEvent e) {
			om.setState(EnumTypes.State.PREPROCESSED);
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			om.setState(EnumTypes.State.PREPROCESSED);
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			om.setState(EnumTypes.State.PREPROCESSED);
		}
	}

	class FieldActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			om.setState(EnumTypes.State.LOADED);
		}
	}

	class ModifierFieldActionListener extends FieldActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			om.setState(EnumTypes.State.PREPROCESSED);
		}
	}

	class ModifierFieldChangeListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			om.setState(EnumTypes.State.PREPROCESSED);

		}
	}
}
