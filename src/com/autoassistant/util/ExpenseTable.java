package com.autoassistant.util;

import java.util.Set;

import com.autoassistant.model.Expense;

public class ExpenseTable extends CustomTableModel<Expense> {

	public ExpenseTable(Set<Expense> elements) {
		super(elements, new String[] { "Date", "Race", "Amount", "Comment" }, new Class[] { Object.class, Integer.class, Double.class, String.class });
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Expense expense = elements.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return expense.getExpenseDate();
		case 1:
			return expense.getRace();
		case 2:
			return expense.getAmount();
		case 3:
			return expense.getText();
		}
		return "";
	}
}
