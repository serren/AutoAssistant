package com.autoassistant.view;

import javax.swing.JPanel;

import com.autoassistant.model.Entity;

public abstract class View extends JPanel {
	
	private static final long serialVersionUID = 6887907407216074041L;
	protected final Entity entity;
	
	public View(Entity entity) {
		this.entity = entity;
	}	

	protected abstract boolean isDataNotValid();
}
