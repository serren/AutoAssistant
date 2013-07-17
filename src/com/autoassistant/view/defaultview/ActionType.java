package com.autoassistant.view.defaultview;

public enum ActionType {
	ADD("Add"), EDIT("Edit"), REMOVE("Remove"), CANCEL("Cancel");

	private final String name;

	ActionType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}