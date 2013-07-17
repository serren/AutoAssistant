package com.autoassistant.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Class implements car object
 */
public class Car implements Comparable<Car> {

	private int id;
	private String name;
	private String comment;
	private final Set<ExpenseCategory> expenseCategories;

	/**
	 * Create new empty car object for hibernate
	 */
	public Car() {
		expenseCategories = new HashSet<ExpenseCategory>();
		id = 0;
		name = "";
		comment = "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<ExpenseCategory> getExpenseCategories() {
		return expenseCategories;
	}

	public void setExpenseCategories(final Set<ExpenseCategory> expenseCategories) {
		this.expenseCategories.clear();
		if (expenseCategories != null)
			this.expenseCategories.addAll(expenseCategories);
	}


	public void addExpenseCategory(ExpenseCategory expenseCategory) {
		expenseCategory.setAutoId(id);
		expenseCategories.add(expenseCategory);
	}

	public void removeExpenseCategory(ExpenseCategory expenseCategory) {
		expenseCategories.remove(expenseCategory);
	}

	@Override
	public String toString() {
		return getName();
	}
	
	@Override
	public int compareTo(Car car) {
		return getId() - car.getId();
	}
}
