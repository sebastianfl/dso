package org.seb.dso.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.seb.dso.CharacterSnapshot;
import org.seb.dso.Inventory;
import org.seb.dso.model.Item;
import org.seb.dso.util.ItemUtils;

import net.miginfocom.swing.MigLayout;

/**
 * @author Sebastian
 *
 */
public class OptimizerApp extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1238659605741906256L;
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
		frame = new JFrame();
		frame.setBounds(100, 100, 795, 336);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panelCenter = new JPanel();
		frame.getContentPane().add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new MigLayout("",
				"[100px:100px:100px,grow][100px:100px,grow][100px:100px:100px][100px:100px:100px,grow]",
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

		JLabel lblSelectCsvFile = new JLabel("Select CSV File");
		panelNorth.add(lblSelectCsvFile);
		openButton = new JButton("Select CSV File");
		panelNorth.add(openButton);

		JLabel lblSelectClass = new JLabel("Select Class");
		panelNorth.add(lblSelectClass);

		JComboBox<String> comboBox = new JComboBox<String>();
		panelNorth.add(comboBox);
		comboBox.addItem("Mage");
		comboBox.addItem("DK");

		JButton btnCalculate = new JButton("Calculate");
		panelNorth.add(btnCalculate);
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
		panelWest.setLayout(new MigLayout("", "[89px]", "[23px][][]"));

		JButton btnNewButton = new JButton("Most Damage");
		btnNewButton.setVerticalAlignment(SwingConstants.TOP);
		panelWest.add(btnNewButton, "cell 0 0,alignx left,aligny top");

		JButton btnNewButton_1 = new JButton("Most Defense");
		btnNewButton_1.setVerticalAlignment(SwingConstants.TOP);
		panelWest.add(btnNewButton_1, "cell 0 1,alignx left,aligny top");

		JButton btnNewButton_2 = new JButton("New button");
		panelWest.add(btnNewButton_2, "cell 0 2,alignx left,aligny top");

		panelEast = new JPanel();
		frame.getContentPane().add(panelEast, BorderLayout.EAST);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == openButton) {
			int ret = fileChooser.showOpenDialog(OptimizerApp.this);
			if (ret == JFileChooser.APPROVE_OPTION) {
				itemsFile = fileChooser.getSelectedFile();
				Thread qthread = new Thread() {
					public void run() {
						processItems();
					}
				};
				qthread.start();
				// textPane.setText(bestSnapshot.toString());

			}
		}
	}

	private void processItems() {
		Collection<Item> items = ItemUtils.getItems(itemsFile);

		Inventory inv = ItemUtils.parseInventoryFromItems(items);
		List<CharacterSnapshot> snapshots = ItemUtils.getAllSnapshots(inv);
		startProgress(snapshots.size());
		System.out.println(snapshots.size());
		double max = 0;
		CharacterSnapshot bestSnapshot = null;
		int i = 0;
		for (Iterator<CharacterSnapshot> iterator = snapshots.iterator(); iterator.hasNext();) {
			CharacterSnapshot cs = iterator.next();
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
				labelAmuletValue.setText(cs.getAmulet().toString());
				labelBeltValue.setText(cs.getBelt().toString());
				labelCloakValue.setText(cs.getCloak().toString());
				labelRing1Value.setText(cs.getRing1().toString());
				labelRing2Value.setText(cs.getRing2().toString());
				labelAdornmentValue.setText(cs.getCrystal().toString());
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
				labelAttackSpeed.setText(
						String.valueOf(Math.round((0.95 + cs.getCp().getAspeed() / 100.0 * 0.95) * 100.0) / 100.0)
								+ " ps");
				labelTravelSpeed.setText(String.valueOf(Math.round(cs.getCp().getTspeed() * 100.0) / 100.0) + "%");

				progressBar.setVisible(false);
				progressLabel.setText("Calculation completed");

			}

		});

	}

}
