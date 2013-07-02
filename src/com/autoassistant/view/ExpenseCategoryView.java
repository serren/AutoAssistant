package com.autoassistant.view;

import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import com.autoassistant.model.ExpenseCategory;

import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ExpenseCategoryView extends View {

	public ExpenseCategoryView(final ExpenseCategory expenseCategory) {

		super(expenseCategory);

		setLayout(null);

		JLabel lblExpenseCategory = new JLabel("Expense category name:");
		lblExpenseCategory.setBounds(0, 0, 380, 32);
		add(lblExpenseCategory);

		final JTextField txtExpenseCategoryName = new JTextField();
		txtExpenseCategoryName.setBounds(0, 32, 380, 32);
		txtExpenseCategoryName.setText(expenseCategory.getName());
		txtExpenseCategoryName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				expenseCategory.setName(txtExpenseCategoryName.getText());
			}
		});
		add(txtExpenseCategoryName);
		txtExpenseCategoryName.setColumns(10);

		this.setPreferredSize(new Dimension(380, 70));
	}

	/**
	 * Method checks if all mandatory fields are filled
	 * 
	 */
	@Override
	public boolean isDataNotValid() {
		return !entity.isDataValid();
	}
}
