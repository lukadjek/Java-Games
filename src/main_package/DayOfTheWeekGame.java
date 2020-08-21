package main_package;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.NumberFormatter;

public class DayOfTheWeekGame implements InterfacePanel {

	private JComboBox<String> cbMonth;
	private JTextField txtYear;
	private JSpinner spinner;
	private JLabel lblResult;

	@Override
	public JPanel getGUIPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(new GridLayout(5, 2));
		
		JLabel lblMonth = new JLabel("Month");
		cbMonth = new JComboBox<String>();
		JLabel lblDay = new JLabel("Day (1-31)");

		// set spinner stuff
		int min = 1;
		int max = 31;
		int step = 1;
		int initValue = 1;
		SpinnerModel model = new SpinnerNumberModel(initValue, min, max, step);
		spinner = new JSpinner(model);

		// make spinner allow only allowed values
		JFormattedTextField txt = ((JSpinner.NumberEditor) spinner.getEditor()).getTextField();
		((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);

		JLabel lblYear = new JLabel("Year (only numbers)");
		txtYear = new JTextField();
		txtYear.addKeyListener(new KeyListener() {

			int x;

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				try {
					x = Integer.parseInt(txtYear.getText());
					txtYear.setBackground(Color.white);
				} catch (NumberFormatException nfe) {
					if ( x != 0 && txtYear.getText().length() > 0 ) {
						txtYear.setText(x + "");
						txtYear.setBackground(Color.red);
					}
					else {
						txtYear.setText("");
						txtYear.setBackground(Color.red);
					}
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		JButton button = new JButton("Check");
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.addActionListener(new ButtonActionListener());
		lblResult = new JLabel("day appears here");

		DateFormatSymbols dates = new DateFormatSymbols();
		String all12Months[] = dates.getMonths();
		cbMonth.setModel(new DefaultComboBoxModel<String>(all12Months));

		cbMonth.setSelectedIndex(-1);
		
		JLabel gameInfo = new JLabel();
		gameInfo.setText("*game goal: guess the Friday");
		gameInfo.setForeground(Color.white);

		panel.add(lblMonth);
		panel.add(cbMonth);
		panel.add(lblDay);
		panel.add(spinner);
		panel.add(lblYear);
		panel.add(txtYear);
		panel.add(button);
		panel.add(lblResult);
		panel.add(gameInfo);
		return panel;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName().toUpperCase();
	}

	class ButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			StringBuilder sb = new StringBuilder();

			if ( (cbMonth.getSelectedIndex() == -1 ) || ( cbMonth.getSelectedIndex() == 12 ) ) {
				sb.append("You must choose a month! \n");
			}

			if (txtYear.getText().strip().length() == 0) {
				sb.append("You must choose a year! \n");
			}

			if (sb.toString().length() != 0)
				JOptionPane.showMessageDialog(null, sb.toString(), "Error checking the date",
						JOptionPane.INFORMATION_MESSAGE);
			else
				checkTheDay();

		}

		void checkTheDay() {

			Calendar cal = Calendar.getInstance();

			SimpleDateFormat sdf = new SimpleDateFormat("EEEE"); // this shows the day only!

			int month = cbMonth.getSelectedIndex();
			int day = (int) spinner.getValue();
			int year = Integer.parseInt(txtYear.getText());

			cal.set(year, month, day);

			Date selectedDate = cal.getTime();

			String dayOfWeek = sdf.format(selectedDate);

			lblResult.setText(dayOfWeek);

			if (lblResult.getText().equals("Friday"))
				lblResult.setForeground(Color.GREEN);
			else {
				lblResult.setText(lblResult.getText() + " is not Friday");
				lblResult.setForeground(Color.red);
			}

		}

	}

}
