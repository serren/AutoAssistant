package com.autoassistant.view;

import java.awt.Dimension;

@SuppressWarnings("serial")
public class DeleteDialogView extends View {
	public DeleteDialogView(final Object object) {
		super(object);
	}

	@Override
	protected void init() {
		addLabel(object.toString(), 0, 1, 384, 30);
		setPreferredSize(new Dimension(385, 60));
	}

	@Override
	public void updateObject() {
	}

	@Override
	public boolean isValidData() {
		return true;
	}
}
