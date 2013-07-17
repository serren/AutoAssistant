package com.autoassistant.view.defaultview;

import com.autoassistant.model.Car;
import com.autoassistant.model.Expense;
import com.autoassistant.model.ExpenseCategory;

public class ViewFactory {

	public static View getView(ActionType actionType, Object object) {

		// for removing object special view is used
		if (actionType == ActionType.REMOVE) {
			return new DeleteDialogView(object);
		}

		if (Car.class.isInstance(object)) {
			return new CarView(object);
		}

		if (ExpenseCategory.class.isInstance(object)) {
			return new ExpenseCategoryView(object);
		}

		if (Expense.class.isInstance(object)) {
			return new ExpenseView(object);
		}

		return null;
	}
}
