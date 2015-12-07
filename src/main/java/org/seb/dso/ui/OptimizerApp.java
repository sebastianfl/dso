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
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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

import org.seb.dso.CharacterSnapshot;
import org.seb.dso.Inventory;
import org.seb.dso.model.Item;
import org.seb.dso.model.Mod;
import org.seb.dso.model.Modifier;
import org.seb.dso.util.ItemUtils;

import net.miginfocom.swing.MigLayout;

/**
 * @author Sebastian
 *
 */
public class OptimizerApp extends JPanel implements ActionListener {

	private static final Logger fLogger = Logger.getLogger(OptimizerApp.class.getPackage().getName());
	/**
	* 
	*/
	private static final long serialVersionUID = 1238659605741906256L;
	private List<CharacterSnapshot> snapshots;
	private JFrame frame;
	private JFileChooser fileChooser;
	private JButton openButton;
	private JPanel panelNorth;
	private JPanel panelSouth;
	private JPanel panelWest;
	private JLabel progressLabel;
	private JProgressBar progressBar;
	private JPanel panelEast;
	private JPanel panelCenter;

	/**
	 * the csv file with the item list to be selected by the user
	 */
	private File itemsFile;
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
	private JButton btnLoadSnapshots;
	private JButton buttonGenerateSnapshots;
	private JLabel lblAttack;
	private JCheckBox checkboxWeaponDamage;
	private JRadioButton radioGreen;
	private JRadioButton radioBlue;
	private JRadioButton radioPurple;
	private JRadioButton radioRed;
	private JRadioButton radioNo;
	private JSlider sliderAttack;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JCheckBox checkboxRage;
	private JLabel lblAgility;
	private JSlider sliderAgility;
	private JLabel lblDefensiveGems;
	private JTextField textFieldDefGems;
	private JLabel lblOffensiveGems;
	private JTextField textFieldOffGems;
	private JLabel lblFunction;
	private JTextField textFieldFunction;
	private JLabel lblFunctions;
	private Component rigidArea;
	private Component rigidArea_1;
	private Component rigidArea_2;
	private Component rigidArea_3;
	private JLabel lblSnapshotsLoaded;
	private Component rigidArea_4;
	private JButton processButton;

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
	/**
	 * 
	 */

