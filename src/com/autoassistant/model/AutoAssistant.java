package com.autoassistant.model;

import java.util.Set;


public interface AutoAssistant {
	
	/**
	 * Loads car list from DB
	 * 
	 * @return 
	 */
	public Set<Car> getCars();
	
	/**
	 * Loads expenses categories list for car
	 * 
	 * @param car
	 */
	void loadExpenseCategoriesToCar(Car car);
	
	/**
	 * Loads expenses list for expense category
	 * 
	 * @param expenseCategory
	 */
	public void loadExpensesToCategory(ExpenseCategory expenseCategory);
	
	/**
	 * Adds new object into DB
	 */
	public void add(Entity entity);
	
	/**
	 * Saves edited object into DB
	 */
	public void save(Entity entity);
	
	/**
	 * Removes object from DB
	 */
	public void remove(Entity entity);
	
	/**
	 * Finishes work with autoassistant
	 */
	public void dispose();

}
