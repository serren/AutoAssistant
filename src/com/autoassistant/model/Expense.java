package com.autoassistant.model;

import java.util.Date;

/**
 * Class implements expense object
 */
public class Expense extends Entity {

	private Date expenseDate;
	private int race;
	private double amount;
	private String text;
	private int categoryId;
	private int autoId;
	private int id;

	public Expense() {

	}

	/**
	 * Creates new instance of Expense
	 * 
	 * @param id
	 * @param categoryId
	 * @param autoId
	 * @param race
	 * @param expenseDate
	 * @param amount
	 * @param text
	 */
	public Expense(int id, int categoryId, int autoId, int race, Date expenseDate, double amount, String text) {
		this();
		setId(id);
		setCategoryId(categoryId);
		setAutoId(autoId);
		setRace(race);
		setExpenseDate(expenseDate);
		setAmount(amount);
		setText(text);
	}

	public Date getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}

	public int getRace() {
		return race;
	}

	public void setRace(int race) {
		this.race = race;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int category) {
		this.categoryId = category;
	}

	public int getAutoId() {
		return autoId;
	}

	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Date: " + getExpenseDate().toString() + "\nRace: " + getRace() + "\nAmount: " + getAmount()
				+ "\nComment: " + getText();
	}

	/**
	 * Returns object type
	 */
	@Override
	public String getObjectType() {
		return "Expense";
	}
}
