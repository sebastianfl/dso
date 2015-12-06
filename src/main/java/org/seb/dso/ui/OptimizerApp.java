package org.seb.dso.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == openButton) {
			int ret = fileChooser.showOpenDialog(OptimizerApp.this);
		}
	}

}
