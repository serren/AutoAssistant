package com.autoassistant.view;

import java.awt.Dimension;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

import com.autoassistant.model.Expense;
import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class ExpenseView extends View {

	private JFormattedTextField ftfRace;
	private JFormattedTextField ftfAmount;
	private JTextField tfDescription;
	private JDateChooser dateChooser;
	
	public ExpenseView(final Object object) {
		super(object);
	}

	@Override
	protected void init() {
		final Expense expense = (Expense) object;
		addLabel("Date", 10, 3, 155, 21);
		addLabel("Race", 169, 3, 103, 21);
		addLabel("Amount", 276, 3, 94, 21);
		addLabel("Description", 10, 61, 360, 21);
		ftfRace = addFormattedTextField(String.valueOf(expense.getRace()), 169, 28, 103, 29);
		ftfAmount = addFormattedTextField(String.valueOf(expense.getAmount()), 276, 28, 94, 29);
		tfDescription = addTextField("", expense.getText(), 10, 86, 360, 31);
		dateChooser = addDateChooser(expense.getExpenseDate(), 10, 28, 155, 29);
		setPreferredSize(new Dimension(380, 140));
	}	

	@Override
	public void updateObject() {
		final Expense expense = (Expense) object;
		expense.setRace(Integer.parseInt(ftfRace.getText()));
		expense.setAmount(Float.parseFloat(ftfAmount.getText()));
		expense.setText(tfDescription.getText());
		expense.setExpenseDate(dateChooser.getDate());		
	}

	@Override
	public boolean isValidData() {
		return (Integer.parseInt(ftfRace.getText()) != 0 && Float.parseFloat(ftfAmount.getText()) != 0 && dateChooser.getDate() != null);
	}
}
