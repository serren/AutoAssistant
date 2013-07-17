package com.autoassistant.view.defaultview;

import java.awt.Dimension;

import com.autoassistant.model.ExpenseCategory;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ExpenseCategoryView extends View {
	private JTextField tfExpenseCategory;

	public ExpenseCategoryView(final Object object) {
		super(object);
	}

	@Override
	protected void init() {
		addLabel("Expense category name:", 0, 0, 380, 32);
		tfExpenseCategory = addTextField("Expense category", ((ExpenseCategory) object).getName(), 0, 32, 380, 32);
		setPreferredSize(new Dimension(380, 70));
	}

	@Override
	public void updateObject() {
		((ExpenseCategory) object).setName(tfExpenseCategory.getText());		
	}

	@Override
	public boolean isValidData() {
		return tfExpenseCategory.getText().length() != 0;
	}
}
