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
