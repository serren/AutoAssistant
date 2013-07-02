package com.autoassistant.view;

import java.awt.Dimension;

import com.autoassistant.model.Entity;

@SuppressWarnings("serial")
public class DeleteDialogView extends View {
	public DeleteDialogView(final Entity entity) {
		super(entity);
	}

	@Override
	protected void init() {
		addLabel(entity.toString(), 0, 1, 384, 30);
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
