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
	 * Adds new object into DB
	 */
	public void add(Object object);
	
	/**
	 * Saves edited object into DB
	 */
	public void save(Object object);
	
	/**
	 * Removes object from DB
	 */
	public void remove(Object object);
	
	/**
	 * Finishes work with autoassistant
	 */
	public void dispose();

}
