package com.autoassistant.model;

public abstract class Entity {

	public abstract boolean isDataValid();

	public abstract String getObjectType();
	
	public abstract String toString();
}
