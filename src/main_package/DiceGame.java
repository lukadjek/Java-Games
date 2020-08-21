package main_package;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DiceGame implements InterfacePanel {

	private JLabel label;
	private JComboBox<String> comboBox;
	private JLabel labelMatcher;
	private JButton button;
	private JPanel panel;
	private int numOfGuesses = 0;

	@Override
	public JPanel getGUIPanel() {
		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		
		button = new JButton("Roll 'em");
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		String[] choices = { "1", "2", "3", "4", "5" };
		comboBox = new JComboBox<String>(choices);
		// comboBox.setModel(new DefaultComboBoxModel<String>(choices)); // like this or like above
		
		comboBox.addItemListener(new CbListener());
		button.addActionListener(new RollEmListener());
		
		label = new JLabel("dice values here");
		labelMatcher = new JLabel("results here");
		
		JLabel gameInfo = new JLabel();
		gameInfo.setText("*game goal: guess same numbers");
		gameInfo.setForeground(Color.white);
		
		panel.add(comboBox);
		panel.add(button);
		panel.add(label);
		panel.add(labelMatcher);
		panel.add(gameInfo);
		return panel;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName().toUpperCase();
	}

	class CbListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {

			if (e.getStateChange() == ItemEvent.SELECTED) {

				numOfGuesses = 0;
				button.setText("Roll 'em again");
				label.setText(label.getText());
				labelMatcher.setText(null);
			}

		}

	}

	class RollEmListener implements ActionListener {

		private int[] arr;
		private StringBuilder sb;

		void updateButtonText() {

			if (button.getText().equals("Reload game")) {
				button.setText("Roll 'em again");
			}

		}

		void playTheGame() {

			numOfGuesses++;

			String newTextDices = "";

			String selectedNumberString = comboBox.getSelectedItem().toString();
			Integer formattedNumber = Integer.parseInt(selectedNumberString);
			arr = new int[formattedNumber];
			sb = new StringBuilder(newTextDices);
			for (int i = 0; i < formattedNumber; i++) {
				int randomNum = (int) ((Math.random() * 6) + 1);
				sb.append(randomNum + " ");
				arr[i] = randomNum;
			}

			System.out.println(Arrays.toString(arr));

		}

		void checkTheResult() {

			boolean match = false;
			int firstNum = arr[0];
			for (int i = 1; i < arr.length; i++) {

				if (firstNum != arr[i]) {
					match = false;
					labelMatcher.setText(match + "");
					break; // if the second or any other next number from the array is not the same as the one (the first)
							// we are using, then we do not have all the same numbers
				} else {
					match = true;
					labelMatcher.setText(match + "");
				}

			}

			if (!match) {
				System.out.println(match);
				label.setText(sb.toString());
			} else {
				label.setText(sb.toString() + " -> you won after " + numOfGuesses + " guesses!");
				button.setText("Reload game");
				labelMatcher.setText(null);
				numOfGuesses = 0;
			}

		}

		@Override
		public void actionPerformed(ActionEvent e) {

			updateButtonText();

			int selectedNumberIndex = comboBox.getSelectedIndex();

			System.out.println("SELECTED NUM INDEX IS " + selectedNumberIndex);

			if (selectedNumberIndex > 0) {

				playTheGame();

				checkTheResult();

			} else {
				label.setText("Game works when you choose 2 dices or more");
				labelMatcher.setText(null);
			}
		}

	}

}
