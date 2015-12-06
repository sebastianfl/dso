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
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import org.seb.dso.CharacterSnapshot;
import org.seb.dso.Inventory;
import org.seb.dso.model.Item;
import org.seb.dso.util.ItemUtils;

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
	private JTextPane textPane;

	/**
	 * the csv file with the item list to be selected by the user
	 */
	private File itemsFile;

	/**
	 * Launch the application.
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		JLabel lblSelectCsvFile = new JLabel("Select CSV File");
		panel.add(lblSelectCsvFile);

		fileChooser = new JFileChooser();
		openButton = new JButton("Select CSV File");
		openButton.addActionListener(this);
		panel.add(openButton);

		JLabel lblSelectClass = new JLabel("Select Class");
		panel.add(lblSelectClass);

		JComboBox comboBox = new JComboBox();
		panel.add(comboBox);
		comboBox.addItem("Mage");
		comboBox.addItem("DK");

		JButton btnCalculate = new JButton("Calculate");
		panel.add(btnCalculate);

		textPane = new JTextPane();
		panel.add(textPane);

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
		System.out.println(snapshots.size());
		double max = 0;
		CharacterSnapshot bestSnapshot = null;
		for (Iterator<CharacterSnapshot> iterator = snapshots.iterator(); iterator.hasNext();) {
			CharacterSnapshot cs = iterator.next();
			double cmd = cs.getCp().calculateEffectiveDamage();
			if (cmd > max) {
				max = cmd;
				bestSnapshot = cs;
			}
		}
		updateGUI(bestSnapshot);
	}

	private void updateGUI(final CharacterSnapshot cs) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				textPane.setText(cs.toString());

			}

		});

	}

}
