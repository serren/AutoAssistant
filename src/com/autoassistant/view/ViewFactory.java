package com.autoassistant.view;

import com.autoassistant.model.Car;
import com.autoassistant.model.Entity;
import com.autoassistant.model.Expense;
import com.autoassistant.model.ExpenseCategory;
import com.autoassistant.util.ActionType;

public class ViewFactory {

	public static View getView(ActionType actionType, Entity object) {

		// for removing object special view is used
		if (actionType == ActionType.REMOVE) {
			return new DeleteDialogView(object);
		}

		if (Car.class.isInstance(object)) {
			return new CarView((Car) object);
		}

		if (ExpenseCategory.class.isInstance(object)) {
			return new ExpenseCategoryView((ExpenseCategory) object);
		}

		if (Expense.class.isInstance(object)) {
			return new ExpenseView((Expense) object);
		}

		return null;
	}
}
