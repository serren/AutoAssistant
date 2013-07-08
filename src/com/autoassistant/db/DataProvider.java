/**
 * 
 */
package com.autoassistant.db;

import java.util.Set;

import com.autoassistant.model.Car;

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
}
