package com.autoassistant.util;

public enum ActionType { 
	CANCEL("Cancel"),
	ADD("Add"),
	EDIT("Edit"),
    REMOVE("Remove");
	
    private final String columnName;
	
	ActionType(String columnName) {
        this.columnName = columnName;
    }
	
    public String columnName() {
        return columnName;
    }	
}