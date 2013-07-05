package com.autoassistant.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Class implements expense category object
 */
public class ExpenseCategory implements Comparable<ExpenseCategory> {

	private String name;
	private int id;
	private int autoId;
	Set<Expense> expenses;

	/**
	 * Creates new expense category for hibernate
	 */
	public ExpenseCategory() {}
	
	/**
	 * Creates new expense category with empty expenses list
	 * 
	 * @param internal
	 *            category Id
	 * @param name
	 */
	public ExpenseCategory(int id, String name) {
		expenses = new HashSet<Expense>();
		this.id = id;
		this.name = name;		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAutoId() {
		return autoId;
	}

	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}

	public Set<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(Set<Expense> expenses) {
		this.expenses = expenses;
	}

	@Override
	public String toString() {
		return getName();
	}

	/**
	 * Adds new expense to expense list
	 * 
	 * @param expense
	 */
	public void addExpense(Expense expense) {
		expense.setAutoId(autoId);
		expense.setCategoryId(id);
		expenses.add(expense);		
	}

	/**
	 * Removes expense from expense list
	 * 
	 * @param expense
	 */
	public void removeExpense(Expense expense) {
		expenses.remove(expense);
	}

	@Override
	public int compareTo(ExpenseCategory expenseCategory) {		
		String name1 = getName().toUpperCase();
		String name2 = expenseCategory.getName().toUpperCase();
		// ascending order
		return name1.compareTo(name2);
	}
}
