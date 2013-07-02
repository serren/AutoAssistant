package com.autoassistant.view;

import javax.swing.JPanel;

import com.autoassistant.model.Entity;

public abstract class View extends JPanel {
	
	private static final long serialVersionUID = 6887907407216074041L;
	private final Entity entity;
	
	public View(Entity entity) {
		this.entity = entity;
	}	

	/**
	 * Method checks if all mandatory fields are filled
	 * 
	 */
	public boolean isDataNotValid() {
		return !entity.isDataValid();
	}
}
