package com.autoassistant.view;

import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JLabel;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

import com.autoassistant.model.Expense;
import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class ExpenseView extends View {

	/**
	 * Creates view to show expense data.
	 * 
	 * @param Expense
	 */
	public ExpenseView(final Expense expense) {

		super(expense);

		setLayout(null);

		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(10, 3, 155, 21);
		add(lblDate);

		JLabel lblRace = new JLabel("Race");
		lblRace.setBounds(169, 3, 103, 21);
		add(lblRace);

		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(276, 3, 94, 21);
		add(lblAmount);

		final JFormattedTextField txtRace = new JFormattedTextField();
		txtRace.setBounds(169, 28, 103, 29);
		txtRace.setText(String.valueOf(expense.getRace()));
		txtRace.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				expense.setRace(Integer.parseInt(txtRace.getText()));
			}
		});
		txtRace.setColumns(10);
		add(txtRace);

		final JFormattedTextField txtAmount = new JFormattedTextField();
		txtAmount.setBounds(276, 28, 94, 29);
		txtAmount.setText(String.valueOf(expense.getAmount()));
		txtAmount.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				expense.setAmount(Float.parseFloat(txtAmount.getText()));
			}
		});
		txtAmount.setColumns(10);
		add(txtAmount);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(10, 61, 360, 21);
		add(lblDescription);

		JTextField txtDescription = new JTextField();
		txtDescription.setBounds(10, 86, 360, 31);
		txtDescription.setText(expense.getText());
		add(txtDescription);
		txtDescription.setColumns(10);

		final JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(10, 28, 155, 29);
		dateChooser.setDate(expense.getExpenseDate());
		dateChooser.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				expense.setExpenseDate(dateChooser.getDate());
			}
		});
		add(dateChooser);

		this.setPreferredSize(new Dimension(380, 140));
	}
}
