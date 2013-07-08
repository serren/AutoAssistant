package com.autoassistant.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Class implements expense category object
 */
public class ExpenseCategory implements Comparable<ExpenseCategory> {

	private int id;
	private String name;
	private Set<Expense> expenses;
	private int autoId;	

	/**
	 * Creates new expense category for hibernate
	 */
	public ExpenseCategory() {
		id = 0;
		name = "";
		expenses = new HashSet<Expense>();
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
