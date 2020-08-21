package main_package;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main extends JFrame {

	private JPanel mainPanel;
	private JComboBox<Object> comboBox;
	private TheGame gameHelper;
	private JFrame frame;
	private JPanel dynamicPanel;

	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 700, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUp();
		addCBValues();
	}

	private void setUp() {

		mainPanel = new JPanel();
		frame.add(mainPanel, BorderLayout.NORTH);

		JLabel lblPleaseSelect = new JLabel("Please select:");
		mainPanel.add(lblPleaseSelect);

		comboBox = new JComboBox<Object>();
		mainPanel.add(comboBox);

		dynamicPanel = new JPanel();
		dynamicPanel.setBackground(Color.gray);

		frame.add(dynamicPanel, BorderLayout.CENTER);

	}

	private void addCBValues() {

		comboBox.setPreferredSize(new Dimension(200, 90));

		gameHelper = new TheGame();
		Object[] values = gameHelper.getServiceNames();

		comboBox.setModel(new DefaultComboBoxModel<Object>(values));

		comboBox.setSelectedIndex(-1);

		comboBox.addActionListener(new MyListListener());

	}

	class MyListListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			Object selectedGame = comboBox.getSelectedItem();
			int selectedGameIndex = comboBox.getSelectedIndex();
			Object selectedGamesJob = gameHelper.getServiceJob(selectedGame);

			System.out.println("You've selected index: " + selectedGameIndex + "; game: " + selectedGame
					+ "; it's job is: " + selectedGamesJob);

			// showSelectedService(selectedGameIndex); // either like this or more preferred method like below

			showSelectedService(selectedGamesJob);

		}

		private void showSelectedService(Object theRightObject) {

			dynamicPanel.removeAll();

			InterfacePanel getTheObjectFromTheSelectedGameClass = (InterfacePanel) theRightObject;

			// System.out.println(getTheObjectFromTheSelectedGameClass);

			frame.setTitle(getTheObjectFromTheSelectedGameClass.toString());

			dynamicPanel.add(getTheObjectFromTheSelectedGameClass.getGUIPanel());

			frame.validate();
			frame.repaint();

		}

//		private void showSelectedService(int selectedServiceIndex) {			// this is not very OO because should we have 10 games, we would have to add many code lines in this place to check each selected index
//
//			InterfacePanel server;
//			JPanel receivedPanel;
//
//			switch (selectedServiceIndex) {
//			case 0:
//				server = new DayOfTheWeekGame();
//				System.out.println("day");
//				receivedPanel = server.getGUIPanel();
//				dynamicPanel.removeAll();
//				dynamicPanel.add(receivedPanel);
//				frame.setTitle(server.toString());
//				frame.validate();
//				frame.repaint();
//				break;
//			case 1:
//				server = new DiceGame();
//				System.out.println("dice");
//				receivedPanel = server.getGUIPanel();
//				dynamicPanel.removeAll();
//				dynamicPanel.add(receivedPanel);
//				frame.setTitle(server.toString());
//				frame.validate();
//				frame.repaint();
//				break;
//			default:
//				break;
//			}
//
//		}

	}

}
