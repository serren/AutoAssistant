package com.autoassistant.model;

import java.util.Date;

/**
 * Class implements expense object
 */
public class Expense implements Entity {

	private Date expenseDate;
	private int race;
	private double amount;
	private String text;
	private int categoryId;
	private int autoId;
	private int id;

	public Expense() {}

	/**
	 * Creates new instance of Expense
	 * 
	 * @param id
	 * @param race
	 * @param expenseDate
	 * @param amount
	 * @param text
	 */
	public Expense(int id, int race, Date expenseDate, double amount, String text) {
		this.id = id;
		this.race = race;
		this.expenseDate = expenseDate;
		this.amount = amount;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getCategoryId() {
		return categoryId;
	}

	public int getAutoId() {
		return autoId;
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

	public void setCategoryId(int category) {
		this.categoryId = category;
	}

	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}

	@Override
	public String toString() {
		return "Date: " + getExpenseDate().toString() + "\nRace: " + getRace() + "\nAmount: " + getAmount()
				+ "\nComment: " + getText();
	}
}