	/**
	 * 
	 */
	/**
	 * 
	 */
	private void initialize() {

		try {
			UIManager.setLookAndFeel("net.sourceforge.napkinlaf.NapkinLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 440);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panelCenter = new JPanel();
		frame.getContentPane().add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new MigLayout("",
				"[100px:100px:100px,grow][100px:100px,grow][100px:100px:100px,grow][100px:100px,grow]",
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

		fileChooser = new JFileChooser(".");

		panelNorth = new JPanel();
		frame.getContentPane().add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) SystemColor.windowBorder));
		FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panelNorth.add(panel, BorderLayout.NORTH);
		openButton = new JButton("Load Items");
		panel.add(openButton);

		rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		panel.add(rigidArea_3);

		buttonGenerateSnapshots = new JButton("Generate Snapshots");
		panel.add(buttonGenerateSnapshots);
		buttonGenerateSnapshots.addActionListener(this);

		rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		panel.add(rigidArea_2);

		btnLoadSnapshots = new JButton("Load Snapshots");
		btnLoadSnapshots.addActionListener(this);
		panel.add(btnLoadSnapshots);

		rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		panel.add(rigidArea_1);

		JLabel lblSelectClass = new JLabel("Select Class");
		panel.add(lblSelectClass);

		JComboBox<String> comboBox = new JComboBox<String>();
		panel.add(comboBox);

		rigidArea = Box.createRigidArea(new Dimension(40, 20));
		panel.add(rigidArea);

		lblFunctions = new JLabel("Functions");
		panel.add(lblFunctions);

		processButton = new JButton("Process");
		panel.add(processButton);
		processButton.setVerticalAlignment(SwingConstants.TOP);
		processButton.addActionListener(this);

		JButton btnNewButton_1 = new JButton("Most Defense");
		panel.add(btnNewButton_1);
		btnNewButton_1.setVerticalAlignment(SwingConstants.TOP);

		panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) SystemColor.windowBorder));
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelNorth.add(panel_1, BorderLayout.CENTER);

		lblSnapshotsLoaded = new JLabel("Snapshots loaded: 0");
		panel_1.add(lblSnapshotsLoaded);

		rigidArea_4 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_4);

		lblAttack = new JLabel("Attack");
		panel_1.add(lblAttack);

		sliderAttack = new JSlider();
		sliderAttack.setValue(25);
		sliderAttack.setMaximum(25);
		sliderAttack.setMinorTickSpacing(1);
		sliderAttack.setMajorTickSpacing(5);
		sliderAttack.setPaintTicks(true);
		sliderAttack.setPaintLabels(true);
		panel_1.add(sliderAttack);

		lblAgility = new JLabel("Agility");
		panel_1.add(lblAgility);

		sliderAgility = new JSlider();
		sliderAgility.setValue(0);
		sliderAgility.setMaximum(25);
		sliderAgility.setMinorTickSpacing(1);
		sliderAgility.setMajorTickSpacing(5);
		sliderAgility.setPaintTicks(true);
		sliderAgility.setPaintLabels(true);
		panel_1.add(sliderAgility);

		checkboxWeaponDamage = new JCheckBox("50% Weapon Damage");
		panel_1.add(checkboxWeaponDamage);

		checkboxRage = new JCheckBox("25% Rage/Mana");
		panel_1.add(checkboxRage);

		panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) SystemColor.windowBorder));
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panelNorth.add(panel_2, BorderLayout.SOUTH);

		radioNo = new JRadioButton("No Essence");
		panel_2.add(radioNo);
		radioNo.setSelected(true);

		radioGreen = new JRadioButton("Green");
		panel_2.add(radioGreen);

		radioBlue = new JRadioButton("Blue");
		panel_2.add(radioBlue);

		radioPurple = new JRadioButton("Purple");
		panel_2.add(radioPurple);

		radioRed = new JRadioButton("Red");
		panel_2.add(radioRed);
		ButtonGroup bp = new ButtonGroup();
		bp.add(radioBlue);
		bp.add(radioGreen);
		bp.add(radioNo);
		bp.add(radioPurple);
		bp.add(radioRed);
		radioBlue.addActionListener(this);
		radioBlue.setActionCommand("100%");
		radioGreen.addActionListener(this);
		radioBlue.setActionCommand("50");
		radioNo.addActionListener(this);
		radioNo.setActionCommand("0");
		radioRed.addActionListener(this);
		radioRed.setActionCommand("300%");
		radioPurple.addActionListener(this);
		radioPurple.setActionCommand("200%");

		comboBox.addItem("Mage");
		comboBox.addItem("DK");
		openButton.addActionListener(this);

		panelSouth = new JPanel();
		frame.getContentPane().add(panelSouth, BorderLayout.SOUTH);

		progressLabel = new JLabel("Creating Snapshot Database...");
		panelSouth.add(progressLabel);
		progressLabel.setVisible(false);

		progressBar = new JProgressBar();
		panelSouth.add(progressBar);
		progressBar.setVisible(false);

		panelWest = new JPanel();
		frame.getContentPane().add(panelWest, BorderLayout.WEST);
		panelWest.setLayout(new MigLayout("", "[120px,grow]", "[23px][23px][23px][23px][23px][23px]"));

		lblDefensiveGems = new JLabel("Defensive Gems");
		panelWest.add(lblDefensiveGems, "cell 0 0");

		textFieldDefGems = new JTextField();
		panelWest.add(textFieldDefGems, "cell 0 1,growx");
		textFieldDefGems.setColumns(10);

		lblOffensiveGems = new JLabel("Offensive Gems");
		panelWest.add(lblOffensiveGems, "cell 0 2");

		textFieldOffGems = new JTextField();
		panelWest.add(textFieldOffGems, "cell 0 3,growx");
		textFieldOffGems.setColumns(10);

		lblFunction = new JLabel("Function");
		panelWest.add(lblFunction, "cell 0 4");

		textFieldFunction = new JTextField();
		panelWest.add(textFieldFunction, "cell 0 5,growx");
		textFieldFunction.setColumns(10);

		panelEast = new JPanel();
		frame.getContentPane().add(panelEast, BorderLayout.EAST);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == openButton) {
			int ret = fileChooser.showOpenDialog(OptimizerApp.this);
			if (ret == JFileChooser.APPROVE_OPTION) {
				itemsFile = fileChooser.getSelectedFile();
				progressLabel.setText("Items Loaded Successfully");
				progressLabel.setVisible(true);
			}
		} else if (e.getSource() == buttonGenerateSnapshots) {
			Thread qthread = new Thread() {
				public void run() {
					generateSnapshots();
				}
			};
			qthread.start();
		} else if (e.getSource() == btnLoadSnapshots) {
			int ret = fileChooser.showOpenDialog(OptimizerApp.this);
			if (ret == JFileChooser.APPROVE_OPTION) {
				itemsFile = fileChooser.getSelectedFile();
				readSnapshots();

			}

		} else if (e.getSource() == processButton) {
			Thread qthread = new Thread() {

				public void run() {
					processItems();
				}

			};
			qthread.start();
		}
	}

	@SuppressWarnings("unchecked")
	private void readSnapshots() {
		try {
			progressLabel.setText("Loading Snapshots from hard drive...");
			InputStream file = new FileInputStream("snapshots.sn");
			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream(buffer);
			snapshots = (List<CharacterSnapshot>) input.readObject();
			System.out.println("Recovered snapshots: " + snapshots.size());
			input.close();
			progressLabel.setText("Successfully loaded " + snapshots.size() + " snapshots.");
			lblSnapshotsLoaded.setText("Snapshots loaded: " + snapshots.size());
			fLogger.log(Level.INFO, "Recovered snapshots: " + snapshots.size());

		} catch (ClassNotFoundException ex) {
			fLogger.log(Level.SEVERE, "Cannot perform input. Class not found.", ex);
		} catch (IOException ex) {
			fLogger.log(Level.SEVERE, "Cannot perform input.", ex);
		}
	}

	private void generateSnapshots() {
		Collection<Item> items = ItemUtils.getItems(itemsFile);
		Inventory inv = ItemUtils.parseInventoryFromItems(items);
		int size = inv.getAmulets().size() * inv.getBelts().size() * inv.getCloaks().size() * inv.getCrystals().size()
				* inv.getTwohands().size() * inv.getHelmets().size() * inv.getPauldrons().size()
				* inv.getTorsos().size() * inv.getGloves().size() * inv.getBoots().size() * (inv.getRings().size())
				* (inv.getRings().size() - 1) / 2;
		progressLabel.setText("Loading " + size + "snapshots...");

		snapshots = ItemUtils.getAllSnapshots(inv);
		progressLabel.setText("Snapshots loaded. Saving...");
		try (OutputStream file = new FileOutputStream("snapshots.sn");
				OutputStream buffer = new BufferedOutputStream(file);
				ObjectOutput output = new ObjectOutputStream(buffer);) {
			output.writeObject(snapshots);
		} catch (IOException ex) {
			fLogger.log(Level.SEVERE, "Cannot perform output.", ex);
		}
		progressLabel.setText("Snapshots saved");
		lblSnapshotsLoaded.setText("Snapshots loaded: " + size);

	}

	private void processItems() {
		startProgress(snapshots.size());

		// off gem mods, def gem mods, attack mods, agility mods, essence mods,
		// wp mod, rage mod
		String setupKey = "";
		String str = textFieldOffGems.getText();
		setupKey += str;
		Modifier[] offGemMods = ItemUtils.parseModifiersFromString(str);
		str = textFieldDefGems.getText();
		setupKey += str;
		Modifier[] defGemMods = ItemUtils.parseModifiersFromString(str);
		Modifier attack = new Modifier();
		attack.setType(Mod.DAMAGE);
		attack.setValue(String.valueOf(sliderAttack.getValue() * 2.0) + "%");

		Modifier agility = new Modifier();
		agility.setType(Mod.ATTACK_SPEED);
		agility.setValue(String.valueOf(sliderAgility.getValue() * 1.6) + "%");

		Modifier[] weaponDmg = null;
		if (checkboxWeaponDamage.isSelected()) {
			weaponDmg = new Modifier[2];
			weaponDmg[0] = new Modifier();
			weaponDmg[1] = new Modifier();
			weaponDmg[0].setType(Mod.EXTRA_WEAPON_DMG);
			weaponDmg[0].setValue("50%");
			weaponDmg[1].setType(Mod.ATTACK_SPEED);
			weaponDmg[1].setValue("-10%");
		}
		Modifier[] rage = null;
		if (checkboxRage.isSelected()) {
			rage = new Modifier[2];
			rage[0] = new Modifier();
			rage[1] = new Modifier();
			rage[0].setType(Mod.MANA);
			rage[0].setValue("25%");
			rage[1].setType(Mod.TRAVEL_SPEED);
			rage[1].setValue("-5%");
		}

		String v = "0";
		if (radioGreen.isSelected())
			v = "40";
		else if (radioBlue.isSelected())
			v = "100%";
		else if (radioPurple.isSelected())
			v = "200%";
		else if (radioRed.isSelected())
			v = "300%";
		Modifier essence = new Modifier();
		essence.setType(Mod.DAMAGE);
		essence.setValue(v);

		Modifier[] additionalMods = { attack, agility, essence };
		// Modifier essence = new Modifier();
		// essence.setType(Mod.ATTACK_SPEED);
		// essence.setValue(String.valueOf(sliderAgility.getValue()*1.6) + "%");

		double max = 0;
		CharacterSnapshot bestSnapshot = null;
		int i = 0;
		for (Iterator<CharacterSnapshot> iterator = snapshots.iterator(); iterator.hasNext();) {
			CharacterSnapshot cs = iterator.next();
			cs.clean();
			// Process all the items
			cs.processModifiers();
			// process sets if any
			cs.processSets();
			// process gems if any
			if (null != offGemMods)
				cs.processModifiers(Arrays.asList(offGemMods));
			if (null != defGemMods)
				cs.processModifiers(Arrays.asList(defGemMods));
			if (null != weaponDmg)
				cs.processModifiers(Arrays.asList(weaponDmg));
			if (null != rage)
				cs.processModifiers(Arrays.asList(rage));
			cs.processModifiers(Arrays.asList(additionalMods));
			double cmd = cs.getCp().calculateEffectiveDamage();
			if (cmd > max) {
				max = cmd;
				bestSnapshot = cs;
			}
			++i;
			updateProgress(i, bestSnapshot);
		}
		updateGUI(bestSnapshot);
		System.out.println(bestSnapshot);
	}

	private void startProgress(int size) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				progressBar.setMaximum(size);
				progressBar.setVisible(true);
				progressLabel.setVisible(true);
			}

		});

	}

	private void updateProgress(int i, CharacterSnapshot cs) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				progressBar.setValue(i);
				/*
				 * labelAmuletValue.setText(cs.getAmulet().toString());
				 * labelBeltValue.setText(cs.getBelt().toString());
				 * labelCloakValue.setText(cs.getCloak().toString());
				 * labelRing1Value.setText(cs.getRing1().toString());
				 * labelRing2Value.setText(cs.getRing2().toString());
				 * labelAdornmentValue.setText(cs.getCrystal().toString());
				 * labelWeaponValue.setText(cs.getTwohand().toString());
				 * labelHelmetValue.setText(cs.getHelmet().toString());
				 * labelPauldronsValue.setText(cs.getPauldrons().toString());
				 * labelTorsoValue.setText(cs.getTorso().toString());
				 * labelGlovesValue.setText(cs.getGloves().toString());
				 * labelBootsValue.setText(cs.getBoots().toString());
				 * 
				 * double minDmg = Math.round(cs.getCp().calculateMinDamage() *
				 * 100.0) / 100.0; double maxDmg =
				 * Math.round(cs.getCp().calculateMaxDamage() * 100.0) / 100.0;
				 * labelMinDamage.setText(String.valueOf(minDmg));
				 * labelMaxDamage.setText(String.valueOf(maxDmg));
				 * labelMedianDamage.setText(String.valueOf((maxDmg + minDmg) /
				 * 2.00)); labelHP.setText(String.valueOf(Math.round(cs.getCp().
				 * calculateHP())));
				 * labelArmor.setText(String.valueOf(Math.round(cs.getCp().
				 * calculateArmor())));
				 * labelCriticalHit.setText(String.valueOf(Math.round(cs.getCp()
				 * .calculateCrit() * 100.0) / 100.0) + "%"); labelOffenseIndex
				 * .setText(String.valueOf(Math.round(cs.getCp().
				 * calculateEffectiveDamage() * 100.0) / 100.0));
				 * labelResistance.setText(String.valueOf(Math.round(cs.getCp().
				 * calculateResist()))); labelCriticalDamage
				 * .setText(String.valueOf(Math.round((cs.getCp().getCd() + 200)
				 * * 100.0) / 100.0) + "%"); labelAttackSpeed.setText(
				 * String.valueOf(Math.round((0.95 + cs.getCp().getAspeed() /
				 * 100.0 * 0.95) * 100.0) / 100.0) + " ps");
				 * labelTravelSpeed.setText(String.valueOf(Math.round(cs.getCp()
				 * .getTspeed() * 100.0) / 100.0) + "%");
				 */
			}

		});

	}

	private void updateGUI(final CharacterSnapshot cs) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				if (labelAmuletValue.getText().equals(cs.getAmulet().toString()))
					labelAmuletValue.setForeground(Color.DARK_GRAY);
				else
					labelAmuletValue.setForeground(Color.red);
				labelAmuletValue.setText(cs.getAmulet().toString());

				if (labelBeltValue.getText().equals(cs.getBelt().toString()))
					labelBeltValue.setForeground(Color.DARK_GRAY);
				else
					labelBeltValue.setForeground(Color.red);
				labelBeltValue.setText(cs.getBelt().toString());

				if (labelCloakValue.getText().equals(cs.getCloak().toString()))
					labelCloakValue.setForeground(Color.DARK_GRAY);
				else
					labelCloakValue.setForeground(Color.red);
				labelCloakValue.setText(cs.getCloak().toString());

				if (labelRing1Value.getText().equals(cs.getRing1().toString()))
					labelRing1Value.setForeground(Color.DARK_GRAY);
				else
					labelRing1Value.setForeground(Color.red);
				labelRing1Value.setText(cs.getRing1().toString());

				if (labelRing2Value.getText().equals(cs.getRing2().toString()))
					labelRing2Value.setForeground(Color.DARK_GRAY);
				else
					labelRing2Value.setForeground(Color.red);
				labelRing2Value.setText(cs.getRing2().toString());

				if (labelAdornmentValue.getText().equals(cs.getCrystal().toString()))
					labelAdornmentValue.setForeground(Color.DARK_GRAY);
				else
					labelAdornmentValue.setForeground(Color.red);
				labelAdornmentValue.setText(cs.getCrystal().toString());

				// TODO add others
				labelWeaponValue.setText(cs.getTwohand().toString());
				labelHelmetValue.setText(cs.getHelmet().toString());
				labelPauldronsValue.setText(cs.getPauldrons().toString());
				labelTorsoValue.setText(cs.getTorso().toString());
				labelGlovesValue.setText(cs.getGloves().toString());
				labelBootsValue.setText(cs.getBoots().toString());

				double minDmg = Math.round(cs.getCp().calculateMinDamage() * 100.0) / 100.0;
				double maxDmg = Math.round(cs.getCp().calculateMaxDamage() * 100.0) / 100.0;
				labelMinDamage.setText(String.valueOf(minDmg));
				labelMaxDamage.setText(String.valueOf(maxDmg));
				labelMedianDamage.setText(String.valueOf((maxDmg + minDmg) / 2.00));
				labelHP.setText(String.valueOf(Math.round(cs.getCp().calculateHP())));
				labelArmor.setText(String.valueOf(Math.round(cs.getCp().calculateArmor())));
				labelCriticalHit.setText(String.valueOf(Math.round(cs.getCp().calculateCrit() * 100.0) / 100.0) + "%");
				labelOffenseIndex
						.setText(String.valueOf(Math.round(cs.getCp().calculateEffectiveDamage() * 100.0) / 100.0));
				labelResistance.setText(String.valueOf(Math.round(cs.getCp().calculateResist())));
				labelCriticalDamage
						.setText(String.valueOf(Math.round((cs.getCp().getCd() + 200) * 100.0) / 100.0) + "%");
				double attackSpeed = 0.95 + cs.getCp().getAspeed() / 100.0 * 0.95;
				System.out.println(attackSpeed);
				labelAttackSpeed.setText(
						String.valueOf(Math.round((0.95 + cs.getCp().getAspeed() / 100.0 * 0.95) * 100.0) / 100.0)
								+ " ps");
				labelTravelSpeed.setText(String.valueOf(Math.round(cs.getCp().getTspeed() * 100.0) / 100.0) + "%");

				progressBar.setVisible(false);
				progressLabel.setText("Calculation completed");

			}

		});

	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}
}
