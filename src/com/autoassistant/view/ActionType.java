package com.autoassistant.view;

public enum ActionType { 
	CANCEL("Cancel"),
	ADD("Add"),
	EDIT("Edit"),
    REMOVE("Remove");
	
    private final String name;
	
	ActionType(String name) {
        this.name = name;
    }
	
    public String getName() {
        return name;
    }	
}