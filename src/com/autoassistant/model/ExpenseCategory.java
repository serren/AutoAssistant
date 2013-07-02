package com.autoassistant.model;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Class implements expense category object
 */
public class ExpenseCategory extends Entity implements Comparable<ExpenseCategory> {

	private String name;
	private int id;
	private int autoId;
	Set<Expense> expenses = new HashSet<Expense>();

	/**
	 * Default constructor
	 */
	public ExpenseCategory() {
		expenses = new HashSet<Expense>();
	}

	/**
	 * Creates new expense category with empty expenses list
	 * 
	 * @param internal
	 *            category Id
	 * @param internal
	 *            auto Id
	 * @param name
	 */
	public ExpenseCategory(int id, int autoId, String name) {

		this();

		setId(id);
		setAutoId(autoId);
		setName(name);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAutoId() {
		return this.autoId;
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
	 * Creates new expense for category
	 */
	public Expense newExpense() {
		return new Expense(0, getId(), getAutoId(), 0, Calendar.getInstance().getTime(), 0d, "");
	}

	/**
	 * Adds new expense to expense list
	 * 
	 * @param expense
	 */
	public void addExpense(Expense expense) {
		this.expenses.add(expense);
	}

	/**
	 * Removes expense from expense list
	 * 
	 * @param expense
	 */
	public void removeExpense(Expense expense) {
		this.expenses.remove(expense);
	}

	/**
	 * Returns object type
	 */
	@Override
	public String getObjectType() {
		return "Expense Category";
	}

	@Override
	public int compareTo(ExpenseCategory expenseCategory) {		
		String name1 = getName().toUpperCase();
		String name2 = expenseCategory.getName().toUpperCase();
		// ascending order
		return name1.compareTo(name2);
	}
}
