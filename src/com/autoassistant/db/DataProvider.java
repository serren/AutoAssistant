/**
 * 
 */
package com.autoassistant.db;

import java.util.Set;

import com.autoassistant.model.Car;
import com.autoassistant.model.Expense;
import com.autoassistant.model.ExpenseCategory;

/**
 * Implements interface for DB
 * 
 */
public interface DataProvider {
	
	/**
	 * Closes DB connection
	 */
	public void close();
	
	/**
	 * Saves edited object into DB
	 */
	public void save(Object object);
	
	/**
	 * Removes object into DB
	 */
	public void remove(Object object);

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
	public Set<ExpenseCategory> getExpenseCategories(Car car);
	
	/**
	 * Loads expenses list for expense category
	 * 
	 * @param expenseCategory
	 */
	public Set<Expense> getExpenses(ExpenseCategory expenseCategory);
}
