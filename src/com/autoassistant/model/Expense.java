package com.autoassistant.model;

import java.util.Date;

/**
 * Class implements expense object
 */
public class Expense implements Entity {

	private int id;
	private int race;
	private Date expenseDate;	
	private double amount;
	private String text;
	
	private int categoryId;
	private int autoId;

	public Expense() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Date: " + getExpenseDate().toString() + "\nRace: " + getRace() + "\nAmount: " + getAmount()
				+ "\nComment: " + getText();
	}
}
