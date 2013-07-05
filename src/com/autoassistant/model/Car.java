package com.autoassistant.model;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * Class implements car object
 */
public class Car implements Comparable<Car> {

	private int id;
	private String name;
	private String comment;
	private Set<ExpenseCategory> expenseCategories;

	/**
	 * Create new empty car object for hibernate
	 */
	public Car() {
	}

	/**
	 * Create new car object with empty expense categories list
	 * 
	 * @param Id
	 * @param Name
	 * @param Comment
	 */
	public Car(int id, String name, String comment) {
		expenseCategories = new HashSet<ExpenseCategory>();
		this.id = id;
		this.name = name;
		this.comment = comment;
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

	public void setExpenseCategories(Set<ExpenseCategory> expenseCategories) {
		this.expenseCategories = expenseCategories;
	}

	@Override
	public String toString() {
		return getName();
	}

	/**
	 * Adds new expense category
	 * 
	 * @param expenseCategory
	 */
	public void addExpenseCategory(ExpenseCategory expenseCategory) {
		expenseCategory.setAutoId(id);
		expenseCategories.add(expenseCategory);
	}

	/**
	 * Removes expense category from car
	 * 
	 * @param expenseCategory
	 */
	public void removeExpenseCategory(ExpenseCategory expenseCategory) {
		expenseCategories.remove(expenseCategory);
	}

	@Override
	public int compareTo(Car car) {
		return getId() - car.getId();
	}
	
	public static Comparator<Car> nameComparator = new Comparator<Car>() {

		public int compare(Car car1, Car car2) {

			String carName1 = car1.getName().toUpperCase();
			String carName2 = car2.getName().toUpperCase();

			// ascending order
			return carName1.compareTo(carName2);

			// descending order
			// return carName2.compareTo(carName1);
		}
	};
}
